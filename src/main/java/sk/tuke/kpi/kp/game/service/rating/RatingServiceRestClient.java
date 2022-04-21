package sk.tuke.kpi.kp.game.service.rating;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;
import sk.tuke.kpi.kp.game.entity.rating.Rating;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class RatingServiceRestClient implements RatingService {
  @Value("${remote.server.api}")
  private String url;

  @Autowired
  private RestTemplate restTemplate;

  @Override
  public void setRating(Rating rating) {
    restTemplate.postForEntity(url + "/rating/add/", rating, Rating.class);
  }

  @Override
  public Double getAverageRating(String game) {
    return restTemplate.getForObject(url + "/rating/game/" + game + "/average", Double.class);
  }

  @Override
  public List<Rating> getRating(String game, String player) {
    return Arrays.asList(
        Objects.requireNonNull(restTemplate.getForEntity(
            url + "/rating/game/" + game + "/player/" + player, Rating[].class).getBody()));
  }

  @Override
  public void reset() {
    restTemplate.delete(url + "/rating/");
  }
}