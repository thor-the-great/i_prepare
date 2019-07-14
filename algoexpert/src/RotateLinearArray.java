/**
 * Rotate Linear Array New
 *     Arrays
 *
 * Rotate an array to the left by k positions without using extra space.k can be greater than the size of the array.
 *
 * Example:
 * rotateLeft({1,2,3,4,5},2) --> {3,4,5,1,2}
 */
public class RotateLinearArray {

    /**
     * Idea - reverse parts 0..k-1 and k..N - 1. Then rotate the rotated array
     * @param arr
     * @param k
     * @return
     */
    public static int[] rotateLeft(int[] arr, int k) {
        int N = arr.length;
        k = k % N;

        reverse(arr, 0, k - 1);

        reverse(arr, k, N - 1);

        reverse(arr, 0, N - 1);

        return arr;

    }

    static void reverse(int[] arr, int s, int e) {
        while (s < e) {
            int t = arr[s];
            arr[s] = arr[e];
            arr[e] = t;
            s++; e--;
        }
    }
}
