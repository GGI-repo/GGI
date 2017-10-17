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
 * Created by ismail ELFAQIR on 16/04/2017.
 */
@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration(loader = AnnotationConfigWebContextLoader.class, classes = {company.ggi.config.WebAppConfig.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SuppressWarnings("SpringJavaAutowiringInspection")
public class MessageTest {

    private Message message;
    private String msg;
    DateTime date;
    private DiscussionGroup discussionGroup;
    private User user;
    private Discussion discussion;
    private DiscussionGroup.ROLE role;

    @Before
    public void setUp() {

        user = new User("LastName", "UserName", "FirstName", "test.example@email.com", new DateTime(new Date(20100222)) );
        discussion = new Discussion("Example title");
        role = DiscussionGroup.ROLE.Owner;
        discussionGroup = new DiscussionGroup(user, discussion, role);
        msg = "Example message";
        date = new DateTime();
        message = new Message(discussionGroup, msg);
    }

    @Test
    public void messageConstructorTest() {
        Assert.assertNotNull(message);
    }

    @Test
    public void messageDicussionGroupGetterTest() {
        Assert.assertEquals(discussionGroup, message.getDiscussionGroup());
    }

    @Test
    public void messageMessageGetterTest() {
        Assert.assertTrue(msg.equals(message.getMessage()));
    }

    @Test
    public void messageSentDateGetterTest() {
        Assert.assertEquals(date, message.getSentDate());
    }

}
