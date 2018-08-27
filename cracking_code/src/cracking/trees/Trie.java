package cracking.trees;

/**
 * Created by thor on 12/11/16.
 */
public class Trie {

    TrieNode root;
    int NUMBER_OF_CHARS = 26;

    public Trie() {
        root = new TrieNode(Character.MIN_VALUE);
    }

    void insertString(String str) {
        TrieNode c = root;
        for (int i = 0; i < str.length(); i++) {
            char key = str.charAt(i);
            if (c.getChildForLetter(key) == null)
                c.addChildForLetter(key);
            c = c.getChildForLetter(key);
        }
        c.addEndOfString();
    }

    boolean inTrie(String str) {
        TrieNode c = root;
        for (int i = 0; i < str.length(); i++) {
            char key = str.charAt(i);
            if (c != null && c.getChildForLetter(key) != null)
                c = c.getChildForLetter(key);
            else
                break;
        }
        if (c == null)
            return false;
        return c.isEndOfString();
    }

    void printWordsAlphabetically() {
        StringBuilder sb = new StringBuilder();
        printWordsRecursive(this.root, sb);
    }

    void printWordsRecursive(TrieNode nextNode, StringBuilder sb) {
        for (int i = 0; i < NUMBER_OF_CHARS; i++) {
            char nextChar = (char) ('a' + i);
            TrieNode node = nextNode.getChildForLetter(nextChar);
            if (node != null) {
                sb.append(nextChar);
                if (node.isEndOfString()) {
                    System.out.println(sb.toString());
                    sb.setLength(0);
                }
                else {
                    printWordsRecursive(node, sb);
                }
            }
        }
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insertString("hello");
        trie.insertString("this");
        trie.insertString("is");
        trie.insertString("me");
        trie.insertString("someone");
        trie.insertString("from");
        trie.insertString("hell");
        trie.insertString("zero");

        String check = "this";
        System.out.println("inTrie " + check + " is " + trie.inTrie(check));

        check = "computer";
        System.out.println("inTrie " + check + " is " + trie.inTrie(check));

        check = "hell";
        System.out.println("inTrie " + check + " is " + trie.inTrie(check));

        check = "is";
        System.out.println("inTrie " + check + " is " + trie.inTrie(check));

        System.out.println("Printing words");
        trie.printWordsAlphabetically();
    }
}
