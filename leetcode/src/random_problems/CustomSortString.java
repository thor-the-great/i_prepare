package random_problems;

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

    /**
     * Count chars in T string first. Then iterate over S and one every char from S add it as many times
     * as it was in T. After that just add the rest of the chars from T in any order
     * @param S
     * @param T
     * @return
     */
    public String customSortString(String S, String T) {
        int[] count = new int[26];
        for (char ch : T.toCharArray())
            ++count[ch - 'a'];
        StringBuilder sb = new StringBuilder();
        for (char ch : S.toCharArray()) {
            for (int i = 0; i < count[ch - 'a']; i++) {
                sb.append(ch);
            }
            count[ch - 'a'] = 0;
        }

        for (char ch = 'a'; ch <= 'z' ; ch++) {
            if (count[ch - 'a'] > 0) {
                for (int j = 0; j < count[ch - 'a']; j++) {
                    sb.append(ch);
                }
            }
        }

        return sb.toString();
    }
}
