package path.google;

/**
 * 159. Longest Substring with At Most Two Distinct Characters
 * Hard
 *
 * Given a string s , find the length of the longest substring t  that contains at most 2 distinct characters.
 *
 * Example 1:
 *
 * Input: "eceba"
 * Output: 3
 * Explanation: t is "ece" which its length is 3.
 * Example 2:
 *
 * Input: "ccaabbb"
 * Output: 5
 * Explanation: t is "aabbb" which its length is 5.
 *
 */
public class LongestSubstringAtMostTwoDistinctChars {
    /**
     * Idea - using sliding window, two pointers. Move left pointer forward and keep count of number of occurrences
     * for each character and number of unique characters. If Number of characters became 3 - start moving right pointer
     * and decrement counter unless we have only 2 unique characters again
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        int[] count = new int[128];
        int c = 0, l = 0, res = -1;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            ++count[ch];
            if (count[ch] == 1)
                ++c;
            if (c > 2) {
                res = Math.max(res, i - l);
                while(c > 2) {
                    --count[s.charAt(l)];
                    if (count[s.charAt(l)] == 0)
                        --c;
                    ++l;
                }
            }
        }
        res = Math.max(res, s.length() - l);
        return res == -1 ? s.length() : res;
    }

    public static void main (String[] args) {
        LongestSubstringAtMostTwoDistinctChars obj = new LongestSubstringAtMostTwoDistinctChars();
        System.out.println(obj.lengthOfLongestSubstringTwoDistinct("ccaa"));
        System.out.println(obj.lengthOfLongestSubstringTwoDistinct("ccaab"));
        System.out.println(obj.lengthOfLongestSubstringTwoDistinct("ccaabbb"));
        System.out.println(obj.lengthOfLongestSubstringTwoDistinct("accaaabaacbvb"));
    }
}
