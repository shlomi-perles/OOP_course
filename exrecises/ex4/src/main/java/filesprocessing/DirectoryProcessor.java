package filesprocessing;


import filesprocessing.parser.CommandFileParser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

/**
 * main class that implement the directory processor software
 */
public class DirectoryProcessor {
    /**
     * number of possible arguments
     */
    private final static int ARGS_NUM = 2;

    /**
     * error messages
     */
    private final static String ARGS_NUM_ERROR = "Wrong usage. Should receive 2 arguments",
            CANT_READ_FILES = "Cant read files.";

    /**
     * where source dir string located at args order
     */
    private final static int SOURCE_DIR_ARG_INDEX = 0;

    /**
     * where command file string located at args order
     */
    private final static int COMMAND_FILE_ARG_INDEX = 1;

    /**
     * error message for type 2
     */
    private final static String ERROR = "ERROR: ";


    /**
     * main method. Starts implement the directory processor software
     *
     * @param args Two strings: sourcedir, commandfile
     */
    public static void main(String[] args) {
        ArrayList<Section> sectionArrayList = new ArrayList<Section>();
        ArrayList<File> filesArray =new ArrayList<File>();

        if (args.length != ARGS_NUM) {
            System.err.println(ERROR + ARGS_NUM_ERROR);
            return;
        }
        try {
            CommandFileParser commandFileParser = new CommandFileParser(args[COMMAND_FILE_ARG_INDEX]);
            sectionArrayList = commandFileParser.parse();

        } catch (Type2ErrorException type2Exception) {
            System.err.println(ERROR + type2Exception);
            return;
        }

        try {
            filesArray = dirToArray(args[SOURCE_DIR_ARG_INDEX]);
        } catch (IOException ioException) {
            System.err.println(ERROR + CANT_READ_FILES);
            return;
        }

        for (Section section: sectionArrayList)
        {
            section.print(new ArrayList<File>(filesArray));
        }
    }


    private static ArrayList<File> dirToArray(String sourceDir) throws  IOException {
        ArrayList<File> result = new ArrayList<File>();
        File files = new File(sourceDir);
        for (File file : Objects.requireNonNull(files.listFiles())) {
            if (!file.isDirectory()) {
                result.add(file);
            }
        }
        return result;
    }
}
