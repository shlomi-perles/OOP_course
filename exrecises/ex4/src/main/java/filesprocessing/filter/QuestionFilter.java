package filesprocessing.filter;

public abstract class QuestionFilter implements Filter {
    /**
     * if the answer is yes - true, otherwise - false
     */
    private boolean yesOrNo;

    /**
     * constructor for ExecutableFilter
     *
     * @param yesOrNo boolean that say if filer the executable or not
     */
    public QuestionFilter(boolean yesOrNo) {
        this.yesOrNo = yesOrNo;
    }

    /**
     * getter for yes or no bollean
     * @return boolean that said if the answer for the question is yes or no
     */
    public boolean getYesOrNo() {
        return yesOrNo;
    }
}
