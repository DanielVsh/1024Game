package sk.tuke.kpi.kp.game.server.service.rating;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sk.tuke.kpi.kp.game.entity.rating.Rating;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {
  Rating getRatingByGameAndPlayer(String game, String player);
}
