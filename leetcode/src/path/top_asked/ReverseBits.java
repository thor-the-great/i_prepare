package path.top_asked;

/**
 * Reverse bits of a given 32 bits unsigned integer.
 *
 *
 *
 * Example 1:
 *
 * Input: 00000010100101000001111010011100
 * Output: 00111001011110000010100101000000
 * Explanation: The input binary string 00000010100101000001111010011100 represents the unsigned
 * integer 43261596, so return 964176192 which its binary representation is
 * 00111001011110000010100101000000.
 * Example 2:
 *
 * Input: 11111111111111111111111111111101
 * Output: 10111111111111111111111111111111
 * Explanation: The input binary string 11111111111111111111111111111101 represents the unsigned
 * integer 4294967293, so return 3221225471 which its binary representation is
 * 10111111111111111111111111111111.
 *
 *
 * Note:
 *
 * Note that in some languages such as Java, there is no unsigned integer type. In this case,
 * both input and output will be given as signed integer type and should not affect your
 * implementation, as the internal binary representation of the integer is the same whether it is
 * signed or unsigned.
 * In Java, the compiler represents the signed integers using 2's complement notation. Therefore,
 * in Example 2 above the input represents the signed integer -3 and the output represents the
 * signed integer -1073741825.
 *
 *
 * Follow up:
 *
 * If this function is called many times, how would you optimize it?
 */
public class ReverseBits {

    /**
     * Going from center to left and right at the same time. Create mask for every bit and then
     * shift masked bit from n to the 2*c - 1 positions
     * @param n
     * @return
     */
    public int reverseBits(int n) {
        long longN = n, res = 0;
        long lMask = 1<<16, rMask = 1<<15;
        int c = 0;
        while (c++ <= 16) {
            long lVal = (longN&lMask), rVal = (longN&rMask);
            int numOfShiftBits = 2*c - 1;
            res |= rVal<<numOfShiftBits;
            res |= lVal>>numOfShiftBits;
            lMask<<=1;
            rMask>>=1;
        }
        return (int)res;
    }

    public static void main(String[] args) {
        ReverseBits obj = new ReverseBits();
        int num = 0b110100001;
        int ans = 0;
        ans = obj.reverseBits(num);
        System.out.println(Integer.toBinaryString(num));
        System.out.println(Integer.toBinaryString(ans));

        num =   0b10000000000000000000000000000000;
        ans = obj.reverseBits(num);
        System.out.println(Integer.toBinaryString(num));
        System.out.println(Integer.toBinaryString(ans));
    }
}
