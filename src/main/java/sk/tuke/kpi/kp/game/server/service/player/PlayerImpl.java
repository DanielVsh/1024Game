package sk.tuke.kpi.kp.game.server.service.player;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sk.tuke.kpi.kp.game.entity.player.Player;

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
