package daniel.vishnievskyi.entity.coments;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Comment {
  @Id
  @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
  private Long id;
  private String player;
  private String game;
  private String comment;
  private Date commentedOn;

  public Comment() {
  }

  public Comment(String player, String game, String comment, Date commentedOn) {
    this.player = player;
    this.game = game;
    this.comment = comment;
    this.commentedOn = commentedOn;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getPlayer() {
    return player;
  }

  public void setPlayer(String player) {
    this.player = player;
  }

  public String getGame() {
    return game;
  }

  public void setGame(String game) {
    this.game = game;
  }

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  public Date getCommentedOn() {
    return commentedOn;
  }

  public void setCommentedOn(Date commentedOn) {
    this.commentedOn = commentedOn;
  }

  @Override
  public String toString() {
    return "Comment{" +
      "player='" + player + '\'' +
      ", game='" + game + '\'' +
      ", comment='" + comment + '\'' +
      ", commentedOn=" + commentedOn +
      '}';
  }
}
