package sk.tuke.kpi.kp.game.service.comments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import sk.tuke.kpi.kp.game.entity.coments.Comment;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class CommentServiceRestClient implements CommentService {
  @Value("${remote.server.api}")
  private String url ;

  @Autowired
  private RestTemplate restTemplate;

  @Override
  public void addComment(Comment comment) {
    restTemplate.postForEntity(url + "/comment", comment, Comment.class);
  }

  @Override
  public List<Comment> getComments(String game) {
    return Arrays.asList(Objects.requireNonNull(restTemplate
        .getForObject(url + "/comment/" + game + "/all", Comment[].class)));
  }

  @Override
  public void reset(){
    restTemplate.delete(url + "/comments");
  }
}
