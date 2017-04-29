package company.ggi.service;

import company.ggi.model.Discussion;
import company.ggi.model.DiscussionGroup;
import company.ggi.model.User;

import java.util.List;

/**
 * Created by ismail ELFAQIR on 22/04/2017.
 */
public interface DiscussionGroupService {

    public DiscussionGroup create(List<User> users) throws Exception;

    public List<DiscussionGroup> findDiscussionGroupsByDiscussion(Discussion discussion) throws Exception;

    public DiscussionGroup findById(int id) throws Exception;
}
