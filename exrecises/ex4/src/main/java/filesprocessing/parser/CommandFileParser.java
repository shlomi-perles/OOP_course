package filesprocessing.parser;

import filesprocessing.Type2ErrorException;
import filesprocessing.Section;

import java.io.File;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

/**
 * This is the main class for parse command file
 */
public class CommandFileParser {
    /**
     * detect filter word
     */
    private static final String FILTER = "FILTER";
    /**
     * detect order word
     */
    private static final String ORDER = "ORDER";

    /**
     * seperator between args
     */
    public static final String LINE_SEPARATOR = "#";

    /**
     * default filter in case of filter problem
     */
    public static final String DEFAULT_FILTER = "all";


    /**
     * default order in case of order problem
     */
    public static final String DEFAULT_ORDER = "abs";

    /**
     * section size for calculations
     */
    private static final int SECTION_SIZE = 4;

    /**
     * section line index
     */
    private static final int FILTER_INDEX = 0, FILTER_TYPE_INDEX = 1, ORDER_INDEX = 2, ORDER_TYPE_INDEX = 3;

    /**
     * errors messages
     */
    private final static String SCANNER_ERROR = "Read command file fail.",
            BAD_SUBSECTION_NAME_ERROR = "bad sub-section name.",
            COMMAND_FILE_ENDED_UNEXPECTEDLY = "Command file ended unexpectedly.";


    /**
     * scanner for the file
     */
    private Scanner scanner; //TODO: final?

    /**
     * constructor for commandFileParse
     *
     * @param commandFilePath string with the file path
     * @throws Type2ErrorException throw when cant read command file
     */
    public CommandFileParser(String commandFilePath) throws Type2ErrorException {
        try {
            scanner = new Scanner(new File(commandFilePath));
        } catch (Exception e) {
            throw new Type2ErrorException(SCANNER_ERROR);
        }

    }


    /**
     * main parser method. create section array while he is parser
     *
     * @return array of sections thar parsed
     * @throws Type2ErrorException //TODO:
     */
    public ArrayList<Section> parse() throws Type2ErrorException {
        ArrayList<Section> sections = new ArrayList<Section>();
        ArrayList<String> lines = this.fileToArray();

        int i = -1;
        int relativeSectionIndex;
        Section curSection = new Section();
        for (String line : lines) { //TODO: try catch throws from parsers
            ++i;
            relativeSectionIndex = i % SECTION_SIZE;

            switch (relativeSectionIndex) {

                case FILTER_INDEX:
                    if (!(Objects.equals(line, FILTER))) {
                        throw new Type2ErrorException(BAD_SUBSECTION_NAME_ERROR);
                    }
                    curSection = new Section();
                    break;

                case FILTER_TYPE_INDEX:
                    try {
                        curSection.setFilter(new FilterParser(line).parse(curSection));

                    } catch (FilterException filterExceptionMsg) {
                        curSection.addLineError(i);
                    }
                    break;

                case ORDER_INDEX:
                    if (!(Objects.equals(line, ORDER))) {
                        throw new Type2ErrorException(BAD_SUBSECTION_NAME_ERROR);
                    }
                    break;

                case ORDER_TYPE_INDEX:
                    try {
                        curSection.setOrder(new OrderParser(line).parse(curSection));

                    } catch (OrderException orderExceptionMsg) {
                        curSection.addLineError(i);
                    }
                    break;
            }
        }
        if (i % SECTION_SIZE != 1) throw new Type2ErrorException(COMMAND_FILE_ENDED_UNEXPECTEDLY);
        return sections;
    }

    /**
     * Convert commandFile to array
     *
     * @return array of lines in command file
     * @throws Type2ErrorException throw when there is a problem while reading command file
     */
    private ArrayList<String> fileToArray() throws Type2ErrorException {
        ArrayList<String> lines = new ArrayList<String>();

        try {
            while (scanner.hasNext()) {
                lines.add(scanner.nextLine());
            }
            return lines;

        } catch (Exception e) {
            throw new Type2ErrorException(SCANNER_ERROR);
        }
    }
}
