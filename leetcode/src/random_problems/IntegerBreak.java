package random_problems;

/**
 * 343. Integer Break
 * Medium
 *
 * Given a positive integer n, break it into the sum of at least two positive integers and maximize the product of
 * those integers. Return the maximum product you can get.
 *
 * Example 1:
 *
 * Input: 2
 * Output: 1
 * Explanation: 2 = 1 + 1, 1 × 1 = 1.
 * Example 2:
 *
 * Input: 10
 * Output: 36
 * Explanation: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36.
 * Note: You may assume that n is not less than 2 and not larger than 58.
 */
public class IntegerBreak {

    /**
     * Idea - it appears that numbers we need to focus are 2 and 3. Every even number can be viewed as 2*n, and even
     * is 2*n - 1 which is combination of 2 and 3.
     * Also observation is - when we have 3 or more left - it's better to cut 3. Otherwise it's better to cut 2.
     * @param n
     * @return
     */
    public int integerBreak(int n) {
        int res = 1;

        if (n == 2) return 1;
        if (n == 3) return 2;

        while (n > 0) {
            if ((n - 3 == 0) || (n - 3 >= 3)) {
                res *= 3;
                n-= 3;
            } else {
                res *= 2;
                n -= 2;
            }
        }

        return res;
    }
}
