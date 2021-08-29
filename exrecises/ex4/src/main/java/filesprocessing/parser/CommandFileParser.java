package filesprocessing.parser;

import filesprocessing.*;

import java.io.File;
import java.io.IOException;
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
     * separator between args
     */
    public static final String LINE_SEPARATOR = "#";

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
    private final Scanner scanner;

    /**
     * constructor for commandFileParse
     *
     * @param commandFilePath string with the file path
     * @throws FileException throw when can't read command file
     */
    public CommandFileParser(String commandFilePath) throws FileException {
        try {
            scanner = new Scanner(new File(commandFilePath));
        } catch (IOException e) {
            throw new FileException(SCANNER_ERROR);
        }
    }


    /**
     * main parser method. create section array while he is parser
     *
     * @return array of sections thar parsed
     * @throws Type2ErrorException throw when a problem at FILTER or ORDER
     */
    public ArrayList<Section> parse() throws Type2ErrorException {
        ArrayList<Section> sections = new ArrayList<Section>();
        ArrayList<String> lines = this.fileToArray();

        Section curSection = new Section();
        int lastPhase = 0, i = -1;
        for (String line : lines) {
            ++i;
            switch (lastPhase) { // four possible phases: FILTER, filter type, ORDER, order type

                case FILTER_INDEX:
                    if (!(Objects.equals(line, FILTER))) {
                        throw new SectionTitleException(BAD_SUBSECTION_NAME_ERROR);
                    }
                    curSection = new Section();
                    ++lastPhase;
                    break;

                case FILTER_TYPE_INDEX:
                    try {
                        curSection.setFilter(new FilterParser(line).parse(curSection));

                    } catch (FilterException filterExceptionMsg) {
                        curSection.addLineError(i + 1);
                    }
                    ++lastPhase;
                    break;

                case ORDER_INDEX:
                    if (!(Objects.equals(line, ORDER))) {
                        throw new SectionTitleException(BAD_SUBSECTION_NAME_ERROR);
                    }
                    sections.add(curSection);
                    ++lastPhase;

                    // check if next line is FILTER
                    if (i + 1 < lines.size() && Objects.equals(lines.get(i + 1), FILTER)) lastPhase = 0;
                    break;

                case ORDER_TYPE_INDEX:
                    try {
                        curSection.setOrder(new OrderParser(line).parse(curSection));

                    } catch (OrderException orderExceptionMsg) {
                        curSection.addLineError(i + 1);
                    }
                    lastPhase = 0;
                    break;
            }
        }

        //Check if didn't stop unexpectedly
        if (lastPhase < ORDER_TYPE_INDEX && lastPhase > FILTER_INDEX) {
            throw new FileException(COMMAND_FILE_ENDED_UNEXPECTEDLY);
        }
        return sections;
    }

    /**
     * Convert commandFile to array
     *
     * @return array of lines in command file
     * @throws FileException throw when there is a problem while reading command file
     */
    private ArrayList<String> fileToArray() throws FileException {
        ArrayList<String> lines = new ArrayList<String>();

        try {
            while (scanner.hasNext()) {
                lines.add(scanner.nextLine());
            }
            return lines;

        } catch (RuntimeException e) {
            throw new FileException(SCANNER_ERROR);
        }
    }
}
