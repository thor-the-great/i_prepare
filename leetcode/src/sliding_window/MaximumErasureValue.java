/**
 * 1695. Maximum Erasure Value
Medium

You are given an array of positive integers nums and want to erase a subarray containing unique elements. The score you get by erasing the subarray is equal to the sum of its elements.

Return the maximum score you can get by erasing exactly one subarray.

An array b is called to be a subarray of a if it forms a contiguous subsequence of a, that is, if it is equal to a[l],a[l+1],...,a[r] for some (l,r).

 

Example 1:

Input: nums = [4,2,4,5,6]
Output: 17
Explanation: The optimal subarray here is [2,4,5,6].
Example 2:

Input: nums = [5,2,1,2,5,2,1,2,5]
Output: 8
Explanation: The optimal subarray here is [5,2,1] or [1,2,5].
 

Constraints:

1 <= nums.length <= 105
1 <= nums[i] <= 104

https://leetcode.com/problems/maximum-erasure-value/

 */
public class MaximumErasureValue {
    
    /**
     * Two pointers - keep left as the array start and move to right. With each new element check if that element wasn't appered before
     * by using Set. Keep current sum and running max
     * 
     * O(n) time
     * O(n) space
     * 
     * @param nums
     * @return
     */
    public int maximumUniqueSubarray(int[] nums) {
        int N = nums.length;
        
        int l = 0, cur = 0, max = 0;
        
        Set<Integer> set = new HashSet();
        for (int i = 0; i < N; i++) {
            int el = nums[i];
            while (set.contains(el)) {
                set.remove(nums[l]);
                cur -= nums[l];
                l++;
            }
            
            set.add(el);
            cur+=el;
            max = Math.max(max, cur);
        }
        
        return max;
    }
}
