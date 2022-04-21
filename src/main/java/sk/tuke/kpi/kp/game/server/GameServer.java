package sk.tuke.kpi.kp.game.server;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sk.tuke.kpi.kp.game.service.comments.CommentService;
import sk.tuke.kpi.kp.game.service.comments.CommentServiceJPA;
import sk.tuke.kpi.kp.game.service.rating.RatingService;
import sk.tuke.kpi.kp.game.service.rating.RatingServiceJPA;
import sk.tuke.kpi.kp.game.service.score.ScoreService;
import sk.tuke.kpi.kp.game.service.score.ScoreServiceJPA;

@SpringBootApplication
@Configuration
@EntityScan(basePackages = "sk.tuke.kpi.kp.game.entity")
public class GameServer {
    public static void main(String[] args) {
        SpringApplication.run(GameServer.class);
    }

    @Bean
    public ScoreService scoreService() {
        return new ScoreServiceJPA();
    }

    @Bean
    public CommentService commentService() {
        return new CommentServiceJPA();
    }

    @Bean
    public RatingService ratingService() {
        return new RatingServiceJPA();
    }
}

