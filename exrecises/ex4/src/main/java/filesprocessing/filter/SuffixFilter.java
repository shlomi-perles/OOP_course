package filesprocessing.filter;

import java.io.File;

/**
 * Filter that search if file name end with specific string
 */
public class SuffixFilter implements Filter {
    private String suffix;

    /**
     * constructor for SuffixFilter
     *
     * @param suffix The string we search at the file name
     */
    public SuffixFilter(String suffix) {
        this.suffix = suffix;
    }

    @Override
    public boolean filter(File file) {
        String filename = file.getName();
        return filename.endsWith(suffix);
    }
}
