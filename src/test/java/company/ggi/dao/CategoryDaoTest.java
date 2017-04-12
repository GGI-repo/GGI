package company.ggi.dao;

import company.ggi.model.Category;
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

/**
 * Created by etudiant on 12/04/17.
 */

@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration(loader = AnnotationConfigWebContextLoader.class, classes = {company.ggi.config.WebAppConfig.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SuppressWarnings("SpringJavaAutowiringInspection")
public class CategoryDaoTest {

    @Autowired
    private CategoryDao categoryDaoTest;

    private Category categoryTest;

    private String categoryNameTest;

    @Before
    public void setUp(){
        categoryDaoTest.deleteAll();
        categoryNameTest = "My test ";
        categoryTest = new Category(categoryNameTest);
        categoryTest = categoryDaoTest.save(categoryTest);
    }

    @Test
    public void categoryDaoSaveTest() {
        Assert.assertEquals(categoryDaoTest.findByName(categoryNameTest).getName(), categoryNameTest);
    }

    @Test
    public void categoryDaoFindNameTest() {
        Assert.assertEquals(categoryTest.getName(), categoryDaoTest.findByName(categoryTest.getName()).getName());
    }

    @Test
    public void categoryDaoUpdateTest() {
        categoryTest.setName("another test");
        categoryDaoTest.save(categoryTest);
        Assert.assertEquals(categoryTest.getName(), categoryDaoTest.findByName(categoryTest.getName()).getName());
    }

    @Test
    public void categoryDaoDeleteTest() {
        categoryDaoTest.delete(categoryTest.getId());
        Assert.assertNull(categoryDaoTest.findOne(categoryTest.getId()));
    }
}
