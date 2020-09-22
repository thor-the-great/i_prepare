package map;

import java.util.HashMap;
import java.util.Map;

/**
 * 1577. Number of Ways Where Square of Number Is Equal to Product of Two Numbers
 * Medium
 *
 * Given two arrays of integers nums1 and nums2, return the number of triplets formed (type 1 and type 2) under the following rules:
 *
 * Type 1: Triplet (i, j, k) if nums1[i]2 == nums2[j] * nums2[k] where 0 <= i < nums1.length and 0 <= j < k < nums2.length.
 * Type 2: Triplet (i, j, k) if nums2[i]2 == nums1[j] * nums1[k] where 0 <= i < nums2.length and 0 <= j < k < nums1.length.
 *
 *
 * Example 1:
 *
 * Input: nums1 = [7,4], nums2 = [5,2,8,9]
 * Output: 1
 * Explanation: Type 1: (1,1,2), nums1[1]^2 = nums2[1] * nums2[2]. (4^2 = 2 * 8).
 * Example 2:
 *
 * Input: nums1 = [1,1], nums2 = [1,1,1]
 * Output: 9
 * Explanation: All Triplets are valid, because 1^2 = 1 * 1.
 * Type 1: (0,0,1), (0,0,2), (0,1,2), (1,0,1), (1,0,2), (1,1,2).  nums1[i]^2 = nums2[j] * nums2[k].
 * Type 2: (0,0,1), (1,0,1), (2,0,1). nums2[i]^2 = nums1[j] * nums1[k].
 * Example 3:
 *
 * Input: nums1 = [7,7,8,3], nums2 = [1,2,9,7]
 * Output: 2
 * Explanation: There are 2 valid triplets.
 * Type 1: (3,0,2).  nums1[3]^2 = nums2[0] * nums2[2].
 * Type 2: (3,0,1).  nums2[3]^2 = nums1[0] * nums1[1].
 * Example 4:
 *
 * Input: nums1 = [4,7,9,11,23], nums2 = [3,5,1024,12,18]
 * Output: 0
 * Explanation: There are no valid triplets.
 *
 *
 * Constraints:
 *
 * 1 <= nums1.length, nums2.length <= 1000
 * 1 <= nums1[i], nums2[i] <= 10^5
 *
 * https://leetcode.com/problems/number-of-ways-where-square-of-number-is-equal-to-product-of-two-numbers/
 */
public class NumberOfWaysWhereSquareEqualToProduct {

    /**
     * Put squares of all numbers from array1 to map1, sq-key, quantity - key
     * Similarly put quantities of every number from array2 to map2
     * Now iterate over map2 and check every number if it has a pair that forms any key from map1.
     * If pair is there we have to cases -
     * - if two numbers are different - add to res qty_of_num1*qty_of_num2. Also this gonna be 2X because we'll count it for
     * other number too
     * - this is a square root of the number - need to count qty*(qty-1)
     * then this qty we need to multiply on times we have this number in map1
     * @param nums1
     * @param nums2
     * @return
     */
    public int numTriplets(int[] nums1, int[] nums2) {
        return helper(nums1, nums2) + helper(nums2, nums1);
    }

    int helper(int[] nums1, int[] nums2) {
        Map<Long, Integer> map1 = new HashMap(), map2 = new HashMap();
        for (int num1 : nums1) {
            long x = (long)num1*num1;
            map1.put((long)x, map1.getOrDefault((long)x, 0) + 1);
        }

        for (int num2 : nums2) {
            map2.put((long)num2, map2.getOrDefault((long)num2, 0) + 1);
        }

        int res = 0;
        long doubleRes = 0;
        for (long key2 : map2.keySet()) {
            for (long key1 : map1.keySet()) {
                long compl = key1/key2;
                if (key1 % key2 == 0 && map2.containsKey(compl)) {
                    if (compl == key2) {
                        long part1 = (map2.get(key2) * (map2.get(key2) - 1))/2;
                        res+=part1*map1.get(key1);
                    } else {
                        doubleRes+= map2.get(key2) * map2.get(compl)*map1.get(key1);
                    }
                }
            }
        }
        res += (doubleRes/2);
        return res;
    }
}
