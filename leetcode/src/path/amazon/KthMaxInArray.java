package path.amazon;

import java.util.PriorityQueue;

/**
 * Kth Largest Element in an Array
 *   Go to Discuss
 * Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order,
 * not the kth distinct element.
 *
 * Example 1:
 *
 * Input: [3,2,1,5,6,4] and k = 2
 * Output: 5
 * Example 2:
 *
 * Input: [3,2,3,1,2,4,5,5,6] and k = 4
 * Output: 4
 * Note:
 * You may assume k is always valid, 1 ≤ k ≤ array's length.
 *
 */
public class KthMaxInArray {

    public int getKthMax(int[] nums, int k) {
        //idea is to maintain min heap with k elements. Add first k elements, then for each next if
        //element can be added - remove the min after.
        //When all elements are traversed the last min element in queue is the K-th largest
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int i = 0;
        while(pq.size() < k) {
            pq.add(nums[i]);
            i++;
        }
        for (int j = i; j < nums.length; j++) {
            if (pq.add(nums[j]))
                pq.poll();
        }
        return pq.peek();
    }

    public static void main(String[] args) {
        KthMaxInArray obj = new KthMaxInArray();
        System.out.println(obj.getKthMax(new int[]{5, 2, 6, 3, 2, 8, 4}, 3));
    }
}
