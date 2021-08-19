/**
 * A superclass for implementations of hash-sets implementing the SimpleSet interface.
 */
public abstract class SimpleHashSet implements SimpleSet {

    /**
     * Describes the capacity of a newly created hash set.
     */
    protected static final int INITIAL_CAPACITY;

    /**
     * Describes the lower load factor of a newly created hash set.
     */
    protected static final float DEFAULT_LOWER_CAPACITY;

    /**
     * Describes the higher load factor of a newly created hash set.
     */
    protected static final float DEFAULT_HIGHER_CAPACITY;

    /**
     * Constructs a new hash set with capacity INITIAL_CAPACITY.
     *
     * @param upperLoadFactor - The upper load factor before rehashing.
     * @param lowerLoadFactor - The lower load factor before rehashing.
     */
    protected SimpleHashSet(float upperLoadFactor, float lowerLoadFactor) {
    }

    /**
     * Constructs a new hash set with the default capacities given in DEFAULT_LOWER_CAPACITY and
     * DEFAULT_HIGHER_CAPACITY.
     */
    protected SimpleHashSet() {
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
    protected float getLowerLoadFactor();

    /**
     * Getter for upper load factor of the table.
     *
     * @return The higher load factor of the table.
     */
    protected float getUpperLoadFactor();

    /**
     * Clamps hashing indices to fit within the current table capacity (see the exercise description for
     * details).
     */
    protected int clamp(int index);

}
