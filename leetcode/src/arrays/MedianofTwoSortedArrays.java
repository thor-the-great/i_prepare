package arrays;

/**
 * 4. Median of Two Sorted Arrays
Hard

Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.

 

Example 1:

Input: nums1 = [1,3], nums2 = [2]
Output: 2.00000
Explanation: merged array = [1,2,3] and median is 2.

Example 2:

Input: nums1 = [1,2], nums2 = [3,4]
Output: 2.50000
Explanation: merged array = [1,2,3,4] and median is (2 + 3) / 2 = 2.5.

Example 3:

Input: nums1 = [0,0], nums2 = [0,0]
Output: 0.00000

Example 4:

Input: nums1 = [], nums2 = [1]
Output: 1.00000

Example 5:

Input: nums1 = [2], nums2 = []
Output: 2.00000

 

Constraints:

    nums1.length == m
    nums2.length == n
    0 <= m <= 1000
    0 <= n <= 1000
    1 <= m + n <= 2000
    -106 <= nums1[i], nums2[i] <= 106

 
Follow up: The overall run time complexity should be O(log (m+n)).
 * 
 * https://www.youtube.com/watch?v=LPFhl65R7ww
 */
public class MedianofTwoSortedArrays {

    /**
     * Key ideas:
     * length of each group is equal. Max in left group is less or eq to min in right group.
     * 
     * Use binary search, starting with indexes of shorter array. short part + long part = (shortLen + longLen + 1) / 2
     * using this knowing shortPart we can compute the longPart. 
     * 
     * Condition when right partition point found:
     * max of (left from short, left from long) <= min of (right fron short, right from long)
     * 
     * if not:
     *  if max left short  > min right long - we to far to the right, need to decrease right
     * otherwise - decrease left
     * 
     * O(log (min (len (n, m)))) time
     *
     * O(1) space 
     * 
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums2.length < nums1.length)
            return findMedianSortedArrays(nums2, nums1);
        
        int shortLen = nums1.length, longLen = nums2.length;
        int l = 0, r = shortLen;
        
        while (l <= r) {
            int partShort = (l + r) / 2;
            int partLong = ((shortLen + longLen + 1)/2) - partShort;
            
            int maxLeftShort = (partShort == 0) ? Integer.MIN_VALUE : nums1[partShort - 1];
            int minRightShort = (partShort == shortLen) ? Integer.MAX_VALUE : nums1[partShort];
            
            int maxLeftLong = (partLong == 0) ? Integer.MIN_VALUE : nums2[partLong - 1];
            int minRightLong = (partLong == longLen) ? Integer.MAX_VALUE : nums2[partLong];
            
            //checking conditions
            
            if (maxLeftShort <= minRightLong && maxLeftLong <= minRightShort) {
                if ((shortLen + longLen) % 2 == 1) {
                    return (double) Math.max(maxLeftShort, maxLeftLong);
                } else {
                    return (double) (Math.max(maxLeftShort, maxLeftLong) + Math.min(minRightShort, minRightLong)) / 2;
                }
            } else if (maxLeftShort > minRightLong) {
                r = partShort - 1;
            } else {
                l = partShort + 1;
            }
        }
        
        throw new RuntimeException("error");
    }
}
