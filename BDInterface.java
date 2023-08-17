import java.io.FileNotFoundException;

public interface BDInterface<T> {
  // public BackendDeveloper(DWObject data, AEObject tree)

  @SuppressWarnings("rawtypes")
  /**
   * calling AEInterface to return the tree into a sorted array
   * 
   * @param minVal
   * @return a sorted list of the trading cards in order
   */


  public TradingCard[] sortCards(double minVal);

  /**
   * Gives the file to the Data Wrangler
   * @param data
   * @throws FileNotFoundException if the file does not exist
   */
  public void loadData(String data) throws FileNotFoundException;

  /**
   * 
   * @return The least valued TradingCard in the tree
   */
  public TradingCard getLow();

  /**
   * 
   * @return the highest valued TradingCard in the tree
   */
  public TradingCard getHigh();

  /**
   * 
   * @return the range of the Tree
   */
  public double getRange();

  /**
   * 
   * @param t
   * @return the value of the Trading Card
   */
  public double getValue(TradingCard t);

  /**
   * 
   * @return the Mean of the Tree
   */
  public double getMean();

  
  
  /**
   * 
   * @param ID
   * @param value
   * @throws IllegalArgumentException
   */
  public void insert(T ID, double value) throws IllegalArgumentException;


}

