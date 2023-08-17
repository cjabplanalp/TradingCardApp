import java.util.Scanner;

/**
 * Runs the Generic TradingCard Application.
 * 
 * @author blue team
 */
public class CardApp {
	public static void main(String[] args) {
		// Use data wrangler's code to load post data
		CardDW dw = new CardDW();
		// Use algorithm engineer's code to store and search for data
		// HashtableWithDuplicateKeysInterface<String, PostInterface> hashtable;
		// hashtable = new HashtableWithDuplicateKeysAE<>();

        CardAE ae = new CardAE();

		// Use the backend developer's code to manage all app specific processing
		CardBD backend = new CardBD(dw, ae);
		// Use the frontend developer's code to drive the text-base user interface
		Scanner scanner = new Scanner(System.in);
		CardFD frontend = new CardFD(scanner, backend);
		frontend.commandLoop();
	}
}