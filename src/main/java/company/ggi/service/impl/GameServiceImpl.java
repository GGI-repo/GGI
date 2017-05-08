package company.ggi.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import company.ggi.dao.CategoryDao;
import company.ggi.dao.GameDao;
import company.ggi.exception.CategoryException;
import company.ggi.exception.GameException;
import company.ggi.model.Category;
import company.ggi.model.Game;
import company.ggi.service.GameService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Benmoumen on 07/05/2017.
 */
public class GameServiceImpl implements GameService {

    @Autowired
    private GameDao gameRepository;
    @Autowired
    private CategoryDao categoryRepository;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public Game create(Game newGame) throws Exception {
        if (null == newGame){
            throw new Exception(GameException.GAME_OBJECT_NULL);
        }
        Category category = newGame.getCategory();
        if(null == category || null == category.getName()) {
            logger.error("Can't create user if category object is null "+mapper.writeValueAsString(newGame));
            throw new Exception(GameException.GAME_OBJECT_NULL);
        }

        if(null != gameRepository.findByName(newGame.getName())){
            logger.error("User already exists " + mapper.writeValueAsString(newGame));
            throw new Exception(GameException.GAME_ALREADY_EXISTS);
        }
        // if category doesn't exists ? create it or not ?
        Game result = gameRepository.saveAndFlush(newGame);

        if(null == result){
            logger.error("Game is not created" + mapper.writeValueAsString(newGame));
            throw new Exception(GameException.GAME_NOT_CREATED);
        }

        return result;
    }

    @Override
    public Game update(Game game) throws Exception {

        if( null == game || null == game.getId()){
            logger.error("game object is null");
            throw new Exception(GameException.GAME_OBJECT_NULL);
        }

        Category category = game.getCategory();

        if(null == category || null == category.getId()){
            logger.error("Can't update game if category object is null");
            throw new Exception(GameException.GAME_OBJECT_NULL);
        }else{
            category = categoryRepository.findOne(category.getId());
            if(null == category ){
                logger.error("Can't update game if category doesn't exists");
                throw  new Exception(CategoryException.CATEGORY_NOT_FOUND);
            }
        }

        Game gameToUpdate = gameRepository.findOne(game.getId());

        if(null == gameToUpdate){
            logger.error("can't update user if doesn't exists "+mapper.writeValueAsString(game));
            throw new Exception(GameException.GAME_NOT_FOUND);
        }
        gameToUpdate = gameRepository.save(game);

        return gameToUpdate;
    }

    @Override
    public List<Game> findAll() throws Exception {
        List<Game> games = gameRepository.findAll();

        if(games.isEmpty()){
            logger.debug("No game in database");
            throw new Exception(GameException.GAME_NO_DATA);
        }
        return games;
    }

    @Override
    public Game findByName(String name) throws Exception {

        if(null == name){
            logger.error("name object is null");
            throw new Exception(GameException.GAME_OBJECT_NULL);
        }
        Game result = gameRepository.findByName(name);

        if(result == null || result.getName() == null) {
            logger.error("Game not found " + mapper.writeValueAsString(name));
            throw new Exception(GameException.GAME_NOT_FOUND);
        }

        return result;
    }

    @Override
    public Game delete(Game game) throws Exception {
        if(null == game || null == game.getId()){
            logger.error("Can't delete null object");
            throw new Exception(GameException.GAME_OBJECT_NULL);
        }

        Category category = game.getCategory();

        if(null == category || null == category.getId()){
            logger.error("Can't delete game if category object is null");
            throw new Exception(GameException.GAME_OBJECT_NULL);
        }else{
            category = categoryRepository.findOne(category.getId());
            if(null == category ){
                logger.error("Can't delete game if category doesn't exists");
                throw  new Exception(CategoryException.CATEGORY_NOT_FOUND);
            }
        }

        Game gameToDelete = gameRepository.findOne(game.getId());

        if(null == gameToDelete || null == gameToDelete.getId()){
            logger.error("Can't delete game if not found" + mapper.writeValueAsString(game));
            throw new Exception(GameException.GAME_NOT_FOUND);
        }
        gameRepository.delete(game.getId());

        return gameToDelete;
    }
}