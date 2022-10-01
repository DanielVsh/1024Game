package daniel.vishnievskyi.server.service.player;

import daniel.vishnievskyi.entity.player.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {

  private final PlayerImpl playerImpl;

  @Autowired
  public PlayerService(PlayerImpl playerImpl) {
    this.playerImpl = playerImpl;
  }

  public void addPlayer(Player player) {
    playerImpl.addPlayer(player);
  }
}
