package company.ggi.dao;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by etudiant on 15/04/17.
 */
public interface UserGroupDao extends JpaRepository<UserDao, Integer> {
}
