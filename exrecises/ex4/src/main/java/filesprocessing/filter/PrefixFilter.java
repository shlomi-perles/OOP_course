package filesprocessing.filter;

import java.io.File;

/**
 * Filter that search if file name start with specific string
 */
public class PrefixFilter implements Filter {
    private String prefix;

    /**
     * constructor for prefix Filter
     *
     * @param prefix The string we search at the file name
     */
    public PrefixFilter(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public boolean filter(File file) {
        String filename = file.getName();
        return filename.startsWith(prefix);
    }
}
