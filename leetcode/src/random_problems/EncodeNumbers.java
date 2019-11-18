package random_problems;

/**
 * 1256. Encode Number
 * Medium
 *
 * Given a non-negative integer num, Return its encoding string.
 *
 * The encoding is done by converting the integer to a string using a secret function that you should deduce from
 * the following table:
 *
 * Example 1:
 *
 * Input: num = 23
 * Output: "1000"
 * Example 2:
 *
 * Input: num = 107
 * Output: "101100"
 *
 *
 * Constraints:
 *
 * 0 <= num <= 10^9
 */
public class EncodeNumbers {

    char[] dig = new char[] {'1', '0'};

    /**
     * Calculate by dividing on 2
     * @param num
     * @return
     */
    public String encode(int num) {
        return num == 0 ? "" : encode((num - 1)/2) + dig[num % 2];
    }
}
