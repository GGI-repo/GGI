package company.ggi.dao;

import company.ggi.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by etudiant on 16/04/17.
 */
public interface GameDao extends JpaRepository<Game, Integer> {

}
