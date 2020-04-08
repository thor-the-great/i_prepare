package grooking_coding_patterns.two_pointers;

import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Subarrays with Product Less than a Target (medium)
 *
 * Example 1:
 *
 * Input: [2, 5, 3, 10], target=30 Output: [2], [5], [2, 5], [3], [5, 3], [10] Explanation: There
 * are six contiguous subarrays whose product is less than the target. Example 2:
 *
 * Input: [8, 2, 6, 5], target=50 Output: [8], [2], [8, 2], [6], [2, 6], [5], [6, 5] Explanation:
 * There are seven contiguous subarrays whose product is less than the target.
 */
public class SubArraysWithProductLessTarget {

  /**
   * advance pointer to the right, keep current product. If >= target - do while until it's less.
   * If something left in the deq - those are good elements. Add them to the list.
   */
  public static List<List<Integer>> findSubarrays(int[] arr, int target) {
    List<List<Integer>> subarrays = new ArrayList<>();
    if (arr.length == 0)
      return subarrays;

    int cur = 1;
    Deque<Integer> deq = new LinkedList();
    for (int r = 0; r < arr.length; r++) {
      cur *= arr[r];
      deq.addLast(arr[r]);

      while (!deq.isEmpty() && cur >= target) {
        cur /= deq.pollFirst();
      }

      if (!deq.isEmpty()) {
        List<Integer> tmp = new ArrayList();
        Iterator<Integer> it = deq.descendingIterator();
        while (it.hasNext()) {
          tmp.add(it.next());
          subarrays.add(new ArrayList(tmp));
        }
      }
    }

    return subarrays;
  }
}

