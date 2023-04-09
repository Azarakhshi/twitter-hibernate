package repository.user.impl;

import base.repository.impl.BaseRepositoryImpl;
import entity.user.User;
import entity.user.UserDTO;
import jakarta.persistence.EntityManager;
import repository.user.UserRepository;
import connection.HibernateUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepositoryImpl extends BaseRepositoryImpl<User> implements UserRepository {

    EntityManager em;

    public UserRepositoryImpl(EntityManager em) {
        super(em);
    }

    @Override
    public Class<User> getEntityClass() {
        return User.class;
    }

    @Override
    public Optional<User> findUser(String username) {
        String jpql = """
                select u from User u where u.username = :username
                """;
        return Optional.ofNullable(HibernateUtils.getEntityManagerFactory().createEntityManager().createQuery(jpql, User.class)
                .setParameter("username", username).getSingleResult());
    }

    @Override
    public Optional<User> findUser(String username, String password) {
        String jpql = """
                select u from User u where u.username = :username and u.password = :password
                """;
        return Optional.ofNullable(HibernateUtils.getEntityManagerFactory().createEntityManager().createQuery(jpql, User.class)
                .setParameter("username", username).setParameter("password", password).getSingleResult());
    }

    @Override
    public List<UserDTO> searchByUsername(String username) {
        List<UserDTO> userDTOS = new ArrayList<>();
        String jpql = """
                select u from User u where u.username like :username
                """;
        List<User> users = HibernateUtils.getEntityManagerFactory().createEntityManager().createQuery(jpql, User.class)
                .setParameter("username", "%" + username + "%").getResultList();
        users.forEach(user -> {
            UserDTO userDTO = new UserDTO(user.getId(), user.getFirstname(), user.getLastname(), user.getUsername());
            userDTOS.add(userDTO);
        });
        return userDTOS;
    }
}
