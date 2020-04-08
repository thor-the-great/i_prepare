package grooking_coding_patterns.two_pointers;

/**
 * Dutch National Flag Problem (medium)
 *
 * Given an array containing 0s, 1s and 2s, sort the array in-place. You should treat numbers of the
 * array as objects, hence, we can’t count 0s, 1s, and 2s to recreate the array.
 *
 * The flag of the Netherlands consists of three colors: red, white and blue; and since our input
 * array also consists of three different numbers that is why it is called Dutch National Flag problem.
 *
 * Example 1:
 *
 * Input: [1, 0, 2, 1, 0]
 * Output: [0 0 1 1 2]
 * Example 2:
 *
 * Input: [2, 2, 0, 1, 2, 0]
 * Output: [0 0 1 2 2 2 ]
 */
public class DutchNationalFlagProblem {
  /**
   * Idea is - when swap don't andvance pointers right away. This will allow double
   * check for some positions and element can be moved twice if needed.
   *
   * [1, 0, 2, 1, 0]
   *
   * [0, 0, 1, 1, 2]
   * ^  ^
   * ^
   * [2, 2, 0, 1, 2, 0]
   * [0, 2, 0, 1, 2, 2]
   * ^        ^
   * ^
   **/
  public static void sort(int[] arr) {
    int pZeroes = 0, pTwoes = arr.length - 1;
    int p = 0;
    while (p <= pTwoes) {
      if (arr[p] == 0) {
        swap(arr, p++, pZeroes++);
      } else if (arr[p] == 2) {
        swap(arr, p, pTwoes--);
      } else {
        ++p;
      }
    }
  }

  static void swap(int[] arr, int i, int j) {
    int t = arr[i];
    arr[i] = arr[j];
    arr[j] = t;
  }
}

