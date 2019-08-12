import java.util.ArrayList;

/**
 * Pascal's Triangle New
 *     Arrays Numbers Puzzles Dynamic Programming
 * Given an input parameter numRows, generate the first numRows number of rows of Pascal's triangle. As a quick
 * refresher - in a Pascal's triangle, each number is equal to the sum of the two directly above it.
 *
 * Example:
 *
 * Input  : 4
 * Output :
 *         [
 *              [1],
 *             [1,1],
 *            [1,2,1],
 *           [1,3,3,1]
 *
 *         ]
 */
public class PascalTraingle {

    public static ArrayList<ArrayList<Integer>> generatePascalTriangle(int numRows) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        if (numRows >= 1) {
            ArrayList<Integer> row = new ArrayList<>();
            row.add(1);
            res.add(row);
        }

        for (int i = 2; i <= numRows; i++) {
            helper(res);
        }
        return res;
    }

    static void helper(ArrayList<ArrayList<Integer>> res) {
        ArrayList<Integer> prevRow = res.get(res.size() - 1);
        ArrayList<Integer> row = new ArrayList<>();
        row.add(1);
        for (int i = 0; i < prevRow.size() - 1; i++) {
            row.add(prevRow.get(i) + prevRow.get(i + 1));
        }
        row.add(1);
        res.add(row);
    }
}
