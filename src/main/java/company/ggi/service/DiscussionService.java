package company.ggi.service;

import company.ggi.model.Discussion;

import java.util.List;

/**
 * Created by Ismail ELFAQIR on 10/04/17.
 */

public interface DiscussionService {

    Discussion create(Discussion discussion) throws Exception;

    Discussion delete(int id) throws Exception;

    List<Discussion> findAll();

    Discussion update(Discussion discussion) throws Exception;

    Discussion findById(int id) throws Exception;
}
