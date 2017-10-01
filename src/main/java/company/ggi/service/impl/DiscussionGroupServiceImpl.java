package company.ggi.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import company.ggi.dao.DiscussionGroupDao;
import company.ggi.exception.DiscussionGroupException;
import company.ggi.model.Discussion;
import company.ggi.model.DiscussionGroup;
import company.ggi.model.User;
import company.ggi.service.DiscussionGroupService;
import company.ggi.service.DiscussionService;
import company.ggi.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ismail ELFAQIR on 22/04/2017.
 */

@Service
public class DiscussionGroupServiceImpl implements DiscussionGroupService {

    @Resource
    private DiscussionGroupDao discussionGroupRepository;

    @Resource
    private UserService userService;

    @Autowired
    private DiscussionService discussionService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    @Transactional
    public DiscussionGroup create(List<User> users) throws Exception {

        logger.info("Trying to create a discussion group");

        // getting users
        users = this.getAllUsersInformation(users);

        Date date = new Date();
        // getting users

        // create the discussion
        Discussion discussion = new Discussion();

        if (users.size() < 2)
            throw new DiscussionGroupException(DiscussionGroupException.NOT_ENOUGH_USERS);

        if (this.isDiscussionGroupExist(users))
            throw new DiscussionGroupException(DiscussionGroupException.DISCUSSION_GROUP_EXIST);

        discussion.setTitle(users.get(0).getUserName());
        discussion.setCreationDate(date);
        discussion = discussionService.create(discussion);

        // first user in the list is the owner of the discussion
        List<DiscussionGroup> discussionGroups = new ArrayList<DiscussionGroup>();
        for (User user : users) {
            DiscussionGroup discussionGroup = new DiscussionGroup();
            discussionGroup.setUser(user);
            discussionGroup.setDiscussion(discussion);
            discussionGroup.setAddedToDiscussion(date);
            if (users.indexOf(user) == 0)
                discussionGroup.setRole(DiscussionGroup.ROLE.Owner);
            else
                discussionGroup.setRole(DiscussionGroup.ROLE.Nothing);

            discussionGroups.add(discussionGroup);
        }

        // save discussion group and return the first one
        DiscussionGroup firstDiscussionGroup = new DiscussionGroup();
        for (DiscussionGroup discussionGroup : discussionGroups) {
            if (discussionGroups.indexOf(discussionGroup) == 0)
                firstDiscussionGroup = discussionGroupRepository.save(discussionGroup);
            else
                discussionGroupRepository.save(discussionGroup);
        }
        return firstDiscussionGroup;
    }

    @Override
    public List<DiscussionGroup> findDiscussionGroupsByDiscussion(Discussion discussion)
            throws Exception {

        discussion = discussionService.findById(discussion.getId());
        return discussion.getDiscussionGroups();
    }

    @Override
    public DiscussionGroup findById(int id) throws Exception {

        DiscussionGroup discussionGroup = discussionGroupRepository.findOne(id);
        if (discussionGroup == null)
            throw new DiscussionGroupException(DiscussionGroupException.DISCUSSION_GROUP_NOT_FOUND);

        return discussionGroup;
    }

    @Override
    public DiscussionGroup addUserToDiscussionGroup(DiscussionGroup discussionGroup, User user) {
        return null;
    }

    private Boolean isDiscussionGroupExist(List<User> users) {

        User owner = users.get(0);
        List<DiscussionGroup> discussionGroupsOfOwner = owner.getDiscussionGroups();
        for (DiscussionGroup discussionGroup : discussionGroupsOfOwner) {
            List<DiscussionGroup> discussionGroupOfDiscussion =
                    discussionGroup.getDiscussion().getDiscussionGroups();
            if (discussionGroupOfDiscussion.size() == users.size()) {
                Boolean isTheSameUsers = true;
                for (DiscussionGroup dg : discussionGroupOfDiscussion) {
                    if (users.indexOf(dg.getUser()) == -1) {
                        isTheSameUsers = false;
                        break;
                    }
                }
                if (isTheSameUsers)
                    return true;
            }
        }
        return false;
    }

    private List<User> getAllUsersInformation(List<User> users) throws Exception {

        List<User> realUsers = new ArrayList<>();
        for (User user : users) {
            User userToAdd = userService.getUserById(user.getId());
            realUsers.add(userToAdd);
        }
        return realUsers;
    }
}