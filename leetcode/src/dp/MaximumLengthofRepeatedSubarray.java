/**
 * 718. Maximum Length of Repeated Subarray
Medium

2488

59

Add to List

Share
Given two integer arrays nums1 and nums2, return the maximum length of a subarray that appears in both arrays.

 

Example 1:

Input: nums1 = [1,2,3,2,1], nums2 = [3,2,1,4,7]
Output: 3
Explanation: The repeated subarray with maximum length is [3,2,1].
Example 2:

Input: nums1 = [0,0,0,0,0], nums2 = [0,0,0,0,0]
Output: 5
 

Constraints:

1 <= nums1.length, nums2.length <= 1000
0 <= nums1[i], nums2[i] <= 100

https://leetcode.com/problems/maximum-length-of-repeated-subarray/
 */
public class MaximumLengthofRepeatedSubarray {
    /**
     * Based on DP, dp[i][j] is the max length of arr1[0, i] and arr2[0, j];
     * O(n*m) time
     * O(m) space 
     */
    public int findLength(int[] nums1, int[] nums2) {
        int len2 = nums2.length;
        
        int max = 0;
        int[] dpPrev = new int[len2 + 1];
        for (int i = 0; i < nums1.length; i++) {
            int[] dpCur = new int[len2 + 1];
            for (int j = 0; j < nums2.length; j++) {
                if (nums1[i] == nums2[j]) {
                    dpCur[j + 1] = dpPrev[j] + 1;
                    max = Math.max(max, dpCur[j +1]);
                }
            }
            dpPrev = dpCur;
        }
        return max;
    }
}
