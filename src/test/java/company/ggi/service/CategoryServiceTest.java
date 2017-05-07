package company.ggi.service;


import company.ggi.exception.CategoryException;
import company.ggi.model.Category;
import company.ggi.dao.CategoryDao;
import company.ggi.service.impl.CategoryServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;

/**
 * Created by Ismail ELFAQIR on 10/04/2017.
 */

@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration(loader = AnnotationConfigWebContextLoader.class, classes = {company.ggi.config.WebAppConfig.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SuppressWarnings("SpringJavaAutowiringInspection")
public class CategoryServiceTest {

    @Autowired
    private CategoryDao categoryDao;

    private CategoryService categoryService;

    private Category test;

    private final String name = "test";

    @Before
    public void setUp() {

        categoryDao.deleteAll();
        test = new Category(name);
        test = categoryDao.save(test);
        categoryService = new CategoryServiceImpl();
        ReflectionTestUtils.setField(categoryService, "categoryRepository", categoryDao);
    }

    @Test
    public void testDaoFindOne() {

        Category result = categoryDao.findOne(test.getId());
        Assert.assertTrue(result.getName().equals(test.getName()));
    }

    @Test
    public void testCreateCategory() throws Exception {

        int count = categoryDao.findAll().size();

        categoryService.create(new Category("test2"));
        Assert.assertEquals(count + 1, categoryDao.findAll().size());
    }

    @Test(expected = CategoryException.class)
    public void testCreateCategoryWithException() throws Exception {

        categoryService.create(new Category(name));
    }

    @Test
    public void testDeleteCategory() throws Exception {

        int count = categoryDao.findAll().size();
        int id = categoryDao.findAll().get(count - 1).getId();
        categoryService.delete(id);
        Assert.assertEquals(count - 1, categoryDao.findAll().size());
    }

    @Test(expected = CategoryException.class)
    public void deleteCategoryWithExceptionTest() throws Exception {

        categoryService.delete(-1);
    }

    @Test
    public void findCategoryByIdTest() throws Exception {

        Category found = categoryService.findById(categoryDao.findAll().get(0).getId());
        Assert.assertTrue(found.getName().equals(categoryDao.findAll().get(0).getName()));
        Assert.assertEquals(categoryDao.findAll().get(0).getId(), found.getId());
    }

    @Test(expected = CategoryException.class)
    public void findCategoryByIdWithExceptionTest() throws Exception {

        categoryService.findById(-1);
    }

    @Test
    public void findCategoryByNameTest() throws Exception {

        Category found = categoryService.findByName(name);
        Assert.assertNotNull(found);
        Assert.assertFalse(found.getName().isEmpty());
        Assert.assertTrue(found.getName().equals(name));
    }

    @Test
    public void updateCategoryTest() throws Exception {

        Category test = categoryDao.findAll().get(0);
        test.setName("TestName");

        categoryService.update(test);
        Assert.assertTrue(categoryDao.findOne(test.getId()).getName().equals(test.getName()));
    }

    @Test
    public void findAllTest() {

        Assert.assertEquals(categoryService.findAll().size(), categoryDao.findAll().size());
        Assert.assertFalse(categoryService.findAll().isEmpty());
    }
}
