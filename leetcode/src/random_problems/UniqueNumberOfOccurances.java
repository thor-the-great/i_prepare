package random_problems;

import java.util.HashSet;
import java.util.Set;

/**
 * 1207. Unique Number of Occurrences
 * Easy
 *
 * Given an array of integers arr, write a function that returns true if and only if the number of occurrences of each
 * value in the array is unique.
 *
 *
 *
 * Example 1:
 *
 * Input: arr = [1,2,2,1,1,3]
 * Output: true
 * Explanation: The value 1 has 3 occurrences, 2 has 2 and 3 has 1. No two values have the same number of occurrences.
 * Example 2:
 *
 * Input: arr = [1,2]
 * Output: false
 * Example 3:
 *
 * Input: arr = [-3,0,1,-3,1,1,1,-3,10,0]
 * Output: true
 *
 *
 * Constraints:
 *
 * 1 <= arr.length <= 1000
 * -1000 <= arr[i] <= 1000
 */
public class UniqueNumberOfOccurances {

    public boolean uniqueOccurrences(int[] arr) {
        //array to store number of occurances
        int[] c = new int[2001];
        //count occurance of each value
        for (int n : arr) {
            c[n+1000]++;
        }
        //check unique occurances (except for 0)
        Set<Integer> set = new HashSet();
        for (int count : c) {
            if (count == 0)
                continue;
            if (!set.add(count))
                return false;
        }
        return true;
    }
}
