package random_problems;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 819. Most Common Word
 * Easy
 *
 * Given a paragraph and a list of banned words, return the most frequent word that is not in the list of banned
 * words.  It is guaranteed there is at least one word that isn't banned, and that the answer is unique.
 *
 * Words in the list of banned words are given in lowercase, and free of punctuation.  Words in the paragraph are not
 * case sensitive.  The answer is in lowercase.
 *
 *
 *
 * Example:
 *
 * Input:
 * paragraph = "Bob hit a ball, the hit BALL flew far after it was hit."
 * banned = ["hit"]
 * Output: "ball"
 * Explanation:
 * "hit" occurs 3 times, but it is a banned word.
 * "ball" occurs twice (and no other word does), so it is the most frequent non-banned word in the paragraph.
 * Note that words in the paragraph are not case sensitive,
 * that punctuation is ignored (even if adjacent to words, such as "ball,"),
 * and that "hit" isn't the answer even though it occurs more because it is banned.
 *
 *
 * Note:
 *
 * 1 <= paragraph.length <= 1000.
 * 0 <= banned.length <= 100.
 * 1 <= banned[i].length <= 10.
 * The answer is unique, and written in lowercase (even if its occurrences in paragraph may have uppercase symbols,
 * and even if it is a proper noun.)
 * paragraph only consists of letters, spaces, or the punctuation symbols !?',;.
 * There are no hyphens or hyphenated words.
 * Words only consist of letters, never apostrophes or other punctuation symbols.
 */
public class MostCommonWord {

    public String mostCommonWord(String paragraph, String[] banned) {
        paragraph += ".";
        Set<String> ban = new HashSet();
        for (String s: banned)
            ban.add(s);

        Map<String, Integer> m = new HashMap();
        StringBuilder sb = new StringBuilder();
        for (char ch : paragraph.toCharArray()) {
            if (Character.isLetter(ch)) {
                sb.append(Character.toLowerCase(ch));
            } else {
                String w = sb.toString();
                if (w.length() > 0 && !ban.contains(w)) {
                    if (m.containsKey(w)) {
                        m.put(w, m.get(w) + 1);
                    } else {
                        m.put(w, 1);
                    }
                }
                sb.setLength(0);
            }
        }

        String res = "";
        int max = -1;
        for (String w : m.keySet()) {
            int count = m.get(w);
            if (count > max) {
                max = count;
                res = w;
            }
        }

        return res;
    }
}
