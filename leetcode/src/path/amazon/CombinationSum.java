package path.amazon;

import java.util.ArrayList;
import java.util.List;

/**
 * Combination Sum
 *
 * Given a set of candidate numbers (candidates) (without duplicates) and a target number (target), find all unique
 * combinations in candidates where the candidate numbers sums to target.
 *
 * The same repeated number may be chosen from candidates unlimited number of times.
 *
 * Note:
 * All numbers (including target) will be positive integers.
 * The solution set must not contain duplicate combinations.
 * Example 1:
 *
 * Input: candidates = [2,3,6,7], target = 7,
 * A solution set is:
 * [
 *   [7],
 *   [2,2,3]
 * ]
 * Example 2:
 *
 * Input: candidates = [2,3,5], target = 8,
 * A solution set is:
 * [
 *   [2,2,2,2],
 *   [2,3,3],
 *   [3,5]
 * ]
 */
public class CombinationSum {

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList();
        helper(res, candidates, target, 0, new ArrayList(), 0);
        return res;
    }

    void helper(List<List<Integer>> res, int[] candidates, int target, int sumSoFar, List<Integer> arr, int startIndex) {
        for (int i = startIndex; i < candidates.length; i++) {
            int candidate = candidates[i];
            int sum = sumSoFar + candidate;
            if (sum <= target) {
                arr.add(candidate);
                if (sum == target) {
                    List<Integer> resList = new ArrayList(arr);
                    res.add(resList);
                } else {
                    helper(res, candidates, target, sum, arr, i);
                }
                arr.remove(arr.size() - 1);
            }
        }
    }
}
