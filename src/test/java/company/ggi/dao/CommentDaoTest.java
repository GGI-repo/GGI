package company.ggi.dao;

import company.ggi.model.*;
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
 * Created by Adam on 29/04/2017.
 */

@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration(loader = AnnotationConfigWebContextLoader.class, classes = {company.ggi.config.WebAppConfig.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SuppressWarnings("SpringJavaAutowiringInspection")
public class CommentDaoTest {

    @Autowired
    private CommentDao commentDaoTest;

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
    private Comment commentTest;
    private String content;
    private DateTime date;



    @Before
    public void setUp() {

        content = "I comment to test";
        date = new DateTime();

        user = new User("LastName", "test", "FirstName", "testForComment.example@email.fr", new DateTime());
        user = userDaoTest.save(user);

        category = new Category("MOBA"); // MOBA : multiplayer online battle arena
        category = categoryDaoTest.save(category);

        game = new Game("LeagueofLegends","bestGameEver",category);
        game = gameDaoTest.save(game);

        party = new Party("ARAM","teamFightOnly",date,"TeamName",game);
        party = partyDaoTest.save(party);

        commentTest = new Comment("ok comment",party,user);
        commentTest = commentDaoTest.save(commentTest);
    }

    @Test
    public void commentSaveDaoTest(){
        Assert.assertNotNull(commentTest);
        Assert.assertEquals(1, commentDaoTest.findAll().size());
    }

    @Test
    public void commentDaoUpdateTest(){
        String newComment = "a nother comment ";
        commentTest.setContent(newComment);
        commentTest = commentDaoTest.save(commentTest);

        Assert.assertEquals(newComment,commentTest.getContent() );
    }

    @Test
    public void commentDaoDeleteTest() {
        commentDaoTest.delete(commentTest);

        Assert.assertTrue(commentDaoTest.findAll().isEmpty());
        Assert.assertNull(commentDaoTest.findOne(commentTest.getId()));
    }

    @After
    public void clean() {
        commentDaoTest.deleteAll();
        userDaoTest.deleteAll();
        partyDaoTest.deleteAll();
        gameDaoTest.deleteAll();
        categoryDaoTest.deleteAll();
    }

}
