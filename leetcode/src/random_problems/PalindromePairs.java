package random_problems;

import java.util.ArrayList;
import java.util.List;

/**
 * 336. Palindrome Pairs
 * Hard
 *
 * Given a list of unique words, find all pairs of distinct indices (i, j) in the given list, so that the
 * concatenation of the two words, i.e. words[i] + words[j] is a palindrome.
 *
 * Example 1:
 *
 * Input: ["abcd","dcba","lls","s","sssll"]
 * Output: [[0,1],[1,0],[3,2],[2,4]]
 * Explanation: The palindromes are ["dcbaabcd","abcddcba","slls","llssssll"]
 * Example 2:
 *
 * Input: ["bat","tab","cat"]
 * Output: [[0,1],[1,0]]
 * Explanation: The palindromes are ["battab","tabbat"]
 *
 */
public class PalindromePairs {

    List<List<Integer>> res;

    /**
     * Idea - we can form a palindrome if end of one word is a beginning of another word. So we build a trie of all
     * words, but traverse each one word from end to beginning. On each step we record if the rest of the word is
     * a palindrome.
     * When we do the search we take words normally and start searching the Trie. IF there are no nodes - if means we
     * can discard the word. If we use all characters from word and still there are more in the trie we go the path
     * and check on every of the rest of characters if there is a plaindrome possible (we saved this info while building
     * the Trie).
     * Complexity - O(k^2*n) time, where k is the average length of word. Space - O(k), k*26 where 26 is possible
     * characters
     * @param words
     * @return
     */
    public List<List<Integer>> palindromePairs(String[] words) {

        res = new ArrayList();
        int N = words.length;
        if (N <= 1)
            return res;

        //build Trie
        Trie root = new Trie();
        for (int i = 0; i < words.length; i++) {
            Trie t = root;
            String word = words[i];
            for (int j = word.length() - 1; j >=0; j--) {
                int idx = word.charAt(j) - 'a';
                if (t.next[idx] == null) {
                    t.next[idx] = new Trie();
                }

                if (check(word,0, j)) {
                    t.getPaliIndexes().add(i);
                }

                t = t.next[idx];
            }
            t.getPaliIndexes().add(i);
            t.wordIdx = i;
        }

        //do the search
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            Trie t = root;
            boolean done = false;
            for (int j = 0; j < word.length(); j++) {
                if (t.wordIdx >= 0 && t.wordIdx != i && check(word, j, word.length() - 1)) {
                    addToResult(i, t.wordIdx);
                }
                t = t.next[word.charAt(j) - 'a'];
                if (t == null) {
                    done = true;
                    break;
                }
            }
            if (!done && t.paliIndexes != null) {
                for (int j : t.getPaliIndexes()) {
                    if (i != j) {
                        addToResult(i, j);
                    }
                }
            }
        }
        return res;
    }

    private void addToResult(int i, int j) {
        List<Integer> oneRes = new ArrayList<>();
        oneRes.add(i);
        oneRes.add(j);
        res.add(oneRes);
    }

    boolean check(String s, int l, int r) {
        while (l < r) {
            if (s.charAt(l) != s.charAt(r))
                return false;
            l++;
            r--;
        }
        return true;
    }

    class Trie {
        Trie[] next;
        int wordIdx;
        List<Integer> paliIndexes;

        Trie() {
            next = new Trie[26];
            wordIdx = -1;
        }

        List<Integer> getPaliIndexes() {
            if (paliIndexes == null)
                paliIndexes = new ArrayList<>();
            return paliIndexes;
        }
    }

    public static void main(String[] args) {
        PalindromePairs obj = new PalindromePairs();
        String[] words;
        List<List<Integer>> res;
        words = new String[] {
                "abcd","dcba","lls","s","sssll"
        };

        res = obj.palindromePairs(words);
        for (List<Integer> pair: res) {
            System.out.print( "[" + pair.get(0) + " " + pair.get(1) +"], ");
        }
        System.out.print("\n");

        words = new String[] {
                "", "a"
        };
        res = obj.palindromePairs(words);
        for (List<Integer> pair: res) {
            System.out.print( "[" + pair.get(0) + " " + pair.get(1) +"], ");
        }
        System.out.print("\n");

        words = new String[] {
                "abcd","dcba","lls","s","sssll",""
        };
        res = obj.palindromePairs(words);
        for (List<Integer> pair: res) {
            System.out.print( "[" + pair.get(0) + " " + pair.get(1) +"], ");
        }
        System.out.print("\n"); //[[0,1],[1,0],[3,2],[3,5],[5,3],[2,4]]

        words = new String[] {
                "a","abc","aba",""
        };
        res = obj.palindromePairs(words);
        for (List<Integer> pair: res) {
            System.out.print( "[" + pair.get(0) + " " + pair.get(1) +"], ");
        }
        System.out.print("\n");
    }
}
