package service.comment.impl;

import base.service.impl.BaseServiceImpl;
import entity.comment.Comment;
import repository.comment.CommentRepository;
import service.comment.CommentService;

import java.util.List;

public class CommentServiceImpl extends BaseServiceImpl<Comment, CommentRepository> implements CommentService {
    public CommentServiceImpl(CommentRepository repository) {
        super(repository);
    }

    @Override
    public List<Comment> findUser(Long id) {
        try {
            return repository.findUser(id);
        } catch (Exception e) {
            return null;
        }
    }
}
