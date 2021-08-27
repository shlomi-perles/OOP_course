package filesprocessing.filter;

import java.io.File;

public class BetweenFilter {
    private double upperBoundary;
    private double lowerBoundary;




    public boolean filter(File file){
        long fileSize = file.length();
        return this.lowerBoundary < fileSize && fileSize < upperBoundary;
    }
}
