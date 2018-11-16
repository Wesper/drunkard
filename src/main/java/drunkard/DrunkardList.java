package drunkard;

import java.util.ArrayList;
import java.util.Collections;

public class DrunkardList {

    private static ArrayList<Integer> cardsIds = new ArrayList<Integer>();
    private static ArrayList<Integer> cardsIdPlayer1 = new ArrayList<Integer>();
    private static ArrayList<Integer> cardsIdPlayer2 = new ArrayList<Integer>();
    private static int firstPlayerScore = 18, secondPlayerScore = 18;
    private static int round = 0;

    private enum Suit {
        HEART, DIAMOND, CLUB, SPADE;

        private static Suit get(int cardId){
            return values()[cardId / 9];
        }
    }

    private enum Value{
        SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE;

        private static Value get(int cardId){
            return values()[cardId % 9];
        }
    }

    static {
        for(int i = 0; i < 36; i++)
            cardsIds.add(i);

        Collections.shuffle(cardsIds);

        for (int i = 0; i < 18; i++)
            cardsIdPlayer1.add(cardsIds.get(i));

        for (int i = 18; i < 36; i++)
            cardsIdPlayer2.add(cardsIds.get(i));

    }

    public static void main(String... __) {

        while (firstPlayerScore != 0 && secondPlayerScore != 0) {
            roundInfo();
            roundWinner(cardsIdPlayer1.get(0), cardsIdPlayer2.get(0));
            round++;
        }

        whoIsWinner(firstPlayerScore);
    }

    private static String toString(int cardId){
        return Value.get(cardId) + " " + Suit.get(cardId);
    }

    private static void roundInfo(){
        System.out.println();
        System.out.println("Round " + round);
        System.out.println("First player: " + toString(cardsIdPlayer1.get(0)));
        System.out.println("Second player: " + toString(cardsIdPlayer2.get(0)));
    }

    private static void whoIsWinner(int firstPlayerScore){
        if (firstPlayerScore == 0) {
            System.out.println("Second player in this game!!!");
        } else {
            System.out.println("First player in this game!!!");
        }
    }

    private static void roundWinner(int player1, int player2){
        if (player1 % 9 == player2 % 9)
            firstWinRound();
        else if (player1 % 9 == 0 && player2 % 9 == 8)
            firstWinRound();
        else if (player1 % 9 == 8 && player2 % 9 == 0)
            secondWinRound();
        else if (player1 % 9 < player2 % 9)
            secondWinRound();
        else firstWinRound();
    }

    public static void firstWinRound(){
        firstPlayerScore++;
        secondPlayerScore--;
        System.out.println("First player win in round!");
        cardsIdPlayer1.add(cardsIdPlayer1.get(0));
        cardsIdPlayer1.add(cardsIdPlayer2.get(0));
        cardsIdPlayer1.remove(0);
        cardsIdPlayer2.remove(0);
    }

    public static void secondWinRound(){
        firstPlayerScore--;
        secondPlayerScore++;
        System.out.println("Second player win in round!");
        cardsIdPlayer2.add(cardsIdPlayer2.get(0));
        cardsIdPlayer2.add(cardsIdPlayer1.get(0));
        cardsIdPlayer1.remove(0);
        cardsIdPlayer2.remove(0);
    }
}
