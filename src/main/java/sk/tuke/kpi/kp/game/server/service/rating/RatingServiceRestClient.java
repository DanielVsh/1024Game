package sk.tuke.kpi.kp.game.server.service.rating;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import sk.tuke.kpi.kp.game.entity.rating.Rating;

@Service
public class RatingServiceRestClient {

  private final RatingServiceJPA ratingServiceJPA;

  @Autowired
  public RatingServiceRestClient(RatingServiceJPA ratingServiceJPA) {
    this.ratingServiceJPA = ratingServiceJPA;
  }

  public void setRating(Rating rating) {
    ratingServiceJPA.setRating(rating);
  }

  public Double getAverageRating(String game) {
    return ratingServiceJPA.getAverageRating(game);
  }

  public Rating getRatingByGameAndPlayer(String game, String player) {
    return ratingServiceJPA.getRatingByGameAndPlayer(game, player);
  }
}