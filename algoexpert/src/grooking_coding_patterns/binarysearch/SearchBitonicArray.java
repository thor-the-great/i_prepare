package grooking_coding_patterns.binarysearch;

/**
 * Search Bitonic Array (medium) #
 * Given a Bitonic array, find if a given ‘key’ is present in it. An array is considered bitonic if it is monotonically
 * increasing and then monotonically decreasing. Monotonically increasing or decreasing means that for any index i
 * in the array arr[i] != arr[i+1].
 *
 * Write a function to return the index of the ‘key’. If the ‘key’ is not present, return -1.
 *
 * Example 1:
 *
 * Input: [1, 3, 8, 4, 3], key=4
 * Output: 3
 * Example 2:
 *
 * Input: [3, 8, 3, 1], key=8
 * Output: 1
 * Example 3:
 *
 * Input: [1, 3, 8, 12], key=12
 * Output: 3
 * Example 4:
 *
 * Input: [10, 9, 8], key=10
 * Output: 0
 */
public class SearchBitonicArray {

  /**
   * Do inary search to find the middle point of the array (the highest value)
   * Then do normal and inverted binary search in each half.
   * @param arr
   * @param key
   * @return
   */
  public static int search(int[] arr, int key) {
    int l = 0, r = arr.length - 1;

    while ( l < r) {
      int m  = l + (r - l)/2;
      if (arr[m] > arr[m + 1])
        r = m;
      else
        l = m + 1;
    }
    int idx = l;
    int left = doSearch(arr, 0, idx, key, true);
    if (left == -1)
      return doSearch(arr, idx, arr.length - 1, key, false);
    return left;
  }

  static int doSearch(int[] arr, int l, int r, int key, boolean asc) {
    while (l <= r) {
      int m = l + (r-l)/2;
      if (arr[m] == key)
        return m;
      if (arr[m] > key) {
        if (asc)
          r = m - 1;
        else
          l = m + 1;
      }
      else {
        if (asc)
          l = m + 1;
        else
          r = m - 1;
      }
    }
    return -1;
  }

  public static void main(String[] args) {
    System.out.println(SearchBitonicArray.search(new int[] { 1, 3, 8, 4, 3 }, 4));
    System.out.println(SearchBitonicArray.search(new int[] { 3, 8, 3, 1 }, 8));
    System.out.println(SearchBitonicArray.search(new int[] { 1, 3, 8, 12 }, 12));
    System.out.println(SearchBitonicArray.search(new int[] { 10, 9, 8 }, 10));
  }
}
