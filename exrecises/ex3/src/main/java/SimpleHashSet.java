/**
 * A superclass for implementations of hash-sets implementing the SimpleSet interface.
 */
public abstract class SimpleHashSet implements SimpleSet {
    /**
     * minimum capacity hash table can have
     */
    protected static final int MIN_CAPACITY = 1;

    /**
     * constant factor for extend hash table
     */
    protected static final int RESIZE_FACTOR = 2;

    /**
     * constant for default initialize
     */
    protected static final int INITIAL_SIZE = 0;

    /**
     * Describes the capacity of a newly created hash set.
     */
    protected static final int INITIAL_CAPACITY = 16;

    /**
     * Describes the lower load factor of a newly created hash set.
     */
    protected static final float DEFAULT_LOWER_CAPACITY = 0.25f;

    /**
     * Describes the higher load factor of a newly created hash set.
     */
    protected static final float DEFAULT_HIGHER_CAPACITY = 0.75f;

    /**
     * Describes the upper load factor of the hash set.
     */
    private float upperLoadFactor;

    /**
     * Describes the lower load factor of the hash set.
     */
    private float lowerLoadFactor;


    /**
     * Constructs a new hash set with capacity INITIAL_CAPACITY.
     *
     * @param upperLoadFactor - The upper load factor before rehashing.
     * @param lowerLoadFactor - The lower load factor before rehashing.
     */
    protected SimpleHashSet(float upperLoadFactor, float lowerLoadFactor) {
        this.upperLoadFactor = upperLoadFactor;
        this.lowerLoadFactor = lowerLoadFactor;
    }

    /**
     * Constructs a new hash set with the default capacities given in DEFAULT_LOWER_CAPACITY and
     * DEFAULT_HIGHER_CAPACITY.
     */
    protected SimpleHashSet() {
        this(DEFAULT_HIGHER_CAPACITY, DEFAULT_LOWER_CAPACITY);

    }

    /**
     * Returns the size of the storage space currently allocated for the set.
     *
     * @return The current capacity (number of cells) of the table.
     */
    public abstract int capacity();

    /**
     * Getter for lower load factor of the table.
     *
     * @return The lower load factor of the table.
     */
    protected float getLowerLoadFactor() {
        return lowerLoadFactor;
    }

    /**
     * Getter for upper load factor of the table.
     *
     * @return The higher load factor of the table.
     */
    protected float getUpperLoadFactor() {
        return upperLoadFactor;
    }

    /**
     * Clamps hashing indices to fit within the current table capacity (see the exercise description for
     *  details).
     * @param index index of the item we clamp
     * @return the clamp on the index
     */
    abstract protected int clamp(int index);

    /**
     * If we add an item to the set, we use this method to check if we need to increase the table
     * based on the load factor
     *
     * @return true if need, false else
     */
    protected boolean needToIncreaseSet() {
        float loadFactor = (float) (size() + 1) / capacity();
        return upperLoadFactor < loadFactor;
    }


    /**
     * If we remove an item from the set, we use this method to check if we need to decrease the table
     * based on the load factor
     *
     * @return true if need, false else
     */
    protected boolean needToDecreaseSet() {
        float loadFactor = (float) size() / capacity();
        return lowerLoadFactor > loadFactor;
    }
}
