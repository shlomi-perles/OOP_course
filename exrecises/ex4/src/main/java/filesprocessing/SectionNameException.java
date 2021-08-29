package filesprocessing;

/**
 * this exception thrown when there is a problem of type 2
 */
public class SectionNameException extends Type2ErrorException {
    private static final long serialVersionUID = 1L;

    /**
     * constructor for SectionNameException
     *
     * @param errorMessage the message error
     */
    public SectionNameException(String errorMessage) {
        super(errorMessage);
    }

    /**
     * constructor for SectionNameException
     */
    public SectionNameException() {
        super();
    }
}