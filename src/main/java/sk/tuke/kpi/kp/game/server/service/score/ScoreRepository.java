package sk.tuke.kpi.kp.game.server.service.score;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sk.tuke.kpi.kp.game.entity.score.Score;

@Repository
public interface ScoreRepository extends JpaRepository<Score, Long> {
}
