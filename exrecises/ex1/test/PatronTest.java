import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class PatronTest {

    @Test
    void getBookScore() {
        final int size = 100;
        Random r = new Random();

        for (int i = 0; i < size; i++) {
            int[] bookScrArr = new int[3];
            int[] patronScrArr = new int[3];
            for (int j = 0; j < 3; j++) {
                bookScrArr[j] = r.nextInt(26000);
                patronScrArr[j] = r.nextInt(26000);
            }

            Book b = new Book("A", "B", 300,
                    bookScrArr[0], bookScrArr[1], bookScrArr[2]);

            Patron p = new Patron("John", "Doe",
                    patronScrArr[0], patronScrArr[1], patronScrArr[2], 0);


            int score = patronScrArr[0] * bookScrArr[0] +
                    patronScrArr[1] * bookScrArr[1] +
                    patronScrArr[2] * bookScrArr[2];
            assertEquals(score, p.getBookScore(b));
        }
    }

    @Test
    void willEnjoyBook() {
        ThreshEqualTest();


        final int size = 100;
        Random r = new Random();

        for (int i = 0; i < size; i++) {
            int[] bookScrArr = new int[3];
            int[] patronScrArr = new int[3];
            for (int j = 0; j < 3; j++) {
                bookScrArr[j] = r.nextInt(1000);
                patronScrArr[j] = r.nextInt(1000);
            }

            Book b = new Book("A", "B", 300,
                    bookScrArr[0], bookScrArr[1], bookScrArr[2]);

            int threshold = r.nextInt(750000 * 2);
            Patron p = new Patron("John", "Doe",
                    patronScrArr[0], patronScrArr[1], patronScrArr[2], threshold);

            if (p.getBookScore(b) >= threshold) {
                assertTrue(p.willEnjoyBook(b));
            } else {
                assertFalse(p.willEnjoyBook(b));
            }
        }
    }

    private void ThreshEqualTest() {
        Random r = new Random();
        int[] bookScrArr = new int[3];
        int[] patronScrArr = new int[3];
        for (int j = 0; j < 3; j++) {
            bookScrArr[j] = r.nextInt(1000);
            patronScrArr[j] = r.nextInt(1000);
        }

        Book b = new Book("A", "B", 300,
                bookScrArr[0], bookScrArr[1], bookScrArr[2]);

        int threshold = patronScrArr[0] * bookScrArr[0] +
                patronScrArr[1] * bookScrArr[1] +
                patronScrArr[2] * bookScrArr[2];

        Patron p = new Patron("John", "Doe",
                patronScrArr[0], patronScrArr[1], patronScrArr[2], threshold);

        assertTrue(p.willEnjoyBook(b));
    }

    @Test
    void stringRepresentation() {
        String first = "Rick", second = "Astley";
        Patron p = new Patron(first, second, 0, 0, 0, 0);
        assertEquals(p.stringRepresentation(), first + " " + second);
    }
}