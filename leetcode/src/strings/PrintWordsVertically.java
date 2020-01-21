package strings;

import java.util.ArrayList;
import java.util.List;

/**
 * 1324. Print Words Vertically
 * Medium
 *
 * Given a string s. Return all the words vertically in the same order in which they appear in s.
 * Words are returned as a list of strings, complete with spaces when is necessary. (Trailing spaces are not allowed).
 * Each word would be put on only one column and that in one column there will be only one word.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "HOW ARE YOU"
 * Output: ["HAY","ORO","WEU"]
 * Explanation: Each word is printed vertically.
 *  "HAY"
 *  "ORO"
 *  "WEU"
 * Example 2:
 *
 * Input: s = "TO BE OR NOT TO BE"
 * Output: ["TBONTB","OEROOE","   T"]
 * Explanation: Trailing spaces is not allowed.
 * "TBONTB"
 * "OEROOE"
 * "   T"
 * Example 3:
 *
 * Input: s = "CONTEST IS COMING"
 * Output: ["CIC","OSO","N M","T I","E N","S G","T"]
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 200
 * s contains only upper case English letters.
 * It's guaranteed that there is only one space between 2 words.
 */
public class PrintWordsVertically {

    /**
     * Iterate over each word from 0 to len, add char to string builder, special handling for spaces
     * @param s
     * @return
     */
    public List<String> printVertically(String s) {
        List<StringBuilder> sbList = new ArrayList();
        String[] arr = s.split(" ");

        for (int i = 0; i < arr.length; i++) {
            int len = arr[i].length(), size = sbList.size();
            if (size < len) {
                for (int j = 0; j < len - size; j++)
                    sbList.add(new StringBuilder());
            }
            for (int j = 0; j < len; j++) {
                StringBuilder sb = sbList.get(j);
                if (sb.length() < i + 1) {
                    for (int kk = sb.length(); kk < i; kk++)
                        sb.append(' ');
                }
                sb.append(arr[i].charAt(j));
            }
        }

        List<String> res = new ArrayList();
        for (StringBuilder sb : sbList)
            res.add(sb.toString());
        return res;
    }
}
