import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CardDW implements DWInterface {
	
	public int size;
    //constructor
    public CardDW() {
        this.size = 0;
    }

	@SuppressWarnings({ "unchecked", "rawtypes" })

	@Override
	public List<TradingCard> readValuesFromFile(String filename) throws FileNotFoundException {
		List<TradingCard> tradingCards = new ArrayList<TradingCard>();
		try {
			Scanner scanner = new Scanner(new File(filename));
			while (scanner.hasNext()) {
				String line = scanner.nextLine();
				String[] cards =  line.split(",");
				TradingCard tradingCard = new TradingCard(cards[0], Double.parseDouble(cards[1]));
				tradingCards.add(tradingCard);
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			System.err.println("File not found: " + filename);
			throw e;
		}
		return tradingCards;
	}
}