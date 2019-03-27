package path.uber;

/**
 * 34. Find First and Last Position of Element in Sorted Array
 * Medium
 *
 * Given an array of integers nums sorted in ascending order, find the starting and ending position of a given
 * target value.
 *
 * Your algorithm's runtime complexity must be in the order of O(log n).
 *
 * If the target is not found in the array, return [-1, -1].
 *
 * Example 1:
 *
 * Input: nums = [5,7,7,8,8,10], target = 8
 * Output: [3,4]
 * Example 2:
 *
 * Input: nums = [5,7,7,8,8,10], target = 6
 * Output: [-1,-1]
 */
public class SearchRange {

    /**
     * Idea - do two binary searches.
     * First for left part, condition is - while arr[i] < target - move left pointer, otherwise - move right
     * Then for right part - while arr[i] > target - move right pointer, otherwise - move left
     * Catch - do + 1 to the middle for right part
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] searchRange(int[] nums, int target) {
        int N = nums.length;
        int[] res = new int[] {-1, -1};

        if ( N == 0)
            return res;

        int l = 0, r = N - 1;
        //search for left bound
        while (l < r) {
            int m = l + (r - l) /2 ;

            if (nums[m] < target)
                l = m + 1;
            else
                r = m;
        }

        if (nums[l] != target)
            return res;

        res[0] = l;

        //now right bound
        r = N - 1;
        while (l < r) {
            int m = 1 + l + (r - l) / 2;

            if (nums[m] > target)
                r = m - 1;
            else
                l = m;
        }
        res[1] = r;

        return res;
    }
}
