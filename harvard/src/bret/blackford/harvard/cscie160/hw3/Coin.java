package bret.blackford.harvard.cscie160.hw3;

import java.util.Random;

public enum Coin {
    HEADS,
    TAILS;

    /** Attempts to replicate the random nature of a coin toss.  Useful for modeling random events durring various program operations.  (Example: HEADS and a person wants to go to a differnt floor)
     * @return
     */
    public static String toss() {
        int side = new Random().nextInt(2);
        if (side == 1) {
            return "HEADS";
        } else {
            return "TAILS";
        }
    }
}
