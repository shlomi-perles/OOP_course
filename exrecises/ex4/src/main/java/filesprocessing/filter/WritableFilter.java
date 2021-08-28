package filesprocessing.filter;

import java.io.File;

/**
 * this class filter a file if the file is writable file
 */
public class WritableFilter extends QuestionFilter{
    /**
     * constructor for WritableFilter
     *
     * @param yesOrNo boolean that say if filer the writable or not
     */
    public WritableFilter(boolean yesOrNo) {
        super(yesOrNo);
    }

    @Override
    public boolean filter(File file) {
        return this.getYesOrNo() == file.canWrite();
    }
}
