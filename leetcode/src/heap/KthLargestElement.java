package heap;

import java.util.PriorityQueue;

/**
 * 703. Kth Largest Element in a Stream
 * Easy
 *
 * Design a class to find the kth largest element in a stream. Note that it is the kth largest
 * element in the sorted order, not the kth distinct element.
 *
 * Your KthLargest class will have a constructor which accepts an integer k and an integer array
 * nums, which contains initial elements from the stream. For each call to the method KthLargest
 * .add, return the element representing the kth largest element in the stream.
 *
 * Example:
 *
 * int k = 3;
 * int[] arr = [4,5,8,2];
 * KthLargest kthLargest = new KthLargest(3, arr);
 * kthLargest.add(3);   // returns 4
 * kthLargest.add(5);   // returns 5
 * kthLargest.add(10);  // returns 5
 * kthLargest.add(9);   // returns 8
 * kthLargest.add(4);   // returns 8
 * Note:
 * You may assume that nums' length ≥ k-1 and k ≥ 1.
 */
public class KthLargestElement {
  int k;
  PriorityQueue<Integer> pq;

  /**
   * Keep the pq of k elements max
   * @param k
   * @param nums
   */
  public KthLargestElement(int k, int[] nums) {
    this.k = k;
    pq = new PriorityQueue<>();
    for (int n : nums) {
      pq.add(n);
      if (pq.size() > k)
        pq.poll();
    }
  }

  public int add(int val) {
    pq.add(val);
    if (pq.size() > k)
      pq.poll();
    return pq.peek();
  }
}
