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

    /**
     * filter files that executable
     * @param file the file we want to filter
     * @return true if it is, false else
     */
    public boolean filter(File file) {
        return this.getYesOrNo() == file.canExecute();
    }
}
