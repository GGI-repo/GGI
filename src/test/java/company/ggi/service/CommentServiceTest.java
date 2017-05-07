package company.ggi.service;

import company.ggi.dao.*;
import company.ggi.exception.CommentException;
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
public class CommentServiceTest {

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

    @Autowired
    private CommentService commentService;

    private User user;
    private Category category;
    private Game game;
    private Party party;
    private Comment commentTest;
    private String content;
    private Date date;


    @Before
    public void setUp() {

        content = "I comment to test";
        date = new Date();

        user = new User("LastName", "test", "FirstName", "testForComment.example@email.fr", new Date());
        user = userDaoTest.save(user);

        category = new Category("MOBA"); // MOBA : multiplayer online battle arena
        category = categoryDaoTest.save(category);

        game = new Game("LeagueofLegends","bestGameEver",category);
        game = gameDaoTest.save(game);

        party = new Party("ARAM","teamFightOnly",date,"TeamName",game);
        party = partyDaoTest.save(party);

        commentTest = new Comment(content,party,user);
        commentTest = commentDaoTest.save(commentTest);

        ReflectionTestUtils.setField(commentService, "commentRepository", commentDaoTest);
    }

    @Test
    public void  commentSaveServiceTest() throws Exception {
        int countRes = commentDaoTest.findAll().size();
        Assert.assertEquals(1,countRes);

        commentService.create(new Comment("content test",party,user));
        Assert.assertEquals(countRes + 1,commentService.findAll().size() );
    }

    @Test(expected = CommentException.class)
    public void  commentSaveServiceWithExceptionTest() throws Exception {
        commentService.create(new Comment("",party,user));
    }

    @Test(expected = CommentException.class)
    public void  commentFindByIdServiceWithExceptionTest() throws Exception {
        commentService.findById(5);
    }

    @Test
    public void  commentUpdateServiceTest() throws Exception {
        String newContent = "new content";
        commentTest.setContent(newContent);
        commentService.update(commentTest);

        Assert.assertEquals(newContent,commentService.findById(commentTest.getId()).getContent()); // comment content change
    }

    @Test
    public void  commentDeleteServiceTest() throws Exception {
        commentService.delete(commentTest.getId());
        Assert.assertTrue(commentService.findAll().isEmpty());
    }

    @Test(expected = CommentException.class)
    public void  commentDeleteServiceWithExceptionTest() throws Exception {
        commentService.delete(7);
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
