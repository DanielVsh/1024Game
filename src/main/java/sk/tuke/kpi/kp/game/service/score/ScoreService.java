package sk.tuke.kpi.kp.game.service.score;

import sk.tuke.kpi.kp.game.entity.score.Score;

import java.util.List;

public interface ScoreService {
    void addScore(Score score);

    List<Score> getTopScores(String game);

    void reset();
}
