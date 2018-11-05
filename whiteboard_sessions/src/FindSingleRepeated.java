/**
 * Leetcode # 137 - Single Number II
 *
 * Given a non-empty array of integers, every element appears three times except for one, which appears exactly once.
 * Find that single one.
 *
 * Note:
 *
 * Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
 *
 * Example 1:
 *
 * Input: [2,2,3,2]
 * Output: 3
 * Example 2:
 *
 * Input: [0,1,0,1,0,1,99]
 * Output: 99
 */
public class FindSingleRepeated {

    public int singleNumber(int[] nums) {
        int bits = 32;
        int num = 0;
        for (int i = 0; i < bits; i++) {
            int sum = 0;
            int checkNum = 1 << i;
            for (int j =0; j < nums.length; j++ ) {                ;
                if ((nums[j]&checkNum) == 0 )
                    sum++;
            }
            if (sum % 3 == 0) {
                num = num | checkNum;
            }
        }
        return num;
    }
}
