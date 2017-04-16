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
 * Created by etudiant on 16/04/17.
 */
@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration(loader = AnnotationConfigWebContextLoader.class, classes = {company.ggi.config.WebAppConfig.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SuppressWarnings("SpringJavaAutowiringInspection")
public class GameTest {

    private Game gameTest;
    private String gameNameTest;
    private String gameDescriptionTest;
    private Category gameCategoryTest;

    @Before
    public void Up(){

        gameNameTest="My game Test";
        gameDescriptionTest="My description for test";
        gameCategoryTest = new Category();
        gameTest = new Game(gameNameTest,gameDescriptionTest,gameCategoryTest);
    }

    @Test
    public void gameConstructorTest(){
        Assert.assertNotNull(gameTest);
    }

    @Test
    public void gameNameGetterTest(){
        Assert.assertEquals(gameNameTest, gameTest.getName());
    }

    @Test
    public void gameDescriptionGetterTest(){
        Assert.assertEquals(gameDescriptionTest, gameTest.getDiscription());
    }

    @Test
    public void gameCategoryGetterTest(){
        Assert.assertEquals(gameCategoryTest, gameTest.getCategory());
    }
}
