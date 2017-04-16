package company.ggi.dao;


import company.ggi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by etudiant on 10/04/17.
 */

public interface UserDao extends JpaRepository<User, Integer> {

}
