package sk.tuke.kpi.kp.game.server.service.score;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import sk.tuke.kpi.kp.game.entity.score.Score;

import java.util.List;

@Service
public class ScoreServiceRestClient {
  @Value("${remote.server.api}")
  private String url;

  private final ScoreServiceJPA scoreServiceJPA;


  @Autowired
  public ScoreServiceRestClient(ScoreServiceJPA scoreServiceJPA) {
    this.scoreServiceJPA = scoreServiceJPA;
  }

  public void addScore(Score score) {
    scoreServiceJPA.addScore(score);
  }

  public List<Score> getTopScores(String game) {
    return scoreServiceJPA.getTopScores(game);
  }

}
