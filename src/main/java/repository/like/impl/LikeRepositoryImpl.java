package repository.like.impl;

import base.repository.impl.BaseRepositoryImpl;
import entity.comment.Comment;
import entity.like.Like;
import entity.tweet.Tweet;
import entity.user.User;
import jakarta.persistence.EntityManager;
import repository.like.LikeRepository;

import java.util.List;
import java.util.Optional;

public class LikeRepositoryImpl extends BaseRepositoryImpl<Like> implements LikeRepository {
    public LikeRepositoryImpl(EntityManager em) {
        super(em);
    }

    @Override
    public Class<Like> getEntityClass() {
        return Like.class;
    }

    @Override
    public List<String> findLikedAccounts(Tweet tweet) {
        return em.createQuery("select l.user.username from Like l where l.tweet.id = :input", String.class)
                .setParameter("input", tweet.getId())
                .getResultList();
    }

    @Override
    public Optional<Like> existLike(User user, Comment comment) {
        String jpql = """
                select l from Like l where l.user.id = :userId and l.comment.id = :commentId
                """;
        return Optional.ofNullable(em.createQuery(jpql, Like.class).setParameter("userId", user.getId())
                .setParameter("commentId", comment.getId()).getSingleResult());
    }

    @Override
    public Optional<Like> existLike(User user, Tweet tweet) {
        String jpql = """
                select l from Like l where l.user.id = :userId and l.tweet.id = :tweetId
                """;
        return Optional.ofNullable(em.createQuery(jpql, Like.class).setParameter("userId", user.getId())
                .setParameter("tweetId", tweet.getId()).getSingleResult());
    }
}
