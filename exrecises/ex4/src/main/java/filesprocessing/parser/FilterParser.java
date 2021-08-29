package filesprocessing.parser;

import filesprocessing.filter.Filter;
import filesprocessing.filter.FilterFactory;
import filesprocessing.Section;

import java.util.Arrays;
import java.util.Objects;

/**
 * This class parser the line belong to filter description and make the correct filter
 */
public class FilterParser {

    /**
     * strings for boolean filters and Not
     */
    private static final String YES = "YES", NO = "NO", NOT = "NOT";

    /**
     * index of filter name and othe args
     */
    private static final int FILTER_INDEX = 0, YES_NO_INDEX = 0, ARG1 = 0, ARG2 = 1;

    /**
     * expected number of args for each type
     */
    private static final int ARGS_NUM_ONE_DOUBLE = 1, ARGS_NUM_TWO_DOUBLE = 2, ARGS_NUM_ONE_STR = 1;

    /**
     * filters are classified into different departments
     */
    private static final String[] ONE_DOUBLE_FILTERS = {"greater_than", "smaller_than"},
            TWO_DOUBLE_FILTERS = {"between"}, CONTAINS_FILTERS = {"file", "contains", "prefix", "suffix"},
            YES_NO_FILTERS = {"writable", "executable", "hidden"}, NO_ARGS_FILTERS = {"all"};

    /**
     * args of the filter line
     */
    private final String[] lineArray;

    /**
     * constructor for FilterParser
     *
     * @param line the line we need to parse
     */
    public FilterParser(String line) {
        this.lineArray = line.split(CommandFileParser.LINE_SEPARATOR);
    }

    /**
     * this method parse filter line and make a Filter object accordingly
     *
     * @param curSection the current section that the filter belong to
     * @return the correct filter
     * @throws FilterException if filter parameters not correct
     */
    public Filter parse(Section curSection) throws FilterException {
        String filter;
        int expectedArgs;
        int additionNotArg = 0;

        try {
            filter = lineArray[FILTER_INDEX];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new FilterException();
        }

        String[] args = Arrays.copyOfRange(lineArray, 1, lineArray.length);
        boolean negateFilter;


        if (Arrays.asList(ONE_DOUBLE_FILTERS).contains(filter)) {
            negateFilter = parseNot(args, ARGS_NUM_ONE_DOUBLE);
            expectedArgs = ARGS_NUM_ONE_DOUBLE;
            doubleCheck(args);

        } else if (Arrays.asList(TWO_DOUBLE_FILTERS).contains(filter)) {
            negateFilter = parseNot(args, ARGS_NUM_TWO_DOUBLE);
            expectedArgs = ARGS_NUM_TWO_DOUBLE;
            BetweenCheck(args);

        } else if (Arrays.asList(CONTAINS_FILTERS).contains(filter)) {
            negateFilter = parseNot(args, ARGS_NUM_ONE_STR);
            expectedArgs = ARGS_NUM_ONE_STR;

        } else if (Arrays.asList(YES_NO_FILTERS).contains(filter)) {
            negateFilter = parseNot(args, ARGS_NUM_ONE_STR);
            expectedArgs = ARGS_NUM_ONE_STR;
            try {
                args[YES_NO_INDEX] = String.valueOf(yesNoCheck(args[YES_NO_INDEX]));
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new FilterException();
            }

        } else if (Arrays.asList(NO_ARGS_FILTERS).contains(filter)) {
            negateFilter = parseNot(args, 0);
            if (negateFilter) additionNotArg = 1;
            expectedArgs = additionNotArg;

        } else {
            throw new FilterException();
        }

        if (negateFilter) additionNotArg = 1;
        expectedArgs += additionNotArg;
        if (lineArray.length != expectedArgs + 1) throw new FilterException();

        curSection.setNegateFilter(negateFilter);
        return FilterFactory.createFilter(filter, args);
    }


    /**
     * check if string contain yes or no
     *
     * @param string string with yes or no
     * @return true if contained, false otherwises
     * @throws FilterException if not type of yes or no
     */
    public boolean yesNoCheck(String string) throws FilterException {
        if (string.equals(YES)) {
            return true;
        } else if (string.equals(NO)) {
            return false;
        } else {
            throw new FilterException();
        }
    }

    /**
     * get array of strings and check if it of format: [double,double]
     *
     * @param args array of strings
     * @throws FilterException if no two args or less than 0
     */
    public void BetweenCheck(String[] args) throws FilterException {
        double lowerBoundary;
        double upperBoundary;

        try {
            lowerBoundary = Double.parseDouble(args[ARG1]);
            upperBoundary = Double.parseDouble(args[ARG2]);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new FilterException();
        }

        if (lowerBoundary < 0 || upperBoundary < 0 || lowerBoundary > upperBoundary) {
            throw new FilterException();
        }
    }

    /**
     * check if a given size is a double and larger than 0
     *
     * @param args the given size
     * @throws FilterException if not a double or less than 0
     */
    public void doubleCheck(String[] args) throws FilterException {
        double bound;
        try {
            bound = Double.parseDouble(args[ARG1]);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new FilterException();
        }
        if (bound < 0) {
            throw new FilterException();
        }
    }

    /**
     * check if there is NOT at the last word
     *
     * @param args     args for check if there is not
     * @param notIndex where not need to be
     * @return return true if there is, false otherwise
     */
    private boolean parseNot(String[] args, int notIndex) {
        try {
            if (Objects.equals(args[notIndex], NOT)) return true;
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
        return false;
    }

}
