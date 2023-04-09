package repository.follow;

import base.repository.BaseRepository;
import entity.follow.Follow;
import entity.user.UserDTO;

import java.util.List;
import java.util.Optional;

public interface FollowRepository extends BaseRepository<Follow> {
    List<UserDTO> showFollowers(Long id);
    List<UserDTO> showFollowing(Long id);
    Optional<Follow> checkFollowing(Long id, Long id2);

}
