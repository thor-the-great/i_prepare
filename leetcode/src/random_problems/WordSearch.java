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
        if (board == null || board.length == 0 || board[0].length == 0)
            return false;

        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                if (board[r][c] == word.charAt(0)) {
                    if (dfs(board, r, c, word, 0)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    boolean dfs(char[][] board, int r, int c, String s, int idx) {
        if (idx >= s.length())
            return false;

        if (board[r][c] == '*' || board[r][c] != s.charAt(idx))
            return false;

        if (idx == s.length() - 1)
            return true;

        char prevChar = board[r][c];
        board[r][c] = '*';

        boolean res = false;
        if (r - 1 >= 0)
            res = dfs(board, r - 1, c, s, idx + 1);
        if (c - 1 >= 0 && !res)
            res = dfs(board, r, c - 1, s, idx + 1);
        if (r + 1 < board.length && !res)
            res = dfs(board, r + 1, c, s, idx + 1);
        if (c + 1 < board[0].length && !res)
            res = dfs(board, r, c + 1, s, idx + 1);
        board[r][c] = prevChar;
        return res;
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
