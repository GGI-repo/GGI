package service;


import company.ggi.model.Category;
import company.ggi.repository.CategoryRepository;
import company.ggi.service.CategoryService;
import company.ggi.service.impl.CategoryServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Ismail ELFAQIR on 10/04/2017.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@DataJpaTest
@SpringBootConfiguration
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class CategoryServiceTest {

    private CategoryService categoryService;

    @Before
    public void setUp(){
        categoryService = new CategoryServiceImpl();
    }

    @Test
    public void findByUsernameShouldReturnUser() {
        Category test = new Category("test");
        Category result = this.categoryService.create(test);

        Assert.assertTrue(result.getName().equals(test.getName()));
    }
}
