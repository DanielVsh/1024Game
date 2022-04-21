package sk.tuke.kpi.kp.game.service.comments;

import org.springframework.transaction.annotation.Transactional;
import sk.tuke.kpi.kp.game.entity.coments.Comment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Transactional
public class CommentServiceJPA implements CommentService {

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public void addComment(Comment comment) {
    entityManager.persist(comment);
  }

  @Override
  public List<Comment> getComments(String game){
    return entityManager
        .createQuery("select s from Comment s where s.game = :game order by s.commentedOn desc")
        .setParameter("game", game)
        .getResultList();
  }

  @Override
  public void reset() {
    entityManager.createQuery("delete from Comment").executeUpdate();
  }
}
