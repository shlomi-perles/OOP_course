/**
 * A hash-set based on closed-hashing with quadratic probing. Extends SimpleHashSet.
 */
public class ClosedHashSet extends SimpleHashSet {

    /**
     * index for deleted cell
     */
    private static final int DELETE_FLAG = -1;

    /**
     * no index found flag
     */
    private static final int NO_INDEX = -1;


    /**
     * number of items in the hash table
     */
    private int size;

    /**
     * the hash table array
     */
    private Object[] hashTable;

    /**
     * Constructs a new, empty table with the specified load factors, and the default initial capacity (16).
     *
     * @param upperLoadFactor - The upper load factor of the hash table.
     * @param lowerLoadFactor - The lower load factor of the hash table.
     */
    public ClosedHashSet(float upperLoadFactor, float lowerLoadFactor) {
        super(upperLoadFactor, lowerLoadFactor);
        size = INITIAL_SIZE;
        hashTable = new Object[getCapacity()];
    }

    /**
     * A default constructor. Constructs a new, empty table with default initial capacity (16), upper load
     * factor (0.75) and lower load factor (0.25).
     */
    public ClosedHashSet() {
        size = INITIAL_SIZE;
        hashTable = new Object[INITIAL_CAPACITY];
    }

    /**
     * Data constructor - builds the hash set by adding the elements one by one. Duplicate values should be
     * ignored. The new table has the default values of initial capacity (16), upper load factor (0.75), and
     * lower load factor (0.25).
     *
     * @param data - Values to add to the set.
     */
    public ClosedHashSet(String[] data) {
        size = INITIAL_SIZE;
        hashTable = new Object[INITIAL_CAPACITY];
        for (String value : data) {
            add(value);
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
            resize(capacity() * RESIZE_FACTOR);
        }
        hashTable[clamp(newValue.hashCode())] = newValue;
        this.size++;
        return true;

    }

    /**
     * Look for a specified value in the set.
     *
     * @param searchVal Value to search for.
     * @return True if searchVal is found in the set, false otherwise.
     */
    public boolean contains(String searchVal) {
        return getIndex(searchVal) != NO_INDEX;
    }

    /**
     * Remove the input element from the set.
     *
     * @param toDelete Value to delete.
     * @return True if toDelete is found and deleted, false otherwise.
     */
    public boolean delete(String toDelete) {
        int index = getIndex(toDelete);

        if (index == NO_INDEX) {
            return false;
        }

        hashTable[index] = DELETE_FLAG;
        --size;

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
        for (int i = 0; i < capacity(); ++i) {
            int clamp = ((index + (i + i * i) / 2) & (capacity() - 1));
            if (!(hashTable[clamp] instanceof String)) { //TODO: null check
                return clamp;
            }
        }
        return DELETE_FLAG;
    }

    /**
     * resize current table
     *
     * @param newSize the size of the new table
     */
    private void resize(int newSize) {
        Object[] hashTableCopy = hashTable.clone();
        hashTable = new Object[newSize];
        size = INITIAL_SIZE;

        for (Object value : hashTableCopy) {
            if (value instanceof String) {
                add((String) value);
            }
        }
    }

    /**
     * return index of a value inside the hash
     * @param searchVal the item we want to find his index
     * @return the index and -1 if there is no index
     */
    private int getIndex(String searchVal) {
        int hash = searchVal.hashCode();
        for (int i = 0; i < capacity(); ++i) {
            int clamp = (hash + ((i + i * i) / 2)) & (capacity() - 1);
            if (hashTable[clamp] == null)
            {
                return NO_INDEX;
            }
            if (hashTable[clamp] instanceof String && hashTable[clamp].equals(searchVal)) {
                return clamp;
            }
        }
        return NO_INDEX;
    }
}
