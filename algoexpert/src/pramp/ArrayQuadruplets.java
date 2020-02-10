package pramp;

import java.util.Arrays;

/**
 * Array Quadruplet
 * Given an unsorted array of integers arr and a number s, write a function findArrayQuadruplet that finds four
 * numbers (quadruplet) in arr that sum up to s. Your function should return an array of these numbers in an
 * ascending order. If such a quadruplet doesn’t exist, return an empty array.
 *
 * Note that there may be more than one quadruplet in arr whose sum is s. You’re asked to return the first one you
 * encounter (considering the results are sorted).
 *
 * Explain and code the most efficient solution possible, and analyze its time and space complexities.
 *
 * Example:
 *
 * input:  arr = [2, 7, 4, 0, 9, 5, 1, 3], s = 20
 *
 * output: [0, 4, 7, 9] # The ordered quadruplet of (7, 4, 0, 9)
 *                      # whose sum is 20. Notice that there
 *                      # are two other quadruplets whose sum is 20:
 *                      # (7, 9, 1, 3) and (2, 4, 9, 5), but again you’re
 *                      # asked to return the just one quadruplet (in an
 *                      # ascending order)
 * Constraints:
 *
 * [time limit] 5000ms
 *
 * [input] array.integer arr
 *
 * 1 ≤ arr.length ≤ 100
 * [input] integer s
 *
 * [output] array.integer
 */
public class ArrayQuadruplets {

    /**
     * Idea - ifwe have two elements of quadruplet then finding the other two is similar to two sum problem.
     * We need to return first is a sorted order, so we have to sort the array. If we do two nested for loops we
     * will have every possible pairs, and they will be sorted. Then for the rest of the sorted array we can do
     * teo sum with two converging pointers - this will be O(n)
     * Overall O(n^2) for two fors and O(n) for while - O(n^3). Space is O(1) cause we using few vars to keep state
     * @param arr
     * @param s
     * @return
     */
    static int[] findArrayQuadruplet(int[] arr, int s) {
        // your code goes here
        int N = arr.length;
        if (N < 4) {
            return new int[0];
        }
        Arrays.sort(arr);
        for (int i = 0 ; i < N - 2; i ++) {
            for (int j = i + 1; j < N - 1; j++) {
                //Set<Integer> set = new HashSet();
                int sum = s - (arr[i] + arr[j]);
                int l = j + 1, r = N - 1;

                while (l < r) {
                    int sum1 = arr[l] + arr[r];
                    if (sum1 == sum) {
                        return new int[] {arr[i], arr[j], arr[l], arr[r]};
                    }
                    if (sum1 > sum) {
                        --r;
                    } else {
                        ++l;
                    }
                }
            }
        }
        return new int[0];
    }
}
