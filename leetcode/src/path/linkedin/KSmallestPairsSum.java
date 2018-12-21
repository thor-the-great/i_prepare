package path.linkedin;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 373. Find K Pairs with Smallest Sums
 * Medium
 *
 * 508
 *
 * 35
 *
 * Favorite
 *
 * Share
 * You are given two integer arrays nums1 and nums2 sorted in ascending order and an integer k.
 *
 * Define a pair (u,v) which consists of one element from the first array and one element from the second array.
 *
 * Find the k pairs (u1,v1),(u2,v2) ...(uk,vk) with the smallest sums.
 *
 * Example 1:
 *
 * Input: nums1 = [1,7,11], nums2 = [2,4,6], k = 3
 * Output: [[1,2],[1,4],[1,6]]
 * Explanation: The first 3 pairs are returned from the sequence:
 *              [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
 * Example 2:
 *
 * Input: nums1 = [1,1,2], nums2 = [1,2,3], k = 2
 * Output: [1,1],[1,1]
 * Explanation: The first 2 pairs are returned from the sequence:
 *              [1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]
 * Example 3:
 *
 * Input: nums1 = [1,2], nums2 = [3], k = 3
 * Output: [1,3],[2,3]
 * Explanation: All possible pairs are returned from the sequence: [1,3],[2,3]
 */
public class KSmallestPairsSum {

    /**
     * Idea - use minHeap to sort pairs based on their sum. Save indexes for easier use
     * idea is that min sum is closer to the indexes we haven't checked from the beginning because arrays are
     * sorted. Thus fill PQ with K (or nums1.length) elements and then start removing one and add one. For every
     * nums1 index we try to increase nums2 index accordingly unless we hit the max of the nums2
     * @param nums1
     * @param nums2
     * @param k
     * @return
     */
    public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<int[]> res = new LinkedList();
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(x -> (nums2[x[1]] + nums1[x[0]])));
        if (nums1.length == 0 || nums2.length == 0 || k == 0)
            return res;
        for (int i = 0; i < nums1.length && i < k; i++)
            pq.add(new int[]{i, 0});

        while(k > 0 && !pq.isEmpty()) {
            int[] minSum = pq.poll();
            res.add(new int[]{nums1[minSum[0]], nums2[minSum[1]]});
            k--;
            //we can't add more for nums2 - we'll be out of bounds
            if (minSum[1] + 1 == nums2.length)
                continue;
            int[] next = new int[] {minSum[0], minSum[1] + 1};
            pq.add(next);
        }
        return res;
    }
}
