package random_problems;

import java.util.TreeMap;

/**
 * 937. Reorder Data in Log Files
 * Easy
 *
 * You have an array of logs.  Each log is a space delimited string of words.
 *
 * For each log, the first word in each log is an alphanumeric identifier.  Then, either:
 *
 * Each word after the identifier will consist only of lowercase letters, or;
 * Each word after the identifier will consist only of digits.
 * We will call these two varieties of logs letter-logs and digit-logs.  It is guaranteed that each log has at least
 * one word after its identifier.
 *
 * Reorder the logs so that all of the letter-logs come before any digit-log.  The letter-logs are ordered
 * lexicographically ignoring identifier, with the identifier used in case of ties.  The digit-logs should be put in
 * their original order.
 *
 * Return the final order of the logs.
 *
 *
 *
 * Example 1:
 *
 * Input: logs = ["dig1 8 1 5 1","let1 art can","dig2 3 6","let2 own kit dig","let3 art zero"]
 * Output: ["let1 art can","let3 art zero","let2 own kit dig","dig1 8 1 5 1","dig2 3 6"]
 *
 *
 * Constraints:
 *
 * 0 <= logs.length <= 100
 * 3 <= logs[i].length <= 100
 * logs[i] is guaranteed to have an identifier, and a word after the identifier.
 */
public class ReorderDatainLogFiles {

    /**
     * identify if it's a digit based or letter. If digit just add it to the res array directly. Otherwise make a key
     * out of string part and identifier, put it to the sorted map of key - index. Starting from the 0 in res array
     * add elements from map
     * @param logs
     * @return
     */
    public String[] reorderLogFiles(String[] logs) {
        int N = logs.length;
        String[] res = new String[N];
        TreeMap<String, Integer> map = new TreeMap();
        int p = N - 1;
        for (int i = N - 1; i >= 0; i--) {
            String s = logs[i];
            int pos = s.indexOf(' ');
            if (s.charAt(pos + 1) >= '0' && s.charAt(pos + 1) <= '9') {
                res[p--] = s;
            } else {
                String part1 = s.substring(pos + 1);
                map.put(part1 + s.substring(0, pos), i);
            }
        }
        p = 0;
        for (String s : map.keySet())
            res[p++] = logs[map.get(s)];
        return res;
    }
}
