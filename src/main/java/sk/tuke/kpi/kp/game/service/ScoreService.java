package sk.tuke.kpi.kp.game.service;

import sk.tuke.kpi.kp.game.entity.Score;

import java.util.List;

public interface ScoreService {
    void addScore(Score score);

    List<Score> getTopScore();

    void reset();
}
