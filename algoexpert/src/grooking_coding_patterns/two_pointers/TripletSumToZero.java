package grooking_coding_patterns.two_pointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Triplet Sum to Zero (medium)
 * <p>
 * Problem Statement #
 * Given an array of unsorted numbers, find all unique triplets in it that add up to zero.
 * <p>
 * Example 1:
 * <p>
 * Input: [-3, 0, 1, 2, -1, 1, -2]
 * Output: [-3, 1, 2], [-2, 0, 2], [-2, 1, 1], [-1, 0, 1]
 * Explanation: There are four unique triplets whose sum is equal to zero.
 * Example 2:
 * <p>
 * Input: [-5, 2, -1, -2, 3]
 * Output: [[-5, 2, 3], [-2, -1, 3]]
 * Explanation: There are two unique triplets whose sum is equal to zero.
 */
public class TripletSumToZero {

  /**
   * Sort the array, then pick elements one by one and with each one element do the
   * two sum trick in O(n) time. Also use sorted array to track the duplicates by
   * checking i and i-1 elements
   * @param arr
   * @return
   */
  public static List<List<Integer>> searchTriplets(int[] arr) {
    List<List<Integer>> triplets = new ArrayList<>();
    Arrays.sort(arr);
    for (int i = 0; i < arr.length - 2; i++) {
      if (i == 0 || arr[i] != arr[i - 1]) {
        int target = -arr[i];
        int l = i + 1, r = arr.length - 1;
        while (l < r) {
          int sum = arr[l] + arr[r];
          if (sum == target) {
            triplets.add(Arrays.asList(arr[i], arr[l], arr[r]));
            ++l;
            --r;
            while (arr[l] == arr[l - 1]) l++;
            while (arr[r] == arr[r + 1]) r--;
          } else if (sum > target) {
            --r;
          } else {
            ++l;
          }
        }
      }
    }
    return triplets;
  }

  public static void main(String[] args) {
    System.out.println(TripletSumToZero.searchTriplets(new int[]{-3, 0, 1, 2, -1, 1, -2}));
    System.out.println(TripletSumToZero.searchTriplets(new int[]{-5, 2, -1, -2, 3}));
  }
}
