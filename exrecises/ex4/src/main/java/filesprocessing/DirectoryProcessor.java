package filesprocessing;


import filesprocessing.parser.CommandFileParser;
import filesprocessing.section.Section;

import java.util.ArrayList;

/**
 * main class that implement the directory processor software
 */
public class DirectoryProcessor {
    /**
     * number of possible arguments
     */
    private final static int ARGS_NUM = 2;

    /**
     * error message while not receive two arguments
     */
    private final static String ARGS_NUM_ERROR = "Wrong usage. Should receive 2 arguments";

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
     * method that print error message
     *
     * @param errorMessage A string with the error message
     */
    static void printError(String errorMessage) {
        System.err.println("ERROR: " + errorMessage);
    }


    /**
     * main method. Starts implement the directory processor software
     *
     * @param args Two strings: sourcedir, commandfile
     */
    public static void main(String[] args) {
        try {
            if (args.length != ARGS_NUM) {
                throw new Type2ErrorException(ARGS_NUM_ERROR);
            }

            CommandFileParser parser = new CommandFileParser(args[COMMAND_FILE_ARG_INDEX]);

            ArrayList<Section> sectionArrayList = parser.parse();
            parser.printScript(sectionArrayList);

        } catch (Type2ErrorException type2Exception) {
            System.out.println(ERROR + type2Exception);
        }
    }
}
