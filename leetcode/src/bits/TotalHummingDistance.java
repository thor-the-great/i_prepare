package bits;

/**
 * 477. Total Hamming Distance
 * Medium
 *
 * The Hamming distance between two integers is the number of positions at which the
 * corresponding bits are different.
 *
 * Now your job is to find the total Hamming distance between all pairs of the given numbers.
 *
 * Example:
 * Input: 4, 14, 2
 *
 * Output: 6
 *
 * Explanation: In binary representation, the 4 is 0100, 14 is 1110, and 2 is 0010 (just
 * showing the four bits relevant in this case). So the answer will be:
 * HammingDistance(4, 14) + HammingDistance(4, 2) + HammingDistance(14, 2) = 2 + 2 + 2 = 6.
 * Note:
 * Elements of the given array are in the range of 0 to 10^9
 * Length of the array will not exceed 10^4.
 */
public class TotalHummingDistance {

    /**
     * Iterate over every i-th bit in every number sequentially. Count number of bits
     * set. For such pairs of x having that  bit and N - x not having the bit the overall
     * number of pairs is x*(N-x).
     * @param nums
     * @return
     */
    public int totalHammingDistanceFaster(int[] nums) {
        int res = 0, N = nums.length;
        for (int b = 0; b < 32; b++) {
            int bitCount = 0, mask = (1<<b);
            for (int i =0; i < nums.length; i++) {
                if ((nums[i]&mask) > 0)
                    ++bitCount;
            }
            res += (bitCount*(N - bitCount));
        }
        return res;
    }

    /**
     * Brute force - get every pair of numbers and get distance for every of that pair
     * @param nums
     * @return
     */
    public int totalHammingDistance(int[] nums) {
        int res = 0;
        for (int i =0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                res+=Integer.bitCount(nums[i]^nums[j]);
            }
        }
        return res;
    }
}
