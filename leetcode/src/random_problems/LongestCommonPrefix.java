package random_problems;

/**
 * 14. Longest Common Prefix
 * Easy
 *
 * Write a function to find the longest common prefix string amongst an array of strings.
 *
 * If there is no common prefix, return an empty string "".
 *
 * Example 1:
 *
 * Input: ["flower","flow","flight"]
 * Output: "fl"
 * Example 2:
 *
 * Input: ["dog","racecar","car"]
 * Output: ""
 * Explanation: There is no common prefix among the input strings.
 * Note:
 *
 * All given inputs are in lowercase letters a-z.
 */
public class LongestCommonPrefix {

    /**
     * Idea - create Trie for all words, include count of each occurrence. Then pick any string and iterate over the
     * trie. Drop when count of next Trie node != to count of strings in input
     * @param strs
     * @return
     */
    public String longestCommonPrefix(String[] strs) {
        int N = strs.length;
        if (N == 0)
            return "";
        Trie root = new Trie(0);
        for (String s : strs) {
            Trie t = root;
            char[] chs = s.toCharArray();
            for (int i = 0; i < chs.length; i++) {
                int idx = chs[i] - 'a';
                Trie n = t.next[idx];
                if (n == null) {
                    n = new Trie(1);
                    t.next[idx] = n;
                } else {
                    n.count++;
                }
                t = n;
            }
        }
        StringBuilder pref = new StringBuilder();
        String s1 = strs[0];
        Trie t = root;
        for(int i = 0; i < s1.length(); i++) {
            char ch = s1.charAt(i);
            int idx = ch - 'a';
            if (t.next[idx] == null || t.next[idx].count != N)
                break;
            else {
                pref.append(ch);
                t = t.next[idx];
            }
        }
        return pref.toString();
    }

    class Trie {
        Trie[] next = new Trie[26];
        int count;
        boolean isWord;

        Trie(int c) {
            count = c;
            this.isWord = isWord;
        }
    }
}
