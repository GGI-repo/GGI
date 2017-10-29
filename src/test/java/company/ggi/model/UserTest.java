package company.ggi.model;

import org.joda.time.DateTime;
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
    private String lastName;
    private String userName;
    private String firstName;
    private String email;

    @Before
    public void setUp() {
        birthDay = new Date(20100222);
        lastName = "LastName";
        userName = "UserName";
        firstName = "FirstName";
        email = "test.example@email.com";

        testConstructorUser = new User(lastName, userName, firstName, email, new DateTime(birthDay));
    }

    @Test
    public void testUserConstructor() {
        Assert.assertNotNull(testConstructorUser);
    }

    @Test
    public void testUserLastNameGetter() {
        Assert.assertEquals(testConstructorUser.getLastName(), lastName);
    }

    @Test
    public void testUserUserNameGetter() {
        Assert.assertEquals(testConstructorUser.getUserName(), userName);
    }

    @Test
    public void testUserFirstNameGetter() {
        Assert.assertEquals(testConstructorUser.getFirstName(), firstName);
    }

    @Test
    public void testUserEmailGetter() {
        Assert.assertEquals(testConstructorUser.getEmail(), email);
    }

    @Test
    public void testUserBirthDayGetter() {
        Assert.assertEquals(testConstructorUser.getBirthDay(), new DateTime(birthDay) );
    }
}
