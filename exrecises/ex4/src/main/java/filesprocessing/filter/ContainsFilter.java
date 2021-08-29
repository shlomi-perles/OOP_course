package filesprocessing.filter;

import java.io.File;

/**
 * Filter that search for a string inside file name
 */
public class ContainsFilter implements Filter {
    private String containsString;

    /**
     * constructor for ContainsFilter
     *
     * @param containsString The string we search at the file name
     */
    public ContainsFilter(String containsString) {
        this.containsString = containsString;
    }

    /**
     * check if file contain a string
     *
     * @param file the file we want to filter
     * @return true if contain, false else
     */
    public boolean filter(File file) {
        String filename = file.getName();
        return filename.contains(containsString);
    }
}
