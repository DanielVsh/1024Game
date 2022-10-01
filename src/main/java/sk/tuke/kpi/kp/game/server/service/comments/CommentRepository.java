package sk.tuke.kpi.kp.game.server.service.comments;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sk.tuke.kpi.kp.game.entity.coments.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

}
