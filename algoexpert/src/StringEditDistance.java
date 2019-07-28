/**
 * Word Similarity - Edit Distance New
 *     Multi Dimensional Arrays Dynamic Programming Strings
 * Edit distance is a classic algorithm that is used in many applications, including Spell Correction,
 * DNA Sequencing and Natural Language Processing. Given two Strings, a and b, write a method - editDistance that
 * returns the minimum number of operations needed to transform a into b. The following character operations are
 * allowed :
 *
 * a) Replace character
 * b) Insert character
 * c) Delete character
 *
 * Examples :
 *
 * editDistance("sale", "sales") => 1
 *
 * Operations :
 * 1) Insert "s"
 *
 * editDistance("sale", "sold") => 2
 *
 * Operations :
 * 1) Replace "a" with "o"
 * 2) Replace "e" with "d"
 *
 * editDistance("sa", "s") => 1
 *
 * Operations :
 * 1) Delete "a"
 */
public class StringEditDistance {

    /**
     * Build matrix that represents edit distance between string length 0..lenA and 0...lenB.
     * Fill 0,0 as 0. r,0 = r, 0,c = c = this is edit distance between empty string and parts of other string
     * start filling matrix, if char is the same = copy r,c value from r-1,c-1. Otherwise we need 1 + min of
     * r-1, c-1 (insert), r, c-1 (replace from one string) and r-1,c (replace from second string)
     * @param a
     * @param b
     * @return
     */
    public int editDistance(String a, String b){
        int lenA = a.length(), lenB = b.length();
        int[][] dp = new int[lenA + 1][lenB + 1];

        dp[0][0] = 0;

        for (int r = 1; r <= lenA; r++)
            dp[r][0] = r;

        for (int c = 1; c <= lenB; c++)
            dp[0][c] = c;


        for (int r = 1; r <= lenA; r++) {
            for (int c = 1; c <= lenB; c++) {
                //if it's the same character
                if (a.charAt(r - 1) == b.charAt(c - 1))
                    dp[r][c] = dp[r - 1][c - 1];
                    //else
                else {
                    dp[r][c] = Math.min(
                            dp[r - 1][c - 1],
                            Math.min(
                                    dp[r - 1][c],
                                    dp[r][c - 1]))
                            + 1;
                }
            }
        }

        return dp[lenA][lenB];
    }
}
