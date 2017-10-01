package company.ggi.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import company.ggi.dao.UserDao;
import company.ggi.exception.UserException;
import company.ggi.model.User;
import company.ggi.service.UserService;
import org.omg.PortableInterceptor.USER_EXCEPTION;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Ismail ELFAQIR on 25/04/2017.
 */
@Service("userDetailsService")
public class UserServiceImpl implements UserDetailsService, UserService {

    @Autowired
    private UserDao userDao;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDao.findByUserName(username);
    }

    @Override
    @Transactional
    public List<User> findAllUsers() throws Exception {
        List<User> userList;
        userList = userDao.findAll();
        if (userList.isEmpty()) {
            logger.debug("User list is empty " + mapper.writeValueAsString(userList));
            throw new Exception(UserException.NOT_ENOUGH_USERS);
        }
        return userList;
    }

    @Override
    public User createUser(User newUser) throws Exception {

        if (null == newUser) {
            logger.debug("Can't create user if user object is null");
            throw new Exception(UserException.USER_OBJECT_NULL);
        }
        User isAlreadyUser = userDao.findByEmail(newUser.getEmail());

        if (isAlreadyUser != null) {
            logger.debug("This user already exists : " + mapper.writeValueAsString(isAlreadyUser));
            throw new Exception(UserException.USER_ALREADY_EXISTS);
        }

        User user = userDao.saveAndFlush(newUser);

        if (user == null) {
            logger.debug("Can't create user : ");
            throw new Exception(UserException.USER_NOT_CREATED);
        }

        return user;
    }

    @Override
    public User getUserByName(String firstName, String lastName) throws Exception {
        if (firstName == null || lastName == null) {
            logger.debug("Can't find user by name object null");
            throw new Exception(UserException.USER_OBJECT_NULL);
        }
        User user = userDao.findByFirstNameAndLastName(firstName, lastName);

        if (user == null) {
            logger.debug("user not found : " + mapper.writeValueAsString(firstName + " " + lastName));
            throw new Exception(UserException.USER_NOT_FOUND);
        }

        return user;
    }

    @Override
    public User getUserById(Integer id) throws Exception {
        if (null == id) {
            logger.debug("User id is null");
            throw new Exception(UserException.USER_NOT_FOUND);
        }
        User user = userDao.findOne(id);
        if (null == user) {
            logger.debug("User not found");
            throw new Exception(UserException.USER_NOT_FOUND);
        }
        return user;
    }

    @Override
    public User update(User user) throws Exception {
        if (null == user) {
            throw new Exception(UserException.USER_OBJECT_NULL);
        }
        if (null == user.getId()) {
            logger.debug("user id is null");
            throw new Exception(UserException.USER_OBJECT_NULL);
        }
        User userToUpdate = userDao.findOne(user.getId());

        if (null == userToUpdate) {
            logger.debug("Can't update user if not found");
            throw new Exception(UserException.USER_NOT_FOUND);
        }
        userToUpdate = userDao.save(user);

        return userToUpdate;
    }

    @Override
    public User disable(User user) throws Exception {
        if (null == user || null == user.getId()) {
            logger.debug("Can't disable user if user object is null");
            throw new Exception(UserException.USER_OBJECT_NULL);
        }

        user.setEnabled(false);
        user = userDao.save(user);
        return user;
    }


}