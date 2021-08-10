/**
 * This class represents a library, which hold a collection of books. Patrons can register at the library
 * to be able to check out books, if a copy of the requested book is available.
 */
class Library {

    /**
     * The maximal number of books this library can hold.
     */
    int bookCapacity;

    /**
     * The maximal number of books this library allows a single patron to borrow at the same time.
     */
    int maxBorrowedLimit;

    /**
     * The maximal patrons capacity.
     */
    int patronCapacity;

    /**
     * An array of the books currently in the library.
     */
    Book[] libraryBooksArray;

    /**
     * The number of books in the library. initial to 0.
     */
    int numBooksInLibrary;

    /**
     * An array of Patrons that register to the library.
     */
    Patron[] libraryPatronsArray;

    /**
     * The number of patrons currently in the library.
     */
    int numPatronsInLibrary;

    /**
     * cells index represents a patron's id, and inside the cell the number of books he takes.
     */
    int[] numPatronsBooksTaken;

    /* -----=  Constructors  =----- */

    /**
     * Creates a new library with the given parameters.
     *
     * @param maxBookCapacity   The maximal number of books this library can hold.
     * @param maxBorrowedBooks  The maximal number of books this library allows a single patron to borrow
     *                          at the same time.
     * @param maxPatronCapacity The maximal number of registered patrons this library can handle.
     */
    Library(int maxBookCapacity, int maxBorrowedBooks, int maxPatronCapacity) {
        bookCapacity = maxBookCapacity;
        maxBorrowedLimit = maxBorrowedBooks;
        patronCapacity = maxPatronCapacity;

        libraryBooksArray = new Book[maxBookCapacity]; //init to 0 null.
        libraryPatronsArray = new Patron[maxPatronCapacity]; // init to null.
        numPatronsBooksTaken = new int[maxPatronCapacity]; // init to 0 values.
        numBooksInLibrary = 0;
        numPatronsInLibrary = 0;
    }

    /* -----=  Instance Methods  =----- */

    /**
     * Adds the given book to this library, if there is place available, and it isn't already in the library.
     *
     * @param book The book to add to this library.
     * @return A non-negative id number for the book if there is a spot and the book was successfully added.
     * The current id of the book if already in the library. Otherwise, a negative number.
     */
    int addBookToLibrary(Book book) {
        // check if the book already exists.
        for (int i = 0; i < numBooksInLibrary; i++) {
            if (libraryBooksArray[i] == book) {
                return i;
            }
        }

        // check library not full.
        if (numBooksInLibrary == bookCapacity) {
            return -1;
        }

        libraryBooksArray[numBooksInLibrary] = book;
        return numBooksInLibrary++;
    }

    /**
     * Marks the book with the given id number as borrowed by the patron with the given patron id, if this
     * book is available, the given patron isn't already borrowing the maximal number of books allowed, and
     * if the patron will enjoy this book.
     *
     * @param bookId   The id number of the book to borrow.
     * @param patronId The id number of the patron that will borrow the book.
     * @return True if the book was borrowed successfully, false otherwise.
     */
    boolean borrowBook(int bookId, int patronId) {
        // Checks book id.
        if (!isBookIdValid(bookId) || !isPatronIdValid(patronId) || !isBookAvailable(bookId)) {
            return false;
        }

        if (numPatronsBooksTaken[patronId] >= maxBorrowedLimit) {
            return false;
        }

        // Checks if patron enjoy the book.
        if (!libraryPatronsArray[patronId].willEnjoyBook(libraryBooksArray[bookId])) {
            return false;
        }

        ++numPatronsBooksTaken[patronId];
        libraryBooksArray[bookId].setBorrowerId(patronId);
        return true;
    }

    /**
     * Returns the id number of the given book if it is owned by this library, -1 otherwise.
     *
     * @param book The book for which to find the id number.
     * @return A non-negative id number of the given book if he is owned by this library, -1 otherwise.
     */
    int getBookId(Book book) {
        for (int i = 0; i < bookCapacity; i++) {
            if (this.libraryBooksArray[i] == book) {
                return i;
            }
        }

        return -1;
    }

    /**
     * Returns the id number of the given patron if he is registered to this library, -1 otherwise.
     *
     * @param patron The patron for which to find the id number.
     * @return A non-negative id number of the given patron if he is registered to this library, -1 otherwise.
     */
    int getPatronId(Patron patron) {
        for (int i = 0; i < this.patronCapacity; i++) {
            if (this.libraryPatronsArray[i] == patron) {
                return i;
            }
        }

        return -1;
    }

    /**
     * Returns true if the book with the given id is available, false otherwise.
     *
     * @param bookId The id number of the book to check.
     * @return True if the book with the given id is available, false otherwise.
     */
    boolean isBookAvailable(int bookId) {
        if (!isBookIdValid(bookId)) {
            return false;
        }

        return libraryBooksArray[bookId].getCurrentBorrowerId() == Book.NO_PATRON;
    }

    /**
     * Returns if the given number is an id of a book in the library.
     *
     * @param bookId The id to check.
     * @return True if the given number is an id of some book in the library, false otherwise.
     */
    boolean isBookIdValid(int bookId) {
        return bookId < numBooksInLibrary && bookId >= 0 && libraryBooksArray[bookId] != null;
    }

    /**
     * Returns if the given number is an id of a patron in the library.
     *
     * @param patronId The id to check.
     * @return True if the given number is an id of a patron in the library, false otherwise.
     */
    boolean isPatronIdValid(int patronId) {
        return patronId < numPatronsInLibrary && patronId >= 0 && libraryPatronsArray[patronId] != null;
    }


    /**
     * Registers the given Patron to this library, if there is a spot available.
     *
     * @param patron The patron to register to this library.
     * @return A non-negative id number for the patron if there is a spot and the patron was successfully
     * registered.
     * The current id of the patron if already registered. Otherwise, a negative number.
     */
    int registerPatronToLibrary(Patron patron) {
        // check if patron in library
        for (int i = 0; i < numPatronsInLibrary; i++) {
            if (libraryPatronsArray[i] == patron) {
                return i;
            }
        }

        //check if patrons not full.
        if (numPatronsInLibrary == patronCapacity) {
            return -1;
        }

        libraryPatronsArray[numPatronsInLibrary] = patron;
        return numPatronsInLibrary++;
    }


    /**
     * Return the given book.
     *
     * @param bookId The id number of the book to return.
     */
    void returnBook(int bookId) {
        if (isBookIdValid(bookId)) {
            int patronBorrowerID = libraryBooksArray[bookId].getCurrentBorrowerId();

            if (patronBorrowerID != Book.NO_PATRON) {
                libraryBooksArray[bookId].returnBook();
                --numPatronsBooksTaken[patronBorrowerID];
            }
        }
    }

    /**
     * Suggest the patron with the given id the book he will enjoy the most, out of all available books he
     * will enjoy, if any such exist.
     *
     * @param patronId The id number of the patron to suggest the book to.
     * @return The available book the patron with the given will enjoy the most. 'null' if no book is
     * available.
     */
    Book suggestBookToPatron(int patronId) {
        if (!isPatronIdValid(patronId)) {
            return null;
        }

        int curBookScore;
        int bestBookEnjoyIndex = -1;
        int bestBookEnjoyScore = 0;
        Patron patron = libraryPatronsArray[patronId];

        for (int i = 0; i < bookCapacity; i++) {
            if (libraryBooksArray[i] == null) {
                break;
            }

            curBookScore = patron.getBookScore(libraryBooksArray[i]);

            if (curBookScore > bestBookEnjoyScore && isBookAvailable(i)) {
                bestBookEnjoyIndex = i;
                bestBookEnjoyScore = curBookScore;
            }
        }

        if (bestBookEnjoyIndex == -1 || bestBookEnjoyScore <= patron.getEnjoymentThreshold()) {
            return null;
        }

        return libraryBooksArray[bestBookEnjoyIndex];
    }
}