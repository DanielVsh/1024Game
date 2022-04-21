package sk.tuke.kpi.kp.game.server.rating;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sk.tuke.kpi.kp.game.entity.rating.Rating;
import sk.tuke.kpi.kp.game.service.rating.RatingService;

import java.util.List;

@RestController
@RequestMapping("/rating")
public class RatingServiceRest {

  @Autowired
  private RatingService ratingService;

  @PostMapping("/add")
  public void getRating(@RequestBody Rating rating) {
    ratingService.setRating(rating);
  }

  @GetMapping("/game/{game}/player/{player}")
  public List<Rating> getRatingByPlayer(@PathVariable("game") String game,
                                        @PathVariable("player") String player) {
    return ratingService.getRating(game, player);
  }

  @GetMapping("/game/{game}/average")
  public Double getAverageRating(@PathVariable("game") String game) {
    return ratingService.getAverageRating(game);
  }
}
