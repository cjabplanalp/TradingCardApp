public class TradingCard<T extends Comparable <T>> implements Comparable<TradingCard<T>>, TradingCardInterface<T>{

  private T ID;
  private double value;

  public TradingCard(T ID, double value) throws IllegalArgumentException {
    if(ID == null)
    {
      throw new IllegalArgumentException();
    }
    if(value < 0)
    {
      throw new IllegalArgumentException("Must be a non negative number");
    }
if(ID instanceof String || ID instanceof Integer)
{
    this.ID = ID;
    this.value = value;
}else {
  throw new IllegalArgumentException("Error: ID must be a String or and Integer");
}


  }


  @Override
  public double getValue() {
    return this.value;
  }

  @Override
  public T getID() {
    return this.ID;
  }


  @Override
  public String toString() {
    return "(" + this.ID + ", " + this.value + ")";

  }


  @Override
  public boolean Equals(TradingCardInterface o) {
    if (o instanceof TradingCard) {
      if (this.ID.equals(((TradingCard) o).getID())) {
        if (this.value == ((TradingCard) o).getValue()) {
          return true;
        }
      }
    }
    return false;
  }


  @Override
  public int compareTo(TradingCard<T> o) {
    TradingCard toCompare = (TradingCard) (o);

    if (this.Equals(toCompare)) {
      return 0;
    } else {
      if (this.value < toCompare.value) {
        return -1;
      }
      if (this.value > toCompare.value) {
        return 1;
      }
      if (this.value == toCompare.value) {
        if (this.ID.toString().compareTo(toCompare.getID().toString()) == -1) {
          return -1;
        } else {
          return 1;
        }
      }

    }
    return 0;
  }


}
