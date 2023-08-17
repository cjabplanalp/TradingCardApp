import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Provide a text-based user interface to the capabilities 
 * of the Generic Card Sorting Application.
 * 
 * @author cjabplanalp
 *
 */
public class CardFD implements FDInterface {

	private Scanner input;
	private CardBD backend;
	
	/**
	 * Initialize frontend to make use of a provided Scanner and CardBackend.
	 * 
	 * @param input user input through Scanner
	 * @param backend implemented by the BackendDeveloper
	 */
	public CardFD(Scanner input, CardBD backend) {
		this.input = input;
		this.backend = backend;
	}
	
	/**
	 * Displays a 79 column wide row of dashes: a horizontal rule.
	 */
 @Override
	public void hr() {
		System.out.println("-------------------------------------------------------------------------------");
	}

	/**
	 * This loop repeated prompts the user for commands and displays appropriate
	 * feedback for each. This continues until the user enters 'q' to quit.
	 */
 @Override
	public void commandLoop() {
		hr();
		System.out.println("Welcome to the Generic Card Sorting App.");
		hr();
		String command = null;
		while (!"Q".equals(command)) {
			command = printPromptingMenu();
			switch (command) {
			case "L": // load a card collection from a CSV file 
				System.out.print("Enter the name of the file to load: ");
				String inputFile = input.nextLine().trim();
				loadDataFromFile(inputFile);
				System.out.println("Successfully loaded file.");
				break;
			case "I": // insert a single card into the current collection
				// FORMAT: <Name, MonetaryValue>
				System.out.print("Enter the name of the TradingCard: ");
				Object insertID = input.nextLine().trim();
				System.out.print("Enter the value of the TradingCard: ");
				String insertValue = input.nextLine().trim();
				
				// make sure the inserted value can be a double
				try {
				    double doubleInsertValue = Double.parseDouble(insertValue);
				    insertSingleData(insertID, doubleInsertValue);
				    hr();
				    System.out.println("Successfully inserted the specified TradingCard.");
				    hr();
				} catch (NumberFormatException e) {
					hr();
				    System.out.println("The entered value cannot be parsed as a value for this TradingCard. Please try again.");
				    hr();
				}
				break;
			// case "R": // remove a single card and return value of removed card
			// 	System.out.print("Enter the name of the TradingCard to remove: ");
			// 	Object removeID = input.nextLine().trim();
			// 	System.out.print("Enter the vaue of the TradingCard you want to remove: ");
			// 	String removeValue = input.nextLine().trim();
				
			// 	// make sure the inserted value can be a double
			// 	try {
			// 		double doubleRemoveValue = Double.parseDouble(removeValue);
			// 		removeSingleData(removeID, doubleRemoveValue);
			// 		System.out.println("Successfully removed the specified TradingCard.");
			// 	} catch (NumberFormatException e) {
			// 		System.out.println("The entered value cannot be parsed as a value for this TradingCard. Please try again.");
			// 	}	
			// 	break;
			case "P": // print a summary of statistic of the current collection
				displaySummary();
				break;
			case "G": // get all cards above a certain value
				System.out.print("Enter the minimum TradingCard value threshold to look above: ");
				String minThreshold = input.nextLine().trim();
				
				// make sure the inserted value can be a double
				try {
					double doubleMinThreshold = Double.parseDouble(minThreshold);
					hr();
					System.out.println("Accessing all TradingCard values above this value...");

					TradingCard[] sorted = getCardsAboveValue(doubleMinThreshold);

					if (sorted == null) {
						System.out.println("There are no trading cards above this value.");
						break;
					}

					String output = "";

					for (int i = 0; i < sorted.length; i++) {
						output += sorted[i].toString();
					}
					System.out.println(output);
					hr();
				} catch (NumberFormatException e) {
				    System.out.println("The entered value cannot be parsed as a value for this TradingCard. Please try again.");
				}
				break;
			case "Q":// quit the program
				break;
			default:
				System.out.println("Didn't recognize that command."
						+ " Please type one of the letters presented within []s to identify the command you would like to choose.");
				break;
			}
		}
		hr();
		System.out.println("Thank you for using the Generic Card Sorting App.");
		hr();
	}

	/**
	 * Prints the command options to System.out and reads user's choice through
	 * userInput scanner.
	 */
 @Override
	public String printPromptingMenu() {
		System.out.println("Please choose a command from the list below:");
		System.out.println("    [L]oad a card collection from a CSV file");
		System.out.println("    [I]nsert a single card into the current collection");
		// System.out.println("    [R]emove a single card and return value of removed card");
		System.out.println("    [P]rint a summary of statistic of the current collection");
		System.out.println("    [G]et all cards above a certain value");
		System.out.println("    [Q]uit");
		System.out.print("Choose a command: ");
		
		String userInput = input.nextLine().trim();
		
		if (userInput.length() == 0) {
			return null; // if user's choice is blank, return null
		}
		// otherwise, return an uppercase version of the first letter entered
		return userInput.toUpperCase().substring(0, 1);
	}

	/**
	 * Loads a file to the Generic Trading Card App using the backend's
	 * implementation. Called when [L] is the user's input.
	 * 
	 * @param filename to be loaded
	 */
 @Override
	public void loadDataFromFile(String filename) {
		try {
			backend.loadData(filename);
		} catch (FileNotFoundException e) {
			System.out.println("Error: Could not find or load file: " + filename);
		}
	}

	/**
	 * Uses the backend to insert a TradingCard into the collection,
	 * specified by the user's input.
	 * 
	 * @param ID name of the TradingCard
	 * @param value monetary value of the TradingCard
	 */
 @Override
	public void insertSingleData(Object ID, double value) {
		try {
			backend.insert(ID, value);
		} catch (IllegalArgumentException e) {
			System.out.println("One or more of the TradingCard specifications were invalid.");
		}
	}

	/**
	 * Accesses the backend to remove a TradingCard specified by the user's input,
	 * and return the TradingCard removed.
	 * 
	 * @param ID of the TradingCard
	 * @param value of the TradingCard
	 * @return the removed TradingCard
	 */
//  @Override
// 	public TradingCardInterface removeSingleData(Object ID, double value) {
// 		TradingCardInterface toReturn = null;
		
// 		try {
// 			toReturn = backend.remove(ID, value);
// 		} catch (IllegalArgumentException args) {
// 			System.out.println("One or more of the TradingCard specifications were invalid.");
// 		} catch (NoSuchElementException none) {
// 			System.out.println("The TradingCard specified to remove doesn't exist.");
// 		}
		
// 		if (toReturn == null) {
// 			System.out.println("Unable to remove.");
// 		}
// 		return toReturn;
// 	}

	/**
	 * Displays dataset statistics to System.out.
	 */
 @Override
	public void displaySummary() {
		try{
		hr();
		System.out.println("Summary statistics for this dataset:");
		System.out.println("Highest TradingCard value: " + Double.toString(backend.getHigh().getValue()));
		System.out.println("Lowest TradingCard value: " + Double.toString(backend.getLow().getValue()));
		System.out.println("Average TradingCard value: " + Double.toString(backend.getMean()));
		System.out.println("Range of TradingCard values: " + Double.toString(backend.getRange()));
		hr();
		}catch(Exception e)
		{
			System.out.println("Please make sure there are TradingCards in your App.");
		}
	}

	/**
	 * Accesses the backend to return a sorted array of TradingCards based on the
	 * users specifications in the command loop.
	 * 
	 * @param value to slice the array by, specified by user
	 * @return an array of sorted cards above the minimum value
	 */
 @Override
	public TradingCard[] getCardsAboveValue(double value) {
	// 	TradingCard[] toReturn = new TradingCard[0];

	// try {
	// 	toReturn = backend.sortCards(value);
	// }	catch (IllegalArgumentException args) {
	// 	return null;
	// }
	// return toReturn;

		try {
			backend.sortCards(value);
		} catch (Exception e) {
			System.out.println("There are no values above your inputted threshold.");
		}
		return backend.sortCards(value);
	}
}
