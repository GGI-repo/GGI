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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by etudiant on 15/04/17.
 */
@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration(loader = AnnotationConfigWebContextLoader.class, classes = {company.ggi.config.WebAppConfig.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SuppressWarnings("SpringJavaAutowiringInspection")
public class UserGroupTest {

    private UserGroup userGroupTest;
    private Date userGroupCreationDate;
    private String userGroupNameTest;
    private Double userGroupCredit;

    @Before
    public void Up() {
        userGroupCredit = 2254.22;
        userGroupNameTest = "My test group";
        userGroupCreationDate = Calendar.getInstance().getTime();
        userGroupTest = new UserGroup(userGroupNameTest, userGroupCreationDate, userGroupCredit);
    }

    @Test
    public void userGroupConstructorTest() {
        Assert.assertNotNull(userGroupTest);
    }

    @Test
    public void userGroupCreationDateGettersTest() {
        Assert.assertEquals(userGroupTest.getCreationDate(), userGroupCreationDate);
    }

    @Test
    public void userGroupNameGetterTest() {
        Assert.assertEquals(userGroupTest.getName(), userGroupNameTest);
    }

    @Test
    public void userGroupCreditGetterTest() {
        Assert.assertEquals(userGroupTest.getCredit(), userGroupCredit);
    }

}