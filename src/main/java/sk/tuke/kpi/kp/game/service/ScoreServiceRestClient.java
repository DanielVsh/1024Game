package sk.tuke.kpi.kp.game.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;
import sk.tuke.kpi.kp.game.entity.Score;

import java.util.Arrays;
import java.util.List;

public class ScoreServiceRestClient implements ScoreService{
    //See value of remote.server.api in application.properties
    @Value("${remote.server.api}")
    private String url;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public void addScore(Score score) {
        restTemplate.postForEntity(url + "/score", score, Score.class);
    }

    @Override
    public List<Score> getTopScores(String game) {
        return Arrays.asList(restTemplate.getForEntity(url + "/score/" + game, Score[].class).getBody());
    }

    @Override
    public void reset() {
        throw new UnsupportedOperationException("Not supported via web service");
    }

}
