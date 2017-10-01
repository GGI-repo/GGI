package company.ggi.dao;

import company.ggi.model.User;
import company.ggi.model.UserGroup;
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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by etudiant on 16/04/17.
 */

@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration(loader = AnnotationConfigWebContextLoader.class, classes = {company.ggi.config.WebAppConfig.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SuppressWarnings("SpringJavaAutowiringInspection")
public class UserGroupDaoTest {

    @Autowired
    private UserGroupDao userGroupDaoTest;

    private UserGroup userGroupTest1, userGroupTest2;
    private Date userGroupCreationDate1, userGroupCreationDate2;
    private String userGroupNameTest1, userGroupNameTest2;
    private Double userGroupCredit1;
    private List<User> users;

    @Before
    public void Up() {

        userGroupDaoTest.deleteAll();
        userGroupCredit1 = 2254.22;
        userGroupNameTest1 = "My test group 1";
        userGroupCreationDate1 = Calendar.getInstance().getTime();
        users = new ArrayList<>();
        userGroupTest1 = new UserGroup(userGroupNameTest1, userGroupCreationDate1, userGroupCredit1, users);
        userGroupDaoTest.save(userGroupTest1);

        userGroupNameTest2 = "My test group 2";
        userGroupCreationDate2 = Calendar.getInstance().getTime();
        userGroupTest2 = new UserGroup(userGroupNameTest2, userGroupCreationDate2, userGroupCredit1, users);
        userGroupDaoTest.save(userGroupTest2);
    }

    @Test
    public void userGroupDaoSaveTest() {
        Assert.assertNotNull(userGroupDaoTest.findByName(userGroupNameTest1));
    }

    @Test
    public void userGroupDaoFindAllTest() {
        Assert.assertEquals(userGroupDaoTest.findAll().get(0).getName(), userGroupNameTest1);
    }

    /*@Test
    public void userGroupDaoFindByCreationDateOrderByDescTest(){
        Assert.assertEquals(userGroupDaoTest.findByAllOrderByCreationDateDesc().get(0).getName(), userGroupNameTest1);
    }*/

    @Test
    public void userGroupDaoUpdateTest() {

        userGroupNameTest1 = "Another Name";
        userGroupUpdate(userGroupNameTest1);
        Assert.assertEquals(userGroupDaoTest.findByName(userGroupNameTest1).getName(), userGroupNameTest1);
    }

    @Test
    public void userGroupDaoDeleteTest() {
        userGroupDelete(userGroupTest1);
        Assert.assertNull(userGroupDaoTest.findByName(userGroupNameTest1));
    }

    private void userGroupUpdate(String name) {

        userGroupTest1.setName(name);
        userGroupDaoTest.save(userGroupTest1);
    }

    private void userGroupDelete(UserGroup userGroup) {
        userGroupDaoTest.delete(userGroup);
    }

}
