package company.ggi.dao;

import company.ggi.model.Discussion;
import company.ggi.model.DiscussionGroup;
import company.ggi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.jws.soap.SOAPBinding;
import java.util.List;

/**
 * Created by ismail ELFAQIR on 16/04/2017.
 */
public interface DiscussionGroupDao extends JpaRepository<DiscussionGroup, Integer> {
}
