package company.ggi.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import company.ggi.dao.UserDao;
import company.ggi.exception.UserException;
import company.ggi.model.User;
import company.ggi.service.UserService;
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
    private UserDao userRepository;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUserName(username);
    }

    @Override
    @Transactional
    public List<User> findAllUsers() throws Exception {
        List<User> userList;
        userList = userRepository.findAll();
        if (userList.isEmpty()) {
            logger.debug("User list is empty "+mapper.writeValueAsString(userList));
            throw new Exception(UserException.NOT_ENOUGH_USERS );
        }
        return userList;
    }

    @Override
    public User createUser(User newUser) throws Exception {
        User isAlreadyUser = userRepository.findByEmail(newUser.getEmail());

        if(isAlreadyUser!=null){
            logger.debug("This user already exists : "+mapper.writeValueAsString(isAlreadyUser));
            throw new Exception(UserException.USER_ALREADY_EXISTS);
        }

        User user = userRepository.saveAndFlush(newUser);

        if (user == null) {
            logger.debug("Can't create user : ");
            throw new Exception(UserException.USER_NOT_CREATED);
        }

        return user;
    }

    @Override
    public User getUserByName(String name) throws Exception {
        User user = userRepository.findByName(name);

        if(user == null) {
            logger.debug("user not found : " + mapper.writeValueAsString(name));
            throw new Exception(UserException.USER_NOT_FOUND);
        }

        return user;
    }
}