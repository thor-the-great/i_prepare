package map;

import java.util.*;

/**
 * 1342. Reduce Array Size to The Half
 * Medium
 *
 * Given an array arr.  You can choose a set of integers and remove all the occurrences of these integers in the array.
 *
 * Return the minimum size of the set so that at least half of the integers of the array are removed.
 *
 *
 *
 * Example 1:
 *
 * Input: arr = [3,3,3,3,5,5,5,2,2,7]
 * Output: 2
 * Explanation: Choosing {3,7} will make the new array [5,5,5,2,2] which has size 5 (i.e equal to half of the size of
 * the old array).
 * Possible sets of size 2 are {3,5},{3,2},{5,2}.
 * Choosing set {2,7} is not possible as it will make the new array [3,3,3,3,5,5,5] which has size greater than half
 * of the size of the old array.
 * Example 2:
 *
 * Input: arr = [7,7,7,7,7,7]
 * Output: 1
 * Explanation: The only possible set you can choose is {7}. This will make the new array empty.
 * Example 3:
 *
 * Input: arr = [1,9]
 * Output: 1
 * Example 4:
 *
 * Input: arr = [1000,1000,3,7]
 * Output: 1
 * Example 5:
 *
 * Input: arr = [1,2,3,4,5,6,7,8,9,10]
 * Output: 5
 *
 *
 * Constraints:
 *
 * 1 <= arr.length <= 10^5
 * arr.length is even.
 * 1 <= arr[i] <= 10^5
 */
public class ReduceArraySizeToHalf {

    /**
     * Using map collect frequency of occurance for each element in array. Idea is that we need to start from the
     * most frequent one, this way we can reach the half faster so the number of elements will be lower.
     * Sort the collection by the frequency, then starting from the most freq one decrement by that qty until we've
     * reach the N/2
     * @param arr
     * @return
     */
    public int minSetSize(int[] arr) {
        int N = arr.length;
        //map num->count_of_num
        Map<Integer, Integer> m = new HashMap();
        for (int n : arr)
            m.put(n, m.getOrDefault(n, 0) + 1);

        List<Integer> list = new ArrayList(m.values());
        //sort list of count of each value
        Collections.sort(list);
        //N/2 is the number we are looking for
        N/=2; int p = list.size() - 1;
        while (N > 0) {
            //decrement N by the next highest quantity while it's still >= 0
            N -= list.get(p--);
        }
        //p now points to the next element after one that splits array into halves. Get number of elements as list.size() - p - 1
        return list.size() - p - 1;
    }
}
