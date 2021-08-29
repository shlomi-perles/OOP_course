package filesprocessing.filter;

import java.io.File;

/**
 * filter that check if the file is in a specific size
 */
public class BetweenFilter implements Filter {
    /**
     * bounds of the filter
     */
    private double upperBoundary, lowerBoundary;

    /**
     * conver to kb
     */
    private static double KB = 1024.0f;

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


    @Override
    public boolean filter(File file) {
        long fileSize = file.length();
        return this.lowerBoundary <= fileSize && fileSize <= upperBoundary;
    }
}
