package grooking_coding_patterns.binarysearch;

/**
 * Search in Rotated Array (medium) #
 * Given an array of numbers which is sorted in ascending order and also rotated by some arbitrary number, find if a given ‘key’ is present in it.
 *
 * Write a function to return the index of the ‘key’ in the rotated array. If the ‘key’ is not present, return -1. You can assume that the given array does not have any duplicates.
 *
 * Example 1:
 *
 * Input: [10, 15, 1, 3, 8], key = 15
 * Output: 1
 * Explanation: '15' is present in the array at index '1'.
 *     1
 *     3
 *     8
 *     10
 *     15
 *  Original array:
 *  Array after 2 rotations:
 *     10
 *     15
 *     1
 *     3
 *     8
 * Example 2:
 *
 * Input: [4, 5, 7, 9, 10, -1, 2], key = 10
 * Output: 4
 * Explanation: '10' is present in the array at index '4'.
 *  Original array:
 *     -1
 *     2
 *     4
 *     5
 *     7
 *     9
 *     10
 *     4
 *     5
 *     7
 *     9
 *     10
 *     -1
 *     2
 */
public class SearchRotatedArray {

  /**
   * Using binary search find the division point first. After that in both part it's possible to run the binary
   * search. Choose one depends on the value of arr[0]
   * O(lgn) time, O(1) space
   * @param arr
   * @param key
   * @return
   */
  public static int search(int[] arr, int key) {
    if (arr == null || arr.length == 0)
      return -1;
    int N = arr.length;
    int idx = 0;
    if (arr[0] > arr[N - 1]) {
      int l = 0, r = N - 1;
      while (l < r) {
        int m  = l + (r - l)/2;
        if (arr[m] > arr[m + 1]) {
          idx = m;
          break;
        }
        if (arr[m] > arr[0])
          l = m + 1;
        else
          r = m;
      }
    }
    if (arr[0] <= key)
      return doSearch(arr, 0, idx, key);
    return doSearch(arr, idx + 1, N - 1, key);
  }

  static int doSearch(int[] arr, int l, int r, int key) {
    while (l <= r) {
      int m = (l+r)/2;
      if (arr[m] == key)
        return m;
      if(arr[m] > key)
        r = m - 1;
      else
        l = m + 1;
    }
    return -1;
  }


  public static void main(String[] args) {
    System.out.println(SearchRotatedArray.search(new int[] { 10, 15, 1, 3, 8 }, 15));
    System.out.println(SearchRotatedArray.search(new int[] { 4, 5, 7, 9, 10, -1, 2 }, 10));
  }
}

