package strings;

/**
 * 459. Repeated Substring Pattern
 * Easy
 *
 * Given a non-empty string check if it can be constructed by taking a substring of it and appending multiple copies
 * of the substring together. You may assume the given string consists of lowercase English letters only and its
 * length will not exceed 10000.
 *
 *
 *
 * Example 1:
 *
 * Input: "abab"
 * Output: True
 * Explanation: It's the substring "ab" twice.
 * Example 2:
 *
 * Input: "aba"
 * Output: False
 * Example 3:
 *
 * Input: "abcabcabcabc"
 * Output: True
 * Explanation: It's the substring "abc" four times. (And the substring "abcabc" twice.)
 */
public class RepeatedSubstringPattern {

    /**
     * Get the possible divisors, for each one get the substring of it length and try if we can form an original
     * string from it
     * @param s
     * @return
     */
    public boolean repeatedSubstringPattern(String s) {
        int N = s.length();
        if (N <= 1)
            return false;
        int pos = 0;
        while (pos < N /2) {
            if (N % (pos + 1) == 0) {
                //check if possible
                int subStrPos = 0, i = pos + 1;
                for (; i < N; i++) {
                    if (s.charAt(i) != s.charAt(subStrPos)) {
                        break;
                    }
                    subStrPos = (subStrPos == pos) ? 0 : subStrPos + 1;
                }
                if (i == N) {
                    return true;
                }
            }
            ++pos;
        }
        return false;
    }
}
