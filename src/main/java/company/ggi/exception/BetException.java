package company.ggi.exception;

import java.io.Serializable;

/**
 * Created by Adam on 30/04/2017.
 */
public class BetException  extends Exception implements Serializable {

    public static final String BET_NOT_FOUND = "Bet not found";
    public static final String BET_WITH_NULL_VALUE = "Bet with null value";
    public static final String BET_ALREADY_EXIST = "Bet already exist";
    public static final String BET_CREDIT_EQUAL_ZERO = "Bet credit equal zero";
    public static final String USER_NOT_FOUND = "User not found";

    private String message;

    public BetException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

}
