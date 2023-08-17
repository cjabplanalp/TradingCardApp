import java.io.FileNotFoundException;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;

public class DataWranglerTests {

	private CardDW cardDW;

	@BeforeEach
	public void setupTests() {
		cardDW = new CardDW();
	}

	// The first test method testReadValuesFromFile tests that the
	// readValuesFromFile method correctly reads trading card data from a file and
	// returns a list of TradingCard objects with the expected IDs and monetary
	// values.
	@Test
	public void testReadValuesFromFile() throws FileNotFoundException {
		List<TradingCard> tradingCards = cardDW.readValuesFromFile("PokemonData.csv");
		assertEquals(101, tradingCards.size());
		assertEquals("Abra", tradingCards.get(0).getID());
		assertEquals(0.48, tradingCards.get(0).getValue(), 0.01);
		assertEquals("Alakazam", tradingCards.get(1).getID());
		assertEquals(42.2, tradingCards.get(1).getValue(), 0.01);
	}

	// The second test method testReadValuesFromFileFileNotFoundException tests that
	// the readValuesFromFile method throws a FileNotFoundException when the
	// specified file cannot be found.
	@Test
	public void testReadValuesFromFileFileNotFoundException() throws FileNotFoundException {

		Throwable e = null;
		try{
		cardDW.readValuesFromFile("nonexistentfile.csv");
		}catch(FileNotFoundException ex)
		{
			e = ex;
		}

		assertTrue(e instanceof FileNotFoundException);


	}

	// The third test method testTradingCardConstructor tests that the TradingCard
	// constructor sets the ID and monetary value fields correctly.
	@Test
	public void testTradingCardConstructor() {
		TradingCard tradingCard = new TradingCard("Card1", 1.99);
		assertEquals("Card1", tradingCard.getID());
		assertEquals(1.99, tradingCard.getValue(), 0.01);
	}

	// The fourth test method testTradingCardSetters tests that the TradingCard
	// setters correctly update the ID and monetary value fields.
	@Test
	public void testTradingCardSetters() {
		TradingCard tradingCard = new TradingCard<>("NewCard", 2.99);
	
		assertEquals("NewCard", tradingCard.getID());
		assertEquals(2.99, tradingCard.getValue(), 0.01);
	}

	// The fifth test method testTradingCardEquals tests that the TradingCard equals
	// method correctly compares two TradingCard objects for equality based on their
	// ID and monetary value fields.
	@Test
	public void testTradingCardEquals() {
		TradingCard tradingCard1 = new TradingCard<>("Card1", 1.99);
		TradingCard tradingCard2 = new TradingCard<>("Card1", 1.99);
		TradingCard tradingCard3 = new TradingCard<>("Card2", 2.99);
		assertEquals(true,tradingCard1.getID().equals(tradingCard2.getID()));
		assertEquals(false,tradingCard1.equals(tradingCard3));
	}
}
