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

import java.util.Date;

/**
 * Created by etudiant on 12/04/17.
 */

@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration(loader = AnnotationConfigWebContextLoader.class, classes = {company.ggi.config.WebAppConfig.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SuppressWarnings("SpringJavaAutowiringInspection")
public class DiscussionTest {

    private Discussion discussionTest;
    private String titleTest;

    @Before
    public void setUp(){
        
        titleTest = "this is Discussion test";
        discussionTest = new Discussion(titleTest);
    }

    @Test
    public void discussionConstructorTest(){
        Assert.assertNotNull(discussionTest);
    }

    @Test
    public void discussionMessageGetterTest(){
        Assert.assertTrue(discussionTest.getTitle().equals(titleTest));
    }

}
