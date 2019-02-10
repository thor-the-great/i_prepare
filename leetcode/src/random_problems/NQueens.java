package random_problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 51. N-Queens
 * Hard
 *
 * The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each
 * other.
Ro *
 * Given an integer n, return all distinct solutions to the n-queens puzzle.
 *
 * Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a
 * queen and an empty space respectively.
 *
 * Example:
 *
 * Input: 4
 * Output: [
 *  [".Q..",  // Sol2 1
 *   "...Q",
 *   "Q...",
 *   "..Q."],
 *
 *  ["..Q.",  // Sol2 2
 *   "Q...",
 *   "...Q",
 *   ".Q.."]
 * ]
 * Explanation: There exist two distinct solutions to the 4-queens puzzle as shown above.
 */
public class NQueens {
    List<List<String>> res;
    int N;
    char[] oneRow;

    /**
     * Idea: backtracking, check each solution in turns, rollback immediately if ti's invalid.
     * Fill the board - copy prefilled row of "." and replace "Q" in one position
     * @param n
     * @return
     */
    public List<List<String>> solveNQueens(int n) {
        res = new ArrayList();
        N = n;
        oneRow = new char[n];
        Arrays.fill(oneRow, '.');
        helper(new ArrayList());
        return res;
    }

    void helper(List<Integer> sol) {
        if (sol.size() == N) {
            solToBoard(sol);
            return;
        }
        for (int i = 0; i < N; i++) {
            if (check(sol, i)) {
                sol.add(i);
                helper(sol);
                sol.remove(sol.size() - 1);
            }
        }
    }

    boolean check(List<Integer> sol, int pos) {
        int pos2 = sol.size();
        for (int i = 0; i < pos2; i++) {
            int el = sol.get(i);
            if ((el == pos) || (pos2 - i) == Math.abs(pos - el))
                return false;
        }
        return true;
    }

    void solToBoard(List<Integer> sol) {
        List<String> board = new ArrayList();
        for (int r = 0; r < N; r ++) {
            board.add(new String(getFilledRow(sol.get(r))));
        }
        res.add(board);
    }

    char[] getFilledRow(int p) {
        char[] nextRow = Arrays.copyOf(oneRow, oneRow.length);
        nextRow[p] = 'Q';
        return nextRow;
    }

    public static void main(String[] args) {
        NQueens obj = new NQueens();
        List<List<String>> res = obj.solveNQueens(4);
        for (List<String> sol : res) {
            System.out.print("[");
            sol.forEach(s->System.out.print(s + "\n"));
            System.out.print("]");
            System.out.print("\n");
        }
    }
}
