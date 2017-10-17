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
public class DiscussionGroupTest {

    private DiscussionGroup discussionGroup;
    private User user;
    private Discussion discussion;
    private DiscussionGroup.ROLE role;

    @Before
    public void setUp() {

        user = new User("LastName", "UserName", "FirstName", "test.example@email.com", new DateTime(new Date(20100222)));
        discussion = new Discussion("Example title");
        role = DiscussionGroup.ROLE.Owner;
        discussionGroup = new DiscussionGroup(user, discussion, role);
    }

    @Test
    public void discussionGroupConstructorTest() {
        Assert.assertNotNull(discussionGroup);
    }

    @Test
    public void discussionGroupUserGetterTest() {
        Assert.assertEquals(user, discussionGroup.getUser());
    }

    @Test
    public void discussionGroupDiscussionGetterTest() {
        Assert.assertEquals(discussion, discussionGroup.getDiscussion());
    }

    @Test
    public void discussionGroupRoleGetterTest() {
        Assert.assertEquals(role, discussionGroup.getRole());
    }
}
