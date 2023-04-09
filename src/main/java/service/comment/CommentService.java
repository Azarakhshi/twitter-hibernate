package service.comment;

import base.service.BaseService;
import entity.comment.Comment;

import java.util.List;

public interface CommentService extends BaseService<Comment> {

    public List<Comment> findUser(Long id);

}
