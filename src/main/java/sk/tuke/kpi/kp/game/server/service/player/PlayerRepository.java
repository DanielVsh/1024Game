package sk.tuke.kpi.kp.game.server.service.player;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sk.tuke.kpi.kp.game.entity.player.Player;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
}
