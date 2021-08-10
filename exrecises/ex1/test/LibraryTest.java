import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class LibraryTest {
    /* -----=  Library <--> Book Tests  =----- */

    @Test
    void addBookToLibrary() {
        AddBooksAllAvailable();
        AddBooksOverflow();
        AddBookAlreadyInLibrary();
    }

    private void AddBooksAllAvailable() {
        int bookCap = 10000;
        Library lib = new Library(bookCap, 0, 0);
        Book[] books = new Book[bookCap];
        for (int i = 0; i < bookCap; i++) {
            books[i] = new Book("A", "B",
                    0, 0, 0, 0);
            assertTrue(lib.addBookToLibrary(books[i]) >= 0);
        }
    }

    private void AddBooksOverflow() {
        int bookCap = 10000;
        Library lib = new Library(bookCap, 0, 0);
        Book[] books = new Book[bookCap + 1];

        for (int i = 0; i < bookCap; i++) {
            books[i] = new Book("A", "B",
                    0, 0, 0, 0);
            assertTrue(lib.addBookToLibrary(books[i]) >= 0);
        }

        books[bookCap - 1] = new Book("A", "B",
                0, 0, 0, 0);
        assertTrue(lib.addBookToLibrary(books[bookCap - 1]) < 0, "Books limit exceeded!");
    }

    private void AddBookAlreadyInLibrary() {
        int bookCap = 10000;
        Library lib = new Library(bookCap, 0, 0);
        Book[] books = new Book[bookCap];
        int[] ids = new int[bookCap];

        for (int i = 0; i < bookCap; i++) {
            books[i] = new Book("A", "B",
                    0, 0, 0, 0);
            ids[i] = lib.addBookToLibrary(books[i]);
            assertTrue(ids[i] >= 0);
            assertEquals(ids[i], lib.addBookToLibrary(books[i]),
                    "tried to add a book that is already in library. should return that book's ID.");
        }
    }

    @Test
    void isBookIdValid() {
        int bookCap = 10000;
        Library lib = new Library(bookCap, 0, 0);
        Book[] books = new Book[bookCap];
        int[] ids = new int[bookCap];

        for (int i = 0; i < bookCap; i++) {
            books[i] = new Book("A", "B",
                    0, 0, 0, 0);
            ids[i] = lib.addBookToLibrary(books[i]);
            assertTrue(ids[i] >= 0);
            assertTrue(lib.isBookIdValid(ids[i]));
        }

        Book bookNotValid = new Book("A", "B",
                0, 0, 0, 0);
        // try to add the book to library (should fail due to overflow) and then check validity
        int id = lib.addBookToLibrary(bookNotValid);
        assertFalse(lib.isBookIdValid(id), "This book is not in the library!");

        // traverse through all the books again and check validity
        for (int i = 0; i < bookCap; i++) {
            assertTrue(lib.isBookIdValid(ids[i]), "Second traverse, all books still valid");
        }
    }

    @Test
    void getBookId() {
        int bookCap = 10000;
        Library lib = new Library(bookCap, 0, 0);
        Book[] books = new Book[bookCap];
        int[] ids = new int[bookCap];

        for (int i = 0; i < bookCap; i++) {
            books[i] = new Book("A", "B",
                    0, 0, 0, 0);
            ids[i] = lib.addBookToLibrary(books[i]);
            assertTrue(ids[i] >= 0);
            assertEquals(lib.getBookId(books[i]), ids[i]);
        }

        Book bookNotValid = new Book("A", "B",
                0, 0, 0, 0);
        // try to add the book to library (should fail due to overflow) and then check that book's ID
        lib.addBookToLibrary(bookNotValid);
        assertEquals(-1, lib.getBookId(bookNotValid));

        // traverse through all the books again and check validity
        for (int i = 0; i < bookCap; i++) {
            assertEquals(ids[i], lib.getBookId(books[i]),
                    "Second traverse, all books still valid");
        }
    }

    /* -----=  Library <--> Patron Tests  =----- */

    @Test
    void registerPatronToLibrary() {
        AddPatronsAllAvailable();
        AddPatronsOverflow();
        AddPatronsAlreadyRegistered();
    }

    private void AddPatronsAllAvailable() {
        int patronCap = 10000;
        Library lib = new Library(0, 0, patronCap);
        Patron[] patrons = new Patron[patronCap];
        for (int i = 0; i < patronCap; i++) {
            patrons[i] = new Patron("John", "Doe", 0, 0, 0, 0);
            assertTrue(lib.registerPatronToLibrary(patrons[i]) >= 0);
        }
    }

    private void AddPatronsOverflow() {
        int patronCap = 10000;
        Library lib = new Library(0, 0, patronCap);
        Patron[] patrons = new Patron[patronCap + 1];

        for (int i = 0; i < patronCap; i++) {
            patrons[i] = new Patron("John", "Doe",
                    0, 0, 0, 0);
            assertTrue(lib.registerPatronToLibrary(patrons[i]) >= 0);
        }

        patrons[patronCap - 1] = new Patron("John", "Doe",
                0, 0, 0, 0);
        assertTrue(lib.registerPatronToLibrary(patrons[patronCap - 1]) < 0, "Patrons limit exceeded!");
    }

    private void AddPatronsAlreadyRegistered() {
        int patronCap = 10000;
        Library lib = new Library(0, 0, patronCap);
        Patron[] patrons = new Patron[patronCap];
        int[] patron_ids = new int[patronCap];
        for (int i = 0; i < patronCap; i++) {
            patrons[i] = new Patron("John", "Doe", 0, 0, 0, 0);
            patron_ids[i] = lib.registerPatronToLibrary(patrons[i]);
            assertTrue(patron_ids[i] >= 0);
            assertEquals(patron_ids[i], lib.registerPatronToLibrary(patrons[i]), "This patron is already registered!");
        }
    }

    @Test
    void isPatronIdValid() {
        int patronCap = 10000;
        Library lib = new Library(0, 0, patronCap);
        Patron[] patrons = new Patron[patronCap];
        int[] patron_ids = new int[patronCap];

        for (int i = 0; i < patronCap; i++) {
            patrons[i] = new Patron("John", "Doe", 0, 0, 0, 0);
            patron_ids[i] = lib.registerPatronToLibrary(patrons[i]);
            assertTrue(patron_ids[i] >= 0);
            assertTrue(lib.isPatronIdValid(patron_ids[i]));
        }

        Patron patronNotValid = new Patron("John", "Doe", 0, 0, 0, 0);
        // try to register the patron to library (should fail due to overflow) and then check validity
        int id = lib.registerPatronToLibrary(patronNotValid);
        assertFalse(lib.isPatronIdValid(id), "This patron is not registered to the library!");

        // traverse through all the patrons again and check validity
        for (int i = 0; i < patronCap; i++) {
            assertTrue(lib.isPatronIdValid(patron_ids[i]), "Second traverse, all patrons still valid");
        }
    }

    @Test
    void getPatronId() {
        int patronCap = 10000;
        Library lib = new Library(0, 0, patronCap);
        Patron[] patrons = new Patron[patronCap];
        int[] patron_ids = new int[patronCap];

        for (int i = 0; i < patronCap; i++) {
            patrons[i] = new Patron("John", "Doe", 0, 0, 0, 0);
            patron_ids[i] = lib.registerPatronToLibrary(patrons[i]);
            assertTrue(patron_ids[i] >= 0);
            assertEquals(patron_ids[i], lib.getPatronId(patrons[i]), "getPatronId() Failed.\nThe Patron has just registered to the library");
        }

        Patron patronNotValid = new Patron("John", "Doe", 0, 0, 0, 0);

        // try to register the patron to library (should fail due to overflow) and then check validity
        int id = lib.registerPatronToLibrary(patronNotValid);
        assertEquals(-1, lib.getPatronId(patronNotValid), "This patron is not registered to the library!");

        // traverse through all the books again and check validity
        for (int i = 0; i < patronCap; i++) {
            assertEquals(patron_ids[i], lib.getPatronId(patrons[i]),
                    "Second traverse, all patrons still valid");
        }
    }

    @Test
    void borrowBook() {
        borrowSimple();
        borrowBookInvalid();
        borrowBookUnavailable();
        borrowPatronInvalid();
        borrowMaxBorrowedBooksExceeded();
        borrowPatronNotEnjoy();
    }

    private void borrowSimple() {
        int maxPatrons = 1000, maxBorrowedPerPatron = 10;
        int bookCap = maxPatrons * maxBorrowedPerPatron;
        Library lib = new Library(bookCap, maxBorrowedPerPatron, maxPatrons);

        Book[] books = new Book[bookCap];
        int[] books_ids = new int[bookCap];

        Patron[] patrons = new Patron[maxPatrons];
        int[] patrons_ids = new int[maxPatrons];


        // generate patrons
        for (int i = 0; i < maxPatrons; i++) {
            patrons[i] = new Patron("John", "Doe",
                    1, 1, 1, 0);
            patrons_ids[i] = lib.registerPatronToLibrary(patrons[i]);
        }

        // generate books
        for (int i = 0; i < bookCap; i++) {
            books[i] = new Book("A", "B",
                    0, 0, 0, 0);
            books_ids[i] = lib.addBookToLibrary(books[i]);
        }

        // borrowing the books
        for (int i = 0; i < maxPatrons; i++) {
            for (int j = 0; j < maxBorrowedPerPatron; j++) {
                assertTrue(lib.borrowBook(books_ids[i * maxBorrowedPerPatron + j], patrons_ids[i]));
            }
        }
    }

    private void borrowBookInvalid() {
        int maxPatrons = 1000, maxBorrowedPerPatron = 10;
        int bookCap = maxPatrons * maxBorrowedPerPatron;
        Library lib = new Library(bookCap, maxBorrowedPerPatron, maxPatrons);

        Patron p = new Patron("John", "Doe", 0, 0, 0, 0);
        int patron_id = lib.registerPatronToLibrary(p);
        for (int i = Integer.MIN_VALUE; i < Integer.MAX_VALUE; i++) {
            assertFalse(lib.borrowBook(i, patron_id));
        }
    }

    private void borrowBookUnavailable() {
        int maxPatrons = 1000, maxBorrowedPerPatron = 10;
        int bookCap = maxPatrons * maxBorrowedPerPatron;
        Library lib = new Library(bookCap, maxBorrowedPerPatron, maxPatrons);

        Book book = new Book("A", "B",
                0, 0, 0, 0);
        int book_id = lib.addBookToLibrary(book);

        Patron patron_1 = new Patron("John", "Doe",
                1, 1, 1, 0);
        Patron patron_2 = new Patron("John", "Doe2",
                1, 1, 1, 0);
        int patron_id_1 = lib.registerPatronToLibrary(patron_1);
        int patron_id_2 = lib.registerPatronToLibrary(patron_2);

        assertTrue(lib.borrowBook(book_id, patron_id_1), "Borrow should succeed");
        assertFalse(lib.borrowBook(book_id, patron_id_2), "Borrow should fail, book unavailable");
    }

    private void borrowPatronInvalid() {
        int maxPatrons = 1000, maxBorrowedPerPatron = 10;
        int bookCap = maxPatrons * maxBorrowedPerPatron;
        Library lib = new Library(bookCap, maxBorrowedPerPatron, maxPatrons);

        Book book = new Book("A", "B",
                0, 0, 0, 0);
        int book_id = lib.addBookToLibrary(book);

        for (int i = Integer.MIN_VALUE; i < Integer.MAX_VALUE; i++) {
            assertFalse(lib.borrowBook(book_id, i), "Borrow should fail, patron unregistered");
        }
    }

    private void borrowMaxBorrowedBooksExceeded() {
        int maxPatrons = 1000, maxBorrowedPerPatron = 10;
        int bookCap = maxPatrons * maxBorrowedPerPatron;
        Library lib = new Library(bookCap, maxBorrowedPerPatron, maxPatrons);

        Book[] books = new Book[bookCap];
        int[] books_ids = new int[bookCap];

        Patron[] patrons = new Patron[maxPatrons];
        int[] patrons_ids = new int[maxPatrons];


        // generate patrons
        for (int i = 0; i < maxPatrons; i++) {
            patrons[i] = new Patron("John", "Doe",
                    1, 1, 1, 0);
            patrons_ids[i] = lib.registerPatronToLibrary(patrons[i]);
        }

        // generate books
        for (int i = 0; i < bookCap; i++) {
            books[i] = new Book("A", "B",
                    0, 0, 0, 0);
            books_ids[i] = lib.addBookToLibrary(books[i]);
        }

        // borrowing the books
        for (int j = 0; j < maxBorrowedPerPatron; j++) {
            assertTrue(lib.borrowBook(books_ids[j], patrons_ids[0]));
        }
        assertFalse(lib.borrowBook(books_ids[maxBorrowedPerPatron], patrons_ids[0]),
                "Patron has borrowed too many books");

    }

    private void borrowPatronNotEnjoy() {
        int maxPatrons = 1000, maxBorrowedPerPatron = 10;
        int bookCap = maxPatrons * maxBorrowedPerPatron;
        Library lib = new Library(bookCap, maxBorrowedPerPatron, maxPatrons);

        Book[] books = new Book[bookCap];
        int[] books_ids = new int[bookCap];

        Patron[] patrons = new Patron[maxPatrons];
        int[] patrons_ids = new int[maxPatrons];


        // generate patrons
        for (int i = 0; i < maxPatrons; i++) {
            patrons[i] = new Patron("John", "Doe",
                    1, 1, 1, bookCap / 2);
            patrons_ids[i] = lib.registerPatronToLibrary(patrons[i]);
        }

        // generate books
        for (int i = 0; i < bookCap; i++) {
            books[i] = new Book("A", "B",
                    0, i, 0, 0);
            books_ids[i] = lib.addBookToLibrary(books[i]);
        }

        // borrowing the books
        for (int i = 0; i < maxPatrons; i++) {
            for (int j = 0; j < maxBorrowedPerPatron; j++) {
                if ((i * maxBorrowedPerPatron + j) < (bookCap / 2)) {
                    assertFalse(lib.borrowBook(books_ids[i * maxBorrowedPerPatron + j], patrons_ids[i]));
                } else {
                    assertTrue(lib.borrowBook(books_ids[i * maxBorrowedPerPatron + j], patrons_ids[i]));
                }
            }
        }
    }

    @Test
    void returnBook() {
        int maxPatrons = 1000, maxBorrowedPerPatron = 10;
        int bookCap = maxPatrons * maxBorrowedPerPatron;
        Library lib = new Library(bookCap, maxBorrowedPerPatron, maxPatrons);

        Book book = new Book("A", "B",
                0, 0, 0, 0);
        int book_id = lib.addBookToLibrary(book);

        Patron patron_1 = new Patron("John", "Doe",
                1, 1, 1, 0);
        Patron patron_2 = new Patron("John", "Doe2",
                1, 1, 1, 0);
        int patron_id_1 = lib.registerPatronToLibrary(patron_1);
        int patron_id_2 = lib.registerPatronToLibrary(patron_2);

        //return an invalid book, should do nothing
        lib.returnBook(-1);

        //return a book that is already available, should do nothing
        lib.returnBook(book_id);

        assertTrue(lib.borrowBook(book_id, patron_id_1), "Borrow should succeed");
        lib.returnBook(book_id);
        assertTrue(lib.borrowBook(book_id, patron_id_2), "Borrow should succeed, the book has returned");
    }

    @Test
    void isBookAvailable() {
        AllBooksAvailable();
        BooksNotInLib();
        BooksAreBorrowed();
    }

    private void AllBooksAvailable() {
        int bookCap = 10000;
        Library lib = new Library(bookCap, 10, 1000);
        Book[] books = new Book[bookCap];
        int[] ids = new int[bookCap];

        for (int i = 0; i < bookCap; i++) {
            books[i] = new Book("A", "B",
                    0, 0, 0, 0);
            ids[i] = lib.addBookToLibrary(books[i]);
            assertTrue(lib.isBookAvailable(ids[i]));
        }
    }

    private void BooksNotInLib() {
        int bookCap = 10000;
        Library lib = new Library(bookCap, 10, 1000);
        Book[] books = new Book[bookCap];
        int[] ids = new int[bookCap];

        for (int i = 0; i < bookCap; i++) {
            books[i] = new Book("A", "B",
                    0, 0, 0, 0);
            ids[i] = lib.addBookToLibrary(books[i]);
            assertTrue(lib.isBookAvailable(ids[i]));
        }

        Book bookNotValid = new Book("A", "B",
                0, 0, 0, 0);
        // try to add the book to library (should fail due to overflow) and then check if that book is available
        int id = lib.addBookToLibrary(bookNotValid);
        assertFalse(lib.isBookAvailable(id));

        // searching some random ids
        Random r = new Random();
        for (int i = 0; i < bookCap; i++) {
            int j = r.nextInt();
            for (int k : ids) {
                if (j == k) {
                    assertTrue(lib.isBookAvailable(j));
                }
            }
            assertFalse(lib.isBookAvailable(j));
        }
    }

    /**
     * for this test to work, make sure that maxPatrons * maxBorrowedPerPatron = bookCap
     */
    private void BooksAreBorrowed() {
        int maxPatrons = 1000, maxBorrowedPerPatron = 10;
        int bookCap = maxPatrons * maxBorrowedPerPatron;
        Library lib = new Library(bookCap, maxBorrowedPerPatron, maxPatrons);

        Book[] books = new Book[bookCap];
        int[] books_ids = new int[bookCap];

        Patron[] patrons = new Patron[maxPatrons];
        int[] patrons_ids = new int[maxPatrons];


        // generate patrons
        for (int i = 0; i < maxPatrons; i++) {
            patrons[i] = new Patron("John", "Doe",
                    1, 1, 1, 0);
            patrons_ids[i] = lib.registerPatronToLibrary(patrons[i]);
        }

        // generate books
        for (int i = 0; i < bookCap; i++) {
            books[i] = new Book("A", "B",
                    0, 0, 0, 0);
            books_ids[i] = lib.addBookToLibrary(books[i]);
            assertTrue(lib.isBookAvailable(books_ids[i]));
        }

        // borrowing ALL the books
        for (int i = 0; i < maxPatrons; i++) {
            for (int j = 0; j < maxBorrowedPerPatron; j++) {
                lib.borrowBook(books_ids[i * maxBorrowedPerPatron + j], patrons_ids[i]);
            }
        }

        // makes sure that all the books are NOT available, and then return the books, and check availability
        for (int i = 0; i < bookCap; i++) {
            String unavailableMsg = String.format("Iteration #%d. This book is not available!" +
                    "Borrower #%d already borrowing it," +
                    "or this book is not in the library.", i, books[i].getCurrentBorrowerId());
            assertFalse(lib.isBookAvailable(books_ids[i]), unavailableMsg);
            lib.returnBook(books_ids[i]);
            assertTrue(lib.isBookAvailable(books_ids[i]), "This book has just returned to the library!\nHence, it's available.");
        }
    }

    @Test
    void suggestBookToPatron() {
        suggestSimple();
        suggestNoAvailableBooks();
        suggestPatronDislikeAllBooks();
    }

    private void suggestSimple() {
        int maxPatrons = 1000, maxBorrowedPerPatron = 10;
        int bookCap = maxPatrons * maxBorrowedPerPatron;
        Library lib = new Library(bookCap, maxBorrowedPerPatron, maxPatrons);

        Book[] books = new Book[bookCap];
        int[] books_ids = new int[bookCap];

        Patron p = new Patron("John", "Doe",
                1, 1, 1, 0);
        int patron_id = lib.registerPatronToLibrary(p);


        // generate books
        for (int i = 0; i < bookCap; i++) {
            books[i] = new Book("A", "B",
                    0, i, 0, 0);
            books_ids[i] = lib.addBookToLibrary(books[i]);
        }

        assertEquals(books[books.length - 1], lib.suggestBookToPatron(patron_id));
    }

    private void suggestNoAvailableBooks() {
        int maxPatrons = 1000, maxBorrowedPerPatron = 10;
        int bookCap = maxPatrons * maxBorrowedPerPatron;
        Library lib = new Library(bookCap, maxBorrowedPerPatron, maxPatrons);

        Patron p = new Patron("John", "Doe",
                1, 1, 1, 0);
        int patron_id = lib.registerPatronToLibrary(p);

        assertNull(lib.suggestBookToPatron(patron_id));
    }

    private void suggestPatronDislikeAllBooks() {
        int maxPatrons = 1000, maxBorrowedPerPatron = 10;
        int bookCap = maxPatrons * maxBorrowedPerPatron;
        Library lib = new Library(bookCap, maxBorrowedPerPatron, maxPatrons);

        Book[] books = new Book[bookCap];
        int[] books_ids = new int[bookCap];

        Patron p = new Patron("John", "Doe",
                1, 1, 1, bookCap + 1);
        int patron_id = lib.registerPatronToLibrary(p);


        // generate books
        for (int i = 0; i < bookCap; i++) {
            books[i] = new Book("A", "B",
                    0, i, 0, 0);
            books_ids[i] = lib.addBookToLibrary(books[i]);
        }

        assertNull(lib.suggestBookToPatron(patron_id));
    }
}