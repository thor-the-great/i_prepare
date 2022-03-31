package random_problems;

/**
 * 287. Find the Duplicate Number
Medium

Given an array of integers nums containing n + 1 integers where each integer is in the range [1, n] inclusive.

There is only one repeated number in nums, return this repeated number.

You must solve the problem without modifying the array nums and uses only constant extra space.

 

Example 1:

Input: nums = [1,3,4,2,2]
Output: 2

Example 2:

Input: nums = [3,1,3,4,2]
Output: 3

 

Constraints:

    1 <= n <= 105
    nums.length == n + 1
    1 <= nums[i] <= n
    All the integers in nums appear only once except for precisely one integer which appears two or more times.

 

Follow up:

    How can we prove that at least one duplicate number must exist in nums?
    Can you solve the problem in linear runtime complexity?

https://leetcode.com/problems/find-the-duplicate-number/    
 */
public class FindDuplicateNumber {
    
    /**
     * Many solutions.
     * This one based on couting bits
     * For each position sum of bits set to 1 is defined - we can check this posistion iterating over
     * 1..n numbers. If we iterate over array and sum up all bits at position we know actual count of bits.
     * If we compare then number for actual will be equal or greater, because of tha duplicate. If thereare 
     * more than 1 duplicate it works as well as count of bits will be even greater.
     * 
     * O(n*m) time - m is number of bits in number ~log(n)
     * O(1) space
     */
    public int findDuplicate(int[] nums) {
        int max = 0;
        for (int n : nums) {
            max = Math.max(n, max);
        }
        int curMask = 1;
        
        int res = 0;
        
        while (curMask <= max) {
            int curBitCount = 0;
            for (int n : nums) {
                if ((n&curMask) > 0) {
                    curBitCount++;
                }
            }
            int baseBitCount = 0;
            for (int n = 1; n <= max; n++) {
                if ((n&curMask) > 0) {
                    baseBitCount++;
                }
            }
            
            if (curBitCount > baseBitCount) {
                res|=curMask;
            }
            curMask<<=1;
        }
        
        return res;
    }    
}
