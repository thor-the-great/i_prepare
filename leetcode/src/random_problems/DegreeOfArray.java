package random_problems;

import java.util.HashMap;
import java.util.Map;

/**
 * 697. Degree of an Array
 * Easy
 *
 * Given a non-empty array of non-negative integers nums, the degree of this array is defined as the maximum
 * frequency of any one of its elements.
 *
 * Your task is to find the smallest possible length of a (contiguous) subarray of nums, that has the same degree
 * as nums.
 *
 * Example 1:
 * Input: [1, 2, 2, 3, 1]
 * Output: 2
 * Explanation:
 * The input array has a degree of 2 because both elements 1 and 2 appear twice.
 * Of the subarrays that have the same degree:
 * [1, 2, 2, 3, 1], [1, 2, 2, 3], [2, 2, 3, 1], [1, 2, 2], [2, 2, 3], [2, 2]
 * The shortest length is 2. So return 2.
 * Example 2:
 * Input: [1,2,2,3,1,4,2]
 * Output: 6
 * Note:
 *
 * nums.length will be between 1 and 50,000.
 * nums[i] will be an integer between 0 and 49,999.
 *
 */
public class DegreeOfArray {

    public int findShortestSubArray(int[] nums) {
        int maxFreq = -1;
        Map<Integer, Integer> freq = new HashMap();
        Map<Integer, int[]> indexes = new HashMap();

        for (int i = 0; i < nums.length; i++) {
            int n = nums[i];
            if (freq.containsKey(n)) {
                int newVal = freq.get(n) + 1;
                maxFreq = Math.max(newVal, maxFreq);
                freq.put(n, newVal);
            }
            else {
                freq.put(n, 1);
                maxFreq = Math.max(1, maxFreq);
            }

            if (!indexes.containsKey(n)) {
                indexes.put(n, new int[] {i, i});
            } else {
                indexes.get(n)[1] = i;
            }
        }

        int res = nums.length;

        for (int num : freq.keySet()) {
            if (freq.get(num) == maxFreq) {
                int[] idx = indexes.get(num);
                res = Math.min(res, idx[1] - idx[0] + 1);
            }
        }

        return res;
    }

    public static void main(String[] args) {
        DegreeOfArray obj = new DegreeOfArray();
        int[] arr;
        arr = new int[] {1, 2, 2, 3, 1};
        System.out.println(obj.findShortestSubArray(arr));
    }
}
