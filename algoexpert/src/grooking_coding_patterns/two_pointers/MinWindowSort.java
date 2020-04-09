package grooking_coding_patterns.two_pointers;

/**
 * Minimum Window Sort (medium) #
 * Given an array, find the length of the smallest subarray in it which when sorted will sort the
 * whole array.
 *
 * Example 1:
 *
 * Input: [1, 2, 5, 3, 7, 10, 9, 12]
 * Output: 5
 * Explanation: We need to sort only the subarray [5, 3, 7, 10, 9] to make the whole array sorted
 * Example 2:
 *
 * Input: [1, 3, 2, 0, -1, 7, 10]
 * Output: 5
 * Explanation: We need to sort only the subarray [1, 3, 2, 0, -1] to make the whole array sorted
 * Example 3:
 *
 * Input: [1, 2, 3]
 * Output: 0
 * Explanation: The array is already sorted
 * Example 4:
 *
 * Input: [3, 2, 1]
 * Output: 3
 * Explanation: The whole array needs to be sorted.
 */
public class MinWindowSort {

  /**
   * Find first and last unsorted elements. Then find min and max of the subarray between start and
   * end. Then look for elements < then min to the left and larger than a max to the right.
   * @param arr
   * @return
   */
  public static int sort(int[] arr) {
    int l = 1, r = arr.length - 2;
    int start = -1, end = -1;
    for (; l < arr.length; l++) {
      if (arr[l] <= arr[l - 1]) {
        start = l - 1;
        break;
      }
    }
    if (start == -1)
      return 0;

    for (; r >= 0; r--) {
      if (arr[r] >= arr[r + 1]) {
        end = r + 1;
        break;
      }
    }

    int subMin = Integer.MAX_VALUE, subMax = Integer.MIN_VALUE;
    for (int i = start; i <= end; i++) {
      subMin = Math.min(subMin, arr[i]);
      subMax = Math.max(subMax, arr[i]);
    }

    for (int i = 0; i < start; i++) {
      if (arr[i] > subMin) {
        start = i;
        break;
      }
    }

    for (int i = arr.length - 1; i > end ; i--) {
      if (arr[i] < subMax) {
        end = i;
        break;
      }
    }

    return end - start + 1;
  }
}
