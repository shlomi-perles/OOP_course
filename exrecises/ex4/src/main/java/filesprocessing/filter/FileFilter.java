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


    @Override
    public boolean filter(File file) {
        String filename = file.getName();
        return filename.equals(searchName);
    }
}
