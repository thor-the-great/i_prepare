package random_problems;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 870. Advantage Shuffle
 * Medium
 *
 * Given two arrays A and B of equal size, the advantage of A with respect to B is the number of indices i for which
 * A[i] > B[i].
 *
 * Return any permutation of A that maximizes its advantage with respect to B.
 *
 *
 *
 * Example 1:
 *
 * Input: A = [2,7,11,15], B = [1,10,4,11]
 * Output: [2,11,7,15]
 * Example 2:
 *
 * Input: A = [12,24,8,32], B = [13,25,32,11]
 * Output: [24,32,8,12]
 *
 *
 * Note:
 *
 * 1 <= A.length = B.length <= 10000
 * 0 <= A[i] <= 10^9
 * 0 <= B[i] <= 10^9
 *
 */
public class AdvantageShuffle {

    /**
     * Idea: sort A and B. Starting from highest B if you can beat it with highest A - take it. Otherwise
     * you can't beat it with any of A, so use lowest and save highest for next B elements.
     * Use PQ of array object because we need to keep original indexes to place element in result array
     * @param A
     * @param B
     * @return
     */
    public int[] advantageCount(int[] A, int[] B) {
        Arrays.sort(A);
        int N = A.length;

        Comparator<int[]> comp = new Comparator<int[]>() {
            public int compare(int[] a1, int[] a2) {
                return a2[0] - a1[0];
            }
        };
        PriorityQueue<int[]> pq = new PriorityQueue<>(comp);

        for (int i = 0; i < N; i++) {
            pq.add(new int[] {B[i], i});
        }

        int[] res = new int[N];
        int lo = 0, hi = N - 1;
        while (!pq.isEmpty() ) {
            int[] b = pq.poll();
            int bEl = b[0];
            if (A[hi] > bEl) {
                res[b[1]] = A[hi--];
            } else {
                res[b[1]] = A[lo++];
            }
        }
        return res;
    }
}
