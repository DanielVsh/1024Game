package daniel.vishnievskyi.server.service.score;

import daniel.vishnievskyi.entity.score.Score;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

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
