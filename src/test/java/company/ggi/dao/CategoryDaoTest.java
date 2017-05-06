package company.ggi.dao;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;
import com.github.springtestdbunit.dataset.FlatXmlDataSetLoader;
import company.ggi.model.Category;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by etudiant on 12/04/17.
 */

@RunWith(SpringRunner.class)
@DbUnitConfiguration(dataSetLoader = FlatXmlDataSetLoader.class)
@ContextConfiguration(loader = AnnotationConfigWebContextLoader.class, classes = {company.ggi.config.WebAppConfig.class})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
@WebAppConfiguration
@DatabaseSetup("/META-INF/dataSets.xml")
public class CategoryDaoTest {

    @Autowired
    private CategoryDao categoryDaoTest;

    private Category categoryTest;

    private String categoryNameTest;

    @Before
    public void setUp(){
        categoryDaoTest.deleteAll();
        categoryNameTest = "My test";
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
