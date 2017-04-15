package company.ggi.dao;

import company.ggi.model.Party;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by etudiant on 16/04/17.
 */
public interface PartyDao extends JpaRepository<Party, Integer> {

}
