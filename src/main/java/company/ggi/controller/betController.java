package company.ggi.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by etudiant on 02/04/17.
 */
@RestController
public class betController {
    @RequestMapping("/")
    public String index(){
        return "Hello bet";
    }

}
