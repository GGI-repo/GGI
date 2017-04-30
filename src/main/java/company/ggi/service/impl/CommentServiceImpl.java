package company.ggi.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import company.ggi.dao.CategoryDao;
import company.ggi.dao.CommentDao;
import company.ggi.exception.CommentException;
import company.ggi.model.Comment;
import company.ggi.service.CommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Adam on 26/04/2017.
 *  Implementation of CommentService interface
 *  This service provide some methods to manage comments
 */
public class CommentServiceImpl implements CommentService {


    @Resource
    private CommentDao commentRepository;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    @Transactional
    public Comment create(Comment comment) throws Exception {
        logger.info("Trying to create a category");
        Comment newComment = comment;

        if(newComment == null )
            throw new CommentException(CommentException.COMMENT_NOT_FOUND);

        if(newComment.getContent() == null )
            throw new CommentException(CommentException.COMMENT_WITH_NULL_CONTENT);

        return commentRepository.save(newComment);
    }

    @Override
    @Transactional(rollbackFor = CommentException.class)
    public Comment delete(int id) throws Exception {
        logger.info("Trying to delete a category");
        Comment comment = commentRepository.findOne(id);

        if (comment == null) {
            logger.error("Category not found with id=" + id);
            throw new CommentException(CommentException.COMMENT_NOT_FOUND);
        }

        commentRepository.delete(comment);
        return comment;
    }

    @Override
    @Transactional
    public List<Comment> findAll() {
        logger.info("Trying to get all categories ");
        return commentRepository.findAll();
    }

    @Override
    public Comment update(Comment comment) throws Exception {

        logger.info("Trying to update category ");
        Comment commentToUpdate = commentRepository.findOne(comment.getId());

        if (commentToUpdate == null) {
            logger.error("Category not found " + mapper.writeValueAsString(comment));
            throw new CommentException(CommentException.COMMENT_NOT_FOUND);
        }

        if(commentToUpdate.getContent() == null )
            throw new CommentException(CommentException.COMMENT_WITH_NULL_CONTENT);

        commentToUpdate.setContent(comment.getContent());
        commentRepository.save(commentToUpdate);
        return commentToUpdate;
    }

    @Override
    public Comment findById(int id) throws Exception {
        logger.info("Trying to find a category by it's id ");
        Comment category = commentRepository.findOne(id);

        if (category == null) {
            logger.error("Category not found with id =" + id);
            throw new CommentException(CommentException.COMMENT_NOT_FOUND);
        }

        return category;
    }
}
