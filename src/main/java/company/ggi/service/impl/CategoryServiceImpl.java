package company.ggi.service.impl;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

import company.ggi.exception.CategoryException;
import company.ggi.model.Category;
import company.ggi.dao.CategoryDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import company.ggi.service.CategoryService;

/**
 * Created by Ismail ELFAQIR on 10/04/2017.
 * Implementation of CategoryService interface
 * This service provide all methods to manage categories
 */

@Service
public class CategoryServiceImpl implements CategoryService {

    @Resource
    private CategoryDao categoryRepository;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    @Transactional
    public Category create(Category category) throws Exception {

        logger.info("Trying to create a category");
        Category newCategory = category;

        if (newCategory == null || newCategory.getName() == null)
            throw new CategoryException(CategoryException.CATEGORY_WITH_NULL_NAME);

        if (categoryRepository.findByName(newCategory.getName()) != null)
            throw new CategoryException(CategoryException.CATEGORY_EXISTS);

        return categoryRepository.save(newCategory);
    }

    @Override
    @Transactional(rollbackFor = CategoryException.class)
    public Category delete(int id) throws Exception {

        logger.info("Trying to delete a category");
        Category category = categoryRepository.findOne(id);

        if (category == null) {
            logger.error("Category not found with id=" + id);
            throw new CategoryException(CategoryException.CATEGORY_NOT_FOUND);
        }

        categoryRepository.delete(category);
        return category;
    }

    @Override
    @Transactional
    public List<Category> findAll() {

        logger.info("Trying to get all categories ");
        return categoryRepository.findAll();
    }

    @Override
    @Transactional(rollbackFor = CategoryException.class)
    public Category update(Category category) throws Exception {

        logger.info("Trying to update category ");
        Category categoryToUpdate = categoryRepository.findOne(category.getId());

        if (categoryToUpdate == null) {
            logger.error("Category not found " + mapper.writeValueAsString(category));
            throw new CategoryException(CategoryException.CATEGORY_NOT_FOUND);
        }

        if (categoryToUpdate.getName() == null)
            throw new CategoryException(CategoryException.CATEGORY_WITH_NULL_NAME);

        categoryToUpdate.setName(category.getName());
        categoryRepository.save(categoryToUpdate);
        return categoryToUpdate;
    }

    @Override
    @Transactional(rollbackFor = CategoryException.class)
    public Category findById(int id) throws Exception {

        logger.info("Trying to find a category by it's id ");
        Category category = categoryRepository.findOne(id);

        if (category == null) {
            logger.error("Category not found with id =" + id);
            throw new CategoryException(CategoryException.CATEGORY_NOT_FOUND);
        }

        return category;
    }

    @Override
    @Transactional(rollbackFor = CategoryException.class)
    public Category findByName(String name) throws Exception {

        logger.info("Trying to find a category by it's name ");
        Category category = categoryRepository.findByName(name);

        if (category == null) {
            logger.error("Category not found with name =" + name);
            throw new CategoryException(CategoryException.CATEGORY_NOT_FOUND);
        }

        return category;
    }
}
