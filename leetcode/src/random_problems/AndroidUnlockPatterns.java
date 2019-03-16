package random_problems;

/**
 * 351. Android Unlock Patterns
 * Medium
 *
 * Given an Android 3x3 key lock screen and two integers m and n, where 1 ≤ m ≤ n ≤ 9,
 * count the total number of unlock patterns of the Android lock screen, which consist
 * of minimum of m keys and maximum n keys.
 *
 *
 *
 * Rules for a valid pattern:
 *
 * Each pattern must connect at least m keys and at most n keys.
 * All the keys must be distinct.
 * If the line connecting two consecutive keys in the pattern passes through any other
 * keys, the other keys must have previously selected in the pattern. No jumps through
 * non selected key is allowed.
 * The order of keys used matters.
 *
 *
 *
 *
 *
 * Explanation:
 *
 * | 1 | 2 | 3 |
 * | 4 | 5 | 6 |
 * | 7 | 8 | 9 |
 * Invalid move: 4 - 1 - 3 - 6
 * Line 1 - 3 passes through key 2 which had not been selected in the pattern.
 *
 * Invalid move: 4 - 1 - 9 - 2
 * Line 1 - 9 passes through key 5 which had not been selected in the pattern.
 *
 * Valid move: 2 - 4 - 1 - 3 - 6
 * Line 1 - 3 is valid because it passes through key 2, which had been selected in the
 * pattern
 *
 * Valid move: 6 - 5 - 4 - 1 - 9 - 2
 * Line 1 - 9 is valid because it passes through key 5, which had been selected in the
 * pattern.
 *
 *
 *
 * Example:
 *
 * Input: m = 1, n = 1
 * Output: 9
 */
public class AndroidUnlockPatterns {

    boolean[] board;
    int res;
    int m, n;

    /**
     * Idea : use backtracking technique, try every "valid" number, save it in state
     * grid, then backtrack after.
     * @param m
     * @param n
     * @return
     */
    public int numberOfPatterns(int m, int n) {
        if ( n == 0)
            return 0;
        this.m = m;
        this.n = n;
        board = new boolean[9];
        res = 0;
        for (int i = 0; i < 9; i++) {
            board[i] = true;
            helper(1, i);
            board[i] = false;
        }
        return res;
    }

    void helper(int count, int cell) {
        if (count >=m ) {
            res++;
        }
        if (count == n) {
            return;
        }


        for (int i = 0; i < 9; i++) {
            if (check(i, cell)) {
                board[i] = true;
                helper(count + 1, i);
                board[i] = false;
            }
        }
    }

    boolean check(int cell, int prev) {
        if (board[cell])
            return false;

        if ((cell + prev) % 2 == 1)
            return true;

        // indexes are at both end of the diagonals for example 0,0, and 8,8
        int mid = (cell + prev)/2;
        if (mid == 4)
            return board[mid];
        // adjacent cells on diagonal  - for example 0,0 and 1,0 or 2,0 and //1,1
        if ((cell % 3 != prev % 3) && (cell/3 != prev/3)) {
            return true;
        }
        // all other cells which are not adjacent
        return board[mid];
    }
}
