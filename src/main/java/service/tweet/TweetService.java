package service.tweet;

import base.service.BaseService;
import entity.tweet.Tweet;

import java.util.List;

public interface TweetService extends BaseService<Tweet>{

    List<Tweet> findUser(Long id);
    List<Tweet> findOther();

}
