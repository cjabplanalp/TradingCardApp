import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;


/**
 * class for the iterator that goes through the RBT
 * @author nakul
 *
 */
public class RBTIterator<T> implements Iterator<T>{

	private ArrayList<T> cards;
	
	/**
	 * constructor for iterator, initializes empty cards list of all elements
	 * @param tree to iterate 
	 */
	@SuppressWarnings("unchecked")
	public RBTIterator(CardAE tree) {
		cards = new ArrayList<T>();
		addItems(tree.root);
	}
	
	/**
	 * private helper method to add everything (in order) to cards list
	 * @param node to start adding at
	 */
	private void addItems(RedBlackTree.Node<T> node) {
		if(node==null) {
			return;
		}
		addItems(node.context[1]);
		cards.add(node.data);
		addItems(node.context[2]);
		
	}
	
	/**
	 * getter for list of cards
	 * @return
	 */
	public List<T> getItems() {
		return cards;
	}
	
	/**
	 * gets lowest value in list to be iterated through
	 * @return lowest value in sorted list
	 */
	public T getLowest() {
		return cards.get(0);
	}
	
	  /**
	   * Returns true if the iteration has more elements.
	   * 
	   * @return true if the iteration has more elements, false otherwise
	   */
	@Override
	public boolean hasNext() {
		
		return cards.size()>0;
	}

	  /**
	   * Removes and returns the next value in the iteration.
	   * 
	   * @return the next value in the iteration
	   * @throws NoSuchElementException if the iteration has no more elements
	   */
	@Override
	public T next() throws NoSuchElementException {
		// if more elements, continue
		if(!hasNext()) {
			throw new NoSuchElementException();
		}
		return cards.remove(0);
	}

}
