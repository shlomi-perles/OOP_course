import java.util.TreeSet;
import java.util.HashSet;
import java.util.LinkedList;


public class SimpleSetPerformanceAnalyzer {

    private static final int NUM_OF_SETS = 5;
    private static final int MILLISECONDS = 1000000;

    private static final int LINKED_LIST_INDEX = 3;

    private static final int PERCENTAGE_MODULO = 10;
    private static final int LINKED_LIST_ITERATIONS = 7000;
    private static final int SETS_ITERATIONS = 70000;

    private static final String[] setsTypes = {"OpenHashSet", "ClosedHashSet0", "TreeSet", "LinkedList",
            "HashSet"};

    private final SimpleSet[] sets = new SimpleSet[NUM_OF_SETS];

    /**
     * constructor for SimpleSetPerformanceAnalyzer
     */
    public SimpleSetPerformanceAnalyzer() {
        this.sets[0] = new OpenHashSet();
        this.sets[1] = new ClosedHashSet();
        this.sets[2] = new CollectionFacadeSet(new TreeSet<>());
        this.sets[3] = new CollectionFacadeSet(new LinkedList<>());
        this.sets[4] = new CollectionFacadeSet(new HashSet<>());
    }

    /**
     * Analayze insert for 5 types of sets
     * @param filePath from where to insert the data
     */
    private void insertAnalyzer(String filePath) {
        String[] data = Ex3Utils.file2array(filePath);
        System.out.println("Inserting to file " + filePath + "\n");

        for (int i = 0; i < NUM_OF_SETS; i++) {
            long startTime = System.nanoTime();
            System.out.println("check set: " + setsTypes[i] + "\n");

            for (int j = 0; j < data.length; j++) {
                sets[i].add(data[j]);
                jobProgress(j, data.length);
            }

            long totalTime = System.nanoTime() - startTime;
            System.out.println("Total time for Insert to" + setsTypes[i]
                    + ": " + (totalTime / MILLISECONDS) + "ms\n");
        }
    }


    /**
     * Analyze contain run time for 5 types of sets
     * @param value the value we search if contain
     * @param fileName were the data from
     */
    private void containsAnalyzer(String value, String fileName) {
        System.out.println("check contains:" + value + "at" + fileName + "\n");
        int numIterate;

        for (int i = 0; i < NUM_OF_SETS; i++) {
            System.out.println("set type: " + setsTypes[i]);

            if (i == LINKED_LIST_INDEX) {
                numIterate = LINKED_LIST_ITERATIONS;

            } else {
                System.out.println("Warming up: " + setsTypes[i] + "\n");
                containsIterate(value, i);
                System.out.println("finished warm up\n");
                numIterate = SETS_ITERATIONS;
            }

            long startTime = System.nanoTime();
            containsIterate(value, i);
            long totalTime = System.nanoTime() - startTime;
            System.out.println("Avg Time: " + (totalTime / numIterate) + "ns\n");
        }
    }


    /**
     * do iteratian for check contain avg
     * @param value the value we search if contain
     * @param dataTypeIndex which data type we check now
     */
    private void containsIterate(String value, int dataTypeIndex) {
        for (int j = 0; j < LINKED_LIST_ITERATIONS; j++) {
            sets[dataTypeIndex].contains(value);
            jobProgress(j, LINKED_LIST_ITERATIONS);
        }
    }

    /**
     * print a modulo 10 the percent of current job
     * @param index which index we at
     * @param dataSize the size of the current data
     */
    private void jobProgress(int index, int dataSize) {
        if (dataSize != 0) {
            float percent = (float) (index) / dataSize * 100;
            if (percent % PERCENTAGE_MODULO == 0f) {
                System.out.println((float) percent + "%\n");
            }
        }
    }

    /**
     * main function for analayz sets
     * @param args no args
     */
    public static void main(String[] args) {
        String mainPath = "D:\\study\\year2\\semester B\\OOP\\exrecises\\ex3\\src\\main\\resources\\";
        String data1 = mainPath + "data1.txt";
        String data2 = mainPath + "data2.txt";
        SimpleSetPerformanceAnalyzer[] analyzer = new SimpleSetPerformanceAnalyzer[2];

        analyzer[0] = new SimpleSetPerformanceAnalyzer();
        analyzer[1] = new SimpleSetPerformanceAnalyzer();

        analyzer[0].insertAnalyzer(data1);
        analyzer[0].containsAnalyzer("hi", data1);
        analyzer[0].containsAnalyzer("-13170890158", data1);

        analyzer[1].insertAnalyzer(data2);
        analyzer[1].containsAnalyzer("23", data2);
        analyzer[1].containsAnalyzer("hi", data2);


    }
}