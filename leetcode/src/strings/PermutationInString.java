package strings;

/**
 * 567. Permutation in String
 * Medium
 *
 * Given two strings s1 and s2, write a function to return true if s2 contains the permutation of s1. In other words,
 * one of the first string's permutations is the substring of the second string.
 *
 *
 *
 * Example 1:
 *
 * Input: s1 = "ab" s2 = "eidbaooo"
 * Output: True
 * Explanation: s2 contains one permutation of s1 ("ba").
 * Example 2:
 *
 * Input:s1= "ab" s2 = "eidboaoo"
 * Output: False
 *
 *
 * Note:
 *
 * The input strings only contain lower case letters.
 * The length of both given strings is in range [1, 10,000].
 */
public class PermutationInString {

    /**
     * sliding window approach - because the actual sequence of chars doesn't matter we can create window in
     * s2 of length len(s1), then compare chars in both windows (without sequence), then move the window to the
     * right and add next char and exclude first one.
     * @param s1
     * @param s2
     * @return
     */
    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length())
            return false;
        int[] count = new int[26];
        int[] count2 = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            count[s1.charAt(i) - 'a']++;
            count2[s2.charAt(i) - 'a']++;
        }

        for (int i = 0; i < s2.length() - s1.length(); i++) {
            if (match(count, count2))
                return true;
            --count2[s2.charAt(i) - 'a'];
            ++count2[s2.charAt(i + s1.length()) - 'a'];
        }
        return match(count, count2);
    }

    boolean match(int[] c1, int[] c2) {
        for (int i = 0; i < 26; i++) {
            if (c1[i] != c2[i])
                return false;
        }
        return true;
    }
}
