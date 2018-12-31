package path.linkedin;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 254. Factor Combinations
 * Medium
 *
 * Numbers can be regarded as product of its factors. For example,
 *
 * 8 = 2 x 2 x 2;
 *   = 2 x 4.
 * Write a function that takes an integer n and return all possible combinations of its factors.
 *
 * Note:
 *
 * You may assume that n is always positive.
 * Factors should be greater than 1 and less than n.
 * Example 1:
 *
 * Input: 1
 * Output: []
 * Example 2:
 *
 * Input: 37
 * Output:[]
 * Example 3:
 *
 * Input: 12
 * Output:
 * [
 *   [2, 6],
 *   [2, 2, 3],
 *   [3, 4]
 * ]
 * Example 4:
 *
 * Input: 32
 * Output:
 * [
 *   [2, 16],
 *   [2, 2, 8],
 *   [2, 2, 2, 4],
 *   [2, 2, 2, 2, 2],
 *   [2, 4, 4],
 *   [4, 8]
 * ]
 */
public class FactorCombinations {

    List<List<Integer>> res;

    /**
     * Idea: iterate over possible factors one by one, use those that possible potentially.
     * catch: iteration up to Math.sqrt only
     * @param n
     * @return
     */
    public List<List<Integer>> getFactors(int n) {
        res = new LinkedList();
        helper(n, 2, new ArrayList<>());
        return res;
    }

    void helper(int n, int start, List<Integer> p) {
        if (n <= 1 && p.size() > 1) {
            List<Integer> p1 = new ArrayList(p);
            res.add(p1);
            return;
        }
        if (n >= 2) {
            for (int i = start; i <= Math.sqrt(n); i++) {
                if (n % i == 0) {
                    p.add(i);
                    helper(n / i, i, p);
                    p.remove(p.size() - 1);
                }
            }
            p.add(n);
            helper(1, n, p);
            p.remove(p.size() - 1);
        }
    }

    public static void main(String[] args) {
        FactorCombinations obj = new FactorCombinations();
        System.out.println(obj.getFactors(12));
    }
}
