package random_problems;

import java.util.Arrays;

/**
 * 75. Sort Colors
 * Medium
 *
 * 1295
 *
 * 131
 *
 * Favorite
 *
 * Share
 * Given an array with n objects colored red, white or blue, sort them in-place so that objects of the same color are
 * adjacent, with the colors in the order red, white and blue.
 *
 * Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.
 *
 * Note: You are not suppose to use the library's sort function for this problem.
 *
 * Example:
 *
 * Input: [2,0,2,1,1,0]
 * Output: [0,0,1,1,2,2]
 * Follow up:
 *
 * A rather straight forward solution is a two-pass algorithm using counting sort.
 * First, iterate the array counting number of 0's, 1's, and 2's, then overwrite array with total number of 0's, then
 * 1's and followed by 2's.
 * Could you come up with a one-pass algorithm using only constant space?
 *
 */
public class SortColors {

    public void sortColors(int[] nums) {
        int N = nums.length;
        int j = 0, k = N - 1;
        for (int i = 0; i <= k; i++) {
            if (nums[i] == 0) {
                swap(nums, i, j++);
            } else if (nums[i] == 2) {
                swap(nums, i--, k--);
            }
        }
    }

    void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    public static void main(String[] args) {
        SortColors obj = new SortColors();
        int[] arr = new int[] {2, 0, 2, 1, 1, 0};
        obj.sortColors(arr);
        System.out.print("[ ");
        Arrays.stream(arr).forEach(n->System.out.print(n + ", "));
        System.out.println(" ]");
    }
}
