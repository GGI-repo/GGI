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
public class BetTest {
    Bet betTest;
    int credit;
    User user;

    @Before
    public void setUp(){
        credit =  50;
        user = new User("LastName", "UserName", "FirstName", "test.example@email.com", new Date(20100222));
        betTest = new Bet(credit, new Party(),user);
    }

    @Test
    public void betConstructorTest(){
        Assert.assertNotNull("The Bet object is null", betTest);
    }

    @Test
    public void betCreditGetterTest(){
        Assert.assertEquals("Bet credit incorrect",credit, betTest.getCredit() );
    }

    @Test
    public void betUserGetterTest(){
        Assert.assertEquals("Bet user incorrect",user, betTest.getUser() );
    }

    @Test
    public void betPartyGetterTest(){
        Assert.assertNotNull(betTest.getParty());
    }

}
