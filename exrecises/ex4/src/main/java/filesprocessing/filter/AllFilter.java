package filesprocessing.filter;

import java.io.File;

/**
 * A type of filter that received any file
 */
public class AllFilter implements Filter{

    @Override
    public boolean filter(File file) {
        return true;
    }
}
