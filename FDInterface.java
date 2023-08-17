/*
 * This interface defines the methods that will be used by the front end to effectively
 * call back end methods, and create a command loop that will let users input many
 * different commands
 */
public interface FDInterface {
  
// public CardFrontend(Scanner input, BDInterface backend);
  
  public void hr();
  
  public void commandLoop();

  public String printPromptingMenu();
  
  public void loadDataFromFile(String filename);

  public void insertSingleData(Object ID, double value);

  // public TradingCardInterface removeSingleData(Object ID, double value);

  public void displaySummary();

  public TradingCardInterface[] getCardsAboveValue(double value);
}

