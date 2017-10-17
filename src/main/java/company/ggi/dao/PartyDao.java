package company.ggi.dao;

import company.ggi.model.Game;
import company.ggi.model.Party;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by etudiant on 16/04/17.
 */
public interface PartyDao extends JpaRepository<Party, Integer> {
    List<Party> findPartiesByGame(Game myGame);
    Party findByName(String name);
}
