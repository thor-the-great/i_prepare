package random_problems;

import java.util.ArrayList;
import java.util.List;

/**
 * 212. Word Search II
 * Hard
 *
 * Given a 2D board and a list of words from the dictionary, find all words in the board.
 *
 * Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those
 * horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.
 *
 * Example:
 *
 * Input:
 * words = ["oath","pea","eat","rain"] and board =
 * [
 *   ['o','a','a','n'],
 *   ['e','t','a','e'],
 *   ['i','h','k','r'],
 *   ['i','f','l','v']
 * ]
 *
 * Output: ["eat","oath"]
 * Note:
 * You may assume that all inputs are consist of lowercase letters a-z.
 *
 */
public class WordSearchII {
    int rows;
    int cols;
    List<String> res;

    public List<String> findWords(char[][] board, String[] words) {
        res = new ArrayList();
        rows = board.length;
        if (rows == 0)
            return res;

        cols = board[0].length;

        //build trie
        Trie root = new Trie();
        for (String w : words) {
            Trie n = root;
            for (char ch : w.toCharArray()) {
                int idx = ch  - 'a';
                if (n.next[idx] == null)
                    n.next[idx] = new Trie();

                n = n.next[idx];
            }
            n.word = w;
        }

        for (int r =  0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                dfs(r, c, root, board);
            }
        }

        return res;
    }

    void dfs(int r, int c, Trie t, char[][] board) {
        char ch = board[r][c];
        if (ch == '.') return;
        t = t.next[ch - 'a'];
        if (t != null) {
            if (t.word != null) {
                res.add(t.word);
                t.word = null;
            }

            char backup = ch;
            board[r][c] = '.';

            if (r > 0) {
                dfs(r - 1, c, t, board);
            }
            if (c > 0)
                dfs(r, c - 1, t, board);

            if (r < rows - 1)
                dfs(r + 1, c, t, board);

            if (c < cols - 1)
                dfs(r, c + 1, t, board);

            board[r][c] = backup;
        }
    }

    class Trie {
        String word;
        Trie[] next;

        Trie() {
            next = new Trie[26];
        }
    }

    public static void main(String[] args) {
        WordSearchII obj = new WordSearchII();
        String[] words = new String[] {"oath","pea","eat","rain"};
        char[][] board = new char[][] {
                {'o','a','a','n'},
                {'e','t','a','e'},
                {'i','h','k','r'},
                {'i','f','l','v'}
        };
        List<String> foundWord = obj.findWords(board, words);
        foundWord.forEach(w->System.out.print(w+" "));
    }
}
