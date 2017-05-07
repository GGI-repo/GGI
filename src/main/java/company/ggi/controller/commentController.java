package company.ggi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import company.ggi.model.Comment;
import company.ggi.service.CommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * Created by Adam on 06/05/2017.
 */

@RestController
@RequestMapping(value = "/comment")
public class commentController {

    @Autowired
    private CommentService commentService;

    private ObjectMapper mapper = new ObjectMapper();
    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity findAllComments(@ModelAttribute Comment comment,
                                      final RedirectAttributes redirectAttributes) throws JsonProcessingException {

        logger.info("Controller find all comments called");
        try {
            List<Comment> listComment = commentService.findAll();
            return ResponseEntity.status(HttpStatus.OK).body(mapper.writeValueAsString(listComment));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mapper.writeValueAsString(e));
        }
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity createNewCategory(@ModelAttribute Comment comment,
                                            final RedirectAttributes redirectAttributes) throws JsonProcessingException {

        logger.info("Controller create comment called");
        try {
            Comment result =  commentService.create(comment);
            return ResponseEntity.status(HttpStatus.OK).body(mapper.writeValueAsString(result));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mapper.writeValueAsString(e));
        }
    }

    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public ResponseEntity findCategory(@ModelAttribute int idComment,
                                       final RedirectAttributes redirectAttributes) throws JsonProcessingException {

        logger.info("controller find comment called");
        try {
            Comment found =  commentService.findById(idComment);
            return ResponseEntity.status(HttpStatus.OK).body(mapper.writeValueAsString(found));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mapper.writeValueAsString(e));
        }
    }
}
