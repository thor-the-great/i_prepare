package sliding_window;

/**
 * 1234. Replace the Substring for Balanced String
 * Medium
 *
 * 85
 *
 * You are given a string containing only 4 kinds of characters 'Q', 'W', 'E' and 'R'.
 *
 * A string is said to be balanced if each of its characters appears n/4 times where n is the length of the string.
 *
 * Return the minimum length of the substring that can be replaced with any other string of the same length to make
 * the original string s balanced.
 *
 * Return 0 if the string is already balanced.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "QWER"
 * Output: 0
 * Explanation: s is already balanced.
 * Example 2:
 *
 * Input: s = "QQWE"
 * Output: 1
 * Explanation: We need to replace a 'Q' to 'R', so that "RQWE" (or "QRWE") is balanced.
 * Example 3:
 *
 * Input: s = "QQQW"
 * Output: 2
 * Explanation: We can replace the first "QQ" to "ER".
 * Example 4:
 *
 * Input: s = "QQQQ"
 * Output: 3
 * Explanation: We can replace the last 3 'Q' to make s = "QWER".
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 10^5
 * s.length is a multiple of 4
 * s contains only 'Q', 'W', 'E' and 'R'.
 */
public class ReplaceSubstringForBalancedString {

    /**
     * Sliding window. Window is the possible substring that we can replace. Everything that outside has to remain
     * <=k, otherwise we can exclude one of the edge symbols.
     * Keep doing is and save the shortest possible window length
     * @param s
     * @return
     */
    public int balancedString(String s) {
        int N = s.length();
        int[] counts = new int[26];

        for (char ch : s.toCharArray()) {
            counts[ch - 'A']++;
        }

        int l = 0, res = N, k = N/4;

        for (int r = 0; r < N; r++) {
            counts[s.charAt(r) - 'A']--;
            while (l < N
                    && counts['Q' - 'A'] <= k
                    && counts['R' - 'A'] <= k
                    && counts['W' - 'A'] <= k
                    && counts['E' - 'A'] <= k) {
                System.out.println(l + ", " + r);
                res = Math.min(res, r - l + 1);
                counts[s.charAt(l++) - 'A']++;
            }
        }
        return res;
    }

    public static void main (String[] args) {
        ReplaceSubstringForBalancedString obj = new ReplaceSubstringForBalancedString();
        System.out.println(obj.balancedString("QWER"));
    }
}
