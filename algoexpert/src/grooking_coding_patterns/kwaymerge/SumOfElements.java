/**
 * Copyright (c) 2019 Ariba, Inc. All rights reserved. Patents pending.
 */
package grooking_coding_patterns.kwaymerge;

import java.util.PriorityQueue;

/**
 * Sum of Elements (medium)
 *
 * Given an array, find the sum of all numbers between the K1’th and K2’th smallest elements of
 * that array.
 *
 * Example 1:
 *
 * Input: [1, 3, 12, 5, 15, 11], and K1=3, K2=6
 * Output: 23
 * Explanation: The 3rd smallest number is 5 and 6th smallest number 15. The sum of numbers coming
 * between 5 and 15 is 23 (11+12).
 * Example 2:
 *
 * Input: [3, 5, 8, 7], and K1=1, K2=4
 * Output: 12
 * Explanation: The sum of the numbers between the 1st smallest number (3) and the 4th smallest
 * number (8) is 12 (5+7).
 *
 */
public class SumOfElements {

  /**
   * Put all elements to the max heap, limited by size k2. While keep removing elemenent till size
   * became k1, but this time accumulating values
   *
   * @param nums
   * @param k1
   * @param k2
   * @return
   */
    public static int findSumOfElements(int[] nums, int k1, int k2) {
      PriorityQueue<Integer> pq = new PriorityQueue<>((i1, i2)-> i2- i1);
      for (int n : nums) {
        pq.add(n);
        if (pq.size() >= k2) {
          pq.poll();
        }
      }

      int sum = 0;
      while ( pq.size() > k1) {
        sum+=pq.poll();
      }

      return sum;
    }

    public static void main(String[] args) {
      int result = SumOfElements.findSumOfElements(new int[] { 1, 3, 12, 5, 15, 11 }, 3, 6);
      System.out.println("Sum of all numbers between k1 and k2 smallest numbers: " + result);

      result = SumOfElements.findSumOfElements(new int[] { 3, 5, 8, 7 }, 1, 4);
      System.out.println("Sum of all numbers between k1 and k2 smallest numbers: " + result);
    }
  }
