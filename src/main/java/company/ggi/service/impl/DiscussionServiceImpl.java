package company.ggi.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import company.ggi.dao.DiscussionDao;
import company.ggi.exception.DiscussionException;
import company.ggi.model.Discussion;
import company.ggi.service.DiscussionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by ismail ELFAQIR on 22/04/2017.
 */
@Service
public class DiscussionServiceImpl implements DiscussionService {

    @Resource
    private DiscussionDao discussionRepository;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    @Transactional
    public Discussion create(Discussion discussion) throws Exception {

        logger.info("Trying to create a discussion");
        Discussion newDiscussion = discussion;
        return discussionRepository.save(newDiscussion);
    }

    @Override
    @Transactional(rollbackFor = DiscussionException.class)
    public Discussion delete(int id) throws Exception {

        logger.info("Trying to delete a discussion");
        Discussion discussion = discussionRepository.findOne(id);

        if (discussion == null) {
            logger.error("Discussion not found with id=" + id);
            throw new DiscussionException(DiscussionException.DISCUSSION_NOT_FOUND);
        }

        discussionRepository.delete(discussion);
        return discussion;
    }

    @Override
    @Transactional
    public List<Discussion> findAll() {

        logger.info("Trying to get all discussions ");
        return discussionRepository.findAll();
    }

    @Override
    @Transactional(rollbackFor = DiscussionException.class)
    public Discussion update(Discussion discussion) throws Exception {

        logger.info("Trying to update discussion ");
        Discussion discussionToUpdate = discussionRepository.findOne(discussion.getId());

        if (discussionToUpdate == null) {
            logger.error("Discussion not found " + mapper.writeValueAsString(discussion));
            throw new DiscussionException(DiscussionException.DISCUSSION_NOT_FOUND);
        }

        discussionToUpdate.setTitle(discussion.getTitle());
        discussionRepository.save(discussionToUpdate);
        return discussionToUpdate;
    }

    @Override
    @Transactional(rollbackFor = DiscussionException.class)
    public Discussion findById(int id) throws Exception {

        logger.info("Trying to find a discussion by it's id ");
        Discussion discussion = discussionRepository.findOne(id);

        if (discussion == null) {
            logger.error("Discussion not found with id =" + id);
            throw new DiscussionException(DiscussionException.DISCUSSION_NOT_FOUND);
        }

        return discussion;
    }
}
