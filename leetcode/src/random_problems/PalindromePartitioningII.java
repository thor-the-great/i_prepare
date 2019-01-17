package random_problems;

import java.util.ArrayList;
import java.util.List;

/**
 * 131. Palindrome Partitioning
 * Medium
 *
 * Given a string s, partition s such that every substring of the partition is a palindrome.
 *
 * Return all possible palindrome partitioning of s.
 *
 * Example:
 *
 * Input: "aab"
 * Output:
 * [
 *   ["aa","b"],
 *   ["a","a","b"]
 * ]
 */
public class PalindromePartitioningII {

    /**
     * Idea: dynamic programming. Keep table of possible palindromes NxN. Two main points:
     * - if string from i + 1 .. j - 1 is palindrome and s[i] == s[j] then i..j is also a palindrome
     * - cut is min (cuts[j - 1] + 1, min). For every next substring end we start from min = num of characters - 1, then
     * it can be lowered
     * @param s
     * @return
     */
    public int minCut(String s) {
        char[] sArr = s.toCharArray();
        int N = s.length();
        int[] cuts = new int[N];
        boolean[][] isPali = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            int min = i;
            for (int j = 0; j <= i; j++) {
                if (sArr[j] == sArr[i] && (j + 1 > i - 1 || isPali[j + 1][i - 1])) {
                    isPali[j][i] = true;
                    min = j == 0 ? 0 : Math.min(min, cuts[j - 1] + 1);
                }
            }
            cuts[i] = min;
        }
        return cuts[N - 1];
    }

    public static void main(String[] args) {
        PalindromePartitioningII obj = new PalindromePartitioningII();
        System.out.println(obj.minCut("aab"));

        System.out.println(obj.minCut("aacabb"));

        System.out.println(obj.minCut("ababababababababababababcbabababababababababababa"));
    }
}
