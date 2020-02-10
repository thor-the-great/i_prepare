package arrays;

/**
 * 283. Move Zeroes
 * Easy
 *
 * Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of
 * the non-zero elements.
 *
 * Example:
 *
 * Input: [0,1,0,3,12]
 * Output: [1,3,12,0,0]
 * Note:
 *
 * You must do this in-place without making a copy of the array.
 * Minimize the total number of operations.
 */
public class MoveZeroes {

    /**
     * Idea - copy not-nul elements as we go, increment pointer for not-'0'. At the end N - pointer == number of '0'.
     * O(n) time, O(1) space
     * @param nums
     */
    public void moveZeroes(int[] nums) {
        int N = nums.length;
        if (N == 0)
            return;

        int p = 0;
        for (int n : nums) {
            if (n != 0) {
                nums[p++] = n;
            }
        }
        while(p < N)
            nums[p++] = 0;
    }
}
