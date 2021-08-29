package filesprocessing.filter;

import java.io.File;

/**
 * This class filter all files that greater than the lower bound
 */
public class GreaterThanFilter implements Filter {
    /**
     * lower bound of the filter
     */
    private double lowerBound;

    /**
     * convert to kb
     */
    private static double KB = 1024.0f;

    /**
     * constructor for GreaterThanFilter
     *
     * @param lowerBound double number
     */
    public GreaterThanFilter(double lowerBound) {
        this.lowerBound = lowerBound*KB;
    }

    @Override
    public boolean filter(File file) {
        long fileSize = file.length();
        return lowerBound < fileSize;
    }
}
