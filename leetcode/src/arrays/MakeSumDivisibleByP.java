package arrays;

import java.util.HashMap;
import java.util.Map;

/**
 * 1590. Make Sum Divisible by P
 * Medium
 *
 * Given an array of positive integers nums, remove the smallest subarray (possibly empty) such that the sum of the remaining elements is divisible by p. It is not allowed to remove the whole array.
 *
 * Return the length of the smallest subarray that you need to remove, or -1 if it's impossible.
 *
 * A subarray is defined as a contiguous block of elements in the array.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [3,1,4,2], p = 6
 * Output: 1
 * Explanation: The sum of the elements in nums is 10, which is not divisible by 6. We can remove the subarray [4], and the sum of the remaining elements is 6, which is divisible by 6.
 * Example 2:
 *
 * Input: nums = [6,3,5,2], p = 9
 * Output: 2
 * Explanation: We cannot remove a single element to get a sum divisible by 9. The best way is to remove the subarray [5,2], leaving us with [6,3] with sum 9.
 * Example 3:
 *
 * Input: nums = [1,2,3], p = 3
 * Output: 0
 * Explanation: Here the sum is 6. which is already divisible by 3. Thus we do not need to remove anything.
 * Example 4:
 *
 * Input: nums = [1,2,3], p = 7
 * Output: -1
 * Explanation: There is no way to remove a subarray in order to get a sum divisible by 7.
 * Example 5:
 *
 * Input: nums = [1000000000,1000000000,1000000000], p = 3
 * Output: 0
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 109
 * 1 <= p <= 109
 *
 * https://leetcode.com/problems/make-sum-divisible-by-p/
 */
public class MakeSumDivisibleByP {

    /**
     * Idea is that we can analyse only remainders. We can save all the remainders in map (value and index) and analyse as
     * we go. If wwe met the pair - check if this is min of the possible results
     *
     * O(n) time
     * O(n) space
     *
     * @param nums
     * @param p
     * @return
     */
    public int minSubarray(int[] nums, int p) {
        int N = nums.length, res = N;
        int sum = 0;
        for (int n : nums) {
            sum = (sum + n)%p;
        }
        if (sum == 0)
            return 0;

        Map<Integer, Integer> map = new HashMap();
        map.put(0, -1);
        int cur = 0;
        for (int i = 0; i < N; i++) {
            cur = (cur + nums[i]) % p;
            map.put(cur, i);
            int need = (cur - sum + p)%p;
            if (map.containsKey(need)) {
                res = Math.min(res, i - map.get(need));
            }
        }
        return res == N ? -1 : res;
    }
}
