package path.google;

import java.util.Arrays;
import java.util.List;

public class WordBreak {
    String s;
    Trie trie;
    int maxLength = 0;
    boolean[][] cache;

    public boolean wordBreak(String s, List<String> wordDict) {
        this.s =s;
        cache = new boolean[s.length()][2];
        trie = buildTrie(wordDict);
        return helper(0, 0);
    }

    boolean helper(int start, int end) {
        if (end > s.length() - 1)
            return false;
        if (cache[start][1]) {
            return cache[start][0];
        }
        if (contains(start, end)) {
            if (end == s.length() - 1) {
                cache[start][0] = true;
                cache[start][1] = true;
                return true;
            }
            else {
                boolean res1 = false;
                if (end + 1 < this.s.length() && trie.next[s.charAt(end + 1) - 'a'] != null) {
                    res1 = helper(end + 1, end + 1);
                }
                return res1 || helper(start, end + 1);
            }
        } else {
            int l = end - start  + 1;
            if (l < maxLength) {
                return helper(start, end + 1);
            } else {
                cache[start][0] = false;
                cache[start][1] = true;
                return false;
            }
        }
    }

    Trie buildTrie(List<String> wordDict) {
        Trie root = new Trie();
        for (String word : wordDict) {
            Trie n = root;
            int N = word.length();
            for(int i =0; i < N; i++) {
                int idx = word.charAt(i) - 'a';
                if (n.next[idx] == null) {
                    n.next[idx] = new Trie();
                }
                n = n.next[idx];
            }
            n.isWord = true;
            maxLength = Math.max(maxLength, N);
        }
        return root;
    }
    boolean contains(int start, int end) {
        Trie n = this.trie;
        for (int i = start; i <= end; i++) {
            int idx = this.s.charAt(i) - 'a';
            n = n.next[idx];
            if (n == null)
                return false;
        }
        return n.isWord;
    }

    class Trie {
        boolean isWord;
        Trie[] next = new Trie[26];
    }

    public static void main(String[] args) {
        WordBreak obj = new WordBreak();
        List<String> dict;
        String s;

        dict = Arrays.asList(new String[]{"aaaa","aaa"});
        s = "aaaaaaa";
        System.out.println(obj.wordBreak(s, dict));

        dict = Arrays.asList(new String[]{"a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa"});
        s = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab";
        System.out.println(obj.wordBreak(s, dict));
    }
}

