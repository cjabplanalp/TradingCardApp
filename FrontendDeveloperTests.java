import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



/**
 * Test class to test the correct implementation of the CardFrontendFD class.
 * I am a FrontendDeveloper for this project.
 * 
 * @author cjabplanalp
 *
 */
public class FrontendDeveloperTests {

	private CardFD frontend;
	private Scanner scnr;
	private CardBD backend;
	private CardDW dw;
	private CardAE ae;

	private final String header = "-------------------------------------------------------------------------------\n"
			+ "Welcome to the Generic Card Sorting App.\n"
			+ "-------------------------------------------------------------------------------\n";
	private final String baseOutput = "Please choose a command from the list below:\n"
			+ "    [L]oad a card collection from a CSV file\n"
			+ "    [I]nsert a single card into the current collection\n"
			+ "    [P]rint a summary of statistic of the current collection\n"
			+ "    [G]et all cards above a certain value\n"
			+ "    [Q]uit\n"
			+ "Choose a command: ";
	private final String quitOutput = "-------------------------------------------------------------------------------\n"
			+ "Thank you for using the Generic Card Sorting App.\n"
			+ "-------------------------------------------------------------------------------";
	
	@BeforeEach
	public void setupTests() {

		dw = new CardDW();
		ae = new CardAE();
		backend = new CardBD(dw, ae);
	}
	
	/**
	 * Tests the correct functionality of loadDataFromFile() in the frontend.
	 * This is accessed when the user presses [L].
	 */
	@Test
	public void test01() {

		// dw = new CardDW<>();
		// ae = new CardAE<>();

		// backend = new CardBD(dw, ae);

		String input = "L\nhehe.txt\nQ\n";
		String expectedOutput = (header + baseOutput + "Enter the name of the file to load: Error: Could not find or load file: hehe.txt\nSuccessfully loaded file.\n" + baseOutput + quitOutput).trim();
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        scnr = new Scanner(in);
		System.setIn(in);
        frontend = new CardFD(scnr, backend);
        
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        frontend.commandLoop();
        
        System.setOut(new PrintStream(outContent));
        assertEquals(expectedOutput, outContent.toString().trim());
        
	}
	
	/**
	 * Tests the correct functionality of insertSingleData() in the frontend.
	 * This is accessed when the user presses [I].
	 */
	@Test
	public void test02() {
		String input = "I\npikachu\n2.5\nQ\n"; //insert pikachu, 2.5
		String expectedOutput = (header + baseOutput + "Enter the name of the TradingCard: Enter the value of the TradingCard: Successfully inserted the specified TradingCard.\n" + baseOutput + quitOutput).trim();
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        scnr = new Scanner(in);
		System.setIn(in);
        frontend = new CardFD(scnr, backend);
        
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        frontend.commandLoop();
        
        System.setOut(new PrintStream(outContent));
        assertEquals(expectedOutput, outContent.toString().trim());
	}
	
	/**
	 * Tests the correct functionality of removeSingleData() in the frontend.
	 * This is accessed when the user presses [R].
	 */
	// @Test
	// public void test03() {
	// 	String input = "R\npikachu\n2.5\nQ\n"; //remove pikachu, 2.5
	// 	String expectedOutput = (header + baseOutput + "Enter the name of the TradingCard to remove: Enter the vaue of the TradingCard you want to remove: Unable to remove.\n"
	// 			+ "Successfully removed the specified TradingCard.\n" + baseOutput + quitOutput).trim();
    //     ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    //     scnr = new Scanner(in);
	// 	System.setIn(in);
    //     frontend = new CardFD(scnr, backend);
        
    //     ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    //     System.setOut(new PrintStream(outContent));
    //     frontend.commandLoop();
        
    //     System.setOut(new PrintStream(outContent));
    //     assertEquals(expectedOutput, outContent.toString().trim());
	// }
	
	/**
	 * Tests the correct functionality of displaySummary() in the frontend.
	 * This is accessed when the user presses [P].
	 */
	@Test
	public void test04() {

		TradingCard card = new TradingCard("Cat", 100.50);
    	TradingCard card1 = new TradingCard("Dog", 200.50);
    	TradingCard card2 = new TradingCard("Parrot", 50.50);

		ae.insert(card);
		ae.insert(card1);
		ae.insert(card2);

		String input = "P\nQ\n";
		String expectedOutput = (header + baseOutput + "Summary statistics for this dataset:\n"
				+ "Highest TradingCard value: 200.5\n"
				+ "Lowest TradingCard value: 50.5\n"
				+ "Average TradingCard value: 0.0\n"
				+ "Range of TradingCard values: 150.0\n" + baseOutput + quitOutput).trim();
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        scnr = new Scanner(in);
		System.setIn(in);
        frontend = new CardFD(scnr, backend);
        
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        frontend.commandLoop();
        
        System.setOut(new PrintStream(outContent));
        assertEquals(expectedOutput, outContent.toString().trim());
	}
	
	/**
	 * Tests the correct functionality of getCardsAboveValue() in the frontend.
	 * This is accessed when the user presses [G].
	 */
	@Test
	public void test05() {

		TradingCard card = new TradingCard("Cat", 100.50);
    	TradingCard card1 = new TradingCard("Dog", 200.50);
    	TradingCard card2 = new TradingCard("Parrot", 50.50);

		ae.insert(card);
		ae.insert(card1);
		ae.insert(card2);

		String input = "G\n75.0\nQ\n";
		String expectedOutput = (header + baseOutput + "Enter the minimum TradingCard value threshold to look above: Accessing all TradingCard values above this value...\n"
				+ "(Cat, 100.5)(Dog, 200.5)\n" + baseOutput + quitOutput).trim();
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        scnr = new Scanner(in);
		System.setIn(in);
        frontend = new CardFD(scnr, backend);
        
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        frontend.commandLoop();
        
        System.setOut(new PrintStream(outContent));
        assertEquals(expectedOutput, outContent.toString().trim());
	}

	/**
	 * Tests for the correct implementation of DataWrangler's readValuesFromFile() method.
	 * 
	 * @throws FileNotFoundException if the file being loaded DNE.
	 */
	@Test
	public void CodeReviewOfDataWrangler() throws FileNotFoundException{

		// LOAD A FILE THAT DOESN'T EXIST
		Throwable e = null;
		try {
		dw.readValuesFromFile("nonexistentfile.csv");
		} catch(FileNotFoundException ex) {
			e = ex;
		}
		assertTrue(e instanceof FileNotFoundException);

		// LOAD A FILE THAT EXISTS
		List<TradingCard> tradingCards = dw.readValuesFromFile("PokemonData.csv");
		assertEquals(101, tradingCards.size());
	}

	/**
	 * Tests the correct implementation of BackendDeveloper's methods.
	 * 
	 * @throws FileNotFoundException if the file to be loaded DNE.
	 */
	@Test
	public void CodeReviewOfBackendDeveloper() throws FileNotFoundException {
    
    	TradingCard card = new TradingCard("Pikachu", 100.50);
    	TradingCard card1 = new TradingCard("Abra", 200.50);
    	TradingCard card2 = new TradingCard("Alakazam", 50.50);
    
    	ae.insert(card);
    	ae.insert(card1);
    	ae.insert(card2);

		assertEquals(150, backend.getRange());
		assertEquals(100.50, backend.getValue(card));
		assertEquals(200.50, backend.getValue(card1));
		assertEquals(50.50, backend.getValue(card2));

		assertEquals(card1.toString(), backend.getHigh().toString());
    
    	assertEquals(card2.toString(), backend.getLow().toString());

		// bd.loadData("PokemonData.csv");
		// assertEquals();
	}

	/**
	 * Tests the correct implementation of pressing [L] on the 
	 * FrontendDeveloper App interface, making sure that data is correctly loaded
	 * when loading our project file PokemonData.csv.
	 */
	@Test
	public void integrationTestWithDataWrangler() {

		String input = "L\nPokemonData.csv\nQ\n";
		String expectedOutput = (header + baseOutput + "Enter the name of the file to load: Successfully loaded file.\n" + baseOutput + quitOutput).trim();
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        scnr = new Scanner(in);
		System.setIn(in);
        frontend = new CardFD(scnr, backend);
        
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        frontend.commandLoop();
        
        System.setOut(new PrintStream(outContent));
        assertEquals(expectedOutput, outContent.toString().trim());
	}

	/**
	 * Tests the correct implementation of pressing [L] to load our project file
	 * PokemonData.csv (already handled in previous test but necessary for this test),
	 * then tests the correct implementation of pressing [P], using the BackendDeveloper's
	 * methods to print out statistics about the dataset.
	 */
	@Test
	public void integrationTestWithBackendDeveloper() {
		String input = "L\nPokemonData.csv\nP\nQ\n";
		String expectedOutput = (header + baseOutput + "Enter the name of the file to load: Successfully loaded file.\n" + baseOutput
				+ "Summary statistics for this dataset:\n"
				+ "Highest TradingCard value: 339.02\n"
				+ "Lowest TradingCard value: 0.18\n"
				+ "Average TradingCard value: 10.532871287128705\n"
				+ "Range of TradingCard values: 338.84\n" + baseOutput + quitOutput).trim();
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        scnr = new Scanner(in);
		System.setIn(in);
        frontend = new CardFD(scnr, backend);
        
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        frontend.commandLoop();
        
        System.setOut(new PrintStream(outContent));
        assertEquals(expectedOutput, outContent.toString().trim());
	}
	
}
