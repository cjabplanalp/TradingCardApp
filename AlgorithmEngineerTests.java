import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * tester class to test method implements of CardAE/RedBlackTree
 * Backend would use CardAE using TradingCard's double value, so implementation was tested using double values
 * 
 * @author Nakul
 *
 */
public class AlgorithmEngineerTests {
	protected CardAE<Double> tree;
	
	/**
	 * creates CardAE tree with every test
	 */
	@BeforeEach
	public void create() {
		tree = new CardAE<Double>();
	}
	
	/**
	 * Check for getHighest() functionality
	 */
	@Test
	void test1() {
		tree.insert(1.0);
		tree.insert(2.0);
		tree.insert(3.0);
		tree.insert(1.5);
		tree.insert(4.0);
		//System.out.println(tree.getHighest());
		//System.out.println(tree.toInOrderString());
		assertEquals(4.0,tree.getHighest());
	}
	
	/**
	 * Check for getLowest() functionality
	 */
	@Test
	void test2() {
		tree.insert(1.0);
		tree.insert(2.0);
		tree.insert(3.0);
		tree.insert(1.5);
		tree.insert(0.5);
		tree.insert(4.0);
		assertEquals(0.5,tree.getLowest());
	}
	
	/**
	 * Check for contains() functionality
	 */
	@Test
	void test3() {
		tree.insert(1.0);
		tree.insert(2.0);
		tree.insert(3.0);
		tree.insert(1.5);
		tree.insert(0.5);
		tree.insert(4.0);
		assertEquals(true,tree.contains(1.5));
		assertEquals(false,tree.contains(5.0));
	}
	
	/**
	 * Check for size and isEmpty()
	 */
	@Test
	void test4() {
		assertEquals(true,tree.isEmpty());
		tree.insert(1.0);
		tree.insert(2.0);
		tree.insert(3.0);
		tree.insert(1.5);
		tree.insert(0.5);
		tree.insert(4.0);
		assertEquals(6,tree.getSize());
		assertEquals(false,tree.isEmpty());
	}
	
	/**
	 * Check for remove() functionality and exceptions
	 */
	// @Test
	// void test5() {
	// 	tree.insert(1.0);
	// 	tree.insert(2.0);
	// 	tree.insert(3.0);
	// 	tree.insert(1.5);
	// 	tree.insert(0.5);
	// 	tree.insert(4.0);
	// 	//System.out.println(tree.toLevelOrderString());
	// 	boolean exp=false;
	// 	assertEquals("[ 0.5, 1.0, 1.5, 2.0, 3.0, 4.0 ]",tree.toInOrderString());
	// 	tree.remove(4.0);
	// 	assertEquals("[ 0.5, 1.0, 1.5, 2.0, 3.0 ]",tree.toInOrderString());
		
	// 	//checks if remove() exceptions work
		
	// 	//checks if removing not existing element throws appropriate exception
	// 	try {
	// 		tree.remove(4.0);
	// 	}
	// 	catch(IllegalArgumentException e) {
	// 		exp=true;
	// 	}
	// 	catch(Exception e) {
			
	// 	}
		
	// 	//checks if removing null throws appropriate exception
	// 	try {
	// 		tree.remove(null);
	// 	}
	// 	catch(NullPointerException e) {
	// 		exp=true;
	// 	}
	// 	catch(Exception e) {
			
	// 	}
	// 	assertEquals(exp,true);
		
		
	// 	//System.out.println(tree.toInOrderString());
	// }
	
	/**
	 * Check for basic insert() and its exceptions
	 */
	@Test
	void test6() {
		tree.insert(20.0);
		tree.insert(14.0);
		tree.insert(25.0);
		tree.insert(23.0);
		boolean exp=false;
		//checks if insert() exceptions work
		
		//adding a null
		try {
			tree.insert(null);
		}
		catch(NullPointerException e) {
			exp=true;
		}
		catch(Exception e) {
			
		}
		
		//adding an already existing value
		try {
			tree.insert(20.0);
		}
		catch(IllegalArgumentException e) {
			exp=true;
		}
		catch(Exception e) {
			
		}
		assertEquals(exp,true);
		//System.out.println(tree.toLevelOrderString());
		
		//checks if size is correct
		assertEquals(tree.size(),4);
	}
	
	/**
	 * check insert with proper rotations
	 */
	@Test
	void test7() {
		tree.insert(30.0);
		tree.insert(20.0);
		tree.insert(39.0);
		assertEquals(0,tree.root.context[2].blackHeight);
		tree.insert(35.0);
		assertEquals(0,tree.root.context[2].context[1].blackHeight);
		tree.insert(34.0);
		
		//check if proper toLevel string
		assertEquals("[ 30.0, 20.0, 35.0, 34.0, 39.0 ]",tree.toLevelOrderString());
	}
	
	/**
	 * check iterator functionality
	 */
	// @Test
	// void test8() {
	// 	tree.insert(19.0);
	// 	tree.insert(14.0);
	// 	tree.insert(25.0);
	// 	tree.insert(23.0);
	// 	tree.insert(30.0);
	// 	tree.insert(31.0);
	// 	List<Double> res;
	// 	//checks if all cards with values over 20.0 are listed
	// 	res=tree.iterateOver(20.0);
	// 	String resultStr= "";
		
	// 	//put all list elements in resultStr string
	// 	for(Double d: res) {
	// 		resultStr+=d+" ";
	// 	}
		
	// 	//expected value
	// 	assertEquals("23.0 25.0 30.0 31.0 ",resultStr);
		
	// }
	
}
