package filesprocessing.order;

import java.io.File;
import java.util.Comparator;

/**
 * A comparator that compare files by their size
 */
public class SizeOrder implements Comparator<File> {

    /**
     * A comparator that compare files by their size
     *
     * @param file1 first file to compare
     * @param file2 second file to compare
     * @return positive number if size of file1 larger than the other, and otherwise else.
     */
    public int compare(File file1, File file2) {
        return Double.compare(file1.length(),file2.length());
    }
}
