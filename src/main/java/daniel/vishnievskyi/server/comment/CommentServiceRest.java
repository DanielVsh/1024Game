package daniel.vishnievskyi.server.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import daniel.vishnievskyi.entity.coments.Comment;
import daniel.vishnievskyi.server.service.comments.CommentServiceRestClient;

@RestController
@RequestMapping("/comment")
public class CommentServiceRest {

  private final CommentServiceRestClient commentServiceRestClient;

  @Autowired
  public CommentServiceRest(CommentServiceRestClient commentServiceRestClient) {
    this.commentServiceRestClient = commentServiceRestClient;
  }


  @PostMapping
  public void addComment(@RequestBody Comment comment) {
    commentServiceRestClient.addComment(comment);
  }

  @GetMapping("/{game}/all")
  public Iterable<Comment> getAllComments(@PathVariable("game") String game) {
    return commentServiceRestClient.getComments(game);
  }
}
