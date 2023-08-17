import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.io.FileNotFoundException;
import java.util.List;

class BackendDeveloperTests {
//test range and get value
  @Test
  void test1() {
RedBlackTree<TradingCard<String>> tree = new RedBlackTree<>();

    CardDW o = new CardDW();
    // CardAE a = new CardAE(tree);
    CardAE a = new CardAE();
    CardBD n = new CardBD(o, a);  
    
    TradingCard card = new TradingCard("Cat", 100.50);
    TradingCard card1 = new TradingCard("Dog", 200.50);
    TradingCard card2 = new TradingCard("Parrot", 50.50);
    
    a.insert(card);
    a.insert(card1);
    a.insert(card2);

    
    assertEquals(150, n.getRange());
    assertEquals(100.50, n.getValue(card));
    
    
    
    
  }
//tests insert 
  @Test
  void test2() {
    //fail("Not yet implemented");
    RedBlackTree<TradingCard<String>> tree = new RedBlackTree<>();
    
    TradingCard card1 = new TradingCard("Cat", 1.0);
    
    TradingCard card2 = new TradingCard("Dog", 3.0);
    
    tree.insert(card1);
    //tree.insert(card2);
    
    //(Cat, 1.0)
    //(Dog, 3.0)
    
    assertEquals("[ (Cat, 1.0) ]", tree.toInOrderString());

    
    
  }
  
  /**
   * Checks the functionality of the card class
   */
  @Test
  void test3(){
    
    //checks the constructor
    Throwable e = null;
    try {
   TradingCard card = new TradingCard(2.2 , 2.5);
    }catch(IllegalArgumentException ex)
    {
      e = ex;
    }
    assertTrue(e instanceof IllegalArgumentException);

    
    TradingCard card = new TradingCard("Selena Gomez", 100.0);
    TradingCard card1 = new TradingCard("Hailey Bieber", 10.0);
    
    assertEquals("Selena Gomez", card.getID());
    assertEquals(100,0, card.getValue());
    
    assertEquals(false, card.Equals(card1));
    assertEquals(1, card.compareTo(card1));
    assertEquals("(Selena Gomez, 100.0)", card.toString());
      
  }
  
//tests highest lowest mean 
  @Test
  void test4() {
    
    CardDW o = new CardDW();
    CardAE a = new CardAE();
    CardBD n = new CardBD(o, a);

    // RedBlackTree<TradingCard<String>> tree = new RedBlackTree<>();
    
    TradingCard card = new TradingCard("Cat", 100.50);
    TradingCard card1 = new TradingCard("Dog", 200.50);
    TradingCard card2 = new TradingCard("Parrot", 50.50);
    
    a.insert(card);
    a.insert(card1);
    a.insert(card2);
    
    //highest
    
    assertEquals(card1.toString(), n.getHigh().toString());
    
    assertEquals(card2.toString(), n.getLow().toString());
    //lowest
    
    //mean
    // System.out.println(a.getSize());
    // assertEquals(117.16666666666667, a.getTotalValue());
    
    
    
    
    


  }
  
//tests sorting
  
  @Test
  void test5() {
//  RedBlackTree<TradingCard<String>> tree = new RedBlackTree<>();
    
    CardDW o = new CardDW();
    CardAE a = new CardAE();

 
 
    CardBD n = new CardBD(o, a);

    TradingCard card = new TradingCard("Cat", 100.50);
    TradingCard card1 = new TradingCard("Dog", 200.50);
    TradingCard card2 = new TradingCard("Parrot", 50.50);
    
    a.insert(card);
    a.insert(card1);
    a.insert(card2);
    
    TradingCard[] check = n.sortCards(100);
    String checkA = "";
    for(int i = 0; i < check.length; i++)
    {
      checkA += check[i].toString();
    }
    
    assertEquals("(Cat, 100.5)(Dog, 200.5)", checkA);
    
    
   
  }


//tests loading data with Data Wrangler
@Test
void integrationTest1() throws FileNotFoundException
{
CardDW dw = new CardDW();
CardAE ae = new CardAE();
CardBD bd = new CardBD(dw, ae);
Throwable e = null;
try{
bd.loadData("Data.csv");
}catch(FileNotFoundException ex)
{
e = ex;
}
assertTrue(e instanceof FileNotFoundException);

bd.loadData("PokemonData.csv");
assertEquals("(Charizard, 339.02)", bd.getHigh().toString());
assertEquals("(Potion, 0.18)", bd.getLow().toString());

}

//tests sort and mean with AE




@Test
void integrationtest2(){

CardDW dw = new CardDW();
CardAE ae = new CardAE();
CardBD bd = new CardBD(dw, ae);

TradingCard card = new TradingCard("Cat", 100.50);
    TradingCard card1 = new TradingCard("Dog", 200.50);
    TradingCard card2 = new TradingCard("Parrot", 50.50);
    
    ae.insert(card);
    ae.insert(card1);
    ae.insert(card2);

TradingCard[] check = bd.sortCards(100.0);
String results = "";
for(int i = 0; i < check.length; i++)
{

results += check[i].toString();
}

assertEquals("(Cat, 100.5)(Dog, 200.5)", results);
}



@Test
void  codeReviewOfFrontendDeveloper()
{
Scanner n = new Scanner(System.in);
CardAE ae = new CardAE();
CardDW dw = new CardDW();
CardBD bd = new CardBD(dw,ae);
CardFD fd = new CardFD(n,bd);


//l
assertEquals("L", fd.printPromptingMenu());

//i
assertEquals("I", fd.printPromptingMenu());
//p
assertEquals("P", fd.printPromptingMenu());
//g
assertEquals("G", fd.printPromptingMenu());
//q
assertEquals("Q", fd.printPromptingMenu());
}

@Test
void codeReviewOfDataWrangler() throws FileNotFoundException
{
CardDW dw = new CardDW();

Throwable e = null;
try{
dw.readValuesFromFile("Cat.csv");
}catch(FileNotFoundException ex)
{
e = ex;
}

assertTrue(e instanceof FileNotFoundException);




Throwable p = null;
try{
dw.readValuesFromFile("PokemonData.csv");
}catch(FileNotFoundException j)
{
p = j;
}

assertFalse(p instanceof FileNotFoundException);
}
}
