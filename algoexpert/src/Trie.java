import java.util.HashMap;

/**
 * Copyright (c) 2019 Ariba, Inc. All rights reserved. Patents pending.
 */

class TrieNode {
  Character c;
  Boolean isLeaf = false;
  HashMap<Character, TrieNode> children = new HashMap<>();
  public TrieNode() {}
  public TrieNode(Character c) {
    this.c = c;
  }
}

public class Trie {
  private TrieNode root;

  // Implement these methods :
  public Trie() {
    root = new TrieNode();
  }
  public void insertWord(String word) {
    if (word == null || word.length() == 0)
      return;
    TrieNode t = root;
    for (char ch : word.toCharArray()) {
      if (t.children.containsKey(ch)) {
        t = t.children.get(ch);
      }
      else {
        TrieNode next = new TrieNode(ch);
        t.children.put(ch, next);
        t = next;
      }
    }
    t.isLeaf = true;
  }

  public Boolean searchWord(String word) {
    if (word == null || word.length() == 0)
      return false;

    TrieNode t = root;
    for (char ch : word.toCharArray()) {
      if (t.children.containsKey(ch))
        t = t.children.get(ch);
      else {
        return false;
      }
    }
    return t.isLeaf;
  }

  public Boolean searchPrefix(String word) {
    if (word == null || word.length() == 0)
      return false;

    TrieNode t = root;
    for (char ch : word.toCharArray()) {
      if (t.children.containsKey(ch))
        t = t.children.get(ch);
      else {
        return false;
      }
    }
    return t != null;
  }
}
