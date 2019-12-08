package map;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 290. Word Pattern
 * Easy
 *
 * Given a pattern and a string str, find if str follows the same pattern.
 *
 * Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in str.
 *
 * Example 1:
 *
 * Input: pattern = "abba", str = "dog cat cat dog"
 * Output: true
 * Example 2:
 *
 * Input:pattern = "abba", str = "dog cat cat fish"
 * Output: false
 * Example 3:
 *
 * Input: pattern = "aaaa", str = "dog cat cat dog"
 * Output: false
 * Example 4:
 *
 * Input: pattern = "abba", str = "dog dog dog dog"
 * Output: false
 * Notes:
 * You may assume pattern contains only lowercase letters, and str contains lowercase letters that may be separated
 * by a single space.
 */
public class WordPattern {

    /**
     *
     * @param pattern
     * @param str
     * @return
     */
    public boolean wordPattern(String pattern, String str) {
        String[] words = str.split(" ");
        if (pattern.length() != words.length)
            return false;
        //maps character to a word (by index in the parsed array)
        int[] chWord = new int[26];
        Arrays.fill(chWord, -1);
        Map<String, Character> wordCh = new HashMap();
        for (int p = 0; p < words.length; p++) {
            char ch = pattern.charAt(p);
            int charIdx = pattern.charAt(p) - 'a';
            //check if this char has any words mapped to it
            if (chWord[charIdx] == -1) {
                //if no words mapped to this char, then this word should not have any character from previous iterations
                if (wordCh.containsKey(words[p]))
                    return false;
                //map both char to word and word to char
                chWord[charIdx] = p;
                wordCh.put(words[p], ch);
            } else if (!words[chWord[charIdx]].equals(words[p])) {
                //if we had met any word for this char - check that this is the same word we met now
                return false;
            }
        }
        return true;
    }
}
