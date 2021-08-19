import java.util.LinkedList;

/**
 * A hash-set based on chaining. Extends SimpleHashSet.
 */
public class OpenHashSet extends SimpleHashSet {

    /**
     * wrraper class for linked list
     */
    private static class LinkedListWrraper extends CollectionFacadeSet {
        /**
         * constructor for the class
         */
        private LinkedListWrraper() {
            super(new LinkedList<String>());
        }
    }

    /**
     * The hash table array
     */
    private LinkedListWrraper[] hashTable;

    /**
     * number of items in the hash table
     */
    private int size;

    /**
     * Constructs a new, empty table with the specified load factors, and the default initial capacity (16).
     *
     * @param upperLoadFactor - The upper load factor of the hash table.
     * @param lowerLoadFactor - The lower load factor of the hash table.
     */
    public OpenHashSet(float upperLoadFactor, float lowerLoadFactor) {
        super(upperLoadFactor, lowerLoadFactor);
        size = INITIAL_SIZE;
        hashTableInitializer(INITIAL_CAPACITY);
    }

    /**
     * A default constructor. Constructs a new, empty table with default initial capacity (16), upper load
     * factor (0.75) and lower load factor (0.25).
     */
    public OpenHashSet() {
        this(DEFAULT_LOWER_CAPACITY, DEFAULT_LOWER_CAPACITY);
    }

    /**
     * Data constructor - builds the hash set by adding the elements one by one. Duplicate values should be
     * ignored. The new table has the default values of initial capacity (16), upper load factor (0.75), and
     * lower load factor (0.25).
     *
     * @param data - Values to add to the set.
     */
    public OpenHashSet(String[] data) {
        this();
        for (String val : data) {
            add(val);
        }
    }

    /**
     * Init hash Table.
     *
     * @param capacity capacity of the hash table
     */
    private void hashTableInitializer(int capacity) {
        hashTable = new LinkedListWrraper[capacity];
        for (int i = 0; i < hashTable.length; ++i) {
            hashTable[i] = new LinkedListWrraper();
        }
    }

    /**
     * Add a specified element to the set if it's not already in it.
     *
     * @param newValue New value to add to the set.
     * @return False if newValue already exists in the set, true otherwise
     */
    public boolean add(String newValue) {
        if (contains(newValue)) {
            return false;
        }
        if (needToIncreaseSet()) {
            resize(hashTable.length * RESIZE_FACTOR);
        }
        hashTable[clamp(newValue.hashCode())].add(newValue);
        ++size;
        return true;
    }

    /**
     * Look for a specified value in the set.
     *
     * @param searchVal Value to search for.
     * @return True if searchVal is found in the set, false otherwise.
     */
    public boolean contains(String searchVal) {
        int index = clamp(searchVal.hashCode());
        return (hashTable[index].size() >= 0) && hashTable[index].contains(searchVal);
    }

    /**
     * Remove the input element from the set.
     *
     * @param toDelete Value to delete.
     * @return True if toDelete is found and deleted, false otherwise.
     */
    public boolean delete(String toDelete) {
        if (!contains(toDelete)) {
            return false;
        }

        hashTable[clamp(toDelete.hashCode())].delete(toDelete);
        --size
        ;
        if (needToDecreaseSet() && capacity() != MIN_CAPACITY) {
            resize(hashTable.length / RESIZE_FACTOR);
        }
        return true;
    }

    /**
     * Returns the number of actual objects held in the set,
     * which is not necessarily equal to its storage capacity.
     *
     * @return The number of elements currently in the set.
     */
    public int size() {
        return this.size;
    }

    /**
     * Returns the size of the storage space currently allocated for the set.
     *
     * @return The current capacity (number of cells) of the table.
     */
    public int capacity() {
        return hashTable.length;
    }

    /**
     * Clamps hashing indices to fit within the current table capacity (see the exercise description for
     * details).
     */
    protected int clamp(int index) {
        return index & (capacity() - 1);
    }

    /**
     * resize current table
     *
     * @param newSize the size of the new table
     */
    private void resize(int newSize) {
        LinkedListWrraper[] hashTableCopy = hashTable.clone();
        hashTableInitializer(newSize);

        size = INITIAL_SIZE;

        for (LinkedListWrraper linkedList : hashTableCopy) {
            for (String subItem : linkedList.collection) {
                add(subItem);
            }
        }
    }


}
