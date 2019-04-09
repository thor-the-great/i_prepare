package random_problems;

import java.util.HashMap;
import java.util.Map;

/**
 * 523. Continuous Subarray Sum
 * Medium
 *
 * Given a list of non-negative numbers and a target integer k, write a function to check if the array has a
 * continuous subarray of size at least 2 that sums up to the multiple of k, that is, sums up to n*k where n is
 * also an integer.
 *
 *
 *
 * Example 1:
 *
 * Input: [23, 2, 4, 6, 7],  k=6
 * Output: True
 * Explanation: Because [2, 4] is a continuous subarray of size 2 and sums up to 6.
 * Example 2:
 *
 * Input: [23, 2, 6, 4, 7],  k=6
 * Output: True
 * Explanation: Because [23, 2, 6, 4, 7] is an continuous subarray of size 5 and sums up to 42.
 *
 *
 * Note:
 *
 * The length of the array won't exceed 10,000.
 * You may assume the sum of all the numbers is in the range of a signed 32-bit integer.
 */
public class ContinuousSubarraySum {

    /**
     * Idea: Required sum can be represented like a sum of modulo. Then if sum is divisible on k then is will start and
     * ends on the same number (like a circle). We create a map and keep every modulo of sum along with the index. On
     * each step we check if we met this number before. If so - we made a circle and sum is possible. The number of
     * elements in between calculated as difference between current and saved indexes.
     * @param nums
     * @param k
     * @return
     */
    public boolean checkSubarraySum(int[] nums, int k) {
        int N = nums.length;
        if (N == 0)
            return false;
        Map<Integer, Integer> m = new HashMap();
        int sum = 0;
        m.put(0, -1);
        for (int i = 0; i < N; i++) {
            sum += nums[i];
            if (k != 0 )
                sum %= k;
            if (m.containsKey(sum)) {
                if (i - m.get(sum) >= 2)
                    return true;
            } else {
                m.put(sum, i);
            }
        }
        return false;
    }
}
