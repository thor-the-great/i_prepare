package random_problems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 30. Substring with Concatenation of All Words
 * Hard
 *
 * You are given a string, s, and a list of words, words, that are all of the same length. Find all starting indices of
 * substring(s) in s that is a concatenation of each word in words exactly once and without any intervening characters.
 *
 * Example 1:
 *
 * Input:
 *   s = "barfoothefoobarman",
 *   words = ["foo","bar"]
 * Output: [0,9]
 * Explanation: Substrings starting at index 0 and 9 are "barfoor" and "foobar" respectively.
 * The output order does not matter, returning [9,0] is fine too.
 * Example 2:
 *
 * Input:
 *   s = "wordgoodgoodgoodbestword",
 *   words = ["word","good","best","word"]
 * Output: []
 */
public class SubstringWithConcatenationOfAllWords {
    /**
     * Idea: count words first, then go from the 0-th index of the string and parse every n-th chars to a string. Keep
     * second map to count number of times we met words and compare it to original counts. If running count is higher or
     * parsed word is not in the main map - break;
     * @param s
     * @param words
     * @return
     */
    public List<Integer> findSubstring(String s, String[] words) {
        int N = words.length;
        List<Integer> res = new ArrayList();
        if (N == 0)
            return res;
        //get counts for every word
        Map <String, Integer> counts = new HashMap();
        int wLen = words[0].length();
        for (String word : words) {
            if (!counts.containsKey(word))
                counts.put(word, 1);
            else {
                counts.put(word, counts.get(word) + 1);
            }
        }
        //Map<Integer, String> sMap = new HashMap<>();
        String[] sMap = new String[s.length() - wLen + 1];
        for (int i = 0; i <= s.length() - wLen; i++) {
            String oneS = s.substring(i, i + wLen);
            if (counts.containsKey(oneS))
                sMap[i] = oneS;
            else
                sMap[i] = null;
        }

        int longestPossibleLen = N * wLen;
        int end = s.length() - longestPossibleLen;
        Map<String, Integer> seen = new HashMap();
        for (int i = 0; i <= end; i++) {
            seen.clear();
            int count = N;
            int p = i;
            while (count > 0) {
                String next = sMap[p];
                if (next == null) {
                    break;
                }
                int c = seen.getOrDefault(next, 0) + 1;
                if (c > counts.get(next)) {
                    break;
                }
                seen.put(next,  c);
                count--;
                p += wLen;
            }
            if (count == 0)
                res.add(i);
        }
        return res;
    }

    public static void main(String[] args)  {
        SubstringWithConcatenationOfAllWords obj = new SubstringWithConcatenationOfAllWords();
        String s;
        String[] words;
        s = "wordgoodgoodgoodbestword";
        words = new String[] { "word","good","best","good"};
        List<Integer> idxs = obj.findSubstring(s, words);
        System.out.print("Result: ");
        idxs.forEach(i->System.out.print(i + ", "));
    }
}
