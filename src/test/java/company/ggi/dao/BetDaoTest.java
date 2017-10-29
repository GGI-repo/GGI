package company.ggi.dao;

import company.ggi.model.*;
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
 * Created by Adam on 29/04/2017.
 */

@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration(loader = AnnotationConfigWebContextLoader.class, classes = {company.ggi.config.WebAppConfig.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SuppressWarnings("SpringJavaAutowiringInspection")
public class BetDaoTest  {

    @Autowired
    private BetDao betDaoTest;

    @Autowired
    private PartyDao partyDaoTest;

    @Autowired
    private UserDao userDaoTest;

    @Autowired
    private GameDao gameDaoTest;

    @Autowired
    private CategoryDao categoryDaoTest;

    private User user;
    private Category category;
    private Game game;
    private Party party;
    private Bet bet;
    private int credit;
    private DateTime date;

    @Before
    public void setUp() {

        credit = 100;
        date = new DateTime();

        user = new User("LastName", "test", "FirstName", "testForBet.example@email.fr", new DateTime());
        user = userDaoTest.save(user);

        category = new Category("MOBA"); // MOBA : multiplayer online battle arena
        category = categoryDaoTest.save(category);

        game = new Game("LeagueOfLegends","bestGameEver",category);
        game = gameDaoTest.save(game);

        party = new Party("ARAM","teamFightOnly",date,"TeamName",game);
        party = partyDaoTest.save(party);

        bet = new Bet(credit,party,user);
        bet = betDaoTest.save(bet);
    }

    @Test
    public void betSaveDaoTest(){
        Assert.assertNotNull(bet);
        Assert.assertEquals(1,betDaoTest.findAll().size());
    }

    @Test
    public void betUpdateDaoTest(){
        int newCredit = 200;
        bet.setCredit(newCredit);
        bet =  betDaoTest.save(bet);

        Assert.assertEquals(newCredit,bet.getCredit() );
    }

    @Test
    public void betDeleteDaoTest(){
        betDaoTest.delete(bet);

        Assert.assertTrue(betDaoTest.findAll().isEmpty());
    }


    @After
    public void clean() {
        betDaoTest.deleteAll();
        userDaoTest.deleteAll();
        partyDaoTest.deleteAll();
        gameDaoTest.deleteAll();
        categoryDaoTest.deleteAll();
    }
}
