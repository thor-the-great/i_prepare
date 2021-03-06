package path.amazon;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a string, find the length of the longest substring without repeating characters.
 *
 * Example 1:
 *
 * Input: "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3.
 * Example 2:
 *
 * Input: "bbbbb"
 * Output: 1
 * Explanation: The answer is "b", with the length of 1.
 * Example 3:
 *
 * Input: "pwwkew"
 * Output: 3
 * Explanation: The answer is "wke", with the length of 3.
 * Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
 *
 */
public class LongestSubstringWithoutRepeatingChars {

    public int lengthOfLongestSubstring(String s) {
        if (s.length() < 2)
            return s.length();

        int[] c = new int[128];
        int res = 0;

        int l = 0;
        for (int r = 0; r < s.length(); r++) {
            char ch = s.charAt(r);
            if (c[ch] == 0) {
                ++c[ch];
            } else {
                res = Math.max(res, r - l);
                ++c[ch];
                while (c[ch] != 1) {
                    --c[s.charAt(l)];
                    l++;
                }
            }
        }
        res = Math.max(res, s.length() - l);
        return res;
    }

    public int lengthOfLongestSubstringMapSlower(String s) {
        Map<Character, Integer> map = new HashMap();
        int max = 0;
        int i = 0;
        int indexToStart = 0;
        while (i < s.length()) {
            char nextChar = s.charAt(i);
            if (!map.containsKey(nextChar)) {
                map.put(nextChar, i);
                i++;
            }
            else {
                int charIndex = map.get(nextChar);
                max = Math.max(max, map.size());
                for (int j = indexToStart; j <= charIndex; j++) {
                    map.remove(s.charAt(j));
                }
                map.put(nextChar, i);
                indexToStart = charIndex + 1;
                i++;
            }
        }
        max = Math.max(max, map.size());
        return max;
    }


}
