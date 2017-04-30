package company.ggi.exception;

import java.io.Serializable;

/**
 * Created by Adam on 26/04/2017.
 */
public class CommentException extends  Exception implements Serializable {
    public static final String COMMENT_NOT_FOUND = "Comment not found";
    public static final String COMMENT_WITH_NULL_CONTENT = "Comment content is null";

    private String message;

    public CommentException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
