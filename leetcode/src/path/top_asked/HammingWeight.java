package path.top_asked;

/**
 *  Number of 1 Bits
 * Write a function that takes an unsigned integer and returns the number of '1' bits it has (also known as the
 * Hamming weight).
 *
 * Example 1:
 *
 * Input: 11
 * Output: 3
 * Explanation: Integer 11 has binary representation 00000000000000000000000000001011
 * Example 2:
 *
 * Input: 128
 * Output: 1
 * Explanation: Integer 128 has binary representation 00000000000000000000000010000000
 *
 */
public class HammingWeight {

    public int hammingWeight(int n) {
        int c = 0;
        for (int i = 0; i < 32; i++) {
            long x = (1l << i);
            if ((n&x) > 0 )
                c++;
        }
        return c;
    }

    public static void main(String[] args) {
        HammingWeight obj = new HammingWeight();
    }
}
