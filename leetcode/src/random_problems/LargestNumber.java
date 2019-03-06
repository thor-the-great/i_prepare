package random_problems;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 179. Largest Number
 * Medium
 *
 * Given a list of non negative integers, arrange them such that they form the largest number.
 *
 * Example 1:
 *
 * Input: [10,2]
 * Output: "210"
 * Example 2:
 *
 * Input: [3,30,34,5,9]
 * Output: "9534330"
 * Note: The result may be very large, so you need to return a string instead of an integer.
 */
public class LargestNumber {

    /**
     * Idea - we need to sort numbers. In case of draw - need to choose bigger number first. This can be done
     * by sorting and comparing s1+s2 vs s2+s1.
     * catch: if all numbers are 0-es - need to check for that edge case
     * @param nums
     * @return
     */
    public String largestNumber(int[] nums) {
        if (nums == null || nums.length == 0)
            return "";

        String[] numsStr = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            numsStr[i] = Integer.toString(nums[i]);
        }

        Comparator<String> comp = (s1, s2) -> (
                (s2 + s1).compareTo(s1 + s2)
        );

        Arrays.sort(numsStr, comp);

        //edge case
        if (numsStr[0].charAt(0) == '0')
            return "0";

        StringBuilder sb = new StringBuilder();
        for (String s : numsStr) {
            sb.append(s);
        }

        return sb.toString();
    }
}
