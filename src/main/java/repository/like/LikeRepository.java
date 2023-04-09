package repository.like;

import base.repository.BaseRepository;
import entity.comment.Comment;
import entity.like.Like;
import entity.tweet.Tweet;
import entity.user.User;

import java.util.List;
import java.util.Optional;

public interface LikeRepository extends BaseRepository<Like> {
    List<String> findLikedAccounts(Tweet tweet);

    Optional<Like> existLike(User user, Comment comment);
    Optional<Like> existLike(User user, Tweet tweet);
}
