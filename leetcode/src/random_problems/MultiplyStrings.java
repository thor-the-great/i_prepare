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
        int[] prod = new int[num1.length() + num2.length() ];
        int[] c = new int[prod.length];
        for (int n2 = num2.length() - 1; n2 >= 0; n2--) {
            int d2 = num2.charAt(n2) - '0';
            if (d2 != 0) {
                for (int n1 = num1.length() - 1; n1 >= 0; n1--) {
                    int d1 = num1.charAt(n1) - '0';
                    int p = d1 * d2 + c[n1 + n2 + 1];
                    c[n1 + n2 + 1] = 0;
                    int newP = prod[n1 + n2 + 1] + (p % 10);
                    if (newP > 9) {
                        prod[n1 + n2 + 1] = newP % 10;
                        c[n1 + n2] = newP / 10;
                    } else {
                        prod[n1 + n2 + 1] = newP;
                    }
                    c[n1 + n2] += p / 10;
                }
            }
            prod[n2] = c[n2];
            c[n2] = 0;
        }
        boolean nonNil = false;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < prod.length; i ++) {
            if (prod[i] == 0 && !nonNil)
                continue;
            else {
                sb.append(prod[i]);
                nonNil = true;
            }
        }
        return !nonNil ? "0" : sb.toString();
    }

    public static void main(String[] args) {
        MultiplyStrings obj = new MultiplyStrings();
        System.out.println(obj.multiply("2", "3"));

        System.out.println(obj.multiply("125", "140"));

        System.out.println(obj.multiply("123", "456"));

        System.out.println(obj.multiply("9133", "0"));
    }
}
