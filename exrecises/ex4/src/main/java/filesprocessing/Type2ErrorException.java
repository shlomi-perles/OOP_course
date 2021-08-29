package filesprocessing;

/**
 * this exception thrown when there is a problem of type 2
 */
public class Type2ErrorException extends Exception {
    private static final long serialVersionUID = 1L;

    /**
     * constructor for Type2ErrorException
     * @param errorMessage the message error
     */
    public Type2ErrorException(String errorMessage) {
        super(errorMessage);
    }
}