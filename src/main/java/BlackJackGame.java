import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class BlackJackGame {

    public static ArrayList<Card> hit(ArrayList<Card> deck, Player player, Dealer dealer) {
        Scanner myObj = new Scanner(System.in);
        for (int i = 0; i < 2; i++) {
            deck = player.hit(dealer, true);
        }
        deck = dealer.hit(deck, true);
        deck = dealer.hit(deck, false);

        System.out.println(player.name + ":");
        player.printHand();
        System.out.println("Dealer " + dealer.name + ":");
        dealer.printHand();

        System.out.println(player.name + " place your bet: ");
        player.placeBet(Double.parseDouble(myObj.nextLine()));

        return deck;
    }

    public static void repeatHit(String hitOrNot, ArrayList<Card> deck, Player player, Dealer dealer) {
        if (Objects.equals(hitOrNot, "y")) {
            deck = player.hit(dealer, true);
            System.out.println(player.name + ":");
            player.printHand();
            System.out.println("Dealer " + dealer.name + ":");
            dealer.printHand();
        } else if (Objects.equals(hitOrNot, "n")) {
            System.out.println("Dealer " + dealer.name + " turn");
            for (Card card : dealer.handCards) {
                card.isVisible = true;
            }
            if (dealer.handValue < 17) {
                dealer.hit(deck, true);
            }
            dealer.printHand();
            results(player, dealer);
        }
    }

    public static void results(Player player, Dealer dealer) {
        System.out.println("Results: ");
        System.out.println(player.name + ":");
        player.printHand();
        System.out.println("Dealer " + dealer.name + ":");
        dealer.printHand();

        if (player.handValue == dealer.handValue) {
            System.out.println("No one wins!");
        } else if (
                (player.handValue == 21)
                        || (player.handValue < 21 && player.handValue > dealer.handValue)
                        || (dealer.handValue > 21 && player.handValue < 21)
        ) {
            System.out.println(player.name + " wins! Player won " + player.bet + "$");
            System.out.println("Player " + player.name + " balance: " + (player.money + player.bet * 2) + "$");
        } else {
            System.out.println("House wins! Player lost " + player.bet + "$");
            System.out.println("Player " + player.name + " balance: " + player.money + "$");
        }
    }

    public static String verify(ArrayList<Card> deck, Player player, Dealer dealer, String hitAgain) {
        Scanner myObj = new Scanner(System.in);
        if (!(dealer.handValue >= 21) && !(player.handValue >= 21)) {
            System.out.println(player.name + " do you want to hit? (y/n)");
            hitAgain = myObj.nextLine();
            repeatHit(hitAgain, deck, player, dealer);
        } else if (dealer.handValue == 21 && player.handValue == 21) {
            String[] arg = new String[0];
            main(arg);
        } else if (dealer.handValue == 21 || player.handValue == 21) {
            results(player, dealer);
        }
        return hitAgain;
    }

    public static void main(String[] args) {
        DeckFactory deckFactory = new DeckFactory();
        Dealer dealer = new Dealer(deckFactory, "Bob");
        ArrayList<Card> deck = DeckFactory.getDeck();

        Scanner myObj = new Scanner(System.in);
        System.out.println("WELCOME TO BLACKJACK \nTAKE A SEAT");
        System.out.println("Hi visitor, how is your name: ");
        String playerName = myObj.nextLine();
        Player player = new Player(playerName);

        System.out.println("Enter the amount of $ to your account: ");
        player.money = Double.parseDouble(myObj.nextLine());

        deck = hit(deck, player, dealer);
        String hitAgain = "";
        hitAgain = verify(deck, player, dealer, hitAgain);

        if (Objects.equals(hitAgain, "y")) hitAgain = verify(deck, player, dealer, hitAgain);
        if (Objects.equals(hitAgain, "y")) {
            verify(deck, player, dealer, hitAgain);
            results(player, dealer);
        }

        System.out.println("\nDo you want to play again ? (y/n)");
        String hitOrNot = myObj.nextLine();

        if (Objects.equals(hitOrNot, "y")) {
            main(args);
        }

    }

}
