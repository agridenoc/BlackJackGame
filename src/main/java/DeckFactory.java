import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class DeckFactory {
    private static ArrayList<Card> deck;

    public DeckFactory() {
        deck = generateRandomDeck();
    }

    private static final String[] suits = {
            "Diamonds",
            "Clubs",
            "Hearts",
            "Spades"
    };

    private static final String[] faceValues = {
            "2",
            "3",
            "4",
            "5",
            "6",
            "7",
            "8",
            "9",
            "10",
            "Jack",
            "Queen",
            "King",
            "Ace"
    };

    public static ArrayList<Card> getDeck() {
        return deck;
    }

    public static void setDeck(ArrayList<Card> deck) {
        DeckFactory.deck = deck;
    }

    public static ArrayList<Card> generateRandomDeck() {
        ArrayList<Card> myList = new ArrayList<Card>();
        for (String suit : suits) {
            for (String faceValue : faceValues) {
                myList.add(new Card(faceValue, suit));
            }
        }

        Collections.shuffle(myList);
        return myList;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(args));
    }
}
