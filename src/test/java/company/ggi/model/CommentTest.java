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
 * Created by Adam on 16/04/2017.
 */
@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration(loader = AnnotationConfigWebContextLoader.class, classes = {company.ggi.config.WebAppConfig.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SuppressWarnings("SpringJavaAutowiringInspection")
public class CommentTest {
    private Comment commentTest;
    private String commentContentTest;
    private Date commentDateTest;
    private User user;

    @Before
    public void setUp(){
        commentContentTest =  " my content test";
        commentDateTest = new Date();
        user = new User("LastName", "UserName", "FirstName", "test.example@email.com", new Date(20100222));
        commentTest = new Comment(commentContentTest,commentDateTest, new Party(),user);
    }

    @Test
    public void commentConstructorTest(){
        Assert.assertNotNull("The Comment object is null", commentTest);
    }

    @Test
    public void commentContentGetterTest(){
        Assert.assertEquals("Comment content  incorrect",commentContentTest, commentTest.getContent() );
    }

    @Test
    public void commentDateGetterTest(){
        Assert.assertEquals("Comment date incorrect",commentDateTest, commentTest.getCommentDate() );
    }

    @Test
    public void commentUserGetterTest(){
        Assert.assertEquals("Comment user incorrect",user, commentTest.getUser() );
    }

    @Test
    public void commentPartyGetterTest(){
        Assert.assertNotNull(commentTest.getParty());
    }

}
