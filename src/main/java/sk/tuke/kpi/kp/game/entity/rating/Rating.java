package sk.tuke.kpi.kp.game.entity.rating;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Rating {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private int rating;
  private String game;
  private String player;
  private Date ratedOn;

  public Rating() {
  }

  public Rating(int rating, String game, String player, Date ratedOn) {
    this.rating = rating;
    this.game = game;
    this.player = player;
    this.ratedOn = ratedOn;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public int getRating() {
    return rating;
  }

  public void setRating(int rating) {
    this.rating = rating;
  }

  public String getGame() {
    return game;
  }

  public void setGame(String game) {
    this.game = game;
  }


  public String getPlayer() {
    return player;
  }

  public void setPlayer(String player) {
    this.player = player;
  }

  public Date getRatedOn() {
    return ratedOn;
  }

  public void setRatedOn(Date ratedOn) {
    this.ratedOn = ratedOn;
  }

  @Override
  public String toString() {
    return "Rating{" +
        "rating=" + rating +
        ", game='" + game + '\'' +
        ", player='" + player + '\'' +
        ", ratedOn=" + ratedOn +
        '}';
  }
}
