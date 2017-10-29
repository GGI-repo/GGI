package company.ggi.dao;

import company.ggi.model.Discussion;
import company.ggi.model.DiscussionGroup;
import company.ggi.model.Message;
import company.ggi.model.User;
import org.joda.time.DateTime;
import org.junit.After;
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
 * Created by ismail ELFAQIR on 16/04/2017.
 */

@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration(loader = AnnotationConfigWebContextLoader.class, classes = {company.ggi.config.WebAppConfig.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SuppressWarnings("SpringJavaAutowiringInspection")
public class MessageDaoTest {

    @Autowired
    private MessageDao messageDao;

    @Autowired
    private DiscussionGroupDao discussionGroupDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private DiscussionDao discussionDao;

    private Message message;

    private String msg;


    private DiscussionGroup discussionGroup;

    private User user;

    private Discussion discussion;


    @Before
    public void setUp() {

        messageDao.deleteAll();
        discussionGroupDao.deleteAll();
        discussionDao.deleteAll();
        userDao.deleteAll();

        user = new User("LastName", "test", "FirstName", "test.example@email.fr", new DateTime());
        discussion = new Discussion("test");
        discussionGroup = new DiscussionGroup(user, discussion, DiscussionGroup.ROLE.Owner);

        user = userDao.save(user);
        discussion = discussionDao.save(discussion);
        discussionGroup = discussionGroupDao.save(discussionGroup);

        msg = "test";
        message = new Message(discussionGroup, msg);

        message = messageDao.save(message);
    }

    @After
    public void clean() {

        messageDao.deleteAll();
        discussionGroupDao.deleteAll();
        discussionDao.deleteAll();
        userDao.deleteAll();
    }

    @Test
    public void messageDaoSaveTest() {

        Assert.assertNotNull(message);
        Assert.assertEquals(messageDao.findOne(message.getId()).getId(), message.getId());
    }

    @Test
    public void messageDaoUpdateTest() {

        final String test = "test";
        DateTime newDate = new DateTime();

        message.setMessage(test);
        message.setSentDate(newDate);
        message = messageDao.save(message);

        Assert.assertTrue(message.getMessage().equals(test));
        Assert.assertEquals(newDate, message.getSentDate());
    }

    @Test
    public void messageDaoDeleteTest() {

        messageDao.delete(message.getId());
        Assert.assertNull(messageDao.findOne(message.getId()));
    }

}
