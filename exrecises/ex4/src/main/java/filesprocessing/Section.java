package filesprocessing;

import filesprocessing.QuickSort;
import filesprocessing.filter.Filter;
import filesprocessing.filter.FilterFactory;
import filesprocessing.order.OrderFactory;


import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


/**
 * a class that represents either a filter section of an order section, as given in the command file
 */
public class Section {


    private static final String DEFAULT_ORDER = "abs";
    private static final String DEFAULT_FILTER = "all";

    /**
     * warning message
     */
    private static final String WARNING_MESSAGE = "Warning in line ";

    private boolean negateFilter;
    private boolean reverseOrder;
    private Filter filter;
    private Comparator<File> order;

    private ArrayList<Integer> errors; //TODO: final?

    /**
     * initializes the data of a section
     */
    public Section() {
        filter = FilterFactory.createFilter(DEFAULT_FILTER, new String[]{});
        order = OrderFactory.createComparator(DEFAULT_ORDER);
        errors = new ArrayList<Integer>();
    }

    public Filter getFilter() {
        return filter;
    }

    public Comparator<File> getOrder() {
        return order;
    }

    public ArrayList<Integer> getErrors() {
        return errors;
    }

    public void setFilter(Filter filter) {
        this.filter = filter;
    }

    public void setOrder(Comparator<File> order) {
        this.order = order;
    }

    public void setNegateFilter(boolean negateFilter) {
        this.negateFilter = negateFilter;
    }

    public void setReverseOrder(boolean reverseOrder) {
        this.reverseOrder = reverseOrder;
    }

    public void addLineError(int lineNumber) {
        errors.add(lineNumber);
    }

    /**
     * this method print files bt section rules
     *
     * @param filesList array of files to print
     */
    public void print(ArrayList<File> filesList) {
        for (Integer lineError : errors) {
            System.err.println(WARNING_MESSAGE + lineError);
        }

        QuickSort.sort(filesList, 0, filesList.size() - 1, order);
        if (reverseOrder) Collections.reverse(filesList);

        for (File file : filesList) {
            System.out.println(file.getName());
        }
    }

}