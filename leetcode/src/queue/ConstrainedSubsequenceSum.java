package queue;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 1425. Constrained Subsequence Sum
 * Hard
 *
 * Given an integer array nums and an integer k, return the maximum sum of a non-empty
 * subsequence of that array such that for every two consecutive integers in the subsequence,
 * nums[i] and nums[j], where i < j, the condition j - i <= k is satisfied.
 *
 * A subsequence of an array is obtained by deleting some number of elements (can be zero) from
 * the array, leaving the remaining elements in their original order.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [10,2,-10,5,20], k = 2
 * Output: 37
 * Explanation: The subsequence is [10, 2, 5, 20].
 * Example 2:
 *
 * Input: nums = [-1,-2,-3], k = 1
 * Output: -1
 * Explanation: The subsequence must be non-empty, so we choose the largest number.
 * Example 3:
 *
 * Input: nums = [10,-2,-10,-5,20], k = 2
 * Output: 23
 * Explanation: The subsequence is [10, -2, -5, 20].
 *
 *
 * Constraints:
 *
 * 1 <= k <= nums.length <= 10^5
 * -10^4 <= nums[i] <= 10^4
 */
public class ConstrainedSubsequenceSum {

  /**
   * Using double ended queue (deque), idea is to keep max of the window of length k in the
   * first position of the queue. As we iterate we add elements that can be potential max and pop
   * elements that are out of the current window (distance is more than k)
   * we use temporary array to save interim results
   * O(n) time - iterate over elements once
   * O(n) space - need to keep copy of array plus the deque (of size k max)
   * @param nums
   * @param k
   * @return
   */
  public int constrainedSubsetSum(int[] nums, int k) {
    Deque<Integer> deq = new LinkedList();
    int res = Integer.MIN_VALUE;
    int[] tmp = new int[nums.length];
    for (int i = 0; i < nums.length; i++) {
      tmp[i] = nums[i] + Math.max(0, deq.isEmpty() ? 0 : tmp[deq.peekFirst()]);
      res = Math.max(res, tmp[i]);

      while(!deq.isEmpty() && tmp[deq.peekLast()] <= tmp[i])
        deq.pollLast();

      deq.addLast(i);

      if ( k < i - deq.peekFirst() + 1) {
        deq.pollFirst();
      }
    }
    return res;
  }
}
