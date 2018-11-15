package random_problems;

import java.util.ArrayList;
import java.util.List;

public class WordSearchII {
    char[][] m;
    int rows = 0, cols = 0;
    List<String> res = new ArrayList();

    public List<String> findWords(char[][] board, String[] words) {
        m = board;
        res.clear();
        rows = m.length;
        if (rows == 0)
            return new ArrayList();
        cols = m[0].length;
        Trie trie = buildTrie(words);

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                dfs(r, c, trie);
            }
        }
        return res;
    }

    void dfs(int r, int c, Trie t) {
        int nextIndex = m[r][c] - 'a';
        if (t.children[nextIndex] == null)
            return;
        t = t.children[nextIndex];
        if (t.word != null) {
            res.add(t.word);
            t.word = null;
        }
        int cell = r * cols + c;
        char savedChar = m[r][c];
        m[r][c] = '$';
        if (r > 0 && m[r - 1][c] != '$') {
            dfs(r - 1, c, t);
        }
        if (r < rows - 1 && m[r + 1][c] != '$') {
            dfs(r + 1, c, t);
        }
        if (c > 0 && m[r][c - 1] != '$') {
            dfs(r, c - 1, t);
        }
        if (c < cols - 1 && m[r][c + 1] != '$') {
            dfs(r, c + 1, t);
        }
        m[r][c] = savedChar;
    }

    Trie buildTrie(String[] words) {
        Trie root = new Trie();
        for (String word : words) {
            Trie n = root;
            for (int i = 0; i < word.length(); i++) {
                int j = word.charAt(i) - 'a';
                if (n.children[j] == null) {
                    n.children[j] = new Trie();
                }
                n = n.children[j];
            }
            n.word = word;
        }
        return root;
    }

    class Trie {
        Trie[] children = new Trie[26];
        String word;
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
