package repository.tweet;

import base.repository.BaseRepository;
import entity.tweet.Tweet;

import java.util.List;

public interface TweetRepository extends BaseRepository<Tweet> {

    List<Tweet> findUser(Long id);

    List<Tweet> findOther();

}