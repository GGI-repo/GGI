package company.ggi.repository;

import company.ggi.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by ielfaqir on 10/04/2017.
 */
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
