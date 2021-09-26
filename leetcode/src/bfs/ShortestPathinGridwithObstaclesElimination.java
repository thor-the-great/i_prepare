package bfs;

/**
 * 1293. Shortest Path in a Grid with Obstacles Elimination
Hard

You are given an m x n integer matrix grid where each cell is either 0 (empty) or 1 (obstacle). You can move up, down, 
left, or right from and to an empty cell in one step.

Return the minimum number of steps to walk from the upper left corner (0, 0) to the lower right corner (m - 1, n - 1) 
given that you can eliminate at most k obstacles. If it is not possible to find such walk return -1.

 

Example 1:

Input: 
grid = 
[[0,0,0],
 [1,1,0],
 [0,0,0],
 [0,1,1],
 [0,0,0]], 
k = 1
Output: 6
Explanation: 
The shortest path without eliminating any obstacle is 10. 
The shortest path with one obstacle elimination at position (3,2) is 6. Such path is (0,0) -> (0,1) -> 
(0,2) -> (1,2) -> (2,2) -> (3,2) -> (4,2).

Example 2:

Input: 
grid = 
[[0,1,1],
 [1,1,1],
 [1,0,0]], 
k = 1
Output: -1
Explanation: 
We need to eliminate at least two obstacles to find such a walk.

 

Constraints:

    m == grid.length
    n == grid[i].length
    1 <= m, n <= 40
    1 <= k <= m * n
    grid[i][j] == 0 or 1
    grid[0][0] == grid[m - 1][n - 1] == 0


 * https://leetcode.com/problems/shortest-path-in-a-grid-with-obstacles-elimination/
 */
public class ShortestPathinGridwithObstaclesElimination {

    int[][] d = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    
    /**
     * Use BFS as it gives shortest path. 
     * Trick - keep visited with the k as otherwise with different k values you'll have different min path
     * O(n*m) time, O(n*m) space
     */
    public int shortestPath(int[][] grid, int k) {
        int rows = grid.length, cols = grid[0].length;
        Queue<int[]> q = new LinkedList();
        q.add(new int[]{ 0, 0, k });
        int res = -1, cur = 0;
        Set<Integer> seen = new HashSet();
        seen.add(k);
        while (!q.isEmpty()) {
            int s = q.size();
            for (int i = 0; i < s; i++) {
                int[] step = q.poll();
                //check if target
                if (step[0] == rows - 1 && step[1] == cols - 1) {
                    return cur;
                }
                //add next moves
                for (int[] dir : d) {
                    int newR = step[0] + dir[0], newC = step[1] + dir[1];
                    //check if we within boundaries
                    if (newR >= 0 && newR < rows && newC >= 0 && newC < cols) {
                        int nextK = grid[newR][newC] == 1 ? step[2] - 1 : step[2];
                        int cellIdx = (newR << 12) + (newC << 6) + nextK;
                        //add if we haven't reached limit of obsticles and the cells hasn't been visited
                        if (nextK >= 0 && !seen.contains(cellIdx)) {
                            q.add(new int[]{ newR, newC, nextK});
                            seen.add(cellIdx);
                        }
                    }
                }
            }
            cur+=1;
        }
        return -1;
    }
    
}
