package company.ggi.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by etudiant on 12/04/17.
 */

@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration(loader = AnnotationConfigWebContextLoader.class, classes = {company.ggi.config.WebAppConfig.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SuppressWarnings("SpringJavaAutowiringInspection")
public class CategoryTest {
    private Category categoryTest;
    private String categoryNameTest;

    @Before
    public void setUp() {
        categoryNameTest = "My category for test";
        categoryTest = new Category(categoryNameTest);
    }

    @Test
    public void categoryConstructorTest() {
        Assert.assertNotNull(categoryTest);
    }

    @Test
    public void categoryNameGetterTest() {
        Assert.assertTrue(categoryTest.getName().equals(categoryNameTest));
    }
}
