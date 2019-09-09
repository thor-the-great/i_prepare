package arrays;

/**
 * 821. Shortest Distance to a Character
 * Easy
 *
 * Given a string S and a character C, return an array of integers representing the shortest distance from the character C in the string.
 *
 * Example 1:
 *
 * Input: S = "loveleetcode", C = 'e'
 * Output: [3, 2, 1, 0, 1, 0, 0, 1, 2, 2, 1, 0]
 *
 *
 * Note:
 *
 * S string length is in [1, 10000].
 * C is a single character, and guaranteed to be in string S.
 * All letters in S and C are lowercase.
 */
public class ShortestDistanceToChar {

    public int[] shortestToChar(String S, char C) {
        int N = S.length();
        int[] res = new int[N];
        int p = -1;
        //going left to right
        for (int i = 0; i < N; i++) {
            if (S.charAt(i) == C) {
                res[i] = 0;
                p = i;
            } else if (p > -1) {
                res[i] = i - p;
            } else {
                res[i] = Integer.MAX_VALUE;
            }
        }
        //going right to left
        p = -1;
        for (int i = N - 1; i >= 0; i--) {
            int val;
            if (S.charAt(i) == C) {
                p = i;
            } else if (p > -1) {
                res[i] = Math.min(p - i, res[i]);
            }
        }
        return res;
    }
}
