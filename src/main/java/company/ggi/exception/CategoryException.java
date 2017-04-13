package company.ggi.exception;

import java.io.Serializable;

/**
 * Created by Ismail ELFAQIR on 10/04/2017.
 */
public class CategoryException extends Exception implements Serializable {

    public static final String CATEGORY_NOT_FOUND = "Category not found";
    public static final String CATEGORY_EXISTS = "Category already exists";
    public static final String CATEGORY_WITH_NULL_NAME = "Category name is null";

    private String message;

    public CategoryException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
