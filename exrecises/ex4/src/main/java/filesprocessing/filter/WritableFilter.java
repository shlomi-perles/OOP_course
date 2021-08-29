package filesprocessing.filter;

import java.io.File;

/**
 * this class filter a file if the file is writable file
 */
public class WritableFilter extends QuestionFilter {
    /**
     * constructor for WritableFilter
     *
     * @param yesOrNo boolean that say if filer the writable or not
     */
    public WritableFilter(boolean yesOrNo) {
        super(yesOrNo);
    }

    /**
     * filter all files that are writable
     *
     * @param file the file we want to filter
     * @return true if it is, false else
     */
    public boolean filter(File file) {
        return this.getYesOrNo() == file.canWrite();
    }
}
