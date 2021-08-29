package filesprocessing;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

/**
 * this class contained implementation of quick sort
 */
public class QuickSort {

    /**
     * quick sort implement recursively.
     *
     * @param fileList array of file we want to sort
     * @param low      from where to sort
     * @param high     until where to sort
     * @param order    comprator for sort
     */
    public static void sort(ArrayList<File> fileList, int low, int high, Comparator<File> order) {
        if (low < high) {

            int pivot = partition(fileList, low, high, order);

            sort(fileList, low, pivot - 1, order);
            sort(fileList, pivot + 1, high, order);
        }
    }

    /**
     * find random pivot
     *
     * @param fileList array which we take from it the random pivot
     * @param low      lowest place can take a pivot
     * @param high     highest place can take a pivot
     */
    static private void randomPivot(ArrayList<File> fileList, int low, int high) {

        Random rand = new Random();
        int pivot = rand.nextInt(high - low) + low;

        swap(fileList, high, pivot);
    }

    /**
     * swap all elemnts according to the pivot
     *
     * @param fileList list of files
     * @param low      from where to begin
     * @param high     from where to end
     * @param order    comparator object
     * @return array after partition done
     */
    static private int partition(ArrayList<File> fileList, int low, int high, Comparator<File> order) {

        randomPivot(fileList, low, high);
        File pivot = fileList.get(high);


        int i = (low - 1);
        for (int j = low; j < high; j++) {

            if (order.compare(fileList.get(j), pivot) < 0) {
                i++;
                swap(fileList, i, j);
            }
        }
        swap(fileList, i + 1, high);

        return i + 1;
    }


    /**
     * swap between two indexes at the array
     *
     * @param fileList list of files
     * @param i        firs index that swap
     * @param j        second index swap
     */
    static private void swap(ArrayList<File> fileList, int i, int j) {
        File temp = fileList.get(i);
        fileList.set(i, fileList.get(j));
        fileList.set(j, temp);
    }

}
