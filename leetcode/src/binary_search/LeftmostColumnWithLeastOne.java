package binary_search;

import java.util.List;

/**
 * Leftmost Column with at Least a One
 *
 * (This problem is an interactive problem.)
 *
 * A binary matrix means that all elements are 0 or 1. For each individual row of the matrix,
 * this row is sorted in non-decreasing order.
 *
 * Given a row-sorted binary matrix binaryMatrix, return leftmost column index(0-indexed) with at
 * least a 1 in it. If such index doesn't exist, return -1.
 *
 * You can't access the Binary Matrix directly.  You may only access the matrix using a
 * BinaryMatrix interface:
 *
 * BinaryMatrix.get(x, y) returns the element of the matrix at index (x, y) (0-indexed).
 * BinaryMatrix.dimensions() returns a list of 2 elements [n, m], which means the matrix is n * m.
 * Submissions making more than 1000 calls to BinaryMatrix.get will be judged Wrong Answer.
 * Also, any solutions that attempt to circumvent the judge will result in disqualification.
 *
 * For custom testing purposes you're given the binary matrix mat as input in the following four
 * examples. You will not have access the binary matrix directly.
 *
 * Example 1:
 *
 * Input: mat = [[0,0],[1,1]]
 * Output: 0
 * Example 2:
 *
 * Input: mat = [[0,0],[0,1]]
 * Output: 1
 * Example 3:
 *
 * Input: mat = [[0,0],[0,0]]
 * Output: -1
 * Example 4:
 *
 * Input: mat = [[0,0,0,1],[0,0,1,1],[0,1,1,1]]
 * Output: 1
 *
 * Constraints:
 *
 * 1 <= mat.length, mat[i].length <= 100
 * mat[i][j] is either 0 or 1.
 * mat[i] is sorted in a non-decreasing way.
 */
public class LeftmostColumnWithLeastOne {

  /**
   * We can count quickly the number and positions of 1-s in each row. For that use the binary search
   * and find the first 1 in the row. After it all cols in this row will have 1.
   * Create a separate array to count 1-s in each row. Fill it and at the end find the min one
   * starting from the left.
   *
   * for each row - O(lg(cols) + cols)= O(cols*log(cols)) overall it's O(rows*cols)
   * space O(cols) for array
   * @param binaryMatrix
   * @return
   */
  public int leftMostColumnWithOne(BinaryMatrix binaryMatrix) {
    List<Integer> dim = binaryMatrix.dimensions();
    int rows = dim.get(0), cols = dim.get(1);
    int[] count = new int[cols];
    for (int r = 0; r < rows; r++) {
      if (binaryMatrix.get(r, cols - 1) == 0)
        continue;

      if (binaryMatrix.get(r, 0) == 1) {
        for (int i = 0; i < cols; i++) {
          ++count[i];
        }
        continue;
      }

      int left = 0, right = cols - 1;
      while(left < right) {
        int m = left + (right - left)/2;
        if (binaryMatrix.get(r, m) == 0) {
          left = m + 1;
        } else {
          right = m;
        }
      }
      for (int i = right; i < cols; i++) {
        ++count[i];
      }
    }
    int min = Integer.MAX_VALUE, res = 0;
    for (int c = 0; c < cols; c++) {
      if (count[c] > 0 && count[c] < min) {
        res = c;
        min = count[c];
      }
    }

    return min == Integer.MAX_VALUE ? -1 : res;
  }
}

interface BinaryMatrix {
  int get(int x, int y);
  List<Integer> dimensions();
};
