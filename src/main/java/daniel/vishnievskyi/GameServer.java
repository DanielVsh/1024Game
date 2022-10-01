package daniel.vishnievskyi;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
public class GameServer {
  public static void main(String[] args) {
    SpringApplication.run(GameServer.class);
  }
}

