package repository.user;

import base.repository.BaseRepository;
import entity.user.User;
import entity.user.UserDTO;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends BaseRepository<User> {

    Optional<User> findUser(String username);

    Optional<User> findUser(String username, String password);

    List<UserDTO> searchByUsername(String username);

}
