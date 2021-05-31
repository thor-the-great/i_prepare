package sorting;

/**
 * 164. Maximum Gap
Hard

Given an integer array nums, return the maximum difference between two successive elements in its sorted form. If the array 
contains less than two elements, return 0.

You must write an algorithm that runs in linear time and uses linear extra space.

 

Example 1:

Input: nums = [3,6,9,1]
Output: 3
Explanation: The sorted form of the array is [1,3,6,9], either (3,6) or (6,9) has the maximum difference 3.

Example 2:

Input: nums = [10]
Output: 0
Explanation: The array contains less than 2 elements, therefore return 0.


Constraints:

    1 <= nums.length <= 104
    0 <= nums[i] <= 109

https://leetcode.com/problems/maximum-gap/

 */
public class MaximumGap {

    /**
     * Idea - bucket sort, build the bucket in a way that min element and max elements that form the max gap are in
     * different buckets. For that bucket size must be small enough:
     * max _of_array - max_of_array / (n - 1)
     * Later we put elements to buckets - O(n), then scan all buckets - O(n). Max gap only possible among max of prev bucket and
     * min of next bucket.
     * O(n) time
     * O(n) space
     * 
     * @param nums
     * @return
     */
    public int maximumGap(int[] nums) {
        int N = nums.length;
        
        if (N  < 2)
            return 0;
        
        int min = nums[0], max = nums[0];
        for (int n : nums) {
            min = Math.min(min, n);
            max = Math.max(max, n);
        }
        
        int oneBuck = (int) Math.ceil((double)(max - min)/ (N - 1));
        
        int[] mins = new int[N - 1], maxs = new int[N - 1];
        Arrays.fill(mins, Integer.MAX_VALUE);
        Arrays.fill(maxs, Integer.MIN_VALUE);
        for (int n : nums) {
            if (n == min || n == max)
                continue;
            
            int idx = (n - min) / oneBuck;
            mins[idx] = Math.min(mins[idx], n);
            maxs[idx] = Math.max(maxs[idx], n);
        }
        
        int prev = min;
        int res = Integer.MIN_VALUE;
        
        for (int i = 0; i < N - 1; i++) {
            if (mins[i] == Integer.MAX_VALUE && maxs[i] == Integer.MIN_VALUE) {
                continue;
            }
            res = Math.max(res, mins[i] - prev);
            prev = maxs[i];
        }
        return Math.max(res, max - prev);
    }
}
