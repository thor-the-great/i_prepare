package random_problems;

/**
 * 52. N-Queens II
 * Hard
 *
 * The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each
 * other.
 *
 *
 *
 * Given an integer n, return the number of distinct solutions to the n-queens puzzle.
 *
 * Example:
 *
 * Input: 4
 * Output: 2
 * Explanation: There are two distinct solutions to the 4-queens puzzle as shown below.
 * [
 *  [".Q..",  // Solution 1
 *   "...Q",
 *   "Q...",
 *   "..Q."],
 *
 *  ["..Q.",  // Solution 2
 *   "Q...",
 *   "...Q",
 *   ".Q.."]
 * ]
 *
 */
public class NQueensII {

    int res;

    public int totalNQueens(int n) {
        res = 0;
        helper(new int[n], 0);
        return res;
    }

    void helper(int[] sol, int idx) {
        if (idx == sol.length) {
            res++;
            return;
        }

        for (int i = 0; i < sol.length; i++) {
            if (check(sol, i, idx)) {
                sol[idx] = i;
                helper(sol, idx + 1);
            }
        }
    }

    boolean check(int[] sol, int pos, int pos2) {
        for (int i = 0; i < pos2; i++) {
            int el = sol[i];
            if ((el == pos) || (pos2 - i) == Math.abs(pos - el))
                return false;
        }
        return true;
    }
}
