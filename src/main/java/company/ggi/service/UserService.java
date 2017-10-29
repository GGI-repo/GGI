package company.ggi.service;

import company.ggi.model.User;

import java.util.List;

/**
 * Created by Benmoumen on 06/05/2017.
 */
public interface UserService {
    List<User> findAllUsers() throws Exception;

    User createUser(User newUser) throws Exception;

    User getUserByName(String firstName, String lastName) throws Exception;

    User getUserById(Integer id) throws Exception;

    User update(User user) throws Exception;

    // add user to black list instead of delete
    User disable(User user) throws Exception;
}
