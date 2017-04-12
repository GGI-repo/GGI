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
 * Created by etudiant on 12/04/17.
 */

@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration(loader = AnnotationConfigWebContextLoader.class, classes = {company.ggi.config.WebAppConfig.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SuppressWarnings("SpringJavaAutowiringInspection")
public class TestDiscussion {

    private Discussion discussionTest;
    private User messageSenderTest;
    private Date sendDate;
    private String messageTest;

    @Before
    public void setUp(){
        //String message, Date sendDate, User sender
        sendDate = new Date(20125544);
        messageTest = "this is Discussion test";
        Date birthDay = new Date(20100222);
        messageSenderTest = new User("LastName", "UserName", "FirstName","test.example@email.com",birthDay);
        discussionTest = new Discussion(messageTest, sendDate, messageSenderTest);
    }

    @Test
    public void discussionConstructorTest(){
        Assert.assertNotNull(discussionTest);
    }

    @Test
    public void discussionMessageGetterTest(){
        Assert.assertTrue(discussionTest.getMessage().equals(messageTest));
    }

    @Test
    public void discussionSendDateGetterTest(){
        Assert.assertTrue(discussionTest.getSendDate().equals(sendDate));
    }

    @Test
    public void discussionSenderUserGetterTest(){
        Assert.assertTrue(discussionTest.getSender().equals(messageSenderTest));
    }
}
