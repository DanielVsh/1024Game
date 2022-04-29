package sk.tuke.kpi.kp.game.service.score;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;
import sk.tuke.kpi.kp.game.entity.score.Score;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ScoreServiceRestClient implements ScoreService{
    @Value("${remote.server.api}")
    private String url ;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public void addScore(Score score) {
        restTemplate.postForEntity(
                url + "/score", score, Score.class);
    }

    @Override
    public List<Score> getTopScores(String game) {
        return Arrays.asList(
            Objects.requireNonNull(
                restTemplate.getForObject(url + "/score/" + game +"/all", Score[].class)));
    }

    @Override
    public void reset() {
        restTemplate.delete(url + "/score");
    }

}
