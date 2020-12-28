package arrays;

/**
 * 1685. Sum of Absolute Differences in a Sorted Array
User Accepted:2374
User Tried:3148
Total Accepted:2438
Total Submissions:5177
Difficulty:Medium
You are given an integer array nums sorted in non-decreasing order.

Build and return an integer array result with the same length as nums such that result[i] is equal to the summation of absolute differences between nums[i] and all the other elements in the array.

In other words, result[i] is equal to sum(|nums[i]-nums[j]|) where 0 <= j < nums.length and j != i (0-indexed).

 

Example 1:

Input: nums = [2,3,5]
Output: [4,3,5]
Explanation: Assuming the arrays are 0-indexed, then
result[0] = |2-2| + |2-3| + |2-5| = 0 + 1 + 3 = 4,
result[1] = |3-2| + |3-3| + |3-5| = 1 + 0 + 2 = 3,
result[2] = |5-2| + |5-3| + |5-5| = 3 + 2 + 0 = 5.
Example 2:

Input: nums = [1,4,6,8,10]
Output: [24,15,13,15,21]
 

Constraints:

2 <= nums.length <= 105
1 <= nums[i] <= nums[i + 1] <= 104
https://leetcode.com/problems/sum-of-absolute-differences-in-a-sorted-array/

*/
public class SumOfAbsoluteDifferences {

    /**
     * Get initial sum. With every next elements fmoving left to right we :
     * - add left elements (if compare to previous sum) by dif between i and (i - 1)  i times (moving line up, bottom segment, it getting bigger)
     * - subtract right elements by same dif (i - (i - 1)) but (N - i) times (moving line up, upper segment, it getting smaller)
     * 
     * @param nums
     * @return
     */
    public int[] getSumAbsoluteDifferences(int[] nums) {
        int N = nums.length;
        int[] res = new int[N];
        for (int i = 1; i < N ; i++) {
            res[0] += (nums[i] - nums[0]);
        }
        for (int i = 1; i < N ; i++) {
            res[i] = res[i - 1];
            res[i] += ((nums[i] - nums[i - 1])*i);
            res[i] -= ((nums[i] - nums[i - 1])*(N - i));
        }
        return res;
    }
}
