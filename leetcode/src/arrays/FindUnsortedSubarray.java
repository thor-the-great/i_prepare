package arrays;

/**
 * 581. Shortest Unsorted Continuous Subarray
 * Easy
 *
 * Given an integer array, you need to find one continuous subarray that if you only sort this subarray in ascending
 * order, then the whole array will be sorted in ascending order, too.
 *
 * You need to find the shortest such subarray and output its length.
 *
 * Example 1:
 * Input: [2, 6, 4, 8, 10, 9, 15]
 * Output: 5
 * Explanation: You need to sort [6, 4, 8, 10, 9] in ascending order to make the whole array sorted in ascending order.
 * Note:
 * Then length of the input array is in range [1, 10,000].
 * The input array may contain duplicates, so ascending order here means <=.
 */
public class FindUnsortedSubarray {

    /**
     * Need to find the left and right most elements that is not in a right place. We can do it scanning array in each
     * direction and keeping max (for left->right and finding right most) and min (for right->left and finding
     * left most). If such element exists means that array exists (could be just 1 element) so return r - l + 1
     * Otherwise return 0
     * @param nums
     * @return
     */
    public int findUnsortedSubarray(int[] nums) {
        int N = nums.length;
        //going left to right, keep track of the right most element not in the place
        int r = -1, max = nums[0];
        for (int i = 1; i < N; i++) {
            max = Math.max(max, nums[i]);
            r = (nums[i] < max) ? i : r;
        }
        //going right to left, keep track of the left most element not in the place
        int l = -1, min = nums[N - 1];
        for (int i = N - 2; i >= 0; i--) {
            min = Math.min(min, nums[i]);
            l = (nums[i] > min) ? i : l;
        }
        //return length of the sub-array or 0 if no such elements found
        return l == -1 ? 0 : r - l + 1;
    }
}
