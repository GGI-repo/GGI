package company.ggi.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import company.ggi.model.Bet;
import company.ggi.service.BetService;
import company.ggi.service.HealthCheckService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * Created by Adam BENJBARA on 02/04/17.
 */
@RestController
@RequestMapping(value = "/bet")
public class betController {

    @Autowired
    private BetService betService;


    private TokenStore tokenStore;

    @Value("${version}")
    private String version;
    private ObjectMapper mapper = new ObjectMapper();
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity findAllBets(@ModelAttribute Bet bet,
                                            final RedirectAttributes redirectAttributes) throws JsonProcessingException {

        logger.info("Controller find all bet called");
        try {
            List<Bet> listBet = betService.findAll();
            return ResponseEntity.status(HttpStatus.OK).body(mapper.writeValueAsString(listBet));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mapper.writeValueAsString(e));
        }
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity createNewCategory(@ModelAttribute Bet bet,
                                            final RedirectAttributes redirectAttributes) throws JsonProcessingException {

        logger.info("Controller create bet called");
        try {
            Bet result =  betService.create(bet);
            return ResponseEntity.status(HttpStatus.OK).body(mapper.writeValueAsString(result));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mapper.writeValueAsString(e));
        }
    }

    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public ResponseEntity findCategory(@ModelAttribute int idBet,
                                       final RedirectAttributes redirectAttributes) throws JsonProcessingException {

        logger.info("controller find bet called");
        try {
            Bet  found = betService.findById(idBet);
            return ResponseEntity.status(HttpStatus.OK).body(mapper.writeValueAsString(found));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mapper.writeValueAsString(e));
        }
    }

    @RequestMapping("/healthcheck")
    public ResponseEntity index() throws JsonProcessingException {
        logger.info("Get health check called ");
        HealthCheckService healthcheck = new HealthCheckService("GGI API health check", version);
        try {
            return ResponseEntity.status(HttpStatus.OK).body(mapper.writeValueAsString(healthcheck));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mapper.writeValueAsString(e));
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ResponseEntity logout(String access_token) {
        logger.info("request to remove access token : " + access_token);
        try {
            if (access_token != null && access_token != "") {
                OAuth2AccessToken accessToken = tokenStore.readAccessToken(access_token);
                tokenStore.removeAccessToken(accessToken);
            }
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

    }

}
