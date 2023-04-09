package repository.comment;

import base.repository.BaseRepository;
import entity.comment.Comment;
import entity.user.User;

import java.util.List;

public interface CommentRepository extends BaseRepository<Comment> {

    List<Comment> findUser(Long id);

}
