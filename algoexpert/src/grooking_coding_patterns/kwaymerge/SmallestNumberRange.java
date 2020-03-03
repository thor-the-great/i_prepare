package grooking_coding_patterns.kwaymerge;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Smallest Number Range (Hard)
 *
 * Given ‘M’ sorted arrays, find the smallest range that includes at least one number from each of the ‘M’ lists.
 *
 * Example 1:
 *
 * Input: L1=[1, 5, 8], L2=[4, 12], L3=[7, 8, 10]
 * Output: [4, 7]
 * Explanation: The range [4, 7] includes 5 from L1, 4 from L2 and 7 from L3.
 * Example 2:
 *
 * Input: L1=[1, 9], L2=[4, 12], L3=[7, 10, 16]
 * Output: [9, 12]
 * Explanation: The range [9, 12] includes 9 from L1, 12 from L2 and 10 from L3.
 */
public class SmallestNumberRange {
    /**
     * Idea - can solve with k-way merge.
     * Insert all first numbers to pq, keep the max element. Start polling elements (will be min) and compute
     * diff between min and max - will be running min dif. Insert next element in the same list back to pq,
     * update running max on insert. Keep doing so until pq has num_of_lists elements
     * O(N*lgM) where M is number of lists and N - total number of elements
     * O(M) space for heap
     * @param lists
     * @return
     */
    public static int[] findSmallestRange(List<Integer[]> lists) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> lists.get(a[0])[a[1]]));

        int max = 0;
        for (int i = 0;i < lists.size(); i++) {
            pq.add(new int[]{i, 0});
            max = Math.max(lists.get(i)[0], max);
        }

        int[] res = new int[] {0, Integer.MAX_VALUE};
        while (pq.size() == lists.size()) {
            int[] next = pq.poll();
            if (max - lists.get(next[0])[next[1]] < res[1] - res[0]) {
                res[0] = lists.get(next[0])[next[1]];
                res[1] = max;
            }
            if (next[1] + 1 < lists.get(next[0]).length) {
                ++next[1];
                max = Math.max(max, lists.get(next[0])[next[1]]);
                pq.add(next);
            }
        }

        return res;
    }

    public static void main(String[] args) {
        Integer[] l1 = new Integer[] { 1, 9 };
        Integer[] l2 = new Integer[] { 4, 12 };
        Integer[] l3 = new Integer[] { 7, 10, 16 };
        List<Integer[]> lists = new ArrayList<Integer[]>();
        lists.add(l1);
        lists.add(l2);
        lists.add(l3);
        int[] result = SmallestNumberRange.findSmallestRange(lists);
        System.out.print("Smallest range is: [" + result[0] + ", " + result[1] + "]");
    }
}
