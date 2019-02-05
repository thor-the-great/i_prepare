package random_problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 368. Largest Divisible Subset
 * Medium
 *
 * Given a set of distinct positive integers, find the largest subset such that every pair (Si, Sj) of elements in
 * this subset satisfies:
 *
 * Si % Sj = 0 or Sj % Si = 0.
 *
 * If there are multiple solutions, return any subset is fine.
 *
 * Example 1:
 *
 * Input: [1,2,3]
 * Output: [1,2] (of course, [1,3] will also be ok)
 * Example 2:
 *
 * Input: [1,2,4,8]
 * Output: [1,2,4,8]
 */
public class LargestDivisibleSubset {

    /**
     * Idea: sort, then start searching for the largest element (from the end of sorted array). Start element will be
     * the one from dp with the highest value
     *
     * @param nums
     * @return
     */
    public List<Integer> largestDivisibleSubset(int[] nums) {
        List<Integer> res = new ArrayList();
        int N = nums.length;
        if (N == 0) {
            return res;
        }

        Arrays.sort(nums);

        int[] dp = new int[N];
        Arrays.fill(dp, 1);

        for (int i = 1; i < N; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (nums[i] % nums[j] == 0) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        int maxIdx = 0;
        int dpMax = dp[maxIdx];
        for(int i = 1; i < N; i++) {
            if (dp[i] > dpMax) {
                maxIdx = i;
                dpMax = dp[maxIdx];
            }
        }

        int tmp = nums[maxIdx];
        int dpIdx = dp[maxIdx];
        for (int i = maxIdx; i >= 0; i--) {
            if (tmp % nums[i] == 0 && dp[i] == dpIdx) {
                res.add(nums[i]);
                tmp = nums[i];
                dpIdx--;
            }
        }

        return res;
    }
}
