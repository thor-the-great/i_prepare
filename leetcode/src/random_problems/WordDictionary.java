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
class WordDictionary {

    Trie tr;

    /** Initialize your data structure here. */
    public WordDictionary() {
        tr = new Trie();
    }

    /** Adds a word into the data structure. */
    public void addWord(String word) {
        Trie t = tr;
        for (char ch : word.toCharArray()) {
            int idx = ch - 'a';
            if (t.next[idx] == null)
                t.next[idx] = new Trie();
            t = t.next[idx];
        }
        t.word = true;
    }

    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        return helper(tr, word, 0);
    }

    boolean helper(Trie t, String word, int idx) {
        if (t == null)
            return false;
        if (idx == word.length())
            return t.word;

        char ch = word.charAt(idx);
        if (ch == '.') {
            for (Trie nTrie : t.next) {
                if (helper(nTrie, word, idx + 1))
                    return true;
            }
        } else {
            return helper(t.next[ch - 'a'], word, idx + 1);
        }
        return false;
    }

    class Trie {
        Trie[] next;
        boolean word;

        Trie() {
            next = new Trie[26];
        }
    }
}

