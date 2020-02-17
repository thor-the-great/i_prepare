package strings;

/**
 * 1347. Minimum Number of Steps to Make Two Strings Anagram
 * Medium
 *
 * Given two equal-size strings s and t. In one step you can choose any character of t and replace it with another
 * character.
 *
 * Return the minimum number of steps to make t an anagram of s.
 *
 * An Anagram of a string is a string that contains the same characters with a different (or the same) ordering.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "bab", t = "aba"
 * Output: 1
 * Explanation: Replace the first 'a' in t with b, t = "bba" which is anagram of s.
 * Example 2:
 *
 * Input: s = "leetcode", t = "practice"
 * Output: 5
 * Explanation: Replace 'p', 'r', 'a', 'i' and 'c' from t with proper characters to make t anagram of s.
 * Example 3:
 *
 * Input: s = "anagram", t = "mangaar"
 * Output: 0
 * Explanation: "anagram" and "mangaar" are anagrams.
 * Example 4:
 *
 * Input: s = "xxyyzz", t = "xxyyzz"
 * Output: 0
 * Example 5:
 *
 * Input: s = "friend", t = "family"
 * Output: 4
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 50000
 * s.length == t.length
 * s and t contain lower-case English letters only.
 */
public class MinimumNumberOfStepsToMakeStringsAnagram {

    /**
     * We need to count chars that are different between strings, those needs to be changed. We count every char in s
     * first, then scan t and decrement counts of the same chars. Every time count goes below 0 it means that some
     * other char is missing, so we add one to result.
     *
     * O(n) - scan s and t which are of the same length. O(1) space - need constant size array to count different chars.
     * @param s
     * @param t
     * @return
     */
    public int minSteps(String s, String t) {
        int[] count = new int[128];
        for (char ch : s.toCharArray()) ++count[ch];
        int res = 0;
        for (char ch : t.toCharArray()) {
            if (count[ch] <= 0)
                ++res;
            else --count[ch];
        }
        return res;
    }
}
