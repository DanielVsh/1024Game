package sk.tuke.kpi.kp.game;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.client.RestTemplate;
import sk.tuke.kpi.kp.game.UI.ConsoleUI;
import sk.tuke.kpi.kp.game.core.Board;
import sk.tuke.kpi.kp.game.service.comments.CommentService;
import sk.tuke.kpi.kp.game.service.comments.CommentServiceJPA;
import sk.tuke.kpi.kp.game.service.comments.CommentServiceRestClient;
import sk.tuke.kpi.kp.game.service.rating.RatingService;
import sk.tuke.kpi.kp.game.service.rating.RatingServiceJPA;
import sk.tuke.kpi.kp.game.service.rating.RatingServiceRestClient;
import sk.tuke.kpi.kp.game.service.score.ScoreService;
import sk.tuke.kpi.kp.game.service.score.ScoreServiceJPA;
import sk.tuke.kpi.kp.game.service.score.ScoreServiceRestClient;

@SpringBootApplication
@Configuration
@ComponentScan(excludeFilters = @ComponentScan.Filter(type = FilterType.REGEX,
    pattern = "sk.tuke.kpi.kp.game.server.*"))

public class SpringClient {
  public static void main(String[] args) {
    new SpringApplicationBuilder(SpringClient.class)
        .web(WebApplicationType.NONE)
        .run(args);
  }

  @Bean
  CommandLineRunner runner(ConsoleUI ui) {
    return args -> ui.play();
  }

  @Bean
  public ConsoleUI consoleUI(Board board) {
    return new ConsoleUI(board);
  }

  @Bean
  public Board board() {
    return new Board(4);
  }

  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }

  @Bean
  public ScoreService scoreService() {
//    return new ScoreServiceJPA();
    return new ScoreServiceRestClient();
  }

  @Bean
  public CommentService commentService() {
//    return new CommentServiceJPA();
    return new CommentServiceRestClient();
  }

  @Bean
  public RatingService ratingService() {
//    return new RatingServiceJPA();
    return new RatingServiceRestClient();
  }
}
