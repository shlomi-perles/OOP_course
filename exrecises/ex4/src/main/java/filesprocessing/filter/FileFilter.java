package filesprocessing.filter;

import java.io.File;

/**
 * class filter files with the same name as given
 */
public class FileFilter implements Filter {

    private String searchName;

    /**
     * FileFilter constructor
     *
     * @param searchName the file we search for
     */
    public FileFilter(String searchName) {
        this.searchName = searchName;
    }


    /**
     * filter file with the given name
     * @param file the file we want to filter
     * @return true if it is, false else
     */
    public boolean filter(File file) {
        String filename = file.getName();
        return filename.equals(searchName);
    }
}
