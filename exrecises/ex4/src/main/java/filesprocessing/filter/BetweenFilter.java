package filesprocessing.filter;

import java.io.File;

/**
 * filter that check if the file is in a specific size
 */
public class BetweenFilter implements Filter {
    /**
     * bounds of the filter
     */
    private final double upperBoundary, lowerBoundary;

    /**
     * conver to kb
     */
    private final static double KB = 1024.0f;

    /**
     * constructor for between filter
     *
     * @param lowerBoundary lower boundary for file size
     * @param upperBoundary upper boundary for file size
     */
    public BetweenFilter(double lowerBoundary, double upperBoundary) {
        this.upperBoundary = upperBoundary * KB;
        this.lowerBoundary = lowerBoundary * KB;
    }


    /**
     * filter all files with size between bounds
     *
     * @param file the file we want to filter
     * @return true if the file size in the bounds
     */
    public boolean filter(File file) {
        long fileSize = file.length();
        return this.lowerBoundary <= fileSize && fileSize <= upperBoundary;
    }
}
