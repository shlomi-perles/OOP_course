import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class BookTest_Omri {

    @Test
    public void testGetLiteraryValueValidValue() {
        Book book = new Book("Harry Potter", "JK Rowling", 1996, 10,9,8);
        int value = book.getLiteraryValue();
        assertEquals(27, value);
    }

    @Test
    public void testGetCurrentBorrowerIdNoBorrower() {
        Book book = new Book("Harry Potter", "JK Rowling", 1996, 10,9,8);
        int borrowerId = book.getCurrentBorrowerId();
        assertEquals(-1, borrowerId);
    }

    @Test
    public void testSetCurrentBorrowerIdValidBorrower() {
        Book book = new Book("Harry Potter", "JK Rowling", 1996, 10,9,8);
        int givenBorrowerId = 7;
        book.setBorrowerId(givenBorrowerId);
        int borrowerId = book.getCurrentBorrowerId();
        assertEquals(givenBorrowerId, borrowerId);
    }

    @Test
    public void testReturnBook() {
        Book book = new Book("Harry Potter", "JK Rowling", 1996, 10,9,8);
        int givenBorrowerId = 7;
        book.setBorrowerId(givenBorrowerId);
        book.returnBook();
        int borrowerId = book.getCurrentBorrowerId();
        assertEquals(-1, borrowerId);
    }
}
