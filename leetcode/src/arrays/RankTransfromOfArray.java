package arrays;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 1331. Rank Transform of an Array
 * Easy
 *
 * Given an array of integers arr, replace each element with its rank.
 *
 * The rank represents how large the element is. The rank has the following rules:
 *
 * Rank is an integer starting from 1.
 * The larger the element, the larger the rank. If two elements are equal, their rank must be the same.
 * Rank should be as small as possible.
 *
 *
 * Example 1:
 *
 * Input: arr = [40,10,20,30]
 * Output: [4,1,2,3]
 * Explanation: 40 is the largest element. 10 is the smallest. 20 is the second smallest. 30 is the third smallest.
 * Example 2:
 *
 * Input: arr = [100,100,100]
 * Output: [1,1,1]
 * Explanation: Same elements share the same rank.
 * Example 3:
 *
 * Input: arr = [37,12,28,9,100,56,80,5,12]
 * Output: [5,3,4,2,8,6,7,1,3]
 *
 *
 * Constraints:
 *
 * 0 <= arr.length <= 105
 * -109 <= arr[i] <= 109
 */
public class RankTransfromOfArray {

    /**
     * Sort the copy of array, start iterating over element and increment rank, put value-rank pair to the map
     * Then iterate over original array, lookup rank by value and insert into resulting array
     * @param arr
     * @return
     */
    public int[] arrayRankTransform(int[] arr) {
        int N = arr.length;

        int[] ranks = Arrays.copyOf(arr, N);
        Arrays.sort(ranks);
        int rank = 1;
        Map<Integer, Integer> m = new HashMap();
        for (int n : ranks) {
            if (!m.containsKey(n))
                m.put(n, rank++);
        }

        for (int i = 0; i < N; i++)
            ranks[i] = m.get(arr[i]);

        return ranks;
    }
}
