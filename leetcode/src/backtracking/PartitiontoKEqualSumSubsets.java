package backtracking;

/**
 * 698. Partition to K Equal Sum Subsets
Medium

Given an integer array nums and an integer k, return true if it is possible to divide this array into k 
non-empty subsets whose sums are all equal. 

Example 1:

Input: nums = [4,3,2,3,5,2,1], k = 4
Output: true
Explanation: It's possible to divide it into 4 subsets (5), (1, 4), (2,3), (2,3) with equal sums.

Example 2:

Input: nums = [1,2,3,4], k = 3
Output: false

Constraints:

    1 <= k <= nums.length <= 16
    1 <= nums[i] <= 104
    The frequency of each element is in the range [1, 4].

 */
public class PartitiontoKEqualSumSubsets {
    int[] nums;
    boolean[] used;
    
    /**
     * Backtracking, start filling backets and mark used indexes via boolean array. If sum reached target - 
     * mark basket as full and switch to next one with 0 as starting sum
     * 
     * Space - O(n) for callstack
     * time - ??? :(
     */
    public boolean canPartitionKSubsets(int[] nums, int k) {
        this.nums = nums;
        used = new boolean[nums.length];
        int sum = 0;
        for (int n : nums) sum +=n;
        if (sum % k != 0) {
            return false;
        }
        return dfs(k, 0, sum/k, 0);
    }
    
    boolean dfs(int k, int curSum, int targetSum, int idx) {
        if (k == 1)
            return true;
        
        if (curSum == targetSum) {
            return dfs(k - 1, 0, targetSum, 0);
        }
        
        for (int i = idx; i < nums.length; i++) {
            if (used[i])
                continue;
            used[i] = true;
            if (curSum  + nums[i] <= targetSum && dfs(k, curSum + nums[i], targetSum, i + 1))
                return true;
            used[i] = false;
        }
        return false;
    }    
}
