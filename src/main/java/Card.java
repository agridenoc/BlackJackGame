import java.util.Arrays;

public class Card {

    public String face;
    public String suit;
    public int cardValue = 0;
    public boolean isVisible = true;

    public Card(String face, String suit) {
        this.face = face;
        this.suit = suit;
    }


    public String getFace() {
        return this.face;
    }

    public void setFace(String face) {
        this.face = face;
    }

    public String getSuit() {
        return this.suit;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public Integer getCardValue() {
        return this.cardValue;
    }

    public void setCardValue(String cardValue) {
        this.face = cardValue;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(args));
    }

}
