package sk.tuke.kpi.kp.game.server;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
@EntityScan(basePackages = "sk.tuke.kpi.kp.game.entity")
public class GameServer {
  public static void main(String[] args) {
    SpringApplication.run(GameServer.class);
  }
}

