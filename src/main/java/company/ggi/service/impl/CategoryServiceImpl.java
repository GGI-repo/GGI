package company.ggi.service.impl;

import java.util.List;

import javax.annotation.Resource;

import company.ggi.exception.CategoryException;
import company.ggi.model.Category;
import company.ggi.dao.CategoryDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import company.ggi.service.CategoryService;

/**
 * Created by ielfaqir on 10/04/2017.
 */

@Service
public class CategoryServiceImpl implements CategoryService {

    @Resource
    private CategoryDao categoryRepository;

    @Override
    @Transactional
    public Category create(Category category) {
        Category newCategory = category;
        return categoryRepository.save(newCategory);
    }

    @Override
    @Transactional(rollbackFor=CategoryException.class)
    public Category delete(int id) throws CategoryException {
        Category category = categoryRepository.findOne(id);

        if(category == null)
            throw new CategoryException(CategoryException.CATEGORY_NOT_FOUND);

        categoryRepository.delete(category);
        return category;
    }

    @Override
    @Transactional
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    @Transactional(rollbackFor=CategoryException.class)
    public Category update(Category category) throws CategoryException {
        Category categoryToUpdate = categoryRepository.findOne(category.getId());

        if(categoryToUpdate == null)
            throw new CategoryException(CategoryException.CATEGORY_NOT_FOUND);

        categoryToUpdate.setName(category.getName());
        return categoryToUpdate;
    }

    @Override
    @Transactional(rollbackFor=CategoryException.class)
    public Category findById(int id) throws CategoryException {
        Category category = categoryRepository.findOne(id);

        if(category == null)
            throw new CategoryException(CategoryException.CATEGORY_NOT_FOUND);

        return category;
    }
}
