/**
 * Copyright (c) 2019 Ariba, Inc. All rights reserved. Patents pending.
 */
package strings;

import java.util.ArrayList;
import java.util.List;

/**
 * 1065. Index Pairs of a String
 * Easy
 *
 * Given a text string and words (a list of strings), return all index pairs [i, j] so that the
 * substring text[i]...text[j] is in the list of words.
 *
 *
 *
 * Example 1:
 *
 * Input: text = "thestoryofleetcodeandme", words = ["story","fleet","leetcode"]
 * Output: [[3,7],[9,13],[10,17]]
 * Example 2:
 *
 * Input: text = "ababa", words = ["aba","ab"]
 * Output: [[0,1],[0,2],[2,3],[2,4]]
 * Explanation:
 * Notice that matches can overlap, see "aba" is found in [0,2] and [2,4].
 *
 *
 * Note:
 *
 * All strings contains only lowercase English letters.
 * It's guaranteed that all strings in words are different.
 * 1 <= text.length <= 100
 * 1 <= words.length <= 20
 * 1 <= words[i].length <= 50
 * Return the pairs [i,j] in sorted order (i.e. sort them by their first coordinate in case of
 * ties sort them by their second coordinate).
 */
public class IndexPairsOfString {
    /**
     * Create trie out of array of words, then iterte over each char in text, and start looking up
     * in the trie. Every time we hit the word node - add the pair of indexes to the result. Keep
     * looking in the trie until we've reach null (dead branch)
     * Catch is - if we iterate this way pairs will be sorted naturally
     * @param text
     * @param words
     * @return
     */
    Trie root;
    int minWordLen = Integer.MAX_VALUE;
    public int[][] indexPairs(String text, String[] words) {
        buildTrie(words);
        List<int[]> list = new ArrayList();
        for (int i = 0; i < text.length() - minWordLen + 1; i++) {
            Trie n = root;
            int j = i;
            while (j < text.length() && n != null) {
                char ch = text.charAt(j);
                if (n.next[ch - 'a'] != null) {
                    n = n.next[ch - 'a'];
                    if (n.word) list.add(new int[]{i, j});
                } else
                    n = null;
                ++j;
            }
        }
        int[][] res = new int[list.size()][2];
        int i = 0;
        for (int[] listArr : list) res[i++] = listArr;
        return res;
    }

    void buildTrie(String[] words) {
        root = new Trie();
        for (String word : words) {
            Trie n = root;
            for (char ch : word.toCharArray()) {
                if (n.next[ch - 'a'] == null) {
                    n.next[ch - 'a'] = new Trie();
                }
                n = n.next[ch - 'a'];
            }
            n.word = true;
            minWordLen = Math.min(minWordLen, word.length());
        }
    }
}

class Trie {
    Trie[] next = new Trie[26];
    boolean word;
}
