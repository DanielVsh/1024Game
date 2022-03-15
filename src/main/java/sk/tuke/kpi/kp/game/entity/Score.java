package sk.tuke.kpi.kp.game.entity;

import java.util.Date;

public class Score {
    private String player;

    private int points;

    private Date playedDate;

    public Score(String player, int points, Date playedDate) {
        this.player = player;
        this.points = points;
        this.playedDate = playedDate;
    }

    public int getScore() {
        return points;
    }

    public void setScore(int score) {
        this.points = score;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public Date getPlayedDate() {
        return playedDate;
    }

    public void setPlayedDate(Date playedDate) {
        this.playedDate = playedDate;
    }

    @Override
    public String toString() {
        return "Score{" +
                "score=" + points +
                ", player='" + player + '\'' +
                ", playedDate=" + playedDate +
                '}';
    }
}
