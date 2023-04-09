package service.follow.impl;

import base.service.impl.BaseServiceImpl;
import entity.follow.Follow;
import entity.user.UserDTO;
import repository.follow.FollowRepository;
import service.follow.FollowService;

import java.util.List;
import java.util.Optional;

public class FollowServiceImpl extends BaseServiceImpl<Follow, FollowRepository> implements FollowService {
    public FollowServiceImpl(FollowRepository repository) {
        super(repository);
    }


    @Override
    public List<UserDTO> showFollowers(Long id) {
        return repository.showFollowers(id);
    }

    @Override
    public List<UserDTO> showFollowing(Long id) {
        return repository.showFollowing(id);
    }

    @Override
    public Optional<Follow> checkFollowing(Long id, Long id2) {
        return repository.checkFollowing(id, id2);
    }
}
