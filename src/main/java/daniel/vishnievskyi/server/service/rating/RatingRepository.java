package daniel.vishnievskyi.server.service.rating;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import daniel.vishnievskyi.entity.rating.Rating;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {
  Rating getRatingByGameAndPlayer(String game, String player);
}
