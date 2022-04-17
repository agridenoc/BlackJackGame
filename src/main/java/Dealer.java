import java.util.*;

public class Dealer {
    public DeckFactory deckFactory;
    public String name;
    public int handValue;
    public static Map<String, String> namesOfCards = new HashMap<String, String>();
    public ArrayList<Card> handCards = new ArrayList<Card>();

    public Dealer(DeckFactory deckFactory, String name) {
        this.deckFactory = deckFactory;
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

    public Card drawACard(ArrayList<Card> deck) {
        Card lastCard = deck.get(deck.size() - 1);

//        ! Use the commented part of code to make sure that we take the last card from the deck
//        System.out.println("stop list");
//        for (Card card : deck) {
//            System.out.println(card.face + ' ' + card.suit);
//        }
//        System.out.println("stop list");
//        System.out.println(lastCard.face + ' ' + lastCard.suit);

        return lastCard;
    }

    public ArrayList<Card> removeCardFromDeck(ArrayList<Card> deck, Card takenCard) {
        deck.remove(takenCard);
        return deck;
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
        for (Card card : handCards) {
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

    public ArrayList<Card> hit(ArrayList<Card> deck, boolean isVisible) {
        Card card = drawACard(deck);
        card.isVisible = isVisible;
        handCards.add(card);
        calculateHandValue(card);
        return removeCardFromDeck(deck, card);
    }
}
