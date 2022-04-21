package sk.tuke.kpi.kp.game.server.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sk.tuke.kpi.kp.game.entity.coments.Comment;
import sk.tuke.kpi.kp.game.service.comments.CommentService;

@RestController
@RequestMapping("/comment")
public class CommentServiceRest {
  @Autowired
  private CommentService commentService;

  @PostMapping
  public void addComment(@RequestBody Comment comment) {
    commentService.addComment(comment);
  }

  @GetMapping("/{game}/all")
  public Iterable<Comment> getAllComments(@PathVariable("game") String game) {
    return commentService.getComments(game);
  }
}
