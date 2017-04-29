package company.ggi.exception;

/**
 * Created by ismail ELFAQIR on 23/04/2017.
 */
public class DiscussionGroupException extends Exception {

    public static final String DISCUSSION_GROUP_NOT_FOUND = "Discussion group not found";
    public static final String NOT_ENOUGH_USERS = "Discussion group require two users to be created";
    public static final String DISCUSSION_GROUP_EXIST = "Discussion group exist";

    private String message;

    public DiscussionGroupException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
