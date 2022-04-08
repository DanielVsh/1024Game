package sk.tuke.kpi.kp.game.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table
public class Score implements Serializable {
    @Id
    @GeneratedValue
    private Long ident;

    private String player;

    private String game;

    private int points;

    private Date playedDate;

    public Long getIdent() {
        return ident;
    }

    public void setIdent(Long ident) {
        this.ident = ident;
    }

    public Score(String game, String player, int points, Date playedDate) {
        this.game = game;
        this.player = player;
        this.points = points;
        this.playedDate = playedDate;
    }

    public Score() {
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
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

}
