import java.io.FileNotFoundException;
import java.util.List;
public interface DWInterface {

    // public DWInterface();
    public List<TradingCard> readValuesFromFile(String filename) throws FileNotFoundException;

}
