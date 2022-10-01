package daniel.vishnievskyi.server.service.comments;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import daniel.vishnievskyi.entity.coments.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

}
