package filesprocessing;

import filesprocessing.QuickSort;
import filesprocessing.filter.Filter;
import filesprocessing.filter.FilterFactory;
import filesprocessing.order.OrderFactory;


import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;


/**
 * a class that represents either a filter section of an order section, as given in the command file
 */
public class Section {


    /**
     * defaults order and filter
     */
    private static final String DEFAULT_ORDER = "abs", DEFAULT_FILTER = "all";

    /**
     * warning message
     */
    private static final String WARNING_MESSAGE = "Warning in line ";

    /**
     * flags indicates if negate filter in this section or reverse order
     */
    private boolean negateFilter, reverseOrder;

    /**
     * filter class
     */
    private Filter filter;

    /**
     * comparator with specific order compare
     */
    private Comparator<File> order;

    /**
     * save all lines error of curren section
     */
    private ArrayList<Integer> errors;

    /**
     * initializes the data of a section
     */
    public Section() {
        filter = FilterFactory.createFilter(DEFAULT_FILTER, new String[]{});
        order = OrderFactory.createComparator(DEFAULT_ORDER);
        errors = new ArrayList<Integer>();
    }

    /**
     * getter for filter
     *
     * @return filter
     */
    public Filter getFilter() {
        return filter;
    }

    /**
     * getter for order comparator
     *
     * @return order comprator
     */
    public Comparator<File> getOrder() {
        return order;
    }

    /**
     * getter for errors array
     *
     * @return array of ints that contains lines number error
     */
    public ArrayList<Integer> getErrors() {
        return errors;
    }

    /**
     * setter for filter
     *
     * @param filter filter to set
     */
    public void setFilter(Filter filter) {
        this.filter = filter;
    }

    /**
     * setter for order
     *
     * @param order order to set
     */
    public void setOrder(Comparator<File> order) {
        this.order = order;
    }

    /**
     * setter for negate filter
     *
     * @param negateFilter boolean we want to set this flag
     */
    public void setNegateFilter(boolean negateFilter) {
        this.negateFilter = negateFilter;
    }

    /**
     * setter for reverse order
     *
     * @param reverseOrder boolean - set true if need reverse order needed
     */
    public void setReverseOrder(boolean reverseOrder) {
        this.reverseOrder = reverseOrder;
    }

    /**
     * Add a line index to error array
     *
     * @param lineNumber int with the index of the error of the section
     */
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

        filesList = filterFiles(filesList);
        QuickSort.sort(filesList, 0, filesList.size() - 1, order);
        if (reverseOrder) Collections.reverse(filesList);

        for (File file : filesList) {
            System.out.println(file.getName());
        }
    }

    /**
     * filter files list according to the current filter
     *
     * @param filesList list with all file in dir
     */
    private ArrayList<File> filterFiles(ArrayList<File> filesList) {
        ArrayList<File> filterFilesList = new ArrayList<File>();
        for (File file : filesList) {
            boolean filterCurFile = filter.filter(file);

            if (filterCurFile && !negateFilter) {
                filterFilesList.add(file);

            } else if (!filterCurFile && negateFilter) {
                filterFilesList.add(file);
            }
        }
        return filterFilesList;
    }

}