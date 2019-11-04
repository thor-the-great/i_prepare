package random_problems;

import java.util.ArrayList;
import java.util.List;

/**
 * 792. Number of Matching Subsequences
 * Medium
 *
 * Given string S and a dictionary of words words, find the number of words[i] that is a subsequence of S.
 *
 * Example :
 * Input:
 * S = "abcde"
 * words = ["a", "bb", "acd", "ace"]
 * Output: 3
 * Explanation: There are three words in words that are a subsequence of S: "a", "acd", "ace".
 * Note:
 *
 * All words in words and S will only consists of lowercase letters.
 * The length of S will be in the range of [1, 50000].
 * The length of words will be in the range of [1, 5000].
 * The length of words[i] will be in the range of [1, 50].
 */
public class NumberOfMatchingSubsequences {

    /**
     * We pre-process the words array into array of lists of nodes where each node is the pointer to
     * the position of each word. The position in parent array corresponds to the letter of the word that
     * we're currently processing.
     * Now iterate over the S and at each char check corresponding heads elements, increment or add to result if
     * we have reached the end of word
     * @param S
     * @param words
     * @return
     */
    public int numMatchingSubseq(String S, String[] words) {
        List<WordPointer>[] heads = new ArrayList[26];
        for (String w : words) {
            int i = w.charAt(0) - 'a';
            if (heads[i] == null) {
                heads[i] = new ArrayList();
            }
            heads[i].add(new WordPointer(w));
        }

        int res = 0;
        for (char ch : S.toCharArray()) {
            int idx = ch - 'a';
            if (heads[idx] == null)
                continue;

            List<WordPointer> head = heads[idx];
            heads[idx] = null;

            for (WordPointer n : head) {
                n.i++;
                if (n.i == n.w.length()) {
                    res++;
                } else {
                    idx = n.w.charAt(n.i) - 'a';
                    if (heads[idx] == null) {
                        heads[idx] = new ArrayList();
                    }
                    heads[idx].add(n);
                }
            }
        }
        return res;
    }

    class WordPointer {
        String w;
        int i = 0;

        WordPointer(String s) {
            this.w = s;
            this.i = 0;
        }
    }
}
