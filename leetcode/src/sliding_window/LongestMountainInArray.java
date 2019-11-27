package sliding_window;

/**
 * 845. Longest Mountain in Array
 * Medium
 *
 * Let's call any (contiguous) subarray B (of A) a mountain if the following properties hold:
 *
 * B.length >= 3
 * There exists some 0 < i < B.length - 1 such that B[0] < B[1] < ... B[i-1] < B[i] > B[i+1] > ... > B[B.length - 1]
 * (Note that B could be any subarray of A, including the entire array A.)
 *
 * Given an array A of integers, return the length of the longest mountain.
 *
 * Return 0 if there is no mountain.
 *
 * Example 1:
 *
 * Input: [2,1,4,7,3,2,5]
 * Output: 5
 * Explanation: The largest mountain is [1,4,7,3,2] which has length 5.
 * Example 2:
 *
 * Input: [2,2,2]
 * Output: 0
 * Explanation: There is no mountain.
 * Note:
 *
 * 0 <= A.length <= 10000
 * 0 <= A[i] <= 10000
 * Follow up:
 *
 * Can you solve it using only one pass?
 * Can you solve it in O(1) space?
 */
public class LongestMountainInArray {

    /**
     * Two pointers, count ups and downs separately, then keep running max
     * @param A
     * @return
     */
    public int longestMountain(int[] A) {
        int res = 0;
        int up = 0, down = 0;
        for (int i = 1; i < A.length; i++) {
            if (A[i - 1] == A[i] || (down > 0 && A[i] > A[i - 1]))
                up = down = 0;
            if (A[i - 1] > A[i])
                down++;
            else if (A[i] > A[i - 1])
                up++;
            if (up > 0 && down > 0)
                res = Math.max(res, up + down + 1);
        }

        return res;
    }
}
