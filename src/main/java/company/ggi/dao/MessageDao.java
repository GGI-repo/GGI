package company.ggi.dao;

import company.ggi.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by ismail ELFAQIR on 16/04/2017.
 */
public interface MessageDao extends JpaRepository<Message, Integer> {
}
