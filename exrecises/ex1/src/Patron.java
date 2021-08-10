/**
 * This class represents a library patron that has a name and assigns values to different literary aspects of
 * books.
 */
class Patron {

    /**
     * patron's first name.
     */
    final String firstName;

    /**
     * patron's last name.
     */
    final String lastName;

    /**
     * The weight the patron assigns to the comic aspects of books.
     */
    int comicTendency;

    /**
     * The weight the patron assigns to the dramatic aspects of books.
     */
    int dramaticTendency;

    /**
     * The weight the patron assigns to the educational aspects of books.
     */
    int educationalTendency;

    /**
     * A book must have this literary value or higher for this patron to enjoy it.
     */
    int enjoymentThreshold;

    /* -----=  Constructors  =----- */

    /**
     * Creates a new Patron with the given characteristics.
     *
     * @param patronFirstName          The first name of the patron.
     * @param patronLastName           The last name of the patron.
     * @param comicTendency            The weight the patron assigns to the comic aspects of books.
     * @param dramaticTendency         The weight the patron assigns to the dramatic aspects of books.
     * @param educationalTendency      The weight the patron assigns to the educational aspects of books.
     * @param patronEnjoymentThreshold A book must have this literary value or higher for this patron to
     *                                 enjoy it.
     */
    Patron(String patronFirstName, String patronLastName, int comicTendency, int dramaticTendency,
           int educationalTendency, int patronEnjoymentThreshold) {
        firstName = patronFirstName;
        lastName = patronLastName;
        this.comicTendency = comicTendency;
        this.dramaticTendency = dramaticTendency;
        this.educationalTendency = educationalTendency;
        enjoymentThreshold = patronEnjoymentThreshold;
    }


    /* -----=  Instance Methods  =----- */

    /**
     * The enjoyment threshold getter.
     *
     * @return The enjoyment threshold of the patron.
     */
    int getEnjoymentThreshold() {
        return enjoymentThreshold;
    }

    /**
     * Returns a string representation of the patron, which is a sequence of its first and last name,
     * separated by a single white-space. For example, if the patron's first name is "Ricky" and his last
     * name is "Bobby", this method will return the String "Ricky Bobby".
     *
     * @return the String representation of this patron.
     */
    String stringRepresentation() {
        return firstName + " " + lastName;
    }

    /**
     * Calculate the literary value this patron assigns to the given book, based on the patron literary
     * aspect weights.
     *
     * @param book The book to assess.
     * @return The literary value this patron assigns to the given book.
     */
    int getBookScore(Book book) {
        return comicTendency * book.getComicValue() +
                dramaticTendency * book.getDramaticValue() +
                educationalTendency * book.getEducationalValue();
    }

    /**
     * Returns true of this patron will enjoy the given book, false otherwise.
     *
     * @param book The book to assess.
     * @return True of this patron will enjoy the given book, false otherwise.
     */
    boolean willEnjoyBook(Book book) {
        return getBookScore(book) >= enjoymentThreshold;
    }
}