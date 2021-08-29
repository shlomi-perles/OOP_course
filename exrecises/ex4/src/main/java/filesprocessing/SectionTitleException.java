package filesprocessing;

/**
 * this exception thrown when there is a problem of type 2
 */
public class SectionTitleException extends Type2ErrorException {
    private static final long serialVersionUID = 1L;

    /**
     * constructor for SectionTitleException
     *
     * @param errorMessage the message error
     */
    public SectionTitleException(String errorMessage) {
        super(errorMessage);
    }

    /**
     * constructor for SectionTitleException
     */
    public SectionTitleException() {
        super();
    }
}