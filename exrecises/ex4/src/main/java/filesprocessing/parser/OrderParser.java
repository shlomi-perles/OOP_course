package filesprocessing.parser;

import filesprocessing.order.OrderFactory;
import filesprocessing.Section;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;

public class OrderParser {
    /**
     * legal reverse string
     */
    private static final String REVERSE = "REVERSE";
    private static final String[] TYPES_POOL = {"abs", "type", "size"};


    /**
     * index of order type in line
     */
    private static final int ORDER_INDEX = 0;

    /**
     * index of reverse in line
     */
    private static final int REVERSE_INDEX = 1;

    /**
     * args of the order line
     */
    private String[] lineArray;

    /**
     * constructor for OrderParser
     *
     * @param line the line we need to parse
     */
    public OrderParser(String line) {
        this.lineArray = line.split(CommandFileParser.LINE_SEPARATOR);
    }

    public Comparator<File> parse(Section curSection) throws OrderException {
        String order;
        try {
            order = lineArray[ORDER_INDEX];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new OrderException();
        }

        if (!Arrays.asList(TYPES_POOL).contains(order)) throw new OrderException();

        curSection.setReverseOrder(checkRevers());
        return OrderFactory.createComparator(lineArray[ORDER_INDEX]);

    }

    /**
     * if only one argument - its the order type. else - check if its legal reverse
     * @return false if not revers, true otherwise
     * @throws OrderException if not legal string
     */
    private boolean checkRevers() throws OrderException {
        if (lineArray.length == 1) {
            return false;
        } else if (lineArray[REVERSE_INDEX].equals(REVERSE)) {
            return true;
        }
        throw new OrderException();
    }


}
