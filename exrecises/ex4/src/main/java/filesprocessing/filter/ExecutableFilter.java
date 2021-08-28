package filesprocessing.filter;

import java.io.File;

/**
 * filter of executable files
 */
public class ExecutableFilter extends QuestionFilter {


    /**
     * constructor for ExecutableFilter
     *
     * @param yesOrNo boolean that say if filer the executable or not
     */
    public ExecutableFilter(boolean yesOrNo) {
        super(yesOrNo);
    }

    @Override
    public boolean filter(File file) {
        return this.getYesOrNo() == file.canExecute();
    }
}
