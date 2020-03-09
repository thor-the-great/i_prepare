package grooking_coding_patterns.xor;

/**
 * Single Number (easy)
 * We'll cover the following
 * Problem Statement
 * Try it yourself
 * Solution
 * Solution with XOR
 * Code
 * Problem Statement #
 * In a non-empty array of integers, every number appears twice except for one, find that single number.
 *
 * Example 1:
 *
 * Input: 1, 4, 2, 1, 3, 2, 3
 * Output: 4
 * Example 2:
 *
 * Input: 7, 9, 7
 * Output: 9
 */
public class SingleNumber {

  /**
   * Doing XOR with the number itself gives us 0. But if the number is missing - it will be the same number.
   *
   * We're doing XOR with every number from array, then the number that is in the xor is our missing number (one
   * that was only once in the array)
   * @param arr
   * @return
   */
  public static int findSingleNumber(int[] arr) {
    int xor = 0;
    for (int n : arr) {
      xor^=n;
    }
    return xor;
  }

  public static void main( String args[] ) {
    System.out.println(findSingleNumber(new int[]{1, 4, 2, 1, 3, 2, 3}));
    System.out.println(findSingleNumber(new int[]{7, 9, 7}));
  }
}
