import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Player {
    String name;
    public ArrayList<Card> hand = new ArrayList<Card>();
    int handValue;
    double bet;
    double money;
    public static Map<String, String> namesOfCards = new HashMap<String, String>();

    public Player(String name) {
        this.name = name;
        fillNamesOfCards();
    }

    public static void fillNamesOfCards() {
        namesOfCards.put("2", "Two");
        namesOfCards.put("3", "Three");
        namesOfCards.put("4", "Four");
        namesOfCards.put("5", "Five");
        namesOfCards.put("6", "Six");
        namesOfCards.put("7", "Seven");
        namesOfCards.put("8", "Eight");
        namesOfCards.put("9", "Nine");
        namesOfCards.put("10", "Ten");
        namesOfCards.put("Jack", "Jack");
        namesOfCards.put("Queen", "Queen");
        namesOfCards.put("King", "King");
        namesOfCards.put("Ace", "Ace");
    }

    public void placeBet(double val) {
        this.bet = this.bet + val;
        this.money = this.money - val;
    }

    public void getWin(double val) {
        this.money = this.money + val;
    }

    public ArrayList<Card> hit(Dealer dealer, boolean isVisible) {
        ArrayList<Card> deck = DeckFactory.getDeck();
        Card card = dealer.drawACard(deck);
        card.isVisible = isVisible;
        this.hand.add(card);
        calculateHandValue(card);
        return dealer.removeCardFromDeck(deck, card);
    }

    public void calculateHandValue(Card card) {
        switch (card.face) {
            case "2", "3", "4", "5", "6", "7", "8", "9", "10" -> this.handValue = this.handValue + Integer.parseInt(card.face);
            case "Jack", "Queen", "King", "Ace" -> this.handValue = this.handValue + 10;
            default -> this.handValue = this.handValue;
        }
    }

    public void printHand() {
        String cardNames = "";
        for (Card card : this.hand) {
            if (!card.isVisible) {
                cardNames = cardNames + "[Hidden Card],";
            } else if (Objects.equals(card.face, "Jack") || Objects.equals(card.face, "Queen") || Objects.equals(card.face, "King") || Objects.equals(card.face, "Ace")) {
                cardNames = cardNames + "[" + card.face + " of " + card.suit + "],";
            } else {
                cardNames = cardNames + "[" + namesOfCards.get(card.face) + " of " + card.suit + "],";
            }
        }
        System.out.println(cardNames + " points: " + this.handValue);
    }
}
