package binary_search;

/**
 * 162. Find Peak Element
 * Medium
 *
 * A peak element is an element that is greater than its neighbors.
 *
 * Given an input array nums, where nums[i] ≠ nums[i+1], find a peak element and return its index.
 *
 * The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.
 *
 * You may imagine that nums[-1] = nums[n] = -∞.
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,1]
 * Output: 2
 * Explanation: 3 is a peak element and your function should return the index number 2.
 * Example 2:
 *
 * Input: nums = [1,2,1,3,5,6,4]
 * Output: 1 or 5
 * Explanation: Your function can return either index number 1 where the peak element is 2,
 *              or index number 5 where the peak element is 6.
 * Note:
 *
 * Your solution should be in logarithmic complexity.
 */
public class FindPeakElement {
    /**
     * Two solutions, binary search, based on template 2 and 3.
     * @param nums
     * @return
     */
    public int findPeakElement(int[] nums) {
        if (nums.length == 1)
            return 0;

        int l = 0, r = nums.length - 1;

        while (l < r) {
            int m = l + (r - l)/2;
            if (nums[m] > nums[m + 1]) {
                r = m;
            }
            else
                l = m + 1;
        }

        return l;
    }


    public int findPeakElementTemplate3(int[] nums) {
        int N = nums.length;
        int l = 0, r = N - 1;

        while (l + 1 < r) {
            int m = l + (r - l)/2;
            if ((m == N - 1 || nums[m] > nums[m + 1])
                    && (m == 0 || nums[m] > nums[m - 1]))
                return m;

            if (nums[m] < nums[m + 1])
                l = m;
            else
                r = m;
        }
        if ((l == 0 || nums[l] > nums[l - 1]) && (l == (N - 1) || nums[l] > nums[l + 1]))
            return l;
        return r;
    }
}
