package company.ggi.dao;

import company.ggi.model.User;
import company.ggi.model.UserGroup;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by etudiant on 15/04/17.
 */
public interface UserGroupDao extends JpaRepository<UserGroup, Integer> {
}
