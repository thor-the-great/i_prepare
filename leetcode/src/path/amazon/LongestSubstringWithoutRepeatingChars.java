package path.amazon;

import java.util.Arrays;
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
        int N = s.length();
        if (N <= 1)
            return N;
        int[] indexes = new int[128];
        Arrays.fill(indexes, -1);

        int head = 0, tail = 0;
        int res = 0;

        for (; tail < N; tail++) {
            int idx = s.charAt(tail);
            if (indexes[idx] != -1) {
                res = Math.max(res, tail - head);
                int duplIdx = indexes[idx];
                for (; head <= duplIdx; head++) {
                    indexes[s.charAt(head)] = -1;
                }
            }
            indexes[idx] = tail;
        }

        res = Math.max(res, tail - head);

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
