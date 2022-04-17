import java.util.ArrayList;

public class BlackApp {

    public static void main(String[] args) {
        DeckFactory deckFactory = new DeckFactory();
        ArrayList<Card> deck = DeckFactory.getDeck();

        Dealer dealer = new Dealer(deckFactory, "testName");

        deck = dealer.hit(deck, true);

        dealer.printHand();
    }
}
