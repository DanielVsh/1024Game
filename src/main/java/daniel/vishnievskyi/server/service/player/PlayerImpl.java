package daniel.vishnievskyi.server.service.player;

import daniel.vishnievskyi.entity.player.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PlayerImpl {

  private final PlayerRepository playerRepository;

  @Autowired
  public PlayerImpl(PlayerRepository playerRepository) {
    this.playerRepository = playerRepository;
  }

  public void addPlayer(Player player) {
    playerRepository.save(player);
  }
}
