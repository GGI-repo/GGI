package company.ggi.controller;

import company.ggi.model.Category;
import company.ggi.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Created by Ismail ELFAQIR on 10/04/2017.
 */

@RestController
@RequestMapping(value="/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value="/create", method=RequestMethod.GET)
    public String createNewCategory(@ModelAttribute Category category,
                                      final RedirectAttributes redirectAttributes) {

        logger.info("Service Create category called");
        try {
            categoryService.create(category);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Category Created";
    }
}
