package arrays;

/**
 * 977. Squares of a Sorted Array
 * Easy
 *
 * Given an array of integers A sorted in non-decreasing order, return an array of the squares of each number, also in
 * sorted non-decreasing order.
 *
 *
 *
 * Example 1:
 *
 * Input: [-4,-1,0,3,10]
 * Output: [0,1,9,16,100]
 * Example 2:
 *
 * Input: [-7,-3,2,3,11]
 * Output: [4,9,9,49,121]
 *
 *
 * Note:
 *
 * 1 <= A.length <= 10000
 * -10000 <= A[i] <= 10000
 * A is sorted in non-decreasing order.
 */
public class SquaresOfSortedArray {

    public int[] sortedSquares(int[] A) {
        int N = A.length;
        //init pointer in result array and 2 pointers in original array
        int i = N - 1, l = 0, r = N - 1;
        int[] res = new int[N];
        //while we have unvisited elements in original array
        while (l <= r ) {
            //get abs of elements at both edges
            int absL = Math.abs(A[l]), absR = Math.abs(A[r]);
            //whichever is the greatest - add it's square to the end of the res array
            if (absL > absR) {
                res[i--] = absL*absL;
                l++;
            } else {
                res[i--] = absR*absR;
                r--;
            }
        }
        return res;
    }
}
