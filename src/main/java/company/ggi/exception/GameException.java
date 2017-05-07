package company.ggi.exception;

/**
 * Created by Benmoumen on 07/05/2017.
 */
public class GameException extends Exception {
    public static final String GAME_NOT_FOUND = "Game not found";
    public static final String GAME_ALREADY_EXISTS = "Game already exists";
    public static final String GAME_NOT_CREATED = "Game not created";
    public static final String GAME_OBJECT_NULL = "Game object is null";
    public static final String GAME_NO_DATA = "Game list is empty";

    private String message;

    public GameException(String message) {
        super(message);
        this.message = message;
    }
    @Override
    public String getMessage() {
        return message;
    }
}