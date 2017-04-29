package company.ggi.exception;

/**
 * Created by ismail ELFAQIR on 22/04/2017.
 */
public class DiscussionException extends Exception {

    public static final String DISCUSSION_NOT_FOUND = "Discussion not found";

    private String message;

    public DiscussionException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
