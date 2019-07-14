/**
 * Find the Maximum Number of Repetitions New
 *     Arrays
 *
 * Given an Array of integers, write a method that will return the integer with the maximum number of repetitions.
 * Your code is expected to run with O(n) time complexity and O(1) space complexity. The elements in the array are
 * between 0 to size(array) - 1 and the array will not be empty.
 *
 * f({3,1,2,2,3,4,4,4}) --> 4
 *
 */
public class MaxNumOfRepetitions {

    /**
     * Use index as additional source of information. Index will indicate the number, for each number we increment
     * element at that number index by N. Incrementing by N gives us feature of using mod(), mod will be the same as
     * element value itself.
     * On second loop will check element for the max absolute value, it will be the answer
     * @param a
     * @return
     */
    public static int getMaxRepetition(int[] a) {

        int N = a.length;

        for (int i = 0; i < N; i++) {
            int idx = a[i] % N;
            a[idx] += N;
        }
        int res = 0;
        int max = a[0];
        for (int i = 1; i < N; i++) {
            if (a[i] > max) {
                res = i;
                max = a[i];
            }
        }

        return res;
    }
}
