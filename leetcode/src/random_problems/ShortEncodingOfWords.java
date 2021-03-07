package random_problems;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

public class ShortEncodingOfWords {

    /**
     * Solution based on set. Put all words into set, then for each word iterate over chars and make each possible
     * substring then check if it's in the set. If it's in the set then then substring word can be encoded using
     * the word, so it's length can be subtracted from result.
     * At the end sum lengths of all words that left in the set.
     * @param words
     * @return
     */
    public int minimumLengthEncodingSet(String[] words) {
        Set<String> allWords = new HashSet();
        for (String s : words) {
            allWords.add(s);
        }

        for (String s : words) {
            for (int i = 1; i < s.length(); i++) {
                String subStr = s.substring(i);
                allWords.remove(subStr);
            }
        }
        int res = 0;
        for (String s : allWords) {
            res += (1 + s.length());
        }
        return res;
    }

    /**
     * Use Trie to store words backwards. Sort array by length first, process longer words first. When adding word to
     * trie set hasChild flag to true for every char except the last one.
     * On smaller words check if the final trie node has child, if so this this is continuation of the longer word
     * thus small word can be encoded.
     * @param words
     * @return
     */
    public int minimumLengthEncodingTrie(String[] words) {
        Comparator<String> comp = (s1, s2) -> s2.length() - s1.length();
        Arrays.sort(words, comp);
        int res = 0;
        TrieNode root = new TrieNode();
        for (String w : words) {
            TrieNode cur = root;
            for (int i = w.length() - 1; i >= 0; i--) {
                int idx = w.charAt(i) - 'a';
                if (cur.next[idx] == null) {
                    cur.next[idx] = new TrieNode();
                }
                cur.hasChild = true;
                cur = cur.next[idx];
            }
            if (!cur.hasChild) {
                res += (1 + w.length());
                cur.hasChild = true;
            }
        }
        return res;
    }

    class TrieNode {
        TrieNode[] next = new TrieNode[26];
        boolean hasChild = false;
    }
}
