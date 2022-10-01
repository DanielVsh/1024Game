package sk.tuke.kpi.kp.game.server.service.score;

import com.google.common.collect.Comparators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sk.tuke.kpi.kp.game.entity.score.Score;

import java.util.Comparator;
import java.util.List;

@Component
public class ScoreServiceJPA {

  private final ScoreRepository scoreRepository;

  @Autowired
  public ScoreServiceJPA(ScoreRepository scoreRepository) {
    this.scoreRepository = scoreRepository;
  }


  public void addScore(Score score) {
    scoreRepository.save(score);
  }

  public List<Score> getTopScores(String game) {
    return scoreRepository.findAll().stream()
      .filter(score -> score.getGame().equalsIgnoreCase(game))
      .collect(Comparators.greatest(10, Comparator.comparing(Score::getScore)));
  }

}
