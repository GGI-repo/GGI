package company.ggi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import company.ggi.model.Game;
import company.ggi.service.GameService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Benmoumen on 08/05/2017.
 */
@RestController
@RequestMapping(value = "/admin/games")
public class GameController {
    @Autowired
    private GameService gameService;
    private ObjectMapper mapper = new ObjectMapper();
    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity getAll() throws JsonProcessingException {

        List<Game> games;
        try {
            games = gameService.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(mapper.writeValueAsString(e));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mapper.writeValueAsString(e));
        }
        return ResponseEntity.status(HttpStatus.OK).body(games);
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public ResponseEntity get(@RequestBody String name) throws JsonProcessingException {
        Game game;

        try {
            game = gameService.findByName(name);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(mapper.writeValueAsString(e));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mapper.writeValueAsString(e));
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(mapper.writeValueAsString(game));
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity create(@RequestBody Game game) throws JsonProcessingException{

        try {
            game = gameService.create(game);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(mapper.writeValueAsString(e));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mapper.writeValueAsString(e));
        }
        return ResponseEntity.status(HttpStatus.OK).body(game);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity update(@RequestBody Game game) throws JsonProcessingException{

        try {
            game = gameService.update(game);
        } catch (Exception e){
            e.printStackTrace();
            logger.error(mapper.writeValueAsString(e));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mapper.writeValueAsString(e));
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(game);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ResponseEntity delete(@RequestBody Game game) throws JsonProcessingException{

        try {
            game = gameService.delete(game);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(mapper.writeValueAsString(e));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mapper.writeValueAsString(e));
        }
        return ResponseEntity.status(HttpStatus.OK).body(game);
    }
}