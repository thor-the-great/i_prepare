package path.google;

/**
 * 260. Single Number III
 * Medium
 * 652
 * 62
 *
 *
 * Given an array of numbers nums, in which exactly two elements appear only once and all the other elements appear
 * exactly twice. Find the two elements that appear only once.
 *
 * Example:
 *
 * Input:  [1,2,1,3,2,5]
 * Output: [3,5]
 * Note:
 *
 * The order of the result is not important. So in the above example, [5, 3] is also correct.
 * Your algorithm should run in linear runtime complexity. Could you implement it using only constant space complexity?
 *
 */
public class TwoNonDuplicateInDuplicates {

    /**
     * Idea is - first XOR all elements, this way we'll get XOR of two singles (others will be cancelled because they
     * are duplicates)
     * If we have XOR it means - if bit is 1 in XOR it's 1 in 1 or 2 single. If it's 0 it's either both 1s or 0s.
     * 
     *
     * @param nums
     * @return
     */
    public int[] singleNumber(int[] nums) {
        int xor = 0;
        for (int num : nums) {
            xor ^= num;
        }
        int bit_mask = xor&(-xor);
        int[] res = new int[2];
        for(int num : nums) {
            if ((num & bit_mask) == 0)
                res[0]^=num;
            else
                res[1]^=num;
        }
        return res;
    }

    public static void main(String[] args) {
        TwoNonDuplicateInDuplicates obj = new TwoNonDuplicateInDuplicates();
        int[] singles = obj.singleNumber(new int[] {2, 4, 2, 5, 6, 3, 4, 3});
        System.out.println(singles[0] + " " + singles[1]);
    }
}
