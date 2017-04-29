package company.ggi.dao;

import company.ggi.model.Discussion;
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

/**
 * Created by ismail ELFAQIR on 16/04/2017.
 */

@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration(loader = AnnotationConfigWebContextLoader.class, classes = {company.ggi.config.WebAppConfig.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SuppressWarnings("SpringJavaAutowiringInspection")
public class DiscussionDaoTest {

    @Autowired
    private DiscussionDao discussionDao;

    private Discussion discussion;

    private String title;

    @Before
    public void setUp() {

        discussionDao.deleteAll();
        title = "My test";
        discussion = new Discussion(title);
        discussion = discussionDao.save(discussion);
    }

    @Test
    public void discussionDaoSaveTest() {

        Discussion myDiscussion = discussionDao.findOne(discussion.getId());
        Assert.assertEquals(myDiscussion.getId(), discussion.getId());
        Assert.assertTrue(myDiscussion.getTitle().equals(discussion.getTitle()));
    }

    @Test
    public void discussionDaoUpdateTest() {

        final String test = "test";
        discussion.setTitle(test);
        discussion = discussionDao.save(discussion);
        Assert.assertTrue(discussion.getTitle().equals(test));
    }

    @Test
    public void categoryDaoDeleteTest() {

        discussionDao.delete(discussion);
        Assert.assertNull(discussionDao.findOne(discussion.getId()));
    }
}
