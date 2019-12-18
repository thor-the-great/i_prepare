package strings;

/**
 * 1055. Shortest Way to Form String
 * Medium
 *
 * From any string, we can form a subsequence of that string by deleting some number of characters (possibly no deletions).
 *
 * Given two strings source and target, return the minimum number of subsequences of source such that their
 * concatenation equals target. If the task is impossible, return -1.
 *
 *
 *
 * Example 1:
 *
 * Input: source = "abc", target = "abcbc"
 * Output: 2
 * Explanation: The target "abcbc" can be formed by "abc" and "bc", which are subsequences of source "abc".
 * Example 2:
 *
 * Input: source = "abc", target = "acdbc"
 * Output: -1
 * Explanation: The target string cannot be constructed from the subsequences of source string due to the character
 * "d" in target string.
 * Example 3:
 *
 * Input: source = "xyz", target = "xzyxz"
 * Output: 3
 * Explanation: The target string can be constructed as follows "xz" + "y" + "xz".
 *
 *
 * Constraints:
 *
 * Both the source and target strings consist of only lowercase English letters from "a"-"z".
 * The lengths of source and target string are between 1 and 1000.
 *
 */
public class ShortestWayToFormString {

    /**
     * In a greedy manner start scanning source several times and each iteration check if we can get some chars to
     * move in target. If at any iteration we could go farther - it's not possible (there is a char in target
     * that is not in a source).
     * @param source
     * @param target
     * @return
     */
    public int shortestWay(String source, String target) {
        int p1 = 0, p2 = 0, cur = 0;
        while (p2 < target.length()) {
            ++cur;
            int pp2 = p2;
            while (p1 < source.length() && p2 < target.length()) {
                if (source.charAt(p1) == target.charAt(p2)) {
                    ++p2;
                }
                ++p1;
            }
            if (pp2 == p2)
                return -1;
            p1 = 0;
        }

        return cur;
    }
}
