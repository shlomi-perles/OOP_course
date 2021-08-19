/**
 * Wraps an underlying Collection<String>and serves to both simplify its API and give it a common type with
 * the implemented SimpleHashSets.
 */
public class CollectionFacadeSet implements SimpleSet {

    /**
     * The warped object by this facade.
     */
    protected java.util.Collection<String> collection;

    /**
     * Creates a new facade wrapping the specified collection.
     *
     * @param collection - The Collection to wrap.
     */
    public CollectionFacadeSet(java.util.Collection<String> collection) {
    }

    /**
     * Add a specified element to the set if it's not already in it.
     *
     * @param newValue New value to add to the set.
     * @return False if newValue already exists in the set, true otherwise
     */
    public boolean add(String newValue) {
        return false;
    }

    /**
     * Look for a specified value in the set.
     *
     * @param searchVal Value to search for.
     * @return True if searchVal is found in the set, false otherwise.
     */
    public boolean contains(String searchVal) {
        return true;
    }

    /**
     * Remove the input element from the set.
     *
     * @param toDelete Value to delete.
     * @return True if toDelete is found and deleted, false otherwise.
     */
    public boolean delete(String toDelete) {
        return true;
    }

    /**
     * Returns the number of actual objects held in the set,
     * which is not necessarily equal to its storage capacity.
     *
     * @return The number of elements currently in the set.
     */
    public int size() {
        return 1;
    }

    /**
     * Returns the size of the storage space currently allocated for the set.
     *
     * @return The current capacity (number of cells) of the table.
     */
    public int capacity() {
        return 1;
    }


}
