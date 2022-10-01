package sk.tuke.kpi.kp.game.server.service.comments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.tuke.kpi.kp.game.entity.coments.Comment;

import java.util.List;

@Service
public class CommentServiceRestClient {

  private final CommentServiceJPA commentServiceJPA;

  @Autowired
  public CommentServiceRestClient(CommentServiceJPA commentServiceJPA) {
    this.commentServiceJPA = commentServiceJPA;
  }


  public void addComment(Comment comment) {
    commentServiceJPA.addComment(comment);
  }

  public List<Comment> getComments(String game) {
    return commentServiceJPA.getComments(game);
  }

}
