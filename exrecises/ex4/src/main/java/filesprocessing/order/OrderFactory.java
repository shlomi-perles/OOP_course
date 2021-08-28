package filesprocessing.order;

import java.io.File;
import java.util.Comparator;

/**
 * factory class for orders
 */
public class OrderFactory {

    private final static String ABS = "abs", TYPE = "type", SIZE = "size";


    public static Comparator<File> createFilter(String order) {
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
