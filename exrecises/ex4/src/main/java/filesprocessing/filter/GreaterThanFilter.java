package filesprocessing.filter;

import java.io.File;

/**
 * This class filter all files that greater than the lower bound
 */
public class GreaterThanFilter implements Filter {
    private double lowerBound;

    /**
     * constructor for GreaterThanFilter
     *
     * @param lowerBound double number
     */
    public GreaterThanFilter(double lowerBound) {
        this.lowerBound = lowerBound;
    }

    @Override
    public boolean filter(File file) {
        long fileSize = file.length();
        return lowerBound < fileSize;
    }
}
