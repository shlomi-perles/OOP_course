package filesprocessing.filter;

import java.io.File;

/**
 * This class filter all files that greater than the lower bound
 */
public class SmallerThanFilter implements Filter {
    /**
     * bound of the filter
     */
    private final double upperBound;

    /**
     * conver to kb
     */
    private final double KB = 1024.0f;

    /**
     * constructor for SmallerThanFilter
     *
     * @param upperBound double number
     */
    public SmallerThanFilter(double upperBound) {
        this.upperBound = upperBound * KB;
    }

    /**
     * filter all files that their size is smaller than the given bound
     *
     * @param file the file we want to filter
     * @return true if it is, false else
     */
    public boolean filter(File file) {
        long fileSize = file.length();
        return upperBound > fileSize;
    }
}
