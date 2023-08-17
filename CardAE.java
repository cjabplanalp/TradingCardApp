import java.util.List;

/**
 * AlgorithmEngineer class that implements a red black tree for the trading card data management structure
 * @author nakul
 *
 */
public class CardAE<T extends Comparable<T>> extends RedBlackTree<T> implements AEInterface<T>{
	
	private double totalValue;
	
	/**
	 * constructor that sets total value to 0 and constructs a RedBlackTree object (superclass)
	 */
	public CardAE(){
		super();
		this.totalValue=0.0;
	}
	
	
	/**
	 * gets size of tree
	 * @return number of values in tree 
	 */
 @Override
	public double getSize() {
		return super.size();
	}
	
	/**
	 * gets total value of entire tree
	 * @return total value instance field
	 */
    @Override
    public double getTotalValue() {
    	return this.totalValue;
    }

    /**
     * gets highest value in tree
     * @return maximum value in tree
     */
    @Override
    public T getHighest() {
    	return super.findMax();
    }
    
    /**
     * gets lowest value in tree
     * @return minimum value in tree
     */
    @Override
    public T getLowest() {
    	return super.findMin();
    }

    /**
     * Iterates through tree for values higher than given trading card value
     * @param minimum value startValue to list after
     * @return List of all values in tree over startValue
     */
    @Override
    public List<TradingCard> iterateOver (double startValue){
    	RBTIterator<TradingCard> iter = new RBTIterator<TradingCard>(this);
    	while(iter.getLowest().getValue()<startValue) {
    		iter.next();
    	}
    	return iter.getItems();
    	
    }
    
    /**
     * Iterate Over Step 1)  Iterate over the whole tree
     *  2) cut the arraylist at the min value (get a subarray list of it) 
     *  3) return the sub arraylist
     *  For testing this, run the runapp java file and try loading the data.csv file, and the printing all cards above a certain value, 
     *  and make sure that it doesn't throw an exception

     */
    
    /**
     * Checks if tree is empty
     * @return true if empty, false otherwise
     */
 @Override
	public boolean isEmpty() {
		return super.isEmpty();
	}
	
	/**
	 * 
	 * Checks if tree contains given value
	 * 
	 * @param data to search for
	 * @return true if contains data, false otherwise
	 */
 @Override
	public boolean contains(T data) {
		return super.contains(data);
	}
	
	/**
	 * Inserts given value into tree
	 * 
	 * @param value to insert
	 * @return true if successfully inserted, false otherwise
	 * @throws NullPointerException when the provided data argument is null
	 * @throws IllegalArgumentException when data is already contained in the tree
	 */
 @Override
	public boolean insert(T id, double value) throws NullPointerException, IllegalArgumentException{
		//totalValue+=value;
	    TradingCard temp = new TradingCard(id, value);
		boolean temp2 = super.insert((T) temp);
	    if (temp2) {
	      this.totalValue = this.totalValue + value;
	    }
	    return temp2;
	}
	
	/**
	 * Removes given value from tree
	 * 
	 * @param value to remove
	 * @return true if successfully removed, false otherwise
	 * @throws NullPointerException when the provided data argument is null
	 * @throws IllegalArgumentException when data is not already in the tree
	 */
	/*
	public TradingCard remove(T id, double value) throws NullPointerException, IllegalArgumentException{
		//totalValue-=value;
	     TradingCard temp = new TradingCard(id, value);
		return super.remove((T) temp);
	}
	*/
}
