/**
 * 462. Minimum Moves to Equal Array Elements II
Medium

Given an integer array nums of size n, return the minimum number of moves required to make all array elements equal.

In one move, you can increment or decrement an element of the array by 1.

 

Example 1:

Input: nums = [1,2,3]
Output: 2
Explanation:
Only two moves are needed (remember each move increments or decrements one element):
[1,2,3]  =>  [2,2,3]  =>  [2,2,2]
Example 2:

Input: nums = [1,10,2,9]
Output: 16
 

Constraints:

n == nums.length
1 <= nums.length <= 105
-109 <= nums[i] <= 109
 * 
 * https://leetcode.com/problems/minimum-moves-to-equal-array-elements-ii
 */
public class MinimumMovesToEqualElementsII {
    
    /**
     * Idea - one of possible solution is to met at the median, idea is - if array is sorted
     * then meeting point is somewhere between min and max. If we took away min and max then the meeting
     * point will be between new_min and new_max. If we continue then it redices to the array median.
     * 
     * O(nlgn) time - sorting plus O(n) for counting sums
     * O(1) space - no extra memory
     */
    public int minMoves2(int[] nums) {
        int N = nums.length;
        
        Arrays.sort(nums);
        int res = 0;
        int i = 0, j = N - 1;
        while (i < j) {
            res += (nums[j] - nums[i]);
            i++; j--;
        }
        return res;
    }
}
