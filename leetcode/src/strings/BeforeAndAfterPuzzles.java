package strings;

import java.util.*;

/**
 * 1181. Before and After Puzzle
 * Medium
 *
 * 33
 *
 * 74
 *
 * Add to List
 *
 * Share
 * Given a list of phrases, generate a list of Before and After puzzles.
 *
 * A phrase is a string that consists of lowercase English letters and spaces only. No space appears in the start or
 * the end of a phrase. There are no consecutive spaces in a phrase.
 *
 * Before and After puzzles are phrases that are formed by merging two phrases where the last word of the first phrase
 * is the same as the first word of the second phrase.
 *
 * Return the Before and After puzzles that can be formed by every two phrases phrases[i] and phrases[j] where i != j.
 * Note that the order of matching two phrases matters, we want to consider both orders.
 *
 * You should return a list of distinct strings sorted lexicographically.
 *
 *
 *
 * Example 1:
 *
 * Input: phrases = ["writing code","code rocks"]
 * Output: ["writing code rocks"]
 * Example 2:
 *
 * Input: phrases = ["mission statement",
 *                   "a quick bite to eat",
 *                   "a chip off the old block",
 *                   "chocolate bar",
 *                   "mission impossible",
 *                   "a man on a mission",
 *                   "block party",
 *                   "eat my words",
 *                   "bar of soap"]
 * Output: ["a chip off the old block party",
 *          "a man on a mission impossible",
 *          "a man on a mission statement",
 *          "a quick bite to eat my words",
 *          "chocolate bar of soap"]
 * Example 3:
 *
 * Input: phrases = ["a","b","a"]
 * Output: ["a"]
 *
 *
 * Constraints:
 *
 * 1 <= phrases.length <= 100
 * 1 <= phrases[i].length <= 100
 */
public class BeforeAndAfterPuzzles {

    public List<String> beforeAndAfterPuzzles(String[] phrases) {
        int N = phrases.length;

        Map<String, List<Integer>> first = new HashMap();
        Map<String, List<Integer>> last = new HashMap();
        //fill maps of first and last words, keep list of indexes from string array for each word
        for (int i = 0; i < N; i++) {
            String s = phrases[i];
            int idx = s.indexOf(" ");
            String f = idx >= 0 ? s.substring(0, idx) : s;
            if (!first.containsKey(f)) {
                first.put(f, new ArrayList());
            }
            first.get(f).add(i);
            idx = s.lastIndexOf(" ");
            String l = idx >= 0 ? s.substring(idx + 1) : s;
            if (!last.containsKey(l)) {
                last.put(l, new ArrayList());
            }
            last.get(l).add(i);
        }
        //iterate over the map trying to find match of last and first words
        Set set = new HashSet();
        for (String lastWord : last.keySet()) {
            if (first.containsKey(lastWord)) {
                List<Integer> lasts = last.get(lastWord);
                for (int lastIdx : lasts) {
                    List<Integer> firsts = first.get(lastWord);
                    for (int firstIdx : firsts) {
                        //if both words matched and index are not the same - form the sentence
                        if (lastIdx != firstIdx) {
                            String fStr = phrases[firstIdx];
                            int idx = fStr.indexOf(" ");
                            StringBuilder sb = new StringBuilder(phrases[lastIdx]);
                            //if last word part is not empty - add it after the space
                            if (idx >= 0) {
                                sb.append(' ').append(fStr, idx + 1, fStr.length());
                            }
                            set.add(sb.toString());
                        }
                    }
                }
            }
        }
        //sort result as per requirement
        List<String> res = new ArrayList(set);
        Collections.sort(res);
        return res;
    }
}
