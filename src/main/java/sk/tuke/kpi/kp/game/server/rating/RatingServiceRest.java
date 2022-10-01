package sk.tuke.kpi.kp.game.server.rating;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sk.tuke.kpi.kp.game.entity.rating.Rating;
import sk.tuke.kpi.kp.game.server.service.rating.RatingServiceRestClient;

@RestController
@RequestMapping("/rating")
public class RatingServiceRest {

  private final RatingServiceRestClient ratingService;

  @Autowired
  public RatingServiceRest(RatingServiceRestClient ratingService) {
    this.ratingService = ratingService;
  }

  @PostMapping("/add")
  public void getRating(@RequestBody Rating rating) {
    ratingService.setRating(rating);
  }

  @GetMapping("/game/{game}/player/{player}")
  public Rating getRatingByPlayer(@PathVariable("game") String game,
                                  @PathVariable("player") String player) {
    return ratingService.getRatingByGameAndPlayer(game, player);
  }

  @GetMapping("/game/{game}/average")
  public Double getAverageRating(@PathVariable("game") String game) {
    return ratingService.getAverageRating(game);
  }
}
