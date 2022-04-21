package sk.tuke.kpi.kp.game.service.rating;

import org.springframework.transaction.annotation.Transactional;
import sk.tuke.kpi.kp.game.entity.rating.Rating;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Transactional
public class RatingServiceJPA implements RatingService {

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public void setRating(Rating rating) {
    entityManager.persist(rating);
  }

  @Override
  public Double getAverageRating(String game) {
    List<Rating> ratings = (List<Rating>) entityManager
        .createQuery("select r from Rating r")
        .getResultList();
    double sum = 0;
    for (Rating rating : ratings) {
      sum += rating.getRating();
    }
    return sum / ratings.size();
  }

  @Override
  public List<Rating> getRating(String game, String player) {
    return (List<Rating>) entityManager
        .createQuery("select r from Rating r")
        .getResultList();
  }

  @Override
  public void reset() {
    entityManager.createQuery("delete from Rating").executeUpdate();
  }
}