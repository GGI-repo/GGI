package company.ggi.dao;

import company.ggi.model.Party;
import company.ggi.model.Play;
import company.ggi.model.UserGroup;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Benmoumen on 01/10/2017.
 */
public interface PlayDao extends JpaRepository<Play, Integer> {
    Play findByUserGroupAndParty(UserGroup userGroup, Party party);
}
