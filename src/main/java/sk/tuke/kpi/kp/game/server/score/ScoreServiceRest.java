package sk.tuke.kpi.kp.game.server.score;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sk.tuke.kpi.kp.game.entity.score.Score;
import sk.tuke.kpi.kp.game.server.service.score.ScoreServiceRestClient;

@RestController
@RequestMapping("/score")
public class ScoreServiceRest {

  private final ScoreServiceRestClient scoreService;

  @Autowired
  public ScoreServiceRest(ScoreServiceRestClient scoreService) {
    this.scoreService = scoreService;
  }

  @PostMapping
  public void addScore(@RequestBody Score score) {
    scoreService.addScore(score);
  }

  @GetMapping("/{game}/all")
  public Iterable<Score> getTopScores(@PathVariable("game") String game) {
    return scoreService.getTopScores(game);
  }
}
