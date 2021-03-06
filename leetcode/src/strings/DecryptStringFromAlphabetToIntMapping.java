package strings;

/**
 * 1309. Decrypt String from Alphabet to Integer Mapping
 * Easy
 *
 * Given a string s formed by digits ('0' - '9') and '#' . We want to map s to English lowercase characters as follows:
 *
 * Characters ('a' to 'i') are represented by ('1' to '9') respectively.
 * Characters ('j' to 'z') are represented by ('10#' to '26#') respectively.
 * Return the string formed after mapping.
 *
 * It's guaranteed that a unique mapping will always exist.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "10#11#12"
 * Output: "jkab"
 * Explanation: "j" -> "10#" , "k" -> "11#" , "a" -> "1" , "b" -> "2".
 * Example 2:
 *
 * Input: s = "1326#"
 * Output: "acz"
 * Example 3:
 *
 * Input: s = "25#"
 * Output: "y"
 * Example 4:
 *
 * Input: s = "12345678910#11#12#13#14#15#16#17#18#19#20#21#22#23#24#25#26#"
 * Output: "abcdefghijklmnopqrstuvwxyz"
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 1000
 * s[i] only contains digits letters ('0'-'9') and '#' letter.
 * s will be valid string such that mapping is always possible.
 */
public class DecryptStringFromAlphabetToIntMapping {

    /**
     * Traverse the string, at each "#" parse the block between this and last "#". Last two chars are always 2 digit
     * mapping, everything before it is a single digit
     * @param s
     * @return
     */
    public String freqAlphabets(String s) {
        StringBuilder sb = new StringBuilder();
        //index of the block beginning
        int l = 0;
        for (int i = 0; i < s.length(); i++) {
            //if we reach the end of the block - start parsing
            if (s.charAt(i) == '#') {
                //first take care of the single digit mappings
                for (int j = l; j < i - 2; j++)
                    sb.append((char)('a' + (s.charAt(j) - '1')));
                //then map last one 2 digit mapping
                sb.append((char)('a' + (10*(s.charAt(i - 2) - '0') + (s.charAt(i - 1) - '0') - 1)));
                l = i + 1;
            }
        }
        //take care of the last part of the single digit mapping if there is any
        for (int j = l; j < s.length(); j++)
            sb.append((char)('a' + (s.charAt(j) - '1')));

        return sb.toString();
    }
}
