package company.ggi.service;
import company.ggi.model.Comment;

import java.util.List;

/**
 * Created by Adam on 26/04/2017.
 */
public interface CommentService  {

    public Comment create(Comment comment) throws Exception;

    public Comment delete(int id) throws Exception;

    public List<Comment> findAll();

    public Comment update(Comment comment) throws Exception;

    public Comment findById(int id) throws Exception;

}
