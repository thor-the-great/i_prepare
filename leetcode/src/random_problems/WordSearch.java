package random_problems;

import java.util.HashSet;
import java.util.Set;

/**
 * 79. Word Search
 * Medium
 *
 *
 * Given a 2D board and a word, find if the word exists in the grid.
 *
 * The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those
 * horizontally or vertically neighboring. The same letter cell may not be used more than once.
 *
 * Example:
 *
 * board =
 * [
 *   ['A','B','C','E'],
 *   ['S','F','C','S'],
 *   ['A','D','E','E']
 * ]
 *
 * Given word = "ABCCED", return true.
 * Given word = "SEE", return true.
 * Given word = "ABCB", return false.
 */
public class WordSearch {
    Set<Integer> visited = new HashSet<>();
    String w;
    char[][] m;

    /**
     * Idea is to use DFS and backtracking. Recursive DFS implementation works better due to backtracking - need to
     * rollback visited path in case we can't find the word
     *
     * @param board
     * @param word
     * @return
     */
    public boolean exist(char[][] board, String word) {
        visited.clear();
        w = word;
        m = board;
        int l = word.length();
        int m = board.length, n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (word.charAt(0) != board[i][j])
                    continue;
                if (l == 1) return true;
                if (dfs(i, j, 0)) return true;
            }
        }
        return false;
    }

    boolean dfs(int r, int c, int index) {
        if (m[r][c] != w.charAt(index))
            return false;
        if (index == w.length() - 1)
            return true;
        int rows = m.length;
        int cols = m[0].length;
        int cell = r * cols + c;
        visited.add(cell);

        boolean result = false;
        if (r > 0 && !visited.contains((r - 1) * cols + c)) {
            result |= dfs(r - 1, c, index + 1);
        }
        if (r < rows - 1 && !visited.contains((r + 1) * cols + c)) {
            result |= dfs(r + 1, c, index + 1);
        }
        if (c > 0 && !visited.contains(r * cols + c - 1)) {
            result |= dfs(r, c - 1, index + 1);
        }
        if (c < cols - 1 && !visited.contains(r * cols + c + 1)) {
            result |= dfs(r , c + 1, index + 1);
        }
        if (result) return true;
        visited.remove(cell);
        return false;
    }

    public static void main(String[] args) {
        WordSearch obj = new WordSearch();
        System.out.println(obj.exist(new char[][]{
                {'a', 'a'}
        }, "aa"));//true
        System.out.println(obj.exist(new char[][]{
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        }, "ABCCED"));//true
        System.out.println(obj.exist(new char[][]{
                {'a', 'b'},
                {'c', 'd'}
        }, "abcd")); //false
        System.out.println(obj.exist(new char[][]{
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'E', 'S'},
                {'A', 'D', 'E', 'E'}
        }, "ABCESEEEFS"));//true
    }
}
