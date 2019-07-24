package random_problems;

/**
 * 1128. Number of Equivalent Domino Pairs
 * Easy
 *
 * Given a list of dominoes, dominoes[i] = [a, b] is equivalent to dominoes[j] = [c, d] if and only if either
 * (a==c and b==d), or (a==d and b==c) - that is, one domino can be rotated to be equal to another domino.
 *
 * Return the number of pairs (i, j) for which 0 <= i < j < dominoes.length, and dominoes[i] is equivalent to dominoes[j].
 *
 *
 *
 * Example 1:
 *
 * Input: dominoes = [[1,2],[2,1],[3,4],[5,6]]
 * Output: 1
 *
 *
 * Constraints:
 *
 * 1 <= dominoes.length <= 40000
 * 1 <= dominoes[i][j] <= 9
 */
public class NumberOfEquvivalentDominoPairs {

    /**
     * Iterate over the array of dominoes. Make it identical by fliping smaller to be the first. Save number of times
     * we've seen the pair. Of each time > 1 increment result by res - 1
     * @param dominoes
     * @return
     */
    public int numEquivDominoPairs(int[][] dominoes) {
        //we need to acceess indexes from 0 to 99. max number of dominoes is 9
        int[] counts = new int[100];
        int res = 0;
        //check every pair of dominoes
        for (int[] d : dominoes) {
            //get upper and down number, make upper always smaller
            int up = d[0], down = d[1];
            if (down < up) {
                int t =  up;
                up = down; down = t;
            }
            int idx = up * 10 + down;
            //increment number of times we've seen exactly this pair
            counts[idx]++;
            //if more than once
            if (counts[idx] > 1)
                //increment result, every next same pair will contribute on 1 more than a previous one
                res+=counts[idx] - 1;
        }

        return res;
    }
}
