package filesprocessing.filter;

import java.io.File;

/**
 * This class filter all files that greater than the lower bound
 */
public class GreaterThanFilter implements Filter {
    /**
     * lower bound of the filter
     */
    private final double lowerBound;

    /**
     * convert to kb
     */
    private final static double KB = 1024.0f;

    /**
     * constructor for GreaterThanFilter
     *
     * @param lowerBound double number
     */
    public GreaterThanFilter(double lowerBound) {
        this.lowerBound = lowerBound * KB;
    }

    /**
     * filter all file that their size greater than the bound
     *
     * @param file the file we want to filter
     * @return true if it is, false else
     */
    public boolean filter(File file) {
        long fileSize = file.length();
        return lowerBound < fileSize;
    }
}
