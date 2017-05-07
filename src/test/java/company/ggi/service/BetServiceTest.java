package company.ggi.service;

import company.ggi.dao.*;
import company.ggi.exception.BetException;
import company.ggi.model.*;
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
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Date;

/**
 * Created by Adam on 01/05/2017.
 */

@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration(loader = AnnotationConfigWebContextLoader.class, classes = {company.ggi.config.WebAppConfig.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SuppressWarnings("SpringJavaAutowiringInspection")
public class BetServiceTest {
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

    @Autowired
    private BetService betService;

    private User user;
    private Category category;
    private Game game;
    private Party party;
    private Bet bet;
    private int credit;
    private Date date;


    @Before
    public void setUp(){
        credit = 100;
        date = new Date();

        user = new User("LastName", "test", "FirstName", "testForBet.example@email.fr", new Date());
        user = userDaoTest.save(user);

        category = new Category("MOBA"); // MOBA : multiplayer online battle arena
        category = categoryDaoTest.save(category);

        game = new Game("LeagueOfLegends","bestGameEver",category);
        game = gameDaoTest.save(game);

        party = new Party("ARAM","teamFightOnly",date,"TeamName",game);
        party = partyDaoTest.save(party);

        bet = new Bet(credit,party,user);
        bet = betDaoTest.save(bet);

        ReflectionTestUtils.setField(betService, "betRepository", betDaoTest);
    }

    @Test
    public void betSaveServiceTest() throws Exception {
        int countRes = betDaoTest.findAll().size();
        Assert.assertEquals(1,countRes);

        betService.create(new Bet(120,party,user));
        Assert.assertEquals(countRes + 1,betService.findAll().size() );
    }

    @Test(expected = BetException.class)
    public void betSaveServiceWithExceptionTest() throws Exception {
        betService.create(bet);
    }

    @Test(expected = BetException.class)
    public void  betFindByIdServiceWithExceptionTest() throws Exception {
        betService.findById(5);
    }

    @Test
    public void  commentUpdateServiceTest() throws Exception {
        int newCredit = 200;
        bet.setCredit(newCredit);
        betService.update(bet);

        Assert.assertEquals(newCredit,betService.findById(bet.getId()).getCredit());
    }

    @Test
    public void  commentDeleteServiceTest() throws Exception {
        betService.delete(bet.getId());
        Assert.assertTrue(betService.findAll().isEmpty());
    }

    @Test(expected = BetException.class)
    public void  commentDeleteServiceWithExceptionTest() throws Exception {
        betService.delete(7);
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
