package company.ggi.service;

import company.ggi.model.Discussion;

import java.util.List;

/**
 * Created by Ismail ELFAQIR on 10/04/17.
 */

public interface DiscussionService {

    public Discussion create(Discussion discussion) throws Exception;

    public Discussion delete(int id) throws Exception;

    public List<Discussion> findAll();

    public Discussion update(Discussion discussion) throws Exception;

    public Discussion findById(int id) throws Exception;
}
