package filesprocessing.order;

import java.io.File;
import java.util.Comparator;

/**
 * A comparator that compare files by their absolute name
 */
public class AbsOrder implements Comparator<File> {

    /**
     * A comparator that compare files by their absolute name
     *
     * @param file1 first file to compare
     * @param file2 second file to compare
     * @return positive number if absolute name of file1 larger than the other, and otherwise else.
     */
    public int compare(File file1, File file2) {
        return file1.getAbsolutePath().compareTo(file2.getAbsolutePath());
    }
}
