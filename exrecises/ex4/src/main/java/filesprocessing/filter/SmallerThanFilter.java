package filesprocessing.filter;

import java.io.File;

/**
 * This class filter all files that greater than the lower bound
 */
public class SmallerThanFilter implements Filter {
    private double upperBound;

    /**
     * constructor for SmallerThanFilter
     *
     * @param upperBound double number
     */
    public SmallerThanFilter(double upperBound) {
        this.upperBound = upperBound;
    }

    @Override
    public boolean filter(File file) {
        long fileSize = file.length();
        return upperBound > fileSize;
    }
}
