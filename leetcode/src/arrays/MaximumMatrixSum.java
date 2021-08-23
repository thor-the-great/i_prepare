package arrays;

/**
 * 1975. Maximum Matrix Sum
Medium

You are given an n x n integer matrix. You can do the following operation any number of times:

    Choose any two adjacent elements of matrix and multiply each of them by -1.

Two elements are considered adjacent if and only if they share a border.

Your goal is to maximize the summation of the matrix's elements. Return the maximum sum of the matrix's elements using the operation mentioned above.

 

Example 1:

Input: matrix = [[1,-1],[-1,1]]
Output: 4
Explanation: We can follow the following steps to reach sum equals 4:
- Multiply the 2 elements in the first row by -1.
- Multiply the 2 elements in the first column by -1.

Example 2:

Input: matrix = [[1,2,3],[-1,-2,-3],[1,2,3]]
Output: 16
Explanation: We can follow the following step to reach sum equals 16:
- Multiply the 2 last elements in the second row by -1.

 

Constraints:

    n == matrix.length == matrix[i].length
    2 <= n <= 250
    -105 <= matrix[i][j] <= 105
 * 
 * https://leetcode.com/problems/maximum-matrix-sum/
 */
public class MaximumMatrixSum {
    
    /**
     * 1. we can always make all positives out of 2 negative numbers.
     * 2. that's true for any pair so generilize it - for any even
     * 3. for add we have to choose some number to be negative
     * 4. best choice for 3 is the minimum by abs number
     * 
     * O(n^2) time
     * O(1) space
     * 
     * @param matrix
     * @return
     */
    public long maxMatrixSum(int[][] matrix) {
        long res = 0;
        
        int countOfNegs = 0;
        int minNeg = Integer.MAX_VALUE;
        
        for (int r = 0; r < matrix.length; r++) {
            for (int c = 0; c < matrix.length; c++) {
                res+=Math.abs(matrix[r][c]);
                if (matrix[r][c] < 0) {
                    countOfNegs++;
                }
                minNeg = Math.min(minNeg, Math.abs(matrix[r][c]));
            }
        }
        
        if (countOfNegs % 2 == 1) {
            res -= (2*minNeg);
        } 
        
        return res;
    }
}
