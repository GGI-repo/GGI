package company.ggi.dao;

import company.ggi.model.Discussion;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by etudiant on 10/04/17.
 */
public interface DiscussionDao extends JpaRepository<Discussion, Integer> {

}
