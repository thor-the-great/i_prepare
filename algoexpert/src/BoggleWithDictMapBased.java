import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class BoggleWithDictMapBased {

    public ArrayList<String> boggleByot(char[][] board, ArrayList<String> dictionary){
        if (board == null || board.length == 0 || board[0].length == 0 || dictionary == null || dictionary.isEmpty())
            return new ArrayList();
        Trie trie = buildTrie(dictionary);
        Set<String> set = new TreeSet();
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                dfs(board, r, c, trie, set);
            }
        }
        return new ArrayList(set);
    }

    void dfs(char[][] board, int r, int c, Trie t, Set<String> resSet) {
        //visited
        if (board[r][c] == '*' || t == null)
            return;
        //check if it's in the trie
        if (!t.next.containsKey(board[r][c])) {
            return;
        }
        t = t.next.get(board[r][c]);
        if (t.word != null)
            resSet.add(t.word);
        //mark as visited
        char curChar = board[r][c];
        board[r][c] = '*';
        //trying move on the board
        if (r > 0) dfs(board, r - 1, c, t, resSet);
        if (c > 0) dfs(board, r, c - 1, t, resSet);
        if (r < board.length - 1) dfs(board, r + 1, c, t, resSet);
        if (c < board[0].length - 1) dfs(board, r, c + 1, t, resSet);
        board[r][c] = curChar;
    }

    Trie buildTrie(ArrayList<String> dictionary) {
        Trie root = new Trie();
        for (String word : dictionary) {
            Trie t = root;
            for (char ch : word.toCharArray()) {
                if (!t.next.containsKey(ch)) {
                    t.next.put(ch, new Trie());
                }
                t = t.next.get(ch);
            }
            t.word = word;
        }
        return root;
    }

    class Trie {
        Map<Character, Trie> next = new HashMap();
        String word;
    }

    public static void main(String[] args) {
        BoggleWithDictMapBased obj = new BoggleWithDictMapBased();
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
