package company.ggi.dao;

import company.ggi.model.Discussion;
import company.ggi.model.DiscussionGroup;
import company.ggi.model.User;
import org.joda.time.DateTime;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Date;

/**
 * Created by ismail ELFAQIR on 16/04/2017.
 */

@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration(loader = AnnotationConfigWebContextLoader.class, classes = {company.ggi.config.WebAppConfig.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SuppressWarnings("SpringJavaAutowiringInspection")
public class DiscussionGroupDaoTest {

    @Autowired
    private DiscussionGroupDao discussionGroupDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private DiscussionDao discussionDao;

    private DiscussionGroup discussionGroup;

    private User user;

    private Discussion discussion;

    private DiscussionGroup.ROLE role;

    @Before
    public void setUp() {

        discussionGroupDao.deleteAll();
        userDao.deleteAll();
        discussionDao.deleteAll();

        user = new User("LastName", "test", "FirstName", "test.example@email.fr", new DateTime());
        user = userDao.save(user);
        discussion = new Discussion("test");
        discussion = discussionDao.save(discussion);
        role = DiscussionGroup.ROLE.Owner;
        discussionGroup = new DiscussionGroup(user, discussion, role);

        discussionGroup = discussionGroupDao.save(discussionGroup);
    }

    @After
    public void clean() {

        discussionGroupDao.deleteAll();
        discussionDao.deleteAll();
        userDao.deleteAll();
    }

    @Test
    public void discussionGroupDaoSaveTest() {

        Assert.assertNotNull(discussionGroup);
        Assert.assertEquals(role, discussionGroup.getRole());
        Assert.assertEquals(user.getId(), discussionGroup.getUser().getId());
        Assert.assertEquals(discussion.getId(), discussionGroup.getDiscussion().getId());
    }

    @Test
    public void discussionGroupDaoUpdateTest() {

        Discussion newDiscussion = new Discussion("test");
        User newuser = new User("Test", "Test", "Test", "test.com", new DateTime(20100222));
        DiscussionGroup.ROLE newrole = DiscussionGroup.ROLE.Nothing;

        newuser = userDao.save(newuser);
        newDiscussion = discussionDao.save(newDiscussion);

        discussionGroup.setUser(newuser);
        discussionGroup.setDiscussion(newDiscussion);
        discussionGroup.setRole(newrole);
        discussionGroup = discussionGroupDao.save(discussionGroup);

        Assert.assertEquals(newuser.getId(), discussionGroup.getUser().getId());
        Assert.assertEquals(newDiscussion.getId(), discussionGroup.getDiscussion().getId());
        Assert.assertEquals(newrole, discussionGroup.getRole());
    }

    @Test
    public void discussionGroupDaoDeleteTest() {

        discussionGroupDao.delete(discussionGroup);
        Assert.assertNull(discussionGroupDao.findOne(discussionGroup.getId()));
    }
}
