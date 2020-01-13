package bits;

/**
 * 1318. Minimum Flips to Make a OR b Equal to c
 * Medium
 *
 * Share
 * Given 3 positives numbers a, b and c. Return the minimum flips required in some bits of a and
 * b to make ( a OR b == c ). (bitwise OR operation).
 * Flip operation consists of change any single bit 1 to 0 or change the bit 0 to 1 in their
 * binary representation.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: a = 2, b = 6, c = 5
 * Output: 3
 * Explanation: After flips a = 1 , b = 4 , c = 5 such that (a OR b == c)
 * Example 2:
 *
 * Input: a = 4, b = 2, c = 7
 * Output: 1
 * Example 3:
 *
 * Input: a = 1, b = 2, c = 3
 * Output: 0
 *
 *
 * Constraints:
 *
 * 1 <= a <= 10^9
 * 1 <= b <= 10^9
 * 1 <= c <= 10^9
 */
public class MinimumFlipsToMakeAOrBEqualsC {
    /**
     * Check bits one by one. For the '1' is c both a and b must be 1, for
     * '0' any '1' must be flipped
     * @param a
     * @param b
     * @param c
     * @return
     */
    public int minFlips(int a, int b, int c) {
        int res = 0;
        //while there is a least one bit in any of the numbers
        while(a > 0 || b > 0 || c > 0) {
            //if c has '1' check how many in a and b has '0'
            if ((c&1) == 1) {
                if ((a&1) == 0 && (b&1) == 0)
                    ++res;
            } else {
                //for '0' in c check how many '1' are in a and b
                if ((a&1) == 1) ++res;
                if ((b&1) == 1) ++res;
            }
            //move to the next bit in all three numbers
            a>>=1; b>>=1; c>>=1;
        }
        return res;
    }
}
