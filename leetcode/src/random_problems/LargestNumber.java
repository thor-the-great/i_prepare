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

    public String largestNumberIntegerSorting(int[] nums) {
        Comparator<Integer> comp = new Comparator<>() {
            public int compare (Integer i1, Integer i2) {
                int shift1 = 1;
                int numOfDigits = numOfDigits(i1);
                while (numOfDigits > 0) {
                    shift1*=10;
                    --numOfDigits;
                }

                int shift2 = 1;
                numOfDigits = numOfDigits(i2);
                while (numOfDigits > 0) {
                    shift2*=10;
                    --numOfDigits;
                }

                return Long.compare((long)i2*shift1 + i1, (long)i1*shift2 + i2);
            }
        };
        
        Integer[] integArray = new Integer[nums.length];
        for (int i = 0; i < nums.length; i++) {
            integArray[i] = nums[i];
        }
        Arrays.sort(integArray, comp);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < integArray.length; i++) {
            int n = integArray[i];
            if (n == 0 && sb.length() == 0 && i < integArray.length - 1) {
                continue;
            }
            sb.append(n);
        }
        return sb.toString();
    }
    
    int numOfDigits(int n) {
        if (n == 0)
            return 1;
        int res = 0;
        while(n > 0) {
            n/=10;
            ++res;
        }
        return res;
    }
}
