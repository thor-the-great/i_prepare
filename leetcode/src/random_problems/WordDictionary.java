package random_problems;

/**
 * 211. Add and Search Word - Data structure design
 * Medium
 *
 * Design a data structure that supports the following two operations:
 *
 * void addWord(word)
 * bool search(word)
 * search(word) can search a literal word or a regular expression string containing only letters a-z or .. A . means
 * it can represent any one letter.
 *
 * Example:
 *
 * addWord("bad")
 * addWord("dad")
 * addWord("mad")
 * search("pad") -> false
 * search("bad") -> true
 * search(".ad") -> true
 * search("b..") -> true
 * Note:
 * You may assume that all words are consist of lowercase letters a-z.
 */
public class WordDictionary {
    TrieNode trie;
    /** Initialize your data structure here. */
    public WordDictionary() {
        trie = new TrieNode();
    }

    /** Adds a word into the data structure. */
    public void addWord(String word) {
        TrieNode n = trie;
        for (char ch : word.toCharArray()) {
            int idx = ch - 'a';
            if (n.next[idx] == null)
                n.next[idx] = new TrieNode();
            n = n.next[idx];
        }
        n.word = true;
    }

    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        return matchHelper(word.toCharArray(), 0, trie);
    }

    boolean matchHelper(char[] wordArray, int idx, TrieNode n) {
        if (n == null)
            return false;
        if (idx == wordArray.length)
            return n.word;
        int trieIdx = wordArray[idx] - 'a';
        if (wordArray[idx] != '.') {
            return matchHelper(wordArray, idx + 1, n.next[trieIdx]);
        } else {
            for (int i = 0; i < 26; i++) {
                if (n.next[i] != null) {
                    if (matchHelper(wordArray, idx + 1, n.next[i]))
                        return true;
                }
            }
        }
        return false;
    }
}

class TrieNode {
    boolean word;
    TrieNode[] next;

    TrieNode() {
        next = new TrieNode[26];
        word = false;
    }
}
