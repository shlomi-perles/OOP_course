import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {

    @Test
    void getLiteraryValue() {
        final int size = 100;
        Random r = new Random();

        for (int i = 0; i < size; i++) {
            int[] list = new int[3];
            for (int j = 0; j < 3; j++) {
                list[j] = r.nextInt(Integer.MAX_VALUE / 3);
            }

            Book b = new Book("A", "B", 300,
                    list[0], list[1], list[2]);

            assertEquals(list[0] + list[1] + list[2], b.getLiteraryValue());
        }
    }

    @Test
    void simpleBorrowerIDTest() {
        Book b = new Book("A", "B", 300,
                1, 1, 1);
        assertTrue(b.getCurrentBorrowerId() < 0);

        Random r = new Random();
        int borrowerId = r.nextInt(Integer.MAX_VALUE);
        b.setBorrowerId(borrowerId);

        assertEquals(borrowerId, b.getCurrentBorrowerId());
    }

    @Test
    void setGetBorrowerId() {
        Book b = new Book("A", "B", 300,
                1, 1, 1);
        assertTrue(b.getCurrentBorrowerId() < 0);

        Random r = new Random();

        for (int i = 0; i < 1000; i++) {
            int j = r.nextInt(Integer.MAX_VALUE);
            b.setBorrowerId(j);
            assertEquals(j, b.getCurrentBorrowerId());
        }

        b.returnBook();
        assertTrue(b.getCurrentBorrowerId() < 0);
    }

    @Test
    void returnBook() {
        Book b = new Book("A", "B", 300,
                1, 1, 1);
        b.setBorrowerId(42);
        b.returnBook();
        assertEquals(-1, b.getCurrentBorrowerId());
    }
}