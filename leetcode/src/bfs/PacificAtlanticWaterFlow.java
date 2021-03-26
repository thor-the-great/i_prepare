package bfs;

import java.util.List;
import java.util.Queue;

/**
 * 417. Pacific Atlantic Water Flow
Medium

Given an m x n matrix of non-negative integers representing the height of each unit cell in a continent, the "Pacific ocean" touches the left and top edges of the matrix and the "Atlantic ocean" touches the right and bottom edges.

Water can only flow in four directions (up, down, left, or right) from a cell to another one with height equal or lower.

Find the list of grid coordinates where water can flow to both the Pacific and Atlantic ocean.

Note:

    The order of returned grid coordinates does not matter.
    Both m and n are less than 150.

 

Example:

Given the following 5x5 matrix:

  Pacific ~   ~   ~   ~   ~ 
       ~  1   2   2   3  (5) *
       ~  3   2   3  (4) (4) *
       ~  2   4  (5)  3   1  *
       ~ (6) (7)  1   4   5  *
       ~ (5)  1   1   2   4  *
          *   *   *   *   * Atlantic

Return:

[[0, 4], [1, 3], [1, 4], [2, 2], [3, 0], [3, 1], [4, 0]] (positions with parentheses in above matrix).


 * 
 * https://leetcode.com/problems/pacific-atlantic-water-flow
 */
public class PacificAtlanticWaterFlow {
    int[][] dir = new int[][] {
        {0, 1}, {1, -0}, {-1, 0}, {0, -1}
    };

    /**
     * Do the BFS from each shore - left upper corner for pacific and right lower for atlantic. 
     * Keep array of visited cells, the answer will be an intersection of these visited arrays for each 
     * part of the ocean
     * 
     * O(N*M) time
     * O(N*M) space
     */
    public List<List<Integer>> pacificAtlantic(int[][] matrix) {
        int rows = matrix.length;
        if (rows == 0)
            return new ArrayList();
        
        int cols = matrix[0].length;
        List<List<Integer>> res = new ArrayList();
        
        //pasific
        boolean[][] visited = new boolean[rows][cols];
        boolean[][] visited2 = new boolean[rows][cols];
        Queue<int[]> pacQ = new LinkedList();
        Queue<int[]> atlQ = new LinkedList();
        for (int i = 0; i < cols; i++) {
            pacQ.add(new int[] {0, i});
            atlQ.add(new int[] {rows - 1, i});
        }
        for (int i = 0; i < rows; i++) {
            pacQ.add(new int[] {i, 0});
            atlQ.add(new int[] {i, cols - 1});
        }
        bfs(pacQ, visited, matrix);
        //atlantic
        bfs(atlQ, visited2, matrix);
        
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c <cols; c++) {
                if (visited[r][c] && visited2[r][c]) {
                    res.add(List.of(r, c));
                }
            }
        }
        return res;
    }
    
    void bfs(Queue<int[]> q, boolean[][] visited, int[][] matrix) {
        int rows = matrix.length, cols = matrix[0].length;
        while (!q.isEmpty()) {
            int[] c = q.poll();
            visited[c[0]][c[1]] = true;
            for (int[] d : dir) {
                int newR = c[0] + d[0];
                int newC = c[1] + d[1];
                if (newR >= 0 && newR < rows && newC >= 0 && newC < cols 
                    && !visited[newR][newC]
                    && matrix[c[0]][c[1]] <= matrix[newR][newC]) {
                        q.add(new int[] {newR, newC});
                    }
            } 
        }
    }
}
