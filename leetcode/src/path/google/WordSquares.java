package path.google;

import java.util.*;

/**
 * Word Squares
 * Given a set of words (without duplicates), find all word squares you can build from them.
 *
 * A sequence of words forms a valid word square if the kth row and column read the exact same string, where 0 ≤ k <
 * max(numRows, numColumns).
 *
 * For example, the word sequence ["ball","area","lead","lady"] forms a word square because each word reads the same
 * both horizontally and vertically.
 *
 * b a l l
 * a r e a
 * l e a d
 * l a d y
 * Note:
 * There are at least 1 and at most 1000 words.
 * All words will have the exact same length.
 * Word length is at least 1 and at most 5.
 * Each word contains only lowercase English alphabet a-z.
 * Example 1:
 *
 * Input:
 * ["area","lead","wall","lady","ball"]
 *
 * Output:
 * [
 *   [ "wall",
 *     "area",
 *     "lead",
 *     "lady"
 *   ],
 *   [ "ball",
 *     "area",
 *     "lead",
 *     "lady"
 *   ]
 * ]
 *
 * Explanation:
 * The output consists of two word squares. The order of output does not matter (just the order of words in each word
 * square matters).
 * Example 2:
 *
 * Input:
 * ["abat","baba","atan","atal"]
 *
 * Output:
 * [
 *   [ "baba",
 *     "abat",
 *     "baba",
 *     "atan"
 *   ],
 *   [ "baba",
 *     "abat",
 *     "baba",
 *     "atal"
 *   ]
 * ]
 *
 * Explanation:
 * The output consists of two word squares. The order of output does not matter (just the order of words in each word
 * square matters).
 *
 */
public class WordSquares {
    String[] words;
    List<List<String>> res;
    int N;
    Trie trie;

    public List<List<String>> wordSquares(String[] words) {
        this.words = words;
        res = new LinkedList();
        N = words[0].length();
        buildTrie();

        List<String> ansBuilder = new ArrayList<>();
        for (String w : words) {
            ansBuilder.add(w);
            search(ansBuilder);
            ansBuilder.remove(ansBuilder.size() - 1);
        }

        return res;
    }

    void search(List<String> ansBuilder) {
        if (ansBuilder.size() == N) {
            res.add(new ArrayList<>(ansBuilder));
            return;
        }

        int idx = ansBuilder.size();
        StringBuilder prefixBuilder = new StringBuilder();
        for (String s : ansBuilder)
            prefixBuilder.append(s.charAt(idx));
        List<String> startWith = findByPrefix(prefixBuilder.toString(), trie);
        for (String sw : startWith) {
            ansBuilder.add(sw);
            search(ansBuilder);
            ansBuilder.remove(ansBuilder.size() - 1);
        }
    }

    void buildTrie() {
        Trie root = new Trie();
        for (String word : words) {
            Trie n = root;
            for (int i = 0; i < word.length(); i++) {
                int index = word.charAt(i) - 'a';
                if (n.next[index] == null)
                    n.next[index] = new Trie();
                n.next[index].startWith.add(word);
                n = n.next[index];
            }
        }
        this.trie = root;
    }

    List<String> findByPrefix(String pref, Trie t) {
        List<String> res = new ArrayList();
        Trie n = t;
        for (int i =0; i < pref.length(); i++) {
            int index = pref.charAt(i) - 'a';
            n = n.next[index];
            if (n == null)
                return res;
        }
        res.addAll(n.startWith);
        return res;
    }

    class Trie {
        Trie[] next = new Trie[26];
        List<String> startWith = new ArrayList();

    }

    public static void main(String[] args) {
        WordSquares obj = new WordSquares();
        List<List<String>> res = obj.wordSquares(new String[] {"abat","baba","atan","atal"});
        for(List<String> l : res) {
            l.forEach(s->System.out.print(s+ " "));
            System.out.print("\n");
        }
    }
}
