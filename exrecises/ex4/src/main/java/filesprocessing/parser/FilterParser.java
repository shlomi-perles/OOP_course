package filesprocessing.parser;

import filesprocessing.filter.BetweenFilter;
import filesprocessing.filter.Filter;

/**
 * This class parser the line belong to filter description and make the correct filter
 */
public class FilterParser {

    /**
     * strings for boolean filters
     */
    private static final String YES = "YES", NO = "NO";

    /**
     * the parse line
     */
    private String line;

    /**
     * constructor for FilterParser
     *
     * @param line the line we need to parse
     */
    public FilterParser(String line) {
        String[] parts = line.split(CommandFileParser.LINE_SEPARATOR);
        this.line = line;
    }

    public Filter parse() throws Exception { //TODO

    }

    /**
     * get array of strings and check if it of format: [double,double]
     *
     * @param args array of strings
     * @return BetweenFilter with the correct bounds
     * @throws FilterException //TODO:
     */
    public BetweenFilter BetweenParser(String[] args) throws FilterException {

        try {
            double lowerBoundary = Double.parseDouble(args[0]);
            double upperBoundary = Double.parseDouble(args[1]); //TODO: exeption for false index
            if (lowerBoundary < 0 || upperBoundary < 0 || lowerBoundary > upperBoundary) {
                throw new FilterException();
            }
            return new BetweenFilter(lowerBoundary, upperBoundary);
        } catch (NumberFormatException e) {
            throw new FilterException();
        }
    }

    /**
     * check if string contain yes or no
     *
     * @param string
     * @return true if contained, false otherwises
     * @throws FilterException //TODO:
     */
    public boolean yesNoParser(String string) throws FilterException {
        if (string.equals(YES)) {
            return true;
        } else if (string.equals(NO)) {
            return false;
        } else {
            throw new FilterException();
        }
    }

    /**
     * check if a given size is a double and larger than 0
     *
     * @param string the given size
     * @throws FilterException //TODO
     */
    public double SizeFilter(String string) throws FilterException {
        try {
            double bound = Double.parseDouble(string);
            if (bound < 0) {
                throw new FilterException();
            }
            return bound;
        } catch (NumberFormatException e) {
            throw new FilterException();
        }
    }
}
