package random_problems;

import java.util.*;

/**
 * 1001. Grid Illumination
 *
 * Difficulty: Hard
 * On a N x N grid of cells, each cell (x, y) with 0 <= x < N and 0 <= y < N has a lamp.
 *
 * Initially, some number of lamps are on.  lamps[i] tells us the location of the i-th lamp that is on.  Each lamp
 * that is on illuminates every square on its x-axis, y-axis, and both diagonals (similar to a Queen in chess).
 *
 * For the i-th query queries[i] = (x, y), the answer to the query is 1 if the cell (x, y) is illuminated, else 0.
 *
 * After each query (x, y) [in the order given by queries], we turn off any lamps that are at cell (x, y) or are
 * adjacent 8-directionally (ie., share a corner or edge with cell (x, y).)
 *
 * Return an array of answers.  Each value answer[i] should be equal to the answer of the i-th query queries[i].
 *
 *
 *
 * Example 1:
 *
 * Input: N = 5, lamps = [[0,0],[4,4]], queries = [[1,1],[1,0]]
 * Output: [1,0]
 * Explanation:
 * Before performing the first query we have both lamps [0,0] and [4,4] on.
 * The grid representing which cells are lit looks like this, where [0,0] is the top left corner, and [4,4] is the
 * bottom right corner:
 * 1 1 1 1 1
 * 1 1 0 0 1
 * 1 0 1 0 1
 * 1 0 0 1 1
 * 1 1 1 1 1
 * Then the query at [1, 1] returns 1 because the cell is lit.  After this query, the lamp at [0, 0] turns off, and
 * the grid now looks like this:
 * 1 0 0 0 1
 * 0 1 0 0 1
 * 0 0 1 0 1
 * 0 0 0 1 1
 * 1 1 1 1 1
 * Before performing the second query we have only the lamp [4,4] on.  Now the query at [1,0] returns 0, because the
 * cell is no longer lit.
 *
 *
 * Note:
 *
 * 1 <= N <= 10^9
 * 0 <= lamps.length <= 20000
 * 0 <= queries.length <= 20000
 * lamps[i].length == queries[i].length == 2
 */
public class GridIllumination {

    /**
     * Similar to NQueens logic, we must optimize boars representation because N could be up to 10^9, so we can't
     * have full board in memory.
     *
     * Instead we just save positions that are lit - column, row and two diagonals (positive slope and negative,
     * meaning row + col and row - col) . The lamp that is on we save in hashset, code it via N * row + col
     * representation. We turning off the lamp we check surounding one by one cell.
     * @param N
     * @param lamps
     * @param queries
     * @return
     */
    public int[] gridIllumination(int N, int[][] lamps, int[][] queries) {
        Map<Integer, Integer> mRows = new HashMap<>();
        Map<Integer, Integer> mCols = new HashMap<>();
        Map<Integer, Integer> mDiagPos = new HashMap<>();
        Map<Integer, Integer> mDiagNeg = new HashMap<>();
        Set<Integer> mLitLamps = new HashSet<>();

        //set initial position
        for(int[] lamp : lamps) {
            int r = lamp[0];
            int c = lamp[1];
            if (!mRows.containsKey(r))
                mRows.put(r, 1);
            else
                mRows.put(r, mRows.get(r) + 1);

            if (!mCols.containsKey(c))
                mCols.put(c, 1);
            else
                mCols.put(c, mCols.get(c) + 1);

            if (!mDiagPos.containsKey(r + c))
                mDiagPos.put(r + c, 1);
            else
                mDiagPos.put(r + c, mDiagPos.get(r + c) + 1);

            if (!mDiagNeg.containsKey(r - c))
                mDiagNeg.put(r - c, 1);
            else
                mDiagNeg.put(r - c, mDiagNeg.get(r - c) + 1);

            mLitLamps.add(N * r + c);
        }

        //start turing off lamps
        int[] res = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int offR = queries[i][0];
            int offC = queries[i][1];

            if ((mRows.containsKey(offR) && mRows.get(offR) > 0)
                    || (mCols.containsKey(offC) && mCols.get(offC) > 0)
                    || (mDiagPos.containsKey(offR + offC) && mDiagPos.get(offR + offC) > 0)
                    || (mDiagNeg.containsKey(offR - offC) && mDiagNeg.get(offR - offC) > 0))
                res[i] = 1;
            else
                res[i] = 0;

            //switch off lamps
            for (int offRow = offR - 1; offRow <= offR + 1; offRow++) {
                for (int offCol = offC - 1; offCol <= offC + 1; offCol++) {
                    if (offRow >=0 && offRow < N && offCol >= 0 && offCol < N) {
                        int lampIdx = N * offRow + offCol;
                        if (mLitLamps.contains(lampIdx)) {
                            mLitLamps.remove(lampIdx);

                            if(mRows.containsKey(offRow))
                                mRows.put(offRow, mRows.get(offRow) - 1);

                            if(mCols.containsKey(offCol))
                                mCols.put(offCol, mCols.get(offCol) - 1);

                            if(mDiagPos.containsKey(offRow + offCol))
                                mDiagPos.put(offRow + offCol, mDiagPos.get(offRow + offCol) - 1);

                            if(mDiagNeg.containsKey(offRow - offCol))
                                mDiagNeg.put(offRow - offCol, mDiagNeg.get(offRow - offCol) - 1);
                        }
                    }
                }
            }
        }

        return res;
    }

    public static void main(String[] args) {
        GridIllumination obj = new GridIllumination();
        int[] res = obj.gridIllumination(5, new int[][] {{0,0}, {4, 4}}, new int[][] {{1,1}, {1, 0}} );
        Arrays.stream(res).forEach(i-> System.out.print(i + " "));
    }
}
