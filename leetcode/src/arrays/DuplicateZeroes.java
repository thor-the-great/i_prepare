package arrays;

/**
 * 1089. Duplicate Zeros
 * Easy
 *
 * Given a fixed length array arr of integers, duplicate each occurrence of zero, shifting the remaining elements to the right.
 *
 * Note that elements beyond the length of the original array are not written.
 *
 * Do the above modifications to the input array in place, do not return anything from your function.
 *
 *
 *
 * Example 1:
 *
 * Input: [1,0,2,3,0,4,5,0]
 * Output: null
 * Explanation: After calling your function, the input array is modified to: [1,0,0,2,3,0,0,4]
 * Example 2:
 *
 * Input: [1,2,3]
 * Output: null
 * Explanation: After calling your function, the input array is modified to: [1,2,3]
 *
 *
 * Note:
 *
 * 1 <= arr.length <= 10000
 * 0 <= arr[i] <= 9
 */
public class DuplicateZeroes {

    /**
     * Create pointer for the end and move it to the left with each next 0. Then From met point move pointer to the
     * left and swap value with the right part, duplicate if needed
     * @param arr
     */
    public void duplicateZeros(int[] arr) {
        int N = arr.length - 1;

        int dupl = 0;

        for (int l = 0; l <= N - dupl; l++) {
            if (arr[l] == 0) {
                if (l == N - dupl) {
                    arr[N] = 0;
                    --N;
                    break;
                }
                dupl++;
            }
        }

        int r = N - dupl;
        for (; r >=0; r--) {
            if (arr[r] == 0) {
                arr[r + dupl] = 0;
                dupl--;
                arr[r + dupl] = 0;
            } else {
                arr[r + dupl] = arr[r];
            }
        }
    }
}
