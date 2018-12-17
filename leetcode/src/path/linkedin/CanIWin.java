package path.linkedin;

import java.util.HashMap;
import java.util.Map;

/**
 * 464. Can I Win
 * Medium
 *
 * In the "100 game," two players take turns adding, to a running total, any integer from 1..10. The player who first
 * causes the running total to reach or exceed 100 wins.
 *
 * What if we change the game so that players cannot re-use integers?
 *
 * For example, two players might take turns drawing from a common pool of numbers of 1..15 without replacement until
 * they reach a total >= 100.
 *
 * Given an integer maxChoosableInteger and another integer desiredTotal, determine if the first player to move can
 * force a win, assuming both players play optimally.
 *
 * You can always assume that maxChoosableInteger will not be larger than 20 and desiredTotal will not be larger
 * than 300.
 *
 * Example
 *
 * Input:
 * maxChoosableInteger = 10
 * desiredTotal = 11
 *
 * Output:
 * false
 *
 * Explanation:
 * No matter which integer the first player choose, the first player will lose.
 * The first player can choose an integer from 1 up to 10.
 * If the first player choose 1, the second player can only choose integers from 2 up to 10.
 * The second player will win by choosing 10 and get a total = 11, which is >= desiredTotal.
 * Same with other integers chosen by the first player, the second player will always win.
 */
public class CanIWin {
    Map<Integer, Boolean> turns;
    boolean[] used;

    /**
     * Idea - use DP up-down + backtracking. Try every possible turn memorize possibility to win in cache. It's
     * possible to win if current turn is possible and next turn for opponent is not possible.
     * Key for the turn created by bit mask of the used boolean array - as per requirements there are up to 20 numbers
     * possible, so it will fit Integer.
     * @param maxChoosableInteger
     * @param desiredTotal
     * @return
     */
    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        if (desiredTotal <= 0)
            return true;
        int maxSum = (maxChoosableInteger*(maxChoosableInteger + 1)) /2;
        if (maxSum < desiredTotal)
            return false;
        used = new boolean[maxChoosableInteger + 1];
        turns = new HashMap();
        return helper(desiredTotal);
    }

    boolean helper(int num) {
        if (num <= 0)
            return false;
        int turnKey = boolToInt(used);
        if (!turns.containsKey(turnKey)) {
            for (int i = 1; i < used.length; i++) {
                if (!used[i]) {
                    used[i] = true;
                    if (!helper(num - i)) {
                        turns.put(turnKey, true);
                        used[i] = false;
                        return true;
                    }
                    used[i] = false;
                }
            }
            turns.put(turnKey, false);
        }
        return turns.get(turnKey);
    }

    int boolToInt(boolean[] bools) {
        int res = 0;
        for (int i = 1; i < bools.length; i++) {
            int bit = 0;
            if (bools[i])
                bit = 1 << i;
            res |= bit;
        }
        return res;
    }
}
