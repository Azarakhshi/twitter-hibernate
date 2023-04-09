package command;

import connection.HibernateUtils;
import entity.follow.Follow;
import entity.user.User;
import entity.user.UserDTO;
import repository.follow.impl.FollowRepositoryImpl;
import repository.user.impl.UserRepositoryImpl;
import service.follow.FollowService;
import service.follow.impl.FollowServiceImpl;
import service.user.UserService;
import service.user.impl.UserServiceImpl;

import java.util.List;
import java.util.Optional;

public class FollowOperations {

    private final FollowService followService = new FollowServiceImpl(new FollowRepositoryImpl(HibernateUtils.getEntityManagerFactory().createEntityManager()));
    private final UserService userService = new UserServiceImpl(new UserRepositoryImpl(HibernateUtils.getEntityManagerFactory().createEntityManager()));

    public void showFollower(User user) {
        List<UserDTO> followers = followService.showFollowers(user.getId());
        System.out.println(followers);
    }

    public void followOrUnfollow(User user, Long id) {
        Optional<User> optionalFollowingUser = userService.findById(id);
        optionalFollowingUser.ifPresentOrElse(followingUser -> {
            Optional<Follow> optionalFollow = followService.checkFollowing(user.getId(), followingUser.getId());
            optionalFollow.ifPresentOrElse(followService::delete,() -> {
                Follow follow = new Follow();
                follow.setFollower(user);
                follow.setFollowing(followingUser);
                followService.save(follow);
            });
        }, () -> {
            System.out.println("User not found");
        });
    }

    public void showFollowing(User user) {
        List<UserDTO> followers = followService.showFollowing(user.getId());
        System.out.println(followers);
    }

    public Boolean searchByUsername(String username) {
        List<UserDTO> userDTOS = userService.searchByUsername(username);
        if(userDTOS.size() == 0)
            return false;
        System.out.println(userDTOS);
        return true;
    }

}
