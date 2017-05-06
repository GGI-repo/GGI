package company.ggi.exception;

/**
 * Created by Benmoumen on 06/05/2017.
 */
public class UserException extends Exception {

    public static final String USER_NOT_FOUND = "User not found";
    public static final String NOT_ENOUGH_USERS = "No user found";
    public static final String USER_ALREADY_EXISTS = "User already exists";
    public static final String USER_NOT_CREATED = "User not created";

    private String message;

    public UserException(String message) {
        super(message);
        this.message = message;
    }
    @Override
    public String getMessage() {
        return message;
    }
}
