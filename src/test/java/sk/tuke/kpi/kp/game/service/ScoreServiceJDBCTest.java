package sk.tuke.kpi.kp.game.service;

import org.junit.jupiter.api.Test;
import sk.tuke.kpi.kp.game.entity.Score;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class ScoreServiceJDBCTest {
    private final ScoreService scoreService = new ScoreServiceJDBC();

    @Test
    void addScore() {
        scoreService.reset();
        var date = new Date();

        scoreService.addScore(new Score("Test1", 100, date));

        var scores = scoreService.getTopScore();
        assertEquals(1, scores.size());
        assertEquals("Test1", scores.get(0).getPlayer());
        assertEquals(100, scores.get(0).getScore());
        assertEquals(date, scores.get(0).getPlayedDate());

    }

    @Test
    void getTopScore() {
        scoreService.reset();
        var date = new Date();
        scoreService.addScore(new Score("Test1", 120, date));
        scoreService.addScore(new Score("Test2", 150, date));
        scoreService.addScore(new Score("Test1", 100, date));

        var scores = scoreService.getTopScore();

        assertEquals(3, scores.size());

        assertEquals("Test2", scores.get(0).getPlayer());
        assertEquals(150, scores.get(0).getScore());
        assertEquals(date, scores.get(0).getPlayedDate());

        assertEquals("Test1", scores.get(1).getPlayer());
        assertEquals(120, scores.get(1).getScore());
        assertEquals(date, scores.get(1).getPlayedDate());

        assertEquals("Test1", scores.get(2).getPlayer());
        assertEquals(100, scores.get(2).getScore());
        assertEquals(date, scores.get(2).getPlayedDate());

    }

    @Test
    void reset() {
        scoreService.reset();

        assertEquals(0, scoreService.getTopScore().size());

    }
}