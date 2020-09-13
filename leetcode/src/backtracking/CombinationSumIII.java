package backtracking;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class CombinationSumIII {

  /**
   * 216. Combination Sum III
   * Medium
   *
   * Find all possible combinations of k numbers that add up to a number n, given that only numbers from 1 to 9 can be used and each combination should be a unique set of numbers.
   *
   * Note:
   *
   * All numbers will be positive integers.
   * The solution set must not contain duplicate combinations.
   * Example 1:
   *
   * Input: k = 3, n = 7
   * Output: [[1,2,4]]
   * Example 2:
   *
   * Input: k = 3, n = 9
   * Output: [[1,2,6], [1,3,5], [2,3,4]]
   */

  List<List<Integer>> res = new ArrayList();

  /**
   * Try every combination, add next number up to 9 and check if sum forms n.
   * @param k
   * @param n
   * @return
   */
  public List<List<Integer>> combinationSum3(int k, int n) {
    helper(k, n, 0, new LinkedList());
    return res;
  }

  void helper(int k, int n, int curNum, Deque<Integer> curSeq) {
    if (curSeq.size() > k)
      return;

    if (curSeq.size() == k && n == 0) {
      res.add(new ArrayList(curSeq));
      return;
    }

    for (int i = curNum + 1; i <= 9; i++) {
      if (n - i >= 0) {
        curSeq.addLast(i);
        helper(k, n - i, i, curSeq);
        curSeq.pollLast();
      } else {
        break;
      }
    }
  }
}
