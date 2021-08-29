package filesprocessing.order;

import java.io.File;
import java.util.Comparator;

/**
 * factory class for orders
 */
public class OrderFactory {

    /**
     * All types of order
     */
    private final static String ABS = "abs", TYPE = "type", SIZE = "size";

    /**
     * create comparator according to the order
     * @param order string with the correct order
     * @return The correct comparator for this order
     */
    public static Comparator<File> createComparator(String order) {
        switch (order) {
            case SIZE:
                return new SizeOrder();
            case TYPE:
                return new TypeOrder();
            case ABS:
                return new AbsOrder();
        }
        return new AbsOrder();
    }
}
