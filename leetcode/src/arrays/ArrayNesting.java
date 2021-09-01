package arrays;

/**
 * 565. Array Nesting
Medium

You are given an integer array nums of length n where nums is a permutation of the numbers in the range [0, n - 1].

You should build a set s[k] = {nums[k], nums[nums[k]], nums[nums[nums[k]]], ... } subjected to the following rule:

The first element in s[k] starts with the selection of the element nums[k] of index = k.
The next element in s[k] should be nums[nums[k]], and then nums[nums[nums[k]]], and so on.
We stop adding right before a duplicate element occurs in s[k].
Return the longest length of a set s[k].

 

Example 1:

Input: nums = [5,4,0,3,1,6,2]
Output: 4
Explanation: 
nums[0] = 5, nums[1] = 4, nums[2] = 0, nums[3] = 3, nums[4] = 1, nums[5] = 6, nums[6] = 2.
One of the longest sets s[k]:
s[0] = {nums[0], nums[5], nums[6], nums[2]} = {5, 6, 2, 0}
Example 2:

Input: nums = [0,1,2]
Output: 1
 

Constraints:

1 <= nums.length <= 105
0 <= nums[i] < nums.length
All the values of nums are unique.

https://leetcode.com/problems/array-nesting/
 */
public class ArrayNesting {

    /**
     * Brute force - go by index from 0 to len, start checking every element using the rule next = nums[cur] while next != nums[index]
     * A lot of repetitions, we can see that in cycle if we start from any element will end up at the same cycle, just shorter. This means
     * we can check the cycle once and every next bump into element of the cycle can be ignored.
     * For that we need to save visited elements. First we can use array of boolean for that, optmimiztion is - to use elements of array itself.
     * 
     * O(n) runtime,
     * O(1) space (change original array)
     * 
     * @param nums
     * @return
     */
    public int arrayNesting(int[] nums) {
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != Integer.MAX_VALUE) {
                int cur = nums[i], c = 0;
                while (nums[i] != Integer.MAX_VALUE) {
                    int t = cur;
                    cur = nums[cur];
                    c++;
                    nums[t] = Integer.MAX_VALUE;
                }
                res = Math.max(res, c);
            }
        }
        return res;
    }
}
