package sk.tuke.kpi.kp.game.service;

import sk.tuke.kpi.kp.game.entity.Score;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ScoreServiceJDBC implements ScoreService {

    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASS = "postgres";

    @Override
    public void addScore(Score score) {
        try (var connection = DriverManager.getConnection(URL, USER, PASS);
             var statement = connection.prepareStatement("INSERT INTO score (player, points, played_date) VALUES (?, ?, ?)")
        ) {
            statement.setString(1, score.getPlayer());
            statement.setInt(2, score.getScore());
            statement.setTimestamp(3, new Timestamp(score.getPlayedDate().getTime()));
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Score> getTopScore() {
        try (var connection = DriverManager.getConnection(URL, USER, PASS);
             var statement = connection.createStatement();
             var rs = statement.executeQuery("SELECT player, points, played_date FROM score ORDER BY points DESC LIMIT 10")
        ) {
            var scores = new ArrayList<Score>();
            while (rs.next()) {
                scores.add(new Score(rs.getString(1), rs.getInt(2), rs.getTimestamp(3)));
            }
            return scores;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void reset() {
        try (var connection = DriverManager.getConnection(URL, USER, PASS);
             var statement = connection.createStatement()
        ) {
            statement.executeUpdate("DELETE FROM score");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
