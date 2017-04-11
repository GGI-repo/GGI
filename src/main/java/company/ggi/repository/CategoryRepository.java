package company.ggi.repository;

import company.ggi.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by ielfaqir on 10/04/2017.
 */
@org.springframework.stereotype.Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Category findByName(String name);
}
