import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 1122. Relative Sort Array
 * Easy
 *
 * Given two arrays arr1 and arr2, the elements of arr2 are distinct, and all elements in arr2 are also in arr1.
 *
 * Sort the elements of arr1 such that the relative ordering of items in arr1 are the same as in arr2.
 * Elements that don't appear in arr2 should be placed at the end of arr1 in ascending order.
 *
 *
 *
 * Example 1:
 *
 * Input: arr1 = [2,3,1,3,2,4,6,7,9,2,19], arr2 = [2,1,4,3,9,6]
 * Output: [2,2,2,1,4,3,3,9,6,7,19]
 *
 *
 * Constraints:
 *
 * arr1.length, arr2.length <= 1000
 * 0 <= arr1[i], arr2[i] <= 1000
 * Each arr2[i] is distinct.
 * Each arr2[i] is in arr1.
 */
public class RelativeSortArray {

    /**
     * Create map of num->freq for arr1. Then iterate over arr2 and pop values from map, fill as per
     * freq. Then the rest of the arr1 (in the map) - get the keys, sort them and do the same routine
     * @param arr1
     * @param arr2
     * @return
     */
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        if (arr1.length == 0)
            return new int[0];

        Map<Integer, Integer> map = new HashMap();
        for (int n : arr1) map.put(n, map.getOrDefault(n, 0) + 1);

        int[] res = new int[arr1.length];
        int p = 0;
        for (int n : arr2) {
            int count = map.get(n);
            for (int i = 0; i < count; i++) {
                res[p++] = n;
            }
            map.remove(n);
        }

        if (map.size() > 0) {
            int[] keys = new int[map.size()];
            int i = 0;
            for (int n : map.keySet()) keys[i++] = n;
            Arrays.sort(keys);
            for (int n : keys) {
                int count = map.get(n);
                for (int j = 0; j < count; j++) {
                    res[p++] = n;
                }
            }
        }
        return res;
    }
}
