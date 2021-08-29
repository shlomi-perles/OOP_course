package filesprocessing.filter;

import java.io.File;

/**
 * This class filter all files that greater than the lower bound
 */
public class SmallerThanFilter implements Filter {
    /**
     * bound of the filter
     */
    private double upperBound;

    /**
     * conver to kb
     */
    private static double KB = 1024.0f;

    /**
     * constructor for SmallerThanFilter
     *
     * @param upperBound double number
     */
    public SmallerThanFilter(double upperBound) {
        this.upperBound = upperBound * KB;
    }

    @Override
    public boolean filter(File file) {
        long fileSize = file.length();
        return upperBound > fileSize;
    }
}
