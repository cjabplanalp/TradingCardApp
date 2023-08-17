import java.io.FileNotFoundException;
import java.util.List;

public class CardBD<T> implements BDInterface<T> {
  CardDW data;
  CardAE tree;

  public CardBD(CardDW data, CardAE tree) {
    this.data = data;
    this.tree = tree;
  }

  @Override
  public TradingCard[] sortCards(double minVal) throws IllegalArgumentException {

    List<TradingCard> n = tree.iterateOver(minVal);
    
    TradingCard[] toReturn = new TradingCard[n.size()];
    
    for(int i = 0 ; i < n.size(); i++)
    {
      toReturn[i] = n.get(i);
    }

    if (toReturn.length == 0) {
      throw new IllegalArgumentException();
    }

    return  toReturn;
  }

  @Override
  public void loadData(String data) throws FileNotFoundException {
       List<TradingCard> toAdd = this.data.readValuesFromFile(data);
    for(int i = 0; i < toAdd.size(); i++)
    {
      insert((T) toAdd.get(i).getID(), toAdd.get(i).getValue());
    }
  }

  @Override
  public TradingCard getLow() {
    return (TradingCard) tree.getLowest();
  }

  @Override
  public TradingCard getHigh() {
    return (TradingCard) tree.getHighest();
  }

  @Override
  public double getRange() {
    return ((TradingCard)tree.getHighest()).getValue() - ((TradingCard)tree.getLowest()).getValue();
  }

  @Override
  public double getValue(TradingCard t) {
    return t.getValue();
  }

  @Override
  public double getMean() {
    return tree.getTotalValue()/tree.getSize();
  }



  @Override
  public void insert(T ID, double value) throws IllegalArgumentException {
    if(ID == null)
    {
      throw new IllegalArgumentException();
    }
    if(value < 0)
    {
      throw new IllegalArgumentException("Value must be greater than 0");
    }
    if(ID instanceof String || ID instanceof Integer)
    {
      tree.insert((Comparable) ID, value);
    }else {
      throw new IllegalArgumentException("Error: ID must be a String or and Integer");
    }
    
    

  }



}
