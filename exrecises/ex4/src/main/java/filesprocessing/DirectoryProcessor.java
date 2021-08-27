package filesprocessing;

import filesprocessing.commandfile.Section;

import java.io.File;
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
     * method that print error message
     * @param errorMessage A string with the error message
     */
    static void printError(String errorMessage) {
        System.err.println("ERROR: " + errorMessage);
    }


    /**
     * main method. Starts implement the directory processor software
     * @param args Two strings: sourcedir, commandfile
     */
    public static void main(String[] args) {
        if (args.length != ARGS_NUM)
        {
            printError(ARGS_NUM_ERROR);
            return;
        }
        try {
            String sourceDir = args[SOURCE_DIR_ARG_INDEX];
            String commandFile = args[COMMAND_FILE_ARG_INDEX];

            ArrayList<Section> allSections = new SectionFactory()
                    .generateAllSections(new CommandFileParser(commandFile).getValidData());
            ArrayList<File> allDirFiles = dir2array(sourceDir);


            for (Section section : allSections) {
                ArrayList<File> result = new validateFilters()
                        .filterFiles(allDirFiles, section.getFilter(), section.isFilterNot());
                Comparator<File> comparator =
                        new CompareFactory().generateComparator(section.getOrder());
                result = new MergeSort().mergeSort(result, comparator, section.isOrderReverse());
                for (File file : result) {
                    System.out.println(file.getName());
                }
                printErrors(section);
            }
        } catch (Exception e) {
            System.err.println(ERROR + e);
        }
    }
}
