package random_problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 1002. Find Common Characters
 * Easy
 *
 * Given an array A of strings made only from lowercase letters, return a list of all characters that show up in all
 * strings within the list (including duplicates).  For example, if a character occurs 3 times in all strings but
 * not 4 times, you need to include that character three times in the final answer.
 *
 * You may return the answer in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: ["bella","label","roller"]
 * Output: ["e","l","l"]
 * Example 2:
 *
 * Input: ["cool","lock","cook"]
 * Output: ["c","o"]
 *
 *
 * Note:
 *
 * 1 <= A.length <= 100
 * 1 <= A[i].length <= 100
 * A[i][j] is a lowercase letter
 *
 */
public class FindCommonCharacters {

    /**
     * Count chars in every string, compare with minimum
     * @param A
     * @return
     */
    public List<String> commonChars(String[] A) {
        List<String> res = new ArrayList();
        int N = A.length;
        if (N == 0)
            return res;

        int[] counts = new int[26];
        Arrays.fill(counts, Integer.MAX_VALUE);

        for (int j =0; j < N; j++) {
            String s = A[j];
            int[] c = new int[26];
            for (int i = 0; i < s.length(); i++) {
                c[s.charAt(i) - 'a']++;
            }

            for (int i = 0; i < 26; i++) {
                counts[i] = Math.min(counts[i], c[i]);
            }
        }

        for (int i =0; i < 26; i++) {
            if (counts[i] != Integer.MAX_VALUE) {
                String s = new String(new char[] {(char)('a' + i)});
                for (int j = 0; j < counts[i]; j++) {
                    res.add(s);
                }
            }
        }

        return res;
    }
}
