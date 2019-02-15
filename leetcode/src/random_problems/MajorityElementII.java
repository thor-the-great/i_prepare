package random_problems;

import java.util.ArrayList;
import java.util.List;

/**
 * 229. Majority Element II
 * Medium
 *
 * Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times.
 *
 * Note: The algorithm should run in linear time and in O(1) space.
 *
 * Example 1:
 *
 * Input: [3,2,3]
 * Output: [3]
 * Example 2:
 *
 * Input: [1,1,1,3,3,2,2,2]
 * Output: [1,2]
 */
public class MajorityElementII {

    /**
     * Idea: use Boyer-Moore Majority Vote algorithm for 2 candidates, then run for each of 2 to confirm that it's
     * enough number
     * @param nums
     * @return
     */
    public List<Integer> majorityElement(int[] nums) {
        int cand1 = 0, cand2 = 0;
        int count1 = 0, count2 = 0;

        for (int num : nums) {
            if (cand1 == num) {
                count1++;
            } else if (cand2 == num) {
                count2++;
            } else if (count1 == 0 ) {
                count1 = 1;
                cand1 = num;
            } else if (count2 == 0) {
                count2 = 1;
                cand2 = num;
            } else {
                count1--;
                count2--;
            }
        }

        count1 = 0; count2 = 0;
        for (int num : nums) {
            if (num == cand1)
                count1++;
            else if (num == cand2)
                count2++;
        }

        int N = nums.length;

        List<Integer> res = new ArrayList();
        if (count1 > N/3)
            res.add(cand1);
        if (count2 > N/3)
            res.add(cand2);

        return res;
    }
}
