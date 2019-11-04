package random_problems;

/**
 * 1247. Minimum Swaps to Make Strings Equal
 * Easy
 *
 * You are given two strings s1 and s2 of equal length consisting of letters "x" and "y" only. Your task is to make
 * these two strings equal to each other. You can swap any two characters that belong to different strings, which
 * means: swap s1[i] and s2[j].
 *
 * Return the minimum number of swaps required to make s1 and s2 equal, or return -1 if it is impossible to do so.
 *
 * Example 1:
 *
 * Input: s1 = "xx", s2 = "yy"
 * Output: 1
 * Explanation:
 * Swap s1[0] and s2[1], s1 = "yx", s2 = "yx".
 * Example 2:
 *
 * Input: s1 = "xy", s2 = "yx"
 * Output: 2
 * Explanation:
 * Swap s1[0] and s2[0], s1 = "yy", s2 = "xx".
 * Swap s1[0] and s2[1], s1 = "xy", s2 = "xy".
 * Note that you can't swap s1[0] and s1[1] to make s1 equal to "yx", cause we can only swap chars in different strings.
 * Example 3:
 *
 * Input: s1 = "xx", s2 = "xy"
 * Output: -1
 * Example 4:
 *
 * Input: s1 = "xxyyxyxyxx", s2 = "xyyxyxxxyx"
 * Output: 4
 *
 *
 * Constraints:
 *
 * 1 <= s1.length, s2.length <= 1000
 * s1, s2 only contain 'x' or 'y'.
 */
public class MinimumSwapToMakeStringsEqual {

    /**
     * For this problem we have to count number of possible variants. Possible variants are:
     *
     * xx/yy - 1 swap required
     * xy/yx - 2 swaps required
     * xy/xx - not possible
     * x/y - 0, no swap needed
     *
     * we iterate over both strings and count number of times we met each variant. Trying to get as much as possible
     * of variant 1 - gives the smallest number of swaps.
     *
     * O(n) time - iterate once over each string. O(1) space - keep state in several variables
     * @param s1
     * @param s2
     * @return
     */
    public int minimumSwap(String s1, String s2) {
        //if strings are not equal in length - requirement is not possible
        if (s1.length() != s2.length())
            return -1;

        int var1 = 0, var2 = 0;
        for (int i = 0; i < s1.length(); i++) {
            char ch1 = s1.charAt(i), ch2 = s2.charAt(i);
            //this is case when chars are the same - there is no need to do the swap
            if (ch1 == ch2)
                continue;
            //count x/y as var1, y/x as var2
            if (ch1 == 'x')
                var1++;
            else
                var2++;
        }

        int res;
        //if number of non-equal pairs is odd - requirement is not possible
        if ((var1 + var2) % 2 == 1 )
            return -1;
        //count number of variants xx/yy and yy/xx - each such 4 values possible to swap in 1 shot
        res = var1 / 2;
        res+= var2 / 2;

        //now count xy/yx variants - each required 2 swaps
        res += var1 % 2;
        res += var2 % 2;

        return res;
    }
}
