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
 * Created by Driss BENMOUMEN on 12/04/17.
 */

@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration(loader = AnnotationConfigWebContextLoader.class, classes = {company.ggi.config.WebAppConfig.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SuppressWarnings("SpringJavaAutowiringInspection")
public class UserTest {

    private User testConstructorUser;

    private Date birthDay;

    @Before
    public void setUp() {
        birthDay = new Date(20100222);
        testConstructorUser = new User("LastName", "UserName", "FirstName","test.example@email.com",birthDay);
    }

    @Test
    public void testUserConstructor(){
        Assert.assertNotNull(testConstructorUser.equals(null));
    }

    @Test
    public void testUserLastNameGetter(){
        Assert.assertTrue(testConstructorUser.getLastName().equals("LastName"));
    }

    @Test
    public void testUserUserNameGetter(){
        Assert.assertTrue(testConstructorUser.getUserName().equals("UserName"));
    }

    @Test
    public void testUserFirstNameGetter(){
        Assert.assertTrue(testConstructorUser.getFirstName().equals("FirstName"));
    }

    @Test
    public void testUserEmailGetter(){
        Assert.assertTrue(testConstructorUser.getEmail().equals("test.example@email.com"));
    }

    @Test
    public void testUserBirthDayGetter(){
        Assert.assertTrue(testConstructorUser.getBirthDay().equals(birthDay));
    }
}
