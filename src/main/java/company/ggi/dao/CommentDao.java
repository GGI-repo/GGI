package company.ggi.dao;

import company.ggi.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Adam on 26/04/2017.
 */
public interface CommentDao extends JpaRepository<Comment, Integer> {

}
