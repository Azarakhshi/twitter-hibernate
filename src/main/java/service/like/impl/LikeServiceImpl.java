package service.like.impl;

import base.service.impl.BaseServiceImpl;
import entity.comment.Comment;
import entity.like.Like;
import entity.tweet.Tweet;
import entity.user.User;
import repository.like.LikeRepository;
import service.like.LikeService;

import java.util.List;
import java.util.Optional;

public class LikeServiceImpl extends BaseServiceImpl<Like, LikeRepository> implements LikeService {
    public LikeServiceImpl(LikeRepository repository) {
        super(repository);
    }

    @Override
    public List<String> findLikedAccounts(Tweet tweet) {
        return repository.findLikedAccounts(tweet);
    }

    @Override
    public Optional<Like> existLike(User user, Comment comment) {
        return repository.existLike(user, comment);
    }

    @Override
    public Optional<Like> existLike(User user, Tweet tweet) {
        try {
            return repository.existLike(user, tweet);
        } catch (Exception e){
            return Optional.empty();
        }
    }
}
