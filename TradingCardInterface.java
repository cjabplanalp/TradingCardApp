
public interface TradingCardInterface<T extends Comparable<T>> {
  // value of a trading card must be non-null and positive
  // public TradingCrading(T ID, double value) throws IllegalArgumentException
  //Comparable<MyQueue<E>>


  /**
   * Returns the monetary value of the trading card
   * 
   * @return the value of a trading card
   */
  public double getValue();

  /**
   * 
   * @return the ID of the TradingCard
   */
  public T getID();


  /**
   * 
   * @return returns the card to a string formatted in (ID, Value)
   */
  @Override
  public String toString();

  /**
   * checks if one card is the same as the other (same id and value)
   * 
   * @param o trading card to compare
   * @return true if the cards are equal and false otherwise
   */
  public boolean Equals(TradingCardInterface o);

 



}

