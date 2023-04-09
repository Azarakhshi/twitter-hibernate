package service.tweet.impl;

import base.service.impl.BaseServiceImpl;
import entity.tweet.Tweet;
import repository.tweet.TweetRepository;
import service.tweet.TweetService;

import java.util.List;

public class TweetServiceImpl extends BaseServiceImpl<Tweet, TweetRepository> implements TweetService {

    public TweetServiceImpl(TweetRepository repository) {
        super(repository);
    }

    @Override
    public List<Tweet> findUser(Long id) {
        try {
            return repository.findUser(id);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List<Tweet> findOther() {
        try {
            return repository.findOther();
        }catch (Exception e){
            return null;
        }
    }
}
