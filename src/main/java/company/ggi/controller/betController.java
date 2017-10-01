package company.ggi.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import company.ggi.service.HealthCheckService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.*;


/**
 * Created by etudiant on 02/04/17.
 */
@RestController
public class betController {

    @Autowired
    private TokenStore tokenStore;

    @Value("${version}")
    private String version;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private ObjectMapper mapper = new ObjectMapper();

    @RequestMapping("/healthcheck")
    public ResponseEntity index() throws JsonProcessingException {
        logger.info("GET health check called ");
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
