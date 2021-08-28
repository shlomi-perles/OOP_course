package filesprocessing.order;

import java.io.File;
import java.util.Comparator;

/**
 * A comparator that compare files by their type
 */
public class TypeOrder implements Comparator<File> {

    /**
     * A comparator that compare files by their type
     *
     * @param file1 first file to compare
     * @param file2 second file to compare
     * @return positive number if type of file1 larger than the other, and otherwise else.
     */
    public int compare(File file1, File file2) {
        String file1Type = file1.getName().substring(file1.getName().lastIndexOf(".") + 1);
        String file2Type = file2.getName().substring(file2.getName().lastIndexOf(".") + 1);
        return file1Type.compareTo(file2Type);
    }
}
