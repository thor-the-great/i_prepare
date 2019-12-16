package recursion;

/**
 * 38. Count and Say
 * Easy
 *
 * The count-and-say sequence is the sequence of integers with the first five terms as following:
 *
 * 1.     1
 * 2.     11
 * 3.     21
 * 4.     1211
 * 5.     111221
 * 1 is read off as "one 1" or 11.
 * 11 is read off as "two 1s" or 21.
 * 21 is read off as "one 2, then one 1" or 1211.
 *
 * Given an integer n where 1 ≤ n ≤ 30, generate the nth term of the count-and-say sequence. You can do so recursively,
 * in other words from the previous member read off the digits, counting the number of digits in groups of the same digit.
 *
 * Note: Each term of the sequence of integers will be represented as a string.
 *
 *
 *
 * Example 1:
 *
 * Input: 1
 * Output: "1"
 * Explanation: This is the base case.
 * Example 2:
 *
 * Input: 4
 * Output: "1211"
 * Explanation: For n = 3 the term was "21" in which we have two groups "2" and "1", "2" can be read as "12" which
 * means frequency = 1 and value = 2, the same way "1" is read as "11", so the answer is the concatenation of "12"
 * and "11" which is "1211".
 */
public class CountAndSay {

    /**
     * Straight recursive solution
     * @param n
     * @return
     */
    public String countAndSay(int n) {
        //base case - for "1" just say "1"
        if (n == 1)
            return "1";
        //get count_and_say string from for n - 1
        String c_say = countAndSay(n - 1);
        //now "say" this n - 1 string
        StringBuilder sb = new StringBuilder();
        //save initial position and init the pointer
        int prev = 0; int p = 1;
        while (p < c_say.length()) {
            //if p-th char is different from the previous one - means we need write all previous chars
            if (c_say.charAt(p) != c_say.charAt(p - 1)) {
                sb.append((p - prev)).append(c_say.charAt(p - 1));
                prev = p;
            }
            ++p;
        }
        //finally need to save last series of chars
        sb.append((p - prev)).append(c_say.charAt(p - 1));
        return sb.toString();
    }
}
