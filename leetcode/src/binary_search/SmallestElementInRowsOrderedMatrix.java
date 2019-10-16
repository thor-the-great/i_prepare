package binary_search;

import java.util.Arrays;

/**
 * 1198. Find Smallest Common Element in All Rows
 * Medium
 *
 * Given a matrix mat where every row is sorted in increasing order, return the smallest common element in all rows.
 *
 * If there is no common element, return -1.
 *
 * Example 1:
 *
 * Input: mat = [[1,2,3,4,5],[2,4,5,8,10],[3,5,7,9,11],[1,3,5,7,9]]
 * Output: 5
 *
 * Constraints:
 *
 * 1 <= mat.length, mat[i].length <= 500
 * 1 <= mat[i][j] <= 10^4
 * mat[i] is sorted in increasing order.
 */
public class SmallestElementInRowsOrderedMatrix {

    /**
     * As there are no duplicates count number of each number, if the quantity == rows - this is it
     * @param mat
     * @return
     */
    public int smallestCommonElement(int[][] mat) {
        int rows = mat.length;
        //start checking each number from 1-st row
        for (int num : mat[0]) {
            int count = 1;
            int r = 1;
            //not start checking each 1+ row for the same number, use binary search cause it's sorted
            for (; r < rows; r++) {
                //if not found - break out of the loop
                if (Arrays.binarySearch(mat[r], num) < 0)
                    break;
            }
            //if number of occurrences == rows - this means number is in every row
            if (count == rows)
                return num;
        }
        //if we reach this point - no proper number found
        return -1;
    }
}
