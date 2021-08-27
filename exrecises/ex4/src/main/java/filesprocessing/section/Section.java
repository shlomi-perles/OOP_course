package filesprocessing.section;

import filesprocessing.filter.Filter;
import filesprocessing.order.Order;

import java.util.ArrayList;


/**
 * a class that represents either a filter section of an order section, as given in the command file
 */
public class Section {


    private static final String DEFAULT_ORDER = "abs";
    private static final String DEFAULT_FILTER = "all";

    private boolean negateFilter;
    private boolean reverseOrder;
    private Filter filter;
    private Order order;

    private ArrayList<String> errors; //TODO: final?

    /**
     * initializes the data of a section
     */
    public Section() {
        this.filter = new Filter(); //TODO: default filter
        this.order = new Order(); //TODO: default order
        errors = new ArrayList<String>();
    }

    public Filter getFilter() {
        return filter;
    }

    public Order getOrder() {
        return order;
    }

    public ArrayList<String> getErrors() {
        return errors;
    }

    public void setFilter(Filter filter) {
        this.filter = filter;
    }

    public void setOrder(Order order) {
        this.order = order;
    }


}