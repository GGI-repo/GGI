package company.ggi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import company.ggi.model.DiscussionGroup;
import company.ggi.model.User;
import company.ggi.service.DiscussionGroupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * Created by ismail ELFAQIR on 23/04/2017.
 */

@RestController
@RequestMapping(value = "/discussion")
public class DiscussionGroupController {

    @Autowired
    private DiscussionGroupService discussionGroupService;

    private ObjectMapper mapper = new ObjectMapper();
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity createNewDiscussionGroup(@RequestBody List<User> users,
                                                   final RedirectAttributes redirectAttributes) throws JsonProcessingException {

        logger.info("Service create discussion group called ");
        try {
            DiscussionGroup result = discussionGroupService.create(users);
            return ResponseEntity.status(HttpStatus.OK).body(mapper.writeValueAsString(result));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mapper.writeValueAsString(e));
        }
    }
}
