package filesprocessing.filter;

import java.io.File;

/**
 * A type of filter that received any file
 */
public class AllFilter implements Filter {

    /**
     * gets all files
     * @param file the file we want to filter
     * @return always true
     */
    public boolean filter(File file) {
        return true;
    }
}
