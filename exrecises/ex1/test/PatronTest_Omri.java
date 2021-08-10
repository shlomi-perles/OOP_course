import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PatronTest_Omri {

    @Test
    public void testGetBookScore() {
        Patron patron = new Patron("Ron", "Weasley", 10, 9, 2, 30);
        Book book = new Book("Harry Potter", "JK Rowling", 1996, 10,9,8);
        int score = patron.getBookScore(book);
        assertEquals(197, score);
    }

    @Test
    public void testStringRepresentation() {
        Patron patron = new Patron("Ron", "Weasley", 10, 9, 2, 30);
        String patronStringRepr = patron.stringRepresentation();
        assertEquals(patronStringRepr, "Ron Weasley");
    }

    @Test
    public void testWillEnjoyBookEnjoyableBook() {
        Patron patron = new Patron("Ron", "Weasley", 10, 9, 2, 30);
        Book book = new Book("Harry Potter", "JK Rowling", 1996, 10,9,8);
        boolean willEnjoyBook = patron.willEnjoyBook(book);
        assertTrue(willEnjoyBook);
    }

    @Test
    public void testWillEnjoyBookNonEnjoyableBook() {
        Patron patron = new Patron("Ron", "Weasley", 10, 9, 2, 200);
        Book book = new Book("Harry Potter", "JK Rowling", 1996, 10,9,8);
        boolean willEnjoyBook = patron.willEnjoyBook(book);
        assertFalse(willEnjoyBook);
    }
}
