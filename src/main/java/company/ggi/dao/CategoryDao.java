package company.ggi.dao;

import company.ggi.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by ielfaqir on 10/04/2017.
 */
public interface CategoryDao extends JpaRepository<Category, Integer> {
	Category findByName(String name);
}
