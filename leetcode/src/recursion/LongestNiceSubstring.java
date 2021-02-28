package recursion;

import java.util.HashSet;
import java.util.Set;

/**
 * 1763. Longest Nice Substring
 *
 * A string s is nice if, for every letter of the alphabet that s contains, it appears both in uppercase and lowercase. For example, "abABB" is nice because 'A' and 'a' appear, and 'B' and 'b' appear. However, "abA" is not because 'b' appears, but 'B' does not.
 *
 * Given a string s, return the longest substring of s that is nice. If there are multiple, return the substring of the earliest occurrence. If there are none, return an empty string.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "YazaAay"
 * Output: "aAa"
 * Explanation: "aAa" is a nice string because 'A/a' is the only letter of the alphabet in s, and both 'A' and 'a' appear.
 * "aAa" is the longest nice substring.
 *
 * Example 2:
 *
 * Input: s = "Bb"
 * Output: "Bb"
 * Explanation: "Bb" is a nice string because both 'B' and 'b' appear. The whole string is a substring.
 *
 * Example 3:
 *
 * Input: s = "c"
 * Output: ""
 * Explanation: There are no nice substrings.
 *
 * Example 4:
 *
 * Input: s = "dDzeE"
 * Output: "dD"
 * Explanation: Both "dD" and "eE" are the longest nice substrings.
 * As there are multiple longest nice substrings, return "dD" since it occurs earlier.
 *
 *
 *
 * Constraints:
 *
 *     1 <= s.length <= 100
 *     s consists of uppercase and lowercase English letters.
 *
 * https://leetcode.com/contest/biweekly-contest-46/problems/longest-nice-substring/
 */
public class LongestNiceSubstring {

    /**
     * Recusrvice algo - collect all chars in substring into set. Then iterate and first that does not have
     * both upper and lower cases in set is dividing the string into parts that each can be potential nice string
     *
     * O(nlgn) as we have lgn rounds of length n each
     * @param s
     * @return
     */
    public String longestNiceSubstring(String s) {
        if (s.length() < 2)
            return "";

        Set<Character> set = new HashSet();
        for (char ch : s.toCharArray()) {
            set.add(ch);
        }

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (set.contains(Character.toLowerCase(ch)) && set.contains(Character.toUpperCase(ch)))
                continue;

            String left = longestNiceSubstring(s.substring(0, i));
            String right = longestNiceSubstring(s.substring(i + 1, s.length()));

            if (left.length() >= right.length())
                return left;
            else
                return right;
        }
        return s;
    }
}
