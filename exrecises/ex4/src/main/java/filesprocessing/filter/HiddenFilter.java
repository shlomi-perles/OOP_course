package filesprocessing.filter;

import java.io.File;

/**
 * this class filter a file if the file is hidden file
 */
public class HiddenFilter extends QuestionFilter {

    /**
     * constructor for HiddenFilter
     *
     * @param yesOrNo boolean that say if filer the hiden or not
     */
    public HiddenFilter(boolean yesOrNo) {
        super(yesOrNo);
    }

    @Override
    public boolean filter(File file) {
        return this.getYesOrNo() == file.isHidden();
    }
}
