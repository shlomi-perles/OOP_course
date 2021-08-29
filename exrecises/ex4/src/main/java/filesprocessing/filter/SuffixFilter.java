package filesprocessing.filter;

import java.io.File;

/**
 * Filter that search if file name end with specific string
 */
public class SuffixFilter implements Filter {
    /**
     * given suffix to filter
     */
    private final String suffix;

    /**
     * constructor for SuffixFilter
     *
     * @param suffix The string we search at the file name
     */
    public SuffixFilter(String suffix) {
        this.suffix = suffix;
    }

    /**
     * filter all files with the give suffix
     *
     * @param file the file we want to filter
     * @return true if it is, false else
     */
    public boolean filter(File file) {
        String filename = file.getName();
        return filename.endsWith(suffix);
    }
}
