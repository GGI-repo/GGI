package company.ggi.service;

import company.ggi.exception.CategoryException;
import company.ggi.model.Category;
import org.springframework.context.annotation.Bean;

import java.util.List;

/**
 * Created by Ismail ELFAQIR on 10/04/2017.
 */
public interface CategoryService {

    public Category create(Category category);

    public Category delete(int id) throws CategoryException;

    public List<Category> findAll();

    public Category update(Category category) throws CategoryException;

    public Category findById(int id) throws CategoryException;
}
