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

import java.util.Calendar;
import java.util.Date;

/**
 * Created by etudiant on 16/04/17.
 */
@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration(loader = AnnotationConfigWebContextLoader.class, classes = {company.ggi.config.WebAppConfig.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SuppressWarnings("SpringJavaAutowiringInspection")
public class PartyTest {

    private Party partyTest;
    private String partyNameTest;
    private String partyDescriptionTest;
    private Date partyStartingDateTest;
    private String partyNameGroupTest;
    private Game game;

    @Before
    public void Up() {
        partyNameTest = "My party Test";
        partyDescriptionTest = "My party description Test";
        partyStartingDateTest = Calendar.getInstance().getTime();
        partyNameGroupTest = "party group name";
        game = new Game();
        partyTest = new Party(partyNameTest, partyDescriptionTest, new DateTime(partyStartingDateTest), partyNameGroupTest, game);
    }

    @Test
    public void partyConstructorTest() {
        Assert.assertNotNull(partyTest);
    }

    @Test
    public void partyPartyNameGetterTest() {
        Assert.assertEquals(partyTest.getName(), partyNameTest);
    }

    @Test
    public void partyDescriptionGetterTest() {
        Assert.assertEquals(partyTest.getDescription(), partyDescriptionTest);
    }

    @Test
    public void partyGroupNameGetterTest() {
        Assert.assertEquals(partyTest.getGroupName(), partyNameGroupTest);
    }

    @Test
    public void partyGameGetterTest() {
        Assert.assertEquals(partyTest.getGame(), game);
    }
}
