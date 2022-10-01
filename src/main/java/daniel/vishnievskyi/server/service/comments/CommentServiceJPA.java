package daniel.vishnievskyi.server.service.comments;

import daniel.vishnievskyi.entity.coments.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
