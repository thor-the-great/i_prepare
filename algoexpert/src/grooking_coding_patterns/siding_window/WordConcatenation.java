package grooking_coding_patterns.siding_window;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Problem Challenge 4
 * We'll cover the following
 * Words Concatenation (hard)
 *
 * Words Concatenation (hard) #
 * Given a string and a list of words, find all the starting indices of substrings in the given string that are a
 * concatenation of all the given words exactly once without any overlapping of words. It is given that all words
 * are of the same length.
 *
 * Example 1:
 *
 * Input: String="catfoxcat", Words=["cat", "fox"]
 * Output: [0, 3]
 * Explanation: The two substring containing both the words are "catfox" & "foxcat".
 * Example 2:
 *
 * Input: String="catcatfoxfox", Words=["cat", "fox"]
 * Output: [3]
 * Explanation: The only substring containing both the words is "catfox".
 */
public class WordConcatenation {
    /**
     * Key catch - all words are of the same length. Get the map of the words in words array.
     * Iterate over the string and get the possible word. If the word is in the map - increment count and increment
     * iteration var on length of word. Keep checking if we found all words exactly number of times.
     * If word is not in the map - reset the map, increment the i on 1 for next iteration.
     *
     * O(len(str) + len(words)) time. Space - O(len(words))
     * @param str
     * @param words
     * @return
     */
    public static List<Integer> findWordConcatenation(String str, String[] words) {
        List<Integer> resultIndices = new ArrayList<Integer>();
        if (str == null || str.isEmpty() || words == null || words.length == 0)
            return resultIndices;

        int len = words[0].length();
        Map<String, Integer> freqMap = new HashMap();

        for (String word : words)
            freqMap.put(word, freqMap.getOrDefault(word, 0) + 1);

        Map<String, Integer> curMap = new HashMap();
        int l = 0, cc = 0;
        int i = 0;
        while (i < str.length() - len + 1) {
            String cur = str.substring(i, i + len);
            if (!freqMap.containsKey(cur)) {
                curMap.clear();
                ++i; l = i;
                continue;
            }

            curMap.put(cur, curMap.getOrDefault(cur, 0) + 1);
            if (curMap.get(cur) == freqMap.get(cur))
                cc++;

            while(curMap.get(cur) > freqMap.get(cur)) {
                String word1 = str.substring(l, l + len);
                curMap.put(word1, curMap.get(word1) - 1);
                if (curMap.get(word1) < freqMap.get(word1))
                    --cc;
                l+=len;
            }

            if (cc == freqMap.size())
                resultIndices.add(l);
            i+=len;
        }
        return resultIndices;
    }
}