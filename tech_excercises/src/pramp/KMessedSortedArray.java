package pramp;

import java.util.PriorityQueue;
import utils.StringUtils;

/**
 * K-Messed Array Sort
 * Given an array of integers arr where each element is at most k places away from its sorted position, code an
 * efficient function sortKMessedArray that sorts arr. For instance, for an input array of size 10 and k = 2, an
 * element belonging to index 6 in the sorted array will be located at either index 4, 5, 6, 7 or 8 in the input array.
 *
 * Analyze the time and space complexities of your solution.
 *
 * Example:
 *
 * input:  arr = [1, 4, 5, 2, 3, 7, 8, 6, 10, 9], k = 2
 *
 * output: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
 * Constraints:
 *
 * [time limit] 5000ms
 *
 * [input] array.integer arr
 *
 * 1 ≤ arr.length ≤ 100
 * [input] integer k
 *
 * 1 ≤ k ≤ 20
 * [output] array.integer
 */
public class KMessedSortedArray {

    /**
     * Idea - use minHeap (java priorityqueue) to store k elements, one of those must be out next min. Every time
     * the number of elements in heap be k. Iterate over array, add element to heap and extract next min.
     * Complexity - O(n*logK) - n elements heap has K elements max
     * Space - O(k) for the heap
     *
     * @param arr
     * @param k
     * @return
     */
    int[] sortKMessedArray(int[] arr, int k) {
        // your code goes here
        int N = arr.length;
        if ( N <= 1)
            return arr;

        PriorityQueue<Integer> pq = new PriorityQueue();
        //k elements
        for (int i = 0; i <= k && i < N; i++)
            pq.add(arr[i]);

        int p = 0;
        int i = k + 1;
        while (!pq.isEmpty()) {
            if (i < N) {
                pq.add(arr[i++]);
            }
            arr[p++] = pq.poll();
        }
        return arr;
    }

    public static void main(String[] args) {
        KMessedSortedArray obj = new KMessedSortedArray();
        int[] arr;
        arr = new int[] {
                1, 4, 5, 2, 3, 7, 8, 6, 10, 9
        };
        int[] sorted = obj.sortKMessedArray(arr, 2);
        System.out.println(StringUtils.intArrayToString(sorted));
    }
}
