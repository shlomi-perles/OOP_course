/**
 * Wraps an underlying Collection<String>and serves to both simplify its API and give it a common type with
 * the implemented SimpleHashSets.
 */
public class CollectionFacadeSet implements SimpleSet {

    /**
     * The warped object by this facade.
     */
    protected Collection<String> collection;

    /**
     * Creates a new facade wrapping the specified collection.
     *
     * @param collection - The Collection to wrap.
     */
    public CollectionFacadeSet(Collection<String> collection) {
    }

    public boolean add(String newValue);

    public boolean contains(String searchVal);

    public boolean delete(String toDelete);

    public int size();

}
