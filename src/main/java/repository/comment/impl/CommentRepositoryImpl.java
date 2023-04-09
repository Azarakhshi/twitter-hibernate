package repository.comment.impl;

import base.repository.impl.BaseRepositoryImpl;
import entity.comment.Comment;
import jakarta.persistence.EntityManager;
import repository.comment.CommentRepository;

import java.util.List;

public class CommentRepositoryImpl extends BaseRepositoryImpl<Comment> implements CommentRepository {

    public CommentRepositoryImpl(EntityManager em) {
        super(em);
    }

    @Override
    public Class<Comment> getEntityClass() {
        return Comment.class;
    }

    @Override
    public List<Comment> findUser(Long id) {
        String jpql = """
                select c from Comment c where c.user.id = :id
                """;
        return em.createQuery(jpql, Comment.class).setParameter("id", id).getResultList();
    }
}
