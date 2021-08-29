package filesprocessing.filter;

import java.io.File;

/**
 * Filter that search if file name start with specific string
 */
public class PrefixFilter implements Filter {
    /**
     * given prefix for filter
     */
    private final String prefix;

    /**
     * constructor for prefix Filter
     *
     * @param prefix The string we search at the file name
     */
    public PrefixFilter(String prefix) {
        this.prefix = prefix;
    }

    /**
     * filter files with the given prefix
     *
     * @param file the file we want to filter
     * @return true if it is, false else
     */
    public boolean filter(File file) {
        String filename = file.getName();
        return filename.startsWith(prefix);
    }
}
