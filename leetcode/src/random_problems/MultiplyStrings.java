package random_problems;
/**
 * 43. Multiply Strings
 * Medium
 *
 * Given two non-negative integers num1 and num2 represented as strings, return the product of num1 and num2, also
 * represented as a string.
 *
 * Example 1:
 *
 * Input: num1 = "2", num2 = "3"
 * Output: "6"
 * Example 2:
 *
 * Input: num1 = "123", num2 = "456"
 * Output: "56088"
 * Note:
 *
 * The length of both num1 and num2 is < 110.
 * Both num1 and num2 contain only digits 0-9.
 * Both num1 and num2 do not contain any leading zero, except the number 0 itself.
 * You must not use any built-in BigInteger library or convert the inputs to integer directly.
 */
public class MultiplyStrings {
    /**
     * Idea just do the multiplication, store result and carry separately.
     * catch: number of digits can't be > len(num1) + len(num2)
     * catch: careful with 0s
     * @param num1
     * @param num2
     * @return
     */
    public String multiply(String num1, String num2) {
        int l1 = num1.length(), l2 = num2.length();
        int[] res = new int[l1 + l2];

        for (int i = l1 - 1; i >= 0; i--) {
            int d1 = num1.charAt(i) - '0';
            for (int j = l2 - 1; j >= 0; j--) {
                int d2 = num2.charAt(j) - '0';
                int prod = (d1 * d2);
                int s = prod + res[i + j + 1];
                res[i + j + 1] = s % 10;
                res[i + j ] += s / 10;
            }
        }
        StringBuilder sb = new StringBuilder();

        for (int i : res ) {
            if (!(i == 0 && sb.length() == 0 ))
                sb.append(i);
        }

        return sb.length() == 0 ? "0" : sb.toString();
    }

    public static void main(String[] args) {
        MultiplyStrings obj = new MultiplyStrings();
        System.out.println(obj.multiply("2", "3"));

        System.out.println(obj.multiply("125", "140"));

        System.out.println(obj.multiply("123", "456"));

        System.out.println(obj.multiply("9133", "0"));
    }
}
