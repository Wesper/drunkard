package drunkard;

import org.apache.commons.math3.util.MathArrays;

public class Drunkard {

    private static int[] cardsIds;
    private static int[] cardsIdPlayer1 = new int[36];
    private static int[] cardsIdPlayer2 = new int[36];
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
        cardsIds = new int[] {
                 0,  1,  2,  3,  4,  5,  6,  7,  8,
                 9, 10, 11, 12, 13, 14, 15, 16, 17,
                18, 19, 20, 21, 22, 23, 24, 25, 26,
                27, 28, 29, 30, 31, 32, 33, 34, 35
        };

        MathArrays.shuffle(cardsIds);
        System.arraycopy(cardsIds, 0, cardsIdPlayer1, 0, 18);
        System.arraycopy(cardsIds, 18, cardsIdPlayer2, 0, 18);

    }

    public static void main(String... __) {

        while (firstPlayerScore != 0 && secondPlayerScore != 0) {
            roundInfo();
            roundWinner(cardsIdPlayer1[round], cardsIdPlayer2[round]);
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
        System.out.println("First player: " + toString(cardsIdPlayer1[round]));
        System.out.println("Second player: " + toString(cardsIdPlayer2[round]));
    }

    private static void whoIsWinner(int firstPlayerScore){
        if (firstPlayerScore == 0) {
            System.out.println("Second player in this game!!!");
        } else {
            System.out.println("First player in this game!!!");
        }
    }

    private static void roundWinner(int player1, int player2){
        if (player1 % 9 == player2 % 9) {
            firstPlayerScore++;
            secondPlayerScore--;
            System.out.println("First player win in round!");
        }
        else if (player1 % 9 == 0 && player2 % 9 == 8){
            firstPlayerScore++;
            secondPlayerScore--;
            System.out.println("First player win in round!");
        }
        else if (player1 % 9 == 8 && player2 % 9 == 0){
            firstPlayerScore--;
            secondPlayerScore++;
            System.out.println("Second player win in round!");
        }
        else if (player1 % 9 < player2 % 9){
            firstPlayerScore--;
            secondPlayerScore++;
            System.out.println("Second player win in round!");
        }
        else {
            firstPlayerScore++;
            secondPlayerScore--;
            System.out.println("First player win in round!");
        }
    }
}
