package filesprocessing.parser;

import filesprocessing.section.Section;

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
    private static final String FILTER_STR = "FILTER";
    /**
     * detect order word
     */
    private static final String ORDER_STR = "ORDER";


    public static final String LINE_SEPARATOR = "#";

    /**
     * section size for calculations
     */
    private static final int SECTION_SIZE = 4;

    /**
     * section line index
     */
    private static final int FILTER_INDEX = 0;
    private static final int FILTER_TYPE_INDEX = 1;
    private static final int ORDER_INDEX = 2;
    private static final int ORDER_TYPE_INDEX = 3;

    /**
     * scanner for the file
     */
    private Scanner scanner; //TODO: final?

    /**
     * constructor for commandFileParse
     *
     * @param commandFilePath string with the file path
     * @throws Exception //TODO: throw?
     */
    public CommandFileParser(String commandFilePath) throws Exception { //TODO: throw?
        scanner = new Scanner(new File(commandFilePath));
    }

    /**
     * Convert commandFile to array
     *
     * @return array of lines in commad file
     * @throws Exception //TODO: throw?
     */
    private ArrayList<String> fileToArray() throws Exception { //TODO: throw?
        ArrayList<String> lines = new ArrayList<String>();
        while (scanner.hasNext()) {
            lines.add(scanner.nextLine());
        }
        return lines;
    }

    /**
     * main parset method. create section array while he is parser
     * @return array of sections thar parsed
     * @throws Exception //TODO:
     */
    public ArrayList<Section> parse() throws Exception { //TODO: throw?
        ArrayList<Section> sections = new ArrayList<Section>();
        ArrayList<String> lines = this.fileToArray(); //TODO: try catch?

        int i = -1;
        int relativeSectionIndex;
        Section curSection = new Section();
        for (String line : lines) { //TODO: try catch throws from parsers
            ++i;
            relativeSectionIndex = i % SECTION_SIZE;
            //TODO: switches?
            if (relativeSectionIndex == FILTER_INDEX) {
                if (!Objects.equals(line, FILTER_STR)) throw new Exception("sdf"); //TODO: throw correct
                curSection = new Section();
                continue;
            }
            if (relativeSectionIndex == FILTER_TYPE_INDEX) {
                try {
                    curSection.setFilter(new FilterParser(line).parse());

                } catch (Exception e) {  //TODO
                    //TODO
                }
                continue;
            }

            if (relativeSectionIndex == ORDER_INDEX)
            {
                if (!Objects.equals(line, ORDER_STR)) throw new Exception("sdf"); //TODO: throw correct
                continue;
            }

            if (relativeSectionIndex == ORDER_TYPE_INDEX) {
                try {
                    curSection.setOrder(new OrderParser(line).parse());

                } catch (Exception e) {  //TODO
                    //TODO
                }
                continue;
            }
        }
        if (i% SECTION_SIZE != 1) throw new Exception("sdf"); //TODO: throw correct
        return sections;
    }
}
