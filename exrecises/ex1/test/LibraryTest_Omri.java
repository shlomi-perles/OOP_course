import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LibraryTest_Omri {
    private Library library;
    private Book book1;
    private Book book2;
    private Patron patron1;
    private Patron patron2;

    @BeforeEach
    public void setUp() {
        library = new Library(5, 5, 5);
        book1 = new Book("Harry Potter", "JK Rowling", 1996, 10, 9, 8);
        book2 = new Book("Game of Thrones", "George RR Martin", 1996, 10, 9, 8);
        patron1 = new Patron("Ron", "Weasley", 10, 9, 2, 30);
        patron2 = new Patron("Hermione", "Granger", 5, 5, 10, 80);
    }


    /* Tests for addBookToLibrary */

    @Test
    public void testAddBookToLibraryNewValidBook() {
        int bookId = library.addBookToLibrary(book1);
        assertEquals(0, bookId, "Book ID preferably should start at 0");
        assertTrue(bookId >= 0, "Book ID should be non-negative");
    }

    @Test
    public void testAddBookToLibraryNullBook() {
        int bookId = library.addBookToLibrary(null);
        assertEquals(0, bookId, "Book ID preferably should start at 0");
        assertTrue(bookId >= 0, "Book ID should be non-negative");
    }


    @Test
    public void testAddBookToLibraryBookExists() {
        int expectedBookId = library.addBookToLibrary(book1);
        int bookId = library.addBookToLibrary(book1);
        assertEquals(expectedBookId, bookId);
    }

    @Test
    public void testAddBookToLibraryBooksCapacityExceeded() {
        Library lib = new Library(1, 5, 5);
        lib.addBookToLibrary(book1);
        int bookId = lib.addBookToLibrary(book2);
        assertTrue(bookId < 0, "Exceeding th maximal number of books should result in a negative ID");
    }

    @Test
    public void testAddBookToLibraryBooksCapacityExceededBookExists() {
        Library lib = new Library(1, 5, 5);
        int expectedBookId = lib.addBookToLibrary(book1);
        int bookId = lib.addBookToLibrary(book1);
        assertEquals(expectedBookId, bookId);
    }

    /* Tests for registerPatronToLibrary */

    @Test
    public void testRegisterPatronToLibraryValidPatron() {
        int patronId = library.registerPatronToLibrary(patron1);
        assertEquals(0, patronId, "Patron ID preferably should start at 0");
        assertTrue(patronId >= 0, "Patron ID should be non-negative");
    }

    @Test
    public void testRegisterPatronToLibraryNullPatron() {
        int patronId = library.registerPatronToLibrary(null);
        assertEquals( 0, patronId,"Patron ID preferably should start at 0");
        assertTrue(patronId >= 0,"Patron ID should be non-negative");
    }

    @Test
    public void testRegisterPatronToLibraryPatronExists() {
        int expectedPatronId = library.registerPatronToLibrary(patron1);
        int patronId = library.registerPatronToLibrary(patron1);
        assertEquals(expectedPatronId, patronId);
    }

    @Test
    public void testRegisterPatronToLibraryCapacityExceeded() {
        Library lib = new Library(5, 5, 1);
        lib.registerPatronToLibrary(patron1);
        int patronId = lib.registerPatronToLibrary(patron2);
        assertTrue( patronId < 0,"Exceeding th maximal number of books should result in a negative ID");
    }

    @Test
    public void testRegisterPatronToLibraryCapacityExceededPatronExists() {
        Library lib = new Library(5, 5, 1);
        int expectedPatronId = lib.registerPatronToLibrary(patron1);
        int patronId = lib.registerPatronToLibrary(patron1);
        assertEquals(expectedPatronId, patronId);
    }

    /* Tests for isBookIdValid */

    @Test
    public void testIsBookIdValidValidBookId() {
        int bookId = library.addBookToLibrary(book1);
        assertTrue(library.isBookIdValid(bookId));
    }


    @Test
    public void testIsBookIdValidNegativeBookId() {
        library.addBookToLibrary(book1);
        assertFalse(library.isBookIdValid(-7));
    }

    @Test
    public void testIsBookIdValidNoBooksInLibrary() {
        assertFalse(library.isBookIdValid(0));
    }

    @Test
    public void testIsBookIdValidInvalidBookId() {
        int bookId = library.addBookToLibrary(book1);

        if (bookId != 3) {
            assertFalse(library.isBookIdValid(3));
        } else {
            assertFalse(library.isBookIdValid(0));
        }
    }

    @Test
    public void testIsBookIdValidMoreThanMax() {
        Library lib = new Library(1, 5, 5);
        lib.addBookToLibrary(book1);
        assertFalse(lib.isBookIdValid(2));
    }


    /* Tests for isPatronIdValid */

    @Test
    public void testIsPatronIdValidValidBookId() {
        int patronId = library.registerPatronToLibrary(patron1);
        assertTrue(library.isPatronIdValid(patronId));
    }


    @Test
    public void testIsPatronIdValidNegativeBookId() {
        library.registerPatronToLibrary(patron1);
        assertFalse(library.isPatronIdValid(-7));
    }

    @Test
    public void testIsPatronIdValidNoBooksInLibrary() {
        assertFalse(library.isPatronIdValid(0));
    }

    @Test
    public void testIsPatronIdValidInvalidBookId() {
        int patronId = library.registerPatronToLibrary(patron1);

        if (patronId != 3) {
            assertFalse(library.isPatronIdValid(3));
        } else {
            assertFalse(library.isPatronIdValid(0));
        }
    }

    @Test
    public void testIsPatronIdValidMoreThanMax() {
        Library lib = new Library(1, 5, 5);
        lib.registerPatronToLibrary(patron1);
        assertFalse(lib.isPatronIdValid(2));
    }

    /* Tests for getBookId */

    @Test
    public void testGetBookIdValidBook() {
        Library lib = new Library(1, 5, 5);
        int expectedBookId = lib.addBookToLibrary(book1);
        int actualBookId = lib.getBookId(book1);
        assertEquals(expectedBookId, actualBookId);
    }

    @Test
    public void testGetBookIdNonexistentBook() {
        Library lib = new Library(1, 5, 5);
        lib.addBookToLibrary(book1);
        int actualBookId = lib.getBookId(book2);
        assertEquals(-1, actualBookId);
    }

    /* Tests for getPatronId */

    @Test
    public void testGetPatronIdValidPatron() {
        Library lib = new Library(1, 5, 5);
        int expectedId = lib.registerPatronToLibrary(patron1);
        int actualId = lib.getPatronId(patron1);
        assertEquals(expectedId, actualId);
    }

    @Test
    public void testGetPatronIdNonexistentPatron() {
        Library lib = new Library(1, 5, 5);
        lib.registerPatronToLibrary(patron1);
        int actualId = lib.getPatronId(patron2);
        assertEquals(-1, actualId);
    }

    /* Tests for borrowBook */

    @Test
    public void testBorrowBookNonexistentBook() {
        Library lib = new Library(1, 5, 5);
        int patronId = lib.registerPatronToLibrary(patron1);
        assertFalse(lib.borrowBook(0, patronId));
    }

    @Test
    public void testBorrowBookNonexistentPatron() {
        Library lib = new Library(1, 5, 5);
        int bookId = lib.addBookToLibrary(book1);
        assertFalse(lib.borrowBook(bookId, 0));
    }

    @Test
    public void testBorrowBookUnavailableBook() {
        Library lib = new Library(1, 5, 5);
        int patronId = lib.registerPatronToLibrary(patron1);
        int bookId = lib.addBookToLibrary(book1);
        book1.setBorrowerId(0);
        assertFalse(lib.borrowBook(bookId, patronId));
    }

    @Test
    public void testBorrowBookValidBook() {
        Library lib = new Library(1, 5, 5);
        int patronId = lib.registerPatronToLibrary(patron1);
        int bookId = lib.addBookToLibrary(book1);
        assertTrue(lib.borrowBook(bookId, patronId));
    }

    @Test
    public void testBorrowBookPatronReachedLimit() {
        Library lib = new Library(1, 1, 5);
        int patronId = lib.registerPatronToLibrary(patron1);
        int bookId = lib.addBookToLibrary(book1);
        int bookId2 = lib.addBookToLibrary(book2);
        lib.borrowBook(bookId, patronId);
        assertFalse(lib.borrowBook(bookId2, patronId));
    }

    @Test
    public void testBorrowBookPatronWillNotEnjoy() {
        Library lib = new Library(1, 1, 5);
        int patronId = lib.registerPatronToLibrary(patron2);
        int bookId = library.addBookToLibrary(new Book("The Alchemist", "Paulo Coelho", 1998, 1, 2, 1));
        lib.borrowBook(bookId, patronId);
        assertFalse(lib.borrowBook(bookId, patronId));
    }

    /* Tests for returnBook and isBookAvailable */

    @Test
    public void testReturnBook() {
        Library lib = new Library(1, 1, 5);
        int patronId = lib.registerPatronToLibrary(patron1);
        int bookId = lib.addBookToLibrary(book1);
        lib.borrowBook(bookId, patronId);
        lib.returnBook(bookId);
        assertTrue(lib.isBookAvailable(bookId));
    }

    @Test
    public void testReturnBookAvailableBook() {
        Library lib = new Library(1, 1, 5);
        int patronId = lib.registerPatronToLibrary(patron1);
        int bookId = lib.addBookToLibrary(book1);
        lib.returnBook(bookId);
        assertTrue(lib.isBookAvailable(bookId));
    }

    @Test
    public void testIsAvailableBorrowedBook() {
        Library lib = new Library(1, 1, 5);
        int patronId = lib.registerPatronToLibrary(patron1);
        int bookId = lib.addBookToLibrary(book1);
        lib.borrowBook(bookId, patronId);
        assertFalse(lib.isBookAvailable(bookId));
    }

    @Test
    public void testIsAvailableNewBook() {
        Library lib = new Library(1, 1, 5);
        int patronId = lib.registerPatronToLibrary(patron1);
        int bookId = lib.addBookToLibrary(book1);
        assertTrue(lib.isBookAvailable(bookId));
    }

    /* Tests for suggestBookToPatron */

    @Test
    public void testSuggestBookToPatronNoBooks() {
        int patronId = library.registerPatronToLibrary(patron1);
        Book suggestedBook = library.suggestBookToPatron(patronId);
        assertNull(suggestedBook);
    }

    @Test
    public void testSuggestBookToPatronNoBooksAboveThreshold() {
        int patronId = library.registerPatronToLibrary(patron2);
        library.addBookToLibrary(new Book("The Kite Runner", "Khaled Hosseini", 2003, 10, 1, 1));
        library.addBookToLibrary(new Book("The Alchemist", "Paulo Coelho", 1998, 1, 2, 1));
        Book suggestedBook = library.suggestBookToPatron(patronId);
        assertNull(suggestedBook);
    }

    @Test
    public void testSuggestBookToPatronNoAvailableBooks() {
        int patronId = library.registerPatronToLibrary(patron1);
        int patronId2 = library.registerPatronToLibrary(patron2);
        int bookId1 = library.addBookToLibrary(book1);
        int bookId2 = library.addBookToLibrary(book2);
        library.borrowBook(bookId1, patronId2);
        library.borrowBook(bookId2, patronId2);
        Book suggestedBook = library.suggestBookToPatron(patronId);
        assertNull(suggestedBook);
    }

    @Test
    public void testSuggestBookToPatronNoAvailableBooksByPatron() {
        int patronId = library.registerPatronToLibrary(patron1);
        int bookId1 = library.addBookToLibrary(book1);
        int bookId2 = library.addBookToLibrary(book2);
        library.borrowBook(bookId1, patronId);
        library.borrowBook(bookId2, patronId);
        Book suggestedBook = library.suggestBookToPatron(patronId);
        assertNull(suggestedBook);
    }

    @Test
    public void testSuggestBookToPatronValid() {
        int patronId = library.registerPatronToLibrary(patron1);
        int bookId1 = library.addBookToLibrary(book1);
        int bookId2 = library.addBookToLibrary(book2);
        Book suggestedBook = library.suggestBookToPatron(patronId);
        assertEquals(bookId1, library.getBookId(suggestedBook));
    }
}
