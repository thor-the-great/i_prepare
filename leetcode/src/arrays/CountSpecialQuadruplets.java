package arrays;

/**
 * 1995. Count Special Quadruplets
Easy

Given a 0-indexed integer array nums, return the number of distinct quadruplets (a, b, c, d) such that:

    nums[a] + nums[b] + nums[c] == nums[d], and
    a < b < c < d

 

Example 1:

Input: nums = [1,2,3,6]
Output: 1
Explanation: The only quadruplet that satisfies the requirement is (0, 1, 2, 3) because 1 + 2 + 3 == 6.

Example 2:

Input: nums = [3,3,6,4,5]
Output: 0
Explanation: There are no such quadruplets in [3,3,6,4,5].

Example 3:

Input: nums = [1,1,1,3,5]
Output: 4
Explanation: The 4 quadruplets that satisfy the requirement are:
- (0, 1, 2, 3): 1 + 1 + 1 == 3
- (0, 1, 3, 4): 1 + 1 + 3 == 5
- (0, 2, 3, 4): 1 + 1 + 3 == 5
- (1, 2, 3, 4): 1 + 1 + 3 == 5

 

Constraints:

    4 <= nums.length <= 50
    1 <= nums[i] <= 100

    https://leetcode.com/problems/count-special-quadruplets/
 */
public class CountSpecialQuadruplets {

    /**
     * Similar to 2 sum, just the order matters. we can simplify to find count of pairs where
     * nums[d] - nums[c] = nums[a] + nums[b]
     * 
     * use array or map to store count of diff of sums
     * 
     * runtime - O(n^2)
     *
     */    
    public int countQuadruplets(int[] nums) {
        int N = nums.length;
    
        int[] counts = new int[301];
        counts[nums[N - 1] - nums[N - 2] + 100] = 1;
        
        int res = 0;
        for (int r = N - 3; r >= 1; r--) {
            for (int l = r - 1; l >= 0; l--) {
                res += counts[nums[r] + nums[l] + 100];
            }
            
            for (int i = N - 1; i > r; i--) {
                counts[nums[i] - nums[r] + 100] += 1;
            }
        }
        
        return res;
    }
}
