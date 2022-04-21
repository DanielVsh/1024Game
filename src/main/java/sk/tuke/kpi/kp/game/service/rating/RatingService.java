package sk.tuke.kpi.kp.game.service.rating;

import sk.tuke.kpi.kp.game.entity.rating.Rating;

import java.util.List;

public interface RatingService {
  void setRating(Rating rating) ;

  Double getAverageRating(String game);

  List<Rating> getRating(String game, String player) ;

  void reset() ;
}
