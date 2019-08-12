import java.util.*;

public class BoggleWithDict {

    Trie root;

    public ArrayList<String> boggleByot(char[][] board, ArrayList<String> dictionary) {
        ArrayList<String> res = new ArrayList();

        root = buildTrie(dictionary);

        int rows = board.length;
        int cols = board[0].length;

        Set<String> found = new TreeSet<>();

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                helper(board, r, c, "", new HashSet<Integer>(), found);
            }
        }
        res.addAll(found);
        return res;
    }

    void helper(char[][] board, int r, int c, String cur, Set<Integer> seen, Set<String> res) {
        int idx = (r << 8) + c;

        if (seen.contains(idx))
            return;

        cur += board[r][c];

        if (!isPrefix(cur))
            return;

        seen.add(idx);

        if (isWord(cur)) {
            res.add(cur);
        }

        if (r > 0)
            helper(board, r - 1, c, cur, seen, res);
        if (c > 0)
            helper(board, r, c - 1, cur, seen, res);
        if (r < board.length - 1)
            helper(board, r + 1, c, cur, seen, res);
        if (c < board[0].length - 1)
            helper(board, r, c + 1, cur, seen, res);

        seen.remove(idx);
    }

    Trie buildTrie(ArrayList<String> dict) {
        Trie root = new Trie();
        if (!dict.isEmpty()) {
            for (String word : dict) {
                Trie t = root;
                for (char ch : word.toCharArray()) {
                    if (t.next[ch] == null)
                        t.next[ch] = new Trie();
                    t = t.next[ch];
                }
                t.isW = true;
            }
        }
        return root;
    }

    boolean isPrefix(String s) {
        if (s.length() == 0)
            return true;
        Trie t = find(s);
        return t != null;
    }

    boolean isWord(String s) {
        Trie t = find(s);
        if (t == null || !t.isW)
            return false;
        return true;
    }

    Trie find(String s) {
        if (s == null || s.length() == 0)
            return null;

        Trie t = root;
        for (char ch : s.toCharArray()) {
            if (t.next[ch] == null)
                return null;
            t = t.next[ch];
        }
        return t;
    }

    class Trie {
        Trie[] next;
        boolean isW;

        Trie() {
            next = new Trie[128];
        }
    }

    public static void main(String[] args) {
        BoggleWithDict obj = new BoggleWithDict();
        char[][] board = new char[][]{
                {'A', 'F', 'A', 'J'},
                {'S', 'I', 'V', 'A'},
                {'E', 'R', 'O', 'C'},
                {'C', 'X', 'E', 'K'},
                {'O', 'D', 'F', 'T'},
                {'D', 'E', 'E', 'H'}
        };
        ArrayList<String> dict = new ArrayList<>(Arrays.asList("FIRECODE", "IS", "OR", "THE", "AS"));
        List<String> words = obj.boggleByot(board, dict);
        words.forEach(w -> System.out.print(w + " "));
        System.out.println("next");
        dict = new ArrayList<>(Arrays.asList("AS", "FIRECODE", "IS", "OR", "THE"));
        words = obj.boggleByot(board, dict);
        words.forEach(w -> System.out.print(w + " "));
    }
}
