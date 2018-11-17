package drunkard;

import java.util.ArrayList;
import java.util.Collections;

public class DrunkardList {

    private static final int COUNT_CARDS = 36;
    private static final int HALF_COUNT_CARDS = 18;

    private static ArrayList<Integer> cardsIds = new ArrayList<Integer>();
    private static ArrayList<Integer> cardsIdPlayer1 = new ArrayList<Integer>();
    private static ArrayList<Integer> cardsIdPlayer2 = new ArrayList<Integer>();
    private static Integer cardIdPlayer1;
    private static Integer cardIdPlayer2;
    private static int firstPlayerScore = HALF_COUNT_CARDS;
    private static int secondPlayerScore = HALF_COUNT_CARDS;
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
        for(int i = 0; i < COUNT_CARDS;)
            cardsIds.add(i++);

        Collections.shuffle(cardsIds);

        for (int i = 0; i < HALF_COUNT_CARDS;)
            cardsIdPlayer1.add(cardsIds.get(i++));

        for (int i = HALF_COUNT_CARDS; i < COUNT_CARDS;)
            cardsIdPlayer2.add(cardsIds.get(i++));

    }

    public static void main(String... __) {

        while (firstPlayerScore != 0 && secondPlayerScore != 0) {

            cardIdPlayer1 = cardsIdPlayer1.get(0);
            cardIdPlayer2 = cardsIdPlayer2.get(0);

            roundInfo(cardIdPlayer1, cardIdPlayer2);
            roundWinner(cardIdPlayer1, cardIdPlayer2);
            round++;
        }

        whoIsWinner(firstPlayerScore);
    }

    private static String toString(int cardId){
        return Value.get(cardId) + " " + Suit.get(cardId);
    }

    private static void roundInfo(int cardIdPlayer1, int cardIdPlayer2){
        System.out.println();
        System.out.println("Round " + round);
        System.out.println("First player: " + toString(cardIdPlayer1));
        System.out.println("Second player: " + toString(cardIdPlayer2));
    }

    private static void whoIsWinner(int firstPlayerScore){
        if (firstPlayerScore == 0) {
            System.out.println();
            System.out.println("Second player win in this game!!!");
        } else {
            System.out.println();
            System.out.println("First player win in this game!!!");
        }
    }

    private static void roundWinner(int player1, int player2){
        int value1 = player1 % 9;
        int value2 = player2 % 9;
        if (value1 == value2)
            dispute();
        else if (value1 == 0 && value2 == 8)
            firstWinRound();
        else if (value1 == 8 && value2 == 0)
            secondWinRound();
        else if (value1 < value2)
            secondWinRound();
        else firstWinRound();
    }

    private static void firstWinRound(){
        firstPlayerScore++;
        secondPlayerScore--;
        System.out.println("First player win in round!");
        cardsIdPlayer1.add(cardIdPlayer1);
        cardsIdPlayer1.add(cardIdPlayer2);
        cardsIdPlayer1.remove(0);
        cardsIdPlayer2.remove(0);
    }

    private static void secondWinRound(){
        firstPlayerScore--;
        secondPlayerScore++;
        System.out.println("Second player win in round!");
        cardsIdPlayer2.add(cardIdPlayer2);
        cardsIdPlayer2.add(cardIdPlayer1);
        cardsIdPlayer1.remove(0);
        cardsIdPlayer2.remove(0);
    }

    private static void dispute(){
        System.out.println("Duspute players!");
        cardsIdPlayer2.add(cardIdPlayer2);
        cardsIdPlayer1.add(cardIdPlayer1);
        cardsIdPlayer1.remove(0);
        cardsIdPlayer2.remove(0);
    }
}
