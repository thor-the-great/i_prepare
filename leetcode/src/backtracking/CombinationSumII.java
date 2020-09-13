package backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 40. Combination Sum II
 * Medium
 *
 * Given a collection of candidate numbers (candidates) and a target number (target), find all unique combinations in candidates where the candidate numbers sums to target.
 *
 * Each number in candidates may only be used once in the combination.
 *
 * Note:
 *
 * All numbers (including target) will be positive integers.
 * The solution set must not contain duplicate combinations.
 * Example 1:
 *
 * Input: candidates = [10,1,2,7,6,1,5], target = 8,
 * A solution set is:
 * [
 *   [1, 7],
 *   [1, 2, 5],
 *   [2, 6],
 *   [1, 1, 6]
 * ]
 * Example 2:
 *
 * Input: candidates = [2,5,2,1,2], target = 5,
 * A solution set is:
 * [
 *   [1,2,2],
 *   [5]
 * ]
 */
public class CombinationSumII {


  List<List<Integer>> res = new ArrayList();

  /**
   * Do the backtracking
   * @param candidates
   * @param target
   * @return
   */
  public List<List<Integer>> combinationSum2(int[] candidates, int target) {
    Arrays.sort(candidates);
    helper(candidates, target, 0, new ArrayList());
    return res;
  }

  void helper(int[] candidates, int target, int idx, List<Integer> curSeq) {
    if (target == 0) {
      res.add(new ArrayList(curSeq));
    }

    for (int i = idx; i < candidates.length; i++) {
      if (target - candidates[i] < 0)
        continue;
      if (i > idx && candidates[i - 1] == candidates[i])
        continue;
      curSeq.add(candidates[i]);
      helper(candidates, target - candidates[i], i + 1, curSeq);
      curSeq.remove(curSeq.size() - 1);
    }
  }
}
