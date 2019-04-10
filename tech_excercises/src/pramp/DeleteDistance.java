package pramp;

/**
 * Deletion Distance
 * The deletion distance of two strings is the minimum number of characters you need to delete in the two strings in
 * order to get the same string. For instance, the deletion distance between "heat" and "hit" is 3:
 *
 * By deleting 'e' and 'a' in "heat", and 'i' in "hit", we get the string "ht" in both cases.
 * We cannot get the same string from both strings by deleting 2 letters or fewer.
 * Given the strings str1 and str2, write an efficient function deletionDistance that returns the deletion distance
 * between them. Explain how your function works, and analyze its time and space complexities.
 *
 * Examples:
 *
 * input:  str1 = "dog", str2 = "frog"
 * output: 3
 *
 * input:  str1 = "some", str2 = "some"
 * output: 0
 *
 * input:  str1 = "some", str2 = "thing"
 * output: 9
 *
 * input:  str1 = "", str2 = ""
 * output: 0
 * Constraints:
 *
 * [input] string str1
 * [input] string str2
 * [output] integer
 */
public class DeleteDistance {

    /**
     * Similar to edit distance - calculate matrix of states for DP.
     * @param str1
     * @param str2
     * @return
     */
    static int deletionDistance(String str1, String str2) {
        // your code goes here

        int N1 = str1.length();
        int N2 = str2.length();

        int[][] dp = new int[N1 + 1][N2 + 1];

        for (int i = 0; i < N1; i++)
            dp[i][0] = i;
        for (int i = 0; i < N2; i++)
            dp[0][i] = i;

        for (int r = 1; r <= N1; r++) {
            for (int c = 1; c <= N2; c++) {
                if (str1.charAt(r - 1) == str2.charAt(c - 1)) {
                    dp[r][c] = dp[r - 1][c - 1];
                }
                else {
                    dp[r][c] = 1 + Math.min(dp[r - 1][c], dp[r][c - 1]);
                }
            }
        }

        return dp[N1][N2];
    }

    public static void main(String[] args) {
        DeleteDistance obj = new DeleteDistance();
        System.out.println(deletionDistance("heat", "hit"));
        System.out.println(deletionDistance("check", "black"));
    }

    static int deletionDistanceRec(String str1, String str2) {
        return helper(str1, str2, 0, 0, 0);
    }

    static int helper(String s1, String s2, int p1, int p2, int num) {
        if (p1 >= s1.length()) {
            return num + (s2.length() - p2);
        }
        if ( p2 >= s2.length()) {
            return num + (s1.length() - p1);
        }

        if (s1.charAt(p1) == s2.charAt(p2)) {
            p1++;
            p2++;
            return helper(s1, s2, p1, p2, num);
        }

        return Math.min(
                helper(s1, s2, p1+1, p2, num + 1),
                helper(s1, s2, p1, p2 + 1, num + 1)
        );

    }
}
