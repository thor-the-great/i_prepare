/**
 *  Kth Smallest Element in a Sorted Matrix

Solution
Given an n x n matrix where each of the rows and columns are sorted in ascending order, return the kth smallest element in the matrix.

Note that it is the kth smallest element in the sorted order, not the kth distinct element.

 

Example 1:

Input: matrix = [[1,5,9],[10,11,13],[12,13,15]], k = 8
Output: 13
Explanation: The elements in the matrix are [1,5,9,10,11,12,13,13,15], and the 8th smallest number is 13
Example 2:

Input: matrix = [[-5]], k = 1
Output: -5
 

Constraints:

n == matrix.length
n == matrix[i].length
1 <= n <= 300
-109 <= matrix[i][j] <= 109
All the rows and columns of matrix are guaranteed to be sorted in non-decreasing order.
1 <= k <= n2

https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/
 */
public class KthSmallestElementinSortedMatrix {

    /**
     * Idea - traverse matrix is a sorted order. For that start with 1st element in each row and
     * move to the right (columwise). Add that next element in row. 
     * If we reach the last element in the row then don't check that row anymore.
     * For traversal use priority queue that keeps elements sorted.
     * 
     * O(nlgn + klgn)) time - keep pq sorted, add n elements initally, then do k iterations
     * O(n) space - keep at max n elements one for each row 
     */
    public int kthSmallest(int[][] matrix, int k) {
        Comparator<int[]> comp = (x1, x2) -> matrix[x1[0]][x1[1]] - matrix[x2[0]][x2[1]];
        PriorityQueue<int[]> pq = new PriorityQueue(comp);
        int rows = matrix.length;
        for (int r = 0; r < rows; r++) {
            pq.add(new int[] {r, 0});
        }
        
        while (k > 1) {
            int[] next = pq.poll();
            ++next[1];
            --k;
            if (next[1] >= rows) {
                continue;
            }
            pq.add(next);
        }
        
        int[] resElement = pq.poll();
        return matrix[resElement[0]][resElement[1]];
    }
}
