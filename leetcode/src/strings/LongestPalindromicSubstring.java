package strings;

/**
 * 5. Longest Palindromic Substring
 * Medium
 *
 * Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.
 *
 * Example 1:
 *
 * Input: "babad"
 * Output: "bab"
 * Note: "aba" is also a valid answer.
 * Example 2:
 *
 * Input: "cbbd"
 * Output: "bb"
 */
public class LongestPalindromicSubstring {

    /**
     * for each i-th index start two checks going from center to the edges. Start from i,i and i,i+1 (odd and even number
     * of chars in string)
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        if (s == null || s.isEmpty())
            return s;
        int left = 0, right = 0;
        for (int i = 0; i < s.length(); i++) {
            int cur = Math.max(pali(s, i, i), pali(s, i, i + 1));
            if (cur > (right - left)) {
                left = i - ((cur - 1)/2);
                right = i + (cur/2);
            }
        }

        return s.substring(left, right + 1);
    }

    int pali(String s, int l, int r) {
        int left = l, right = l;
        while (l >= 0 && r < s.length() )  {
            if (s.charAt(l) == s.charAt(r)) {
                left = l;
                right = r;
                --l; ++r;
            }
            else
                break;
        }

        return right + 1 - left;
    }
}
