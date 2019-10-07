package sliding_window;

/**
 * 1208. Get Equal Substrings Within Budget
 * Medium
 *
 * You are given two strings s and t of the same length. You want to change s to t. Changing the i-th character of s
 * to i-th character of t costs |s[i] - t[i]| that is, the absolute difference between the ASCII values of the characters.
 *
 * You are also given an integer maxCost.
 *
 * Return the maximum length of a substring of s that can be changed to be the same as the corresponding substring
 * of twith a cost less than or equal to maxCost.
 *
 * If there is no substring from s that can be changed to its corresponding substring from t, return 0.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "abcd", t = "bcdf", maxCost = 3
 * Output: 3
 * Explanation: "abc" of s can change to "bcd". That costs 3, so the maximum length is 3.
 * Example 2:
 *
 * Input: s = "abcd", t = "cdef", maxCost = 3
 * Output: 1
 * Explanation: Each character in s costs 2 to change to charactor in t, so the maximum length is 1.
 * Example 3:
 *
 * Input: s = "abcd", t = "acde", maxCost = 0
 * Output: 1
 * Explanation: You can't make any change, so the maximum length is 1.
 *
 *
 * Constraints:
 *
 * 1 <= s.length, t.length <= 10^5
 * 0 <= maxCost <= 10^6
 * s and t only contain lower case English letters.
 */
public class EqualStringsWithinBudget {

    /**
     * sliding window
     * @param s
     * @param t
     * @param maxCost
     * @return
     */
    public int equalSubstring(String s, String t, int maxCost) {
        int N = s.length();
        int[] costs = new int[N];
        //count cost for very symbol
        for (int i = 0; i < N; i++) {
            costs[i] = Math.abs(s.charAt(i) -  t.charAt(i));
        }
        int l = 0, r = 0;
        //iterate over the strings
        for (; r < N; r++) {
            //decrease the leftover unless it's == 0
            maxCost -= costs[r];
            //if we over the cost - need to move left pointer
            if (maxCost < 0) {
                maxCost += costs[l++];
            }
        }
        //resulting string will be from r to l (inclusive/exclusive)
        return r - l;
    }

    public static void main(String[] args) {
        EqualStringsWithinBudget obj = new EqualStringsWithinBudget();
        System.out.println(obj.equalSubstring("abcd", "cdef", 1));
    }
}
