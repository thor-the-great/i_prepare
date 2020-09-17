package arrays;

/**
 * 1574. Shortest Subarray to be Removed to Make Array Sorted
 * Medium
 *
 * Given an integer array arr, remove a subarray (can be empty) from arr such that the remaining elements in arr are non-decreasing.
 *
 * A subarray is a contiguous subsequence of the array.
 *
 * Return the length of the shortest subarray to remove.
 *
 *
 *
 * Example 1:
 *
 * Input: arr = [1,2,3,10,4,2,3,5]
 * Output: 3
 * Explanation: The shortest subarray we can remove is [10,4,2] of length 3. The remaining elements after that will be [1,2,3,3,5] which are sorted.
 * Another correct solution is to remove the subarray [3,10,4].
 * Example 2:
 *
 * Input: arr = [5,4,3,2,1]
 * Output: 4
 * Explanation: Since the array is strictly decreasing, we can only keep a single element. Therefore we need to remove a subarray of length 4, either [5,4,3,2] or [4,3,2,1].
 * Example 3:
 *
 * Input: arr = [1,2,3]
 * Output: 0
 * Explanation: The array is already non-decreasing. We do not need to remove any elements.
 * Example 4:
 *
 * Input: arr = [1]
 * Output: 0
 *
 *
 * Constraints:
 *
 * 1 <= arr.length <= 10^5
 * 0 <= arr[i] <= 10^9
 *
 * https://leetcode.com/problems/shortest-subarray-to-be-removed-to-make-array-sorted/
 */
public class ShoertestSubarrayToBeRemovedToMakeArraySorted {
    /**
     * Idea - the part to remove will be not sorted for either of ends for sure. This gives us first approximation
     * start going to the right and compare if i <= i + 1, then go from end to the left and do the same. This gives
     * interval l to r. Then start comparing the rest of elements and check which one to remove and which gives smaller
     * internal part to remove.
     * O(n) time
     * O(1) space
     * @param arr
     * @return
     */
    public int findLengthOfShortestSubarray(int[] arr) {
        int l = 0, N = arr.length;
        while (l + 1 < N && arr[l] <= arr[l + 1]) {
            ++l;
        }
        if (l == N - 1)
            return 0;
        int r = N - 1;
        while (r - 1 >= 0 && arr[r - 1] <= arr[r]) {
            --r;
        }

        int res = Math.min(r, N - l - 1);

        int i = 0;
        while (i <= l && r < N) {
            if (arr[i] <= arr[r]) {
                res = Math.min(res, r - i - 1);
                ++i;
            } else {
                ++r;
            }
        }
        return res;
    }
}
