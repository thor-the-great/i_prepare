package strings;

import java.util.HashMap;
import java.util.Map;

/**
 * 1297. Maximum Number of Occurrences of a Substring
 * Medium
 *
 * Given a string s, return the maximum number of ocurrences of any substring under the following rules:
 *
 * The number of unique characters in the substring must be less than or equal to maxLetters.
 * The substring size must be between minSize and maxSize inclusive.
 *
 *
 * Example 1:
 *
 * Input: s = "aababcaab", maxLetters = 2, minSize = 3, maxSize = 4
 * Output: 2
 * Explanation: Substring "aab" has 2 ocurrences in the original string.
 * It satisfies the conditions, 2 unique letters and size 3 (between minSize and maxSize).
 * Example 2:
 *
 * Input: s = "aaaa", maxLetters = 1, minSize = 3, maxSize = 3
 * Output: 2
 * Explanation: Substring "aaa" occur 2 times in the string. It can overlap.
 * Example 3:
 *
 * Input: s = "aabcabcab", maxLetters = 2, minSize = 2, maxSize = 3
 * Output: 3
 * Example 4:
 *
 * Input: s = "abcde", maxLetters = 2, minSize = 3, maxSize = 3
 * Output: 0
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 10^5
 * 1 <= maxLetters <= 26
 * 1 <= minSize <= maxSize <= min(26, s.length)
 * s only contains lowercase English letters.
 */
public class MaximumNumberOfOccurencesOfSubstring {

    /**
     * starting from every next char in a string check every maxSize chars. At each step of nested loop check
     * substring for conditions and increment it count in map. Keep running max count
     * @param s
     * @param maxLetters
     * @param minSize
     * @param maxSize
     * @return
     */
    public int maxFreq(String s, int maxLetters, int minSize, int maxSize) {
        Map<String, Integer> m = new HashMap();
        int res = 0;
        //we check substrings that can be formed starting from every char except cases when there are too few chars
        //before the end of the string
        for (int i = 0; i < s.length() - minSize + 1; i++) {
            //count times we met every char and number of unique letters
            int[] count = new int[26]; int letters = 0;
            //starting from current char checking all possible substrings of length between
            //minLengt and maxLength
            for (int j = i; j < s.length() && j < i + maxSize; j++) {
                int idx = s.charAt(j) - 'a';
                ++count[idx];
                //this is first occurrence of this char some increment the number of unique letters
                if (count[idx] == 1)
                    ++letters;
                //check if maxLetters condition is still true
                if (letters > maxLetters)
                    break;
                //for a string of at least minSize length save number of it occurrence in a map
                if (j - i >= minSize - 1) {
                    String substr = s.substring(i, j + 1);
                    if (m.containsKey(substr)) {
                        m.put(substr, m.get(substr) + 1);
                    } else {
                        m.put(substr, 1);
                    }
                    //update the max count of substrings if needed
                    res = Math.max(res, m.get(substr));
                }
            }
        }
        return res;
    }
}
