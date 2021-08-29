package filesprocessing;

/**
 * this exception thrown when there is a problem of type 2
 */
public class FileException extends Type2ErrorException {
    private static final long serialVersionUID = 1L;

    /**
     * constructor for FileException
     *
     * @param errorMessage the message error
     */
    public FileException(String errorMessage) {
        super(errorMessage);
    }

    /**
     * constructor for FileException
     */
    public FileException() {
        super();
    }
}