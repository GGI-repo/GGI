package company.ggi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import company.ggi.model.User;
import company.ggi.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Created by Benmoumen on 07/05/2017.
 */
@RestController
@RequestMapping(value = "/users")
public class UserController {
    @Autowired
    UserService userService;
    private ObjectMapper mapper = new ObjectMapper();
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/user/signin", method = RequestMethod.POST)
    public ResponseEntity addUser(@RequestBody User newUser, final
                                  RedirectAttributes redirectAttributes) throws JsonProcessingException {
        User result;
        try {
            result = userService.createUser(newUser);
        } catch (Exception e) {
            e.printStackTrace();
            logger.debug("Failed to create user "+mapper.writeValueAsString(e));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mapper.writeValueAsString(e));
        }
        logger.debug("Success user created "+mapper.writeValueAsString(result.getUserName()));
        return ResponseEntity.status(HttpStatus.OK).body(mapper.writeValueAsString(result));
    }
}
