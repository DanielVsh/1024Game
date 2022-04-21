package sk.tuke.kpi.kp.game.service.comments;

import sk.tuke.kpi.kp.game.entity.coments.Comment;

import java.util.List;

public interface CommentService {
  void addComment(Comment comment) ;

  List<Comment> getComments(String game);

  void reset() throws CommentException;
}
