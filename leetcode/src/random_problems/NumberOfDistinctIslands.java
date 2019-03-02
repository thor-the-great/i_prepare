package random_problems;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 694. Number of Distinct Islands
 * Medium
 *
 * Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's (representing land) connected
 * 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.
 *
 * Count the number of distinct islands. An island is considered to be the same as another if and only if one
 * island can be translated (and not rotated or reflected) to equal the other.
 *
 * Example 1:
 * 11000
 * 11000
 * 00011
 * 00011
 * Given the above grid map, return 1.
 * Example 2:
 * 11011
 * 10000
 * 00001
 * 11011
 * Given the above grid map, return 3.
 *
 * Notice that:
 * 11
 * 1
 * and
 *  1
 * 11
 * are considered different island shapes, because we do not consider reflection / rotation.
 * Note: The length of each dimension in the given grid does not exceed 50.
 */
public class NumberOfDistinctIslands {

    boolean[][] visited;
    int[][] grid;
    int rows, cols;

    /**
     * Idea: keep track of unique paths - if we follow same logic in DFS the path record will be the same
     * for the same islands.
     * @param grid
     * @return
     */
    public int numDistinctIslands(int[][] grid) {
        rows = grid.length;
        cols = grid[0].length;
        this.grid = grid;
        visited = new boolean[rows][cols];

        Set<String> paths = new HashSet();

        StringBuilder path = new StringBuilder();
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                path.setLength(0);
                helper(r, c, 0, path);
                String s = path.toString();
                if (s.length() > 0 && !paths.contains(s))
                    paths.add(s);
            }
        }

        return paths.size();
    }

    void helper(int r, int c, int dir, StringBuilder path) {
        if (r >=0 && r < rows && c >= 0 && c < cols && !visited[r][c] && grid[r][c] == 1) {
            visited[r][c] = true;
            path.append(dir);
            helper(r - 1, c, 1, path);
            helper(r + 1, c, 2, path);
            helper(r, c - 1, 3, path);
            helper(r, c + 1, 4, path);
            path.append(0);
        }
    }
}
