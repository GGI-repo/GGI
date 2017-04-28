package company.ggi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import company.ggi.exception.CategoryException;
import company.ggi.model.Category;
import company.ggi.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * Created by Ismail ELFAQIR on 10/04/2017.
 */

@RestController
@RequestMapping(value = "/admin/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    private ObjectMapper mapper = new ObjectMapper();
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity findAllCategories(@ModelAttribute Category category,
                                            final RedirectAttributes redirectAttributes) throws JsonProcessingException {

        logger.info("Service find all categories called");
        try {
            List<Category> list = categoryService.findAll();
            return ResponseEntity.status(HttpStatus.OK).body(mapper.writeValueAsString(list));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mapper.writeValueAsString(e));
        }
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity createNewCategory(@ModelAttribute Category category,
                                    final RedirectAttributes redirectAttributes) throws JsonProcessingException {

        logger.info("Service create category called");
        try {
            Category result = categoryService.create(category);
            return ResponseEntity.status(HttpStatus.OK).body(mapper.writeValueAsString(result));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mapper.writeValueAsString(e));
        }
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public ResponseEntity updateCategory(@ModelAttribute Category category,
                                    final RedirectAttributes redirectAttributes) throws JsonProcessingException {

        logger.info("Service update category called");
        try {
            Category result = categoryService.update(category);
            return ResponseEntity.status(HttpStatus.OK).body(mapper.writeValueAsString(result));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mapper.writeValueAsString(e));
        }
    }

    @RequestMapping(value = "/", method = RequestMethod.DELETE)
    public ResponseEntity deleteCategory(@ModelAttribute Category category,
                                         final RedirectAttributes redirectAttributes) throws JsonProcessingException {

        logger.info("Service delete category called");
        try {
            categoryService.update(category);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mapper.writeValueAsString(e));
        }
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public ResponseEntity findCategory(@ModelAttribute Category category,
                                         final RedirectAttributes redirectAttributes) throws JsonProcessingException {

        logger.info("Service find category called");
        try {
            Category found;
            if(category.getId() != null)
                found = categoryService.findById(category.getId());
            else found = categoryService.findByName(category.getName());

            return ResponseEntity.status(HttpStatus.OK).body(mapper.writeValueAsString(found));

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mapper.writeValueAsString(e));
        }
    }



}
