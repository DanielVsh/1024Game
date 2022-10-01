package sk.tuke.kpi.kp.game.server.service.comments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sk.tuke.kpi.kp.game.entity.coments.Comment;

import java.util.List;

@Component
public class CommentServiceJPA {

  private final CommentRepository commentRepository;

  @Autowired
  public CommentServiceJPA(CommentRepository commentRepository) {
    this.commentRepository = commentRepository;
  }


  public void addComment(Comment comment) {
    commentRepository.save(comment);
  }

  public List<Comment> getComments(String game) {
    return commentRepository.findAll();
  }

}
