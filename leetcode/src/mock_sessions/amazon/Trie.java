package mock_sessions.amazon;

class Trie {

    /** Initialize your data structure here. */
    TrieNode root;
    public Trie() {
        root = new TrieNode();
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        TrieNode next = root;
        for (int i = 0; i < word.length(); i++) {
            next = next.add(word.charAt(i));
        }
        next.isWordEnd = true;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TrieNode next = root;
        for (int i = 0; i < word.length(); i++) {
            next = next.get(word.charAt(i));
            if (next == null)
                return false;
        }
        return next.isWordEnd;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        TrieNode next = root;
        for (int i = 0; i < prefix.length(); i++) {
            next = next.get(prefix.charAt(i));
            if (next == null)
                return false;
        }
        return true;
    }
}

class TrieNode {
    int R = 26;
    TrieNode[] links = new TrieNode[R];
    boolean isWordEnd;

    TrieNode() {
    }

    TrieNode add(char ch) {
        int i = ch - 'a';
        if (links[i] == null) {
            links[i] = new TrieNode();
        }
        return links[i];
    }

    TrieNode get(char ch) {
        return links[ch - 'a'];
    }

    boolean contains(char ch) {
        return links[ch - 'a'] != null;
    }
}