package filesprocessing.filter;

/**
 * factory class for filters
 */
public class FilterFactory {

    /**
     * All the names of the filters
     */
    private final static String GREATER_THAN = "greater_than", BETWEEN = "between",
            SMALLER_THAN = "smaller_than", FILE = "file", CONTAINS = "contains", PREFIX = "prefix",
            SUFFIX = "suffix", WRITABLE = "writable", EXECUTABLE = "executable", HIDDEN = "hidden",
            ALL = "all";

    /**
     * args index for filters
     */
    private final static int ARG1 = 0,ARG2 = 1;


    /**
     * get arguments and return the correct filter
     * @param filter string of the filter name
     * @param args array of two strings (can be empty) with the necessary arguments for the filter
     * @return a filter
     */
    public static Filter createFilter(String filter, String[] args) {
        switch (filter) {
            case GREATER_THAN:
                return new GreaterThanFilter(Double.parseDouble(args[ARG1]));
            case BETWEEN:
                return new BetweenFilter(Double.parseDouble(args[ARG1]), Double.parseDouble(args[ARG2]));
            case SMALLER_THAN:
                return new SmallerThanFilter(Double.parseDouble(args[ARG1]));
            case FILE:
                return new FileFilter(args[ARG1]);
            case CONTAINS:
                return new ContainsFilter(args[ARG1]);
            case PREFIX:
                return new PrefixFilter(args[ARG1]);
            case SUFFIX:
                return new SuffixFilter(args[ARG1]);
            case WRITABLE:
                return new WritableFilter(Boolean.parseBoolean(args[ARG1]));
            case EXECUTABLE:
                return new ExecutableFilter(Boolean.parseBoolean(args[ARG1]));
            case HIDDEN:
                return new HiddenFilter(Boolean.parseBoolean(args[ARG1]));
            case ALL:
                return new AllFilter();
            default:
                return new AllFilter();
        }
    }
}
