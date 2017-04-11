package company.ggi.service;

import company.ggi.model.Category;
import org.springframework.context.annotation.Bean;

import java.util.List;

/**
 * Created by Ismail ELFAQIR on 10/04/2017.
 */
public interface CategoryService {

    public Category create(Category category) throws Exception;

    public Category delete(int id) throws Exception;

    public List<Category> findAll();

    public Category update(Category category) throws Exception;

    public Category findById(int id) throws Exception;

    public Category findByName(String name) throws Exception;
}
