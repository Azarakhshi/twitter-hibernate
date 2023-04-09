package service.user;

import base.service.BaseService;
import entity.user.User;
import entity.user.UserDTO;

import java.util.List;
import java.util.Optional;

public interface UserService extends BaseService<User> {

    Optional<User> findUser(String username);
    Optional<User> findUser(String username, String password);
    List<UserDTO> searchByUsername(String username);

}
