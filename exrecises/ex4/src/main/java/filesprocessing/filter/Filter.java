package filesprocessing.filter;

import java.io.File;

/**
 * a interface for the filters
 */
public interface  Filter {
    /**
     * method that receive a file and according to the filter - decide if its passed or not.
     * @param file the file we want to filter
     * @return true if tha file passed, false otherwise.
     */
    public boolean filter(File file);
}
