package sk.tuke.kpi.kp.game.server.service.rating;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sk.tuke.kpi.kp.game.entity.rating.Rating;

@Component
public class RatingServiceJPA {

  private final RatingRepository ratingRepository;

  @Autowired
  public RatingServiceJPA(RatingRepository ratingRepository) {
    this.ratingRepository = ratingRepository;
  }


  public void setRating(Rating rating) {
    ratingRepository.save(rating);
  }

  public Double getAverageRating(String game) {
    var ratings = ratingRepository.findAll();
    double sum = 0;
    for (Rating rating : ratings) {
      sum += rating.getRating();
    }
    return sum / ratings.size();
  }

  public Rating getRatingByGameAndPlayer(String game, String player) {
    return ratingRepository.getRatingByGameAndPlayer(game, player);
  }

}