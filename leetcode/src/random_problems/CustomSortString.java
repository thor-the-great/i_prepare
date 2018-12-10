package random_problems;

import java.util.Arrays;

/**
 * 791. Custom Sort String
 * Medium
 *
 *
 * S and T are strings composed of lowercase letters. In S, no letter occurs more than once.
 *
 * S was sorted in some custom order previously. We want to permute the characters of T so that they match the order
 * that S was sorted. More specifically, if x occurs before y in S, then x should occur before y in the returned string.
 *
 * Return any permutation of T (as a string) that satisfies this property.
 *
 * Example :
 * Input:
 * S = "cba"
 * T = "abcd"
 * Output: "cbad"
 * Explanation:
 * "a", "b", "c" appear in S, so the order of "a", "b", "c" should be "c", "b", and "a".
 * Since "d" does not appear in S, it can be at any position in T. "dcba", "cdba", "cbda" are also valid outputs.
 *
 *
 * Note:
 *
 * S has length at most 26, and no character is repeated in S.
 * T has length at most 200.
 * S and T consist of lowercase letters only.
 */
public class CustomSortString {

    public String customSortString(String S, String T) {
        char[] order = new char[26];
        int[] counts = new int[26];
        Arrays.fill(order, '$');
        //form the array of order in which chars are in S - index of element corresponds to order
        for (int i = 0; i < S.length(); i++) {
            char ch = S.charAt(i);
            order[i] = ch;
        }
        //count chars in T
        for (int i = 0; i < T.length(); i++) {
            counts[T.charAt(i) - 'a']++;
        }
        //first fill chars that are in S
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < 26 && order[i] != '$') {
            char nextChar = order[i];
            for (int j =0; j < counts[nextChar - 'a']; j++) {
                sb.append(nextChar);
            }
            counts[nextChar - 'a'] = 0;
            i++;
        }
        //now fill the rest of the string, chars that are not in S. Determine those based
        //on non-zero count in counts
        for (i =0; i < 26; i++) {
            if (counts[i] > 0) {
                char ch = (char)(i + 'a');
                for (int j =0; j < counts[i]; j++) {
                    sb.append(ch);
                }
            }
        }

        return sb.toString();
    }
}
