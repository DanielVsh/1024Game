package daniel.vishnievskyi.server.service.score;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import daniel.vishnievskyi.entity.score.Score;

@Repository
public interface ScoreRepository extends JpaRepository<Score, Long> {
}
