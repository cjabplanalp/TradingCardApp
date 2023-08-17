run: CardApp.class
	java CardApp

CardApp.class: CardApp.java CardDW.java CardAE.java TradingCard.class RedBlackTree.java RBTIterator.java CardBD.class CardFD.class
	javac CardApp.java

runFrontendDeveloperTests: FrontendDeveloperTests.java CardFD.class
	javac -cp .:junit5.jar FrontendDeveloperTests.java
	java -jar junit5.jar -cp . -c FrontendDeveloperTests

CardFD.class: CardFD.java FDInterface.java CardBD.class
	javac CardFD.java FDInterface.java

CardBD.class: CardBD.java BDInterface.java TradingCard.class
	javac CardBD.java BDInterface.java

TradingCard.class: TradingCard.java TradingCardInterface.java
	javac TradingCard.java TradingCardInterface.java

runAlgorithmEngineerTests: AlgorithmEngineerTests.class
	java -jar junit5.jar -cp . -c AlgorithmEngineerTests

AlgorithmEngineerTests.class: AlgorithmEngineerTests.java CardAE.java RedBlackTree.java RBTIterator.java TradingCard.java
	javac -cp .:junit5.jar AlgorithmEngineerTests.java

runDataWranglerTests: DataWranglerTests.class
	java -jar junit5.jar -cp . -c DataWranglerTests

DataWranglerTests.class: DataWranglerTests.java CardDW.java TradingCard.java
	javac -cp .:junit5.jar DataWranglerTests.java

runBackendDeveloperTests: BackendDeveloperTests.class
	java -jar junit5.jar -cp . -c BackendDeveloperTests

BackendDeveloperTests.class: BackendDeveloperTests.java CardBD.java RedBlackTree.java TradingCard.java  
	javac -cp .:junit5.jar BackendDeveloperTests.java

clean:
	rm *.class
