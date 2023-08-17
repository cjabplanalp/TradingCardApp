import java.util.List;
public interface AEInterface<T extends Comparable<T>> extends SortedCollectionInterface<T> {

	//public int size; //already defined in collection
    //private double totalValue; //update in insert/remove

	/**
	 * gets size of tree
	 * @return number of values in tree 
	 */
    public double getSize();
    
	/**
	 * gets total value of entire tree
	 * @return total value instance field
	 */
    public double getTotalValue();

    /**
     * gets highest value in tree
     * @return maximum value in tree
     */
    public T getHighest();
    
    /**
     * gets lowest value in tree
     * @return minimum value in tree
     */
    public T getLowest();

    /**
     * Iterates through tree for values higher than given trading card value
     * @param minimum value startValue to list after
     * @return List of all values in tree over startValue
     */
    public List<TradingCard> iterateOver (double startValue);

    
    // --------- already defined in collection -----------
    
    /**
     * Checks if tree is empty
     * @return true if empty, false otherwise
     */
    public boolean isEmpty();
    
	/**
	 * 
	 * Checks if tree contains given value
	 * 
	 * @param data to search for
	 * @return true if contains data, false otherwise
	 */
    public boolean contains(T data);
    
	/**
	 * Inserts given value into tree
	 * 
	 * @param value to insert
	 * @return true if successfully inserted, false otherwise
	 * @throws NullPointerException when the provided data argument is null
	 * @throws IllegalArgumentException when data is already contained in the tree
	 */
    public boolean insert(T id, double value) throws NullPointerException, IllegalArgumentException;
    
	/**
	 * Removes given value from tree
	 * 
	 * @param value to remove
	 * @return true if successfully removed, false otherwise
	 * @throws NullPointerException when the provided data argument is null
	 * @throws IllegalArgumentException when data is not already in the tree
	 */
    
    /*
    public TradingCard remove(T id, double value) throws NullPointerException, IllegalArgumentException;
    */
}
