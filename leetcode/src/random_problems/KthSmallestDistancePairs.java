package random_problems;

import java.util.Arrays;

/**
 * 719. Find K-th Smallest Pair Distance
 * Hard
 *
 * Given an integer array, return the k-th smallest distance among all the pairs. The distance of a pair (A, B) is
 * defined as the absolute difference between A and B.
 *
 * Example 1:
 * Input:
 * nums = [1,3,1]
 * k = 1
 * Output: 0
 * Explanation:
 * Here are all the pairs:
 * (1,3) -> 2
 * (1,1) -> 0
 * (3,1) -> 2
 * Then the 1st smallest distance pair is (1,1), and its distance is 0.
 * Note:
 * 2 <= len(nums) <= 10000.
 * 0 <= nums[i] < 1000000.
 * 1 <= k <= len(nums) * (len(nums) - 1) / 2.
 *
 */
public class KthSmallestDistancePairs {

    /**
     * binary search based on distance plus sliding window to count number of pairs
     *
     * @param nums
     * @param k
     * @return
     */
    public int smallestDistancePair(int[] nums, int k) {
        Arrays.sort(nums);
        int N = nums.length;

        int small = 0;
        int big = nums[N - 1] - nums[0];

        while (small < big ) {
            int mid = (big + small) / 2;
            int c = 0, l = 0, r = 0;
            for (; r < N; ++r) {
                while (nums[r] - nums[l] > mid)
                    l++;
                c += r - l;
            }
            if (c >= k)
                big = mid;
            else
                small = mid + 1;
        }
        return small;
    }
}
