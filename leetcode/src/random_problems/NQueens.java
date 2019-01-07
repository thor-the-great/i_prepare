package random_problems;

import java.util.ArrayList;
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
 * Explanation: There exist two distinct solutions to the 4-queens puzzle as shown above.
 */
public class NQueens {
    List<List<String>> res;
    int N;
    public List<List<String>> solveNQueens(int n) {
        res = new ArrayList();
        N = n;
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
            if (sol.get(i) == pos)
                return false;
            if ((pos2 - i) == Math.abs(pos - sol.get(i)))
                return false;
        }
        return true;
    }

    void solToBoard(List<Integer> sol) {
        List<String> board = new ArrayList();
        StringBuilder sb = new StringBuilder();
        for (int r = 0; r < N; r ++) {
            sb.setLength(0);
            int pos = sol.get(r);
            for (int c =0; c < N; c++) {
                if (c != pos)
                    sb.append('.');
                else
                    sb.append('Q');
            }
            board.add(sb.toString());
        }
        res.add(board);
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
