package repository.tweet.impl;

import base.repository.impl.BaseRepositoryImpl;
import entity.tweet.Tweet;
import jakarta.persistence.EntityManager;
import repository.tweet.TweetRepository;

import java.util.List;

public class TweetRepositoryImpl extends BaseRepositoryImpl<Tweet> implements TweetRepository {
    public TweetRepositoryImpl(EntityManager em) {
        super(em);
    }

    @Override
    public Class<Tweet> getEntityClass() {
        return Tweet.class;
    }

    @Override
    public List<Tweet> findUser(Long id) {
        String jpql = """
                select t from Tweet t where t.user.id = :id
                """;
        return em.createQuery(jpql, Tweet.class).setParameter("id", id).getResultList();
    }

    @Override
    public List<Tweet> findOther() {
        String jpql = """
                select t from Tweet t order by t.createDateTime desc
                """;
        return em.createQuery(jpql, Tweet.class).getResultList();
    }
}
