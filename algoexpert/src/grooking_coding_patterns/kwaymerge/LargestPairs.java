package grooking_coding_patterns.kwaymerge;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * K Pairs with Largest Sums (Hard) #
 * Given two sorted arrays in descending order, find ‘K’ pairs with the largest sum where each pair consists of
 * numbers from both the arrays.
 *
 * Example 1:
 *
 * Input: L1=[9, 8, 2], L2=[6, 3, 1], K=3
 * Output: [9, 3], [9, 6], [8, 6]
 * Explanation: These 3 pairs have the largest sum. No other pair has a sum larger than any of these.
 * Example 2:
 *
 * Input: L1=[5, 2, 1], L2=[2, -1], K=3
 * Output: [5, 2], [5, -1], [2, 2]
 */
public class LargestPairs {

    /**
     * Put pointers to ar1(0) and ar2(0) to pq. Sort by sum.
     * Extract one pointer, put ot result and advance both ar1 and ar2 pointers by 1.
     * @param nums1
     * @param nums2
     * @param k
     * @return
     */
    public static List<int[]> findKLargestPairs(int[] nums1, int[] nums2, int k) {
        List<int[]> result = new ArrayList<>();
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>(
                Comparator.comparingInt(a->- (nums1[a[0]] + nums2[a[1]])));

        pq.add(new int[] {0,0});
        while(!pq.isEmpty() && result.size() < k) {
            int[] next = pq.poll();

            result.add(new int[] {
                    Math.max(nums1[next[0]], nums2[next[1]]),
                    Math.min(nums1[next[0]], nums2[next[1]])
            });

            if (next[0] + 1 < nums1.length) {
                pq.add(new int[] {next[0] + 1, next[1]});
            }
            if (next[1] + 1 < nums2.length) {
                pq.add(new int[] {next[0], next[1] + 1});
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] l1 = new int[] { 9, 8, 2 };
        int[] l2 = new int[] { 6, 3, 1 };
        List<int[]> result = LargestPairs.findKLargestPairs(l1, l2, 3);
        System.out.print("Pairs with largest sum are: ");
        for (int[] pair : result)
            System.out.print("[" + pair[0] + ", " + pair[1] + "] ");

        l1 = new int[] { 5, 2, 1 };
        l2 = new int[] { 2, -1 };
        result = LargestPairs.findKLargestPairs(l1, l2, 3);
        System.out.print("Pairs with largest sum are: ");
        for (int[] pair : result)
            System.out.print("[" + pair[0] + ", " + pair[1] + "] ");
    }
}