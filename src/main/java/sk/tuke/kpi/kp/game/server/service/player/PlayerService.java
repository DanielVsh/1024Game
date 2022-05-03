package sk.tuke.kpi.kp.game.server.service.player;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.tuke.kpi.kp.game.entity.player.Player;

@Service
public class PlayerService implements IPlayer {

  private final PlayerImpl playerImpl;

  @Autowired
  public PlayerService(PlayerImpl playerImpl) {
    this.playerImpl = playerImpl;
  }

  @Override
  public void addPlayer(Player player) {
    playerImpl.addPlayer(player);
  }
}
