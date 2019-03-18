package random_problems;

/**
 * 424. Longest Repeating Character Replacement
 * Medium
 *
 * Given a string that consists of only uppercase English letters, you can replace any letter in the string with
 * another letter at most k times. Find the length of a longest substring containing all repeating letters you can
 * get after performing the above operations.
 *
 * Note:
 * Both the string's length and k will not exceed 104.
 *
 * Example 1:
 *
 * Input:
 * s = "ABAB", k = 2
 *
 * Output:
 * 4
 *
 * Explanation:
 * Replace the two 'A's with two 'B's or vice versa.
 * Example 2:
 *
 * Input:
 * s = "AABABBA", k = 1
 *
 * Output:
 * 4
 *
 * Explanation:
 * Replace the one 'A' in the middle with 'B' and form "AABBBBA".
 * The substring "BBBB" has the longest repeating letters, which is 4.
 *
 */
public class LongestRepeatingCharacterReplacement {

    /**
     * Idea: sliding window + 2 pointers.
     * extending right pointer to the point when num of replacements is <= k. After that move left pointer and decrease
     * count of that letter
     * keep current max variable
     * @param s
     * @param k
     * @return
     */
    public int characterReplacement(String s, int k) {
        int N = s.length();
        int l = 0, r = 0;
        int max = 0, curr = 0;

        int[] counts = new int[26];

        while (r < N) {
            int idx = s.charAt(r) - 'A';
            curr = Math.max(curr, ++counts[idx]);
            int replaceCount = r - l + 1 - curr;

            while (replaceCount > k) {
                counts[s.charAt(l++) - 'A']--;
                replaceCount--;
            }
            max = Math.max(max, r - l + 1);
            r++;
        }

        return max;
    }
}
