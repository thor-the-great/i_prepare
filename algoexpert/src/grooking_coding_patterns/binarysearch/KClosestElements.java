package grooking_coding_patterns.binarysearch;

import java.util.*;

/**
 * 'K' Closest Numbers (medium)
 *
 * Given a sorted number array and two integers ‘K’ and ‘X’, find ‘K’ closest numbers to ‘X’ in
 * the array. Return the numbers in the sorted order. ‘X’ is not necessarily present in the array.
 *
 * Example 1:
 *
 * Input: [5, 6, 7, 8, 9], K = 3, X = 7
 * Output: [6, 7, 8]
 * Example 2:
 *
 * Input: [2, 4, 5, 6, 9], K = 3, X = 6
 * Output: [4, 5, 6]
 * Example 3:
 *
 * Input: [2, 4, 5, 6, 9], K = 3, X = 10
 * Output: [5, 6, 9]
 */
public class KClosestElements {

  /**
   * find the
   * @param arr
   * @param K
   * @param X
   * @return
   */
  public static List<Integer> findClosestElements(int[] arr, int K, Integer X) {
    List<Integer> result = new ArrayList<>();
    int leftIndex = getIndex(arr, X);
    int rightIndex = leftIndex + 1;
    while(result.size() < K) {
      if (leftIndex >= 0 && rightIndex < arr.length) {
        int leftDif = Math.abs(X - arr[leftIndex]);
        int rightDif = Math.abs(X - arr[rightIndex]);
        if (leftDif <= rightDif) {
          result.add(0, arr[leftIndex--]);
        } else {
          result.add(arr[rightIndex++]);
        }
      } else if (leftIndex >= 0) {
        result.add(0, arr[leftIndex--]);
      } else if (rightIndex < arr.length) {
        result.add(arr[rightIndex++]);
      }
    }
    return result;
  }

  static int getIndex(int[] arr, int X) {
    int l = 0, r = arr.length - 1;
    int dif = Integer.MAX_VALUE;
    while (l <= r ) {
      int m = l + (r - l) / 2;
      if (arr[m] == X)
        return m;
      if (arr[m] < X)
        l = m + 1;
      else
        r = m - 1;
    }
    return l > 0 ? l - 1 : 0;
  }

  public static void main(String[] args) {
    List<Integer> result = KClosestElements.findClosestElements(new int[] { 3, 5, 6, 7, 8, 9 }, 3, 7);
    System.out.println("'K' closest numbers to 'X' are: " + result);

    result = KClosestElements.findClosestElements(new int[] { 1, 2, 4, 5, 6, 9 }, 3, 6);
    System.out.println("'K' closest numbers to 'X' are: " + result);

    result = KClosestElements.findClosestElements(new int[] { 0, 2, 4, 5, 6, 9 }, 3, 10);
    System.out.println("'K' closest numbers to 'X' are: " + result);
  }
}
