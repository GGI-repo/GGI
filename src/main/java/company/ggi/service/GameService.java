package company.ggi.service;

import company.ggi.model.Game;

import java.util.List;

/**
 * Created by Benmoumen on 07/05/2017.
 */

public interface GameService {
    Game create(Game newGame) throws Exception;

    Game update(Game game) throws Exception;

    List<Game> findAll() throws Exception;

    Game findByName(String name) throws Exception;

    Game delete(Game game) throws Exception;
}
