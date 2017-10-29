package company.ggi.dao;

import company.ggi.model.User;
import org.joda.time.DateTime;
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

import java.util.Date;

/**
 * Created by etudiant on 16/04/17.
 */

@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration(loader = AnnotationConfigWebContextLoader.class, classes = {company.ggi.config.WebAppConfig.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SuppressWarnings("SpringJavaAutowiringInspection")
public class UserDaoTest {

    @Autowired
    private UserDao userDaoTest;
    private DateTime birthDay;
    private User userTest;
    private String lastName;
    private String userName;
    private String firstName;
    private String email;

    @Before
    public void Up() {
        userDaoTest.deleteAll();
        birthDay = new DateTime(20100222);
        lastName = "LastName";
        userName = "UserName";
        firstName = "FirstName";
        email = "test.example@email.com";
        userTest = new User(lastName, userName, firstName, email, birthDay);
        userTest = userDaoTest.save(userTest);
    }

    @Test
    public void userDaoSaveTest() {
        Assert.assertNotNull(userDaoTest.findByEmail(email));
    }

    @Test
    public void userDaoFindAllTest() {
        Assert.assertEquals(userDaoTest.findAll().get(0).getEmail(), userTest.getEmail());
    }

    @Test
    public void userDaoUpdateTest() {
        firstName = "newFirstName";
        updateUserFirstNameDao(firstName);
        Assert.assertEquals(userDaoTest.findByUserName(userName).getFirstName(), firstName);
    }

    @Test
    public void userDaoDeleteTest() {
        deleteUser(userTest);
        Assert.assertNull(userDaoTest.findByUserName(userName));
    }

    private void updateUserFirstNameDao(String firstName) {
        userTest.setFirstName(firstName);
        userDaoTest.save(userTest);
    }

    private void deleteUser(User user) {
        userDaoTest.delete(user.getId());
    }
}
