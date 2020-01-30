package strings;

import java.util.ArrayList;
import java.util.List;

/**
 * 809. Expressive Words
 * Medium
 *
 * Sometimes people repeat letters to represent extra feeling, such as "hello" -> "heeellooo", "hi" -> "hiiii".  In
 * these strings like "heeellooo", we have groups of adjacent letters that are all the same:  "h", "eee", "ll", "ooo".
 *
 * For some given string S, a query word is stretchy if it can be made to be equal to S by any number of applications
 * of the following extension operation: choose a group consisting of characters c, and add some number of characters
 * c to the group so that the size of the group is 3 or more.
 *
 * For example, starting with "hello", we could do an extension on the group "o" to get "hellooo", but we cannot get
 * "helloo" since the group "oo" has size less than 3.  Also, we could do another extension like "ll" -> "lllll" to
 * get "helllllooo".  If S = "helllllooo", then the query word "hello" would be stretchy because of these two
 * extension operations: query = "hello" -> "hellooo" -> "helllllooo" = S.
 *
 * Given a list of query words, return the number of words that are stretchy.
 *
 *
 *
 * Example:
 * Input:
 * S = "heeellooo"
 * words = ["hello", "hi", "helo"]
 * Output: 1
 * Explanation:
 * We can extend "e" and "o" in the word "hello" to get "heeellooo".
 * We can't extend "helo" to get "heeellooo" because the group "ll" is not size 3 or more.
 *
 *
 * Notes:
 *
 * 0 <= len(S) <= 100.
 * 0 <= len(words) <= 100.
 * 0 <= len(words[i]) <= 100.
 * S and all words in words consist only of lowercase letters
 */
public class ExpressiveWords {

    /**
     * Create list of char and its qty in sequence they appear, one for query word, and for each next word
     * iterate over that collection and check if chars are same and if so - that qty as per problem statement.
     * If all matches - increment running qty.
     * @param S
     * @param words
     * @return
     */
    public int expressiveWords(String S, String[] words) {
        List<Integer> queryCount = encode(S);

        int res = 0;
        for (String word : words) {
            List<Integer> wordCount = encode(word);
            if (wordCount.size() != queryCount.size())
                break;
            boolean wrong = false;
            for (int i = 0; i < wordCount.size(); i++) {
                int q = queryCount.get(i);
                int w = wordCount.get(i);
                if (q == w)
                    continue;

                if (q % 32 != w % 32) {
                    wrong = true;
                    break;
                }

                int qC = (q>>6);
                int wC = (w>>6);
                if (qC != wC && (qC < 3 || wC > qC)) {
                    wrong = true;
                    break;
                }
            }
            if (!wrong)
                ++res;
        }
        return res;
    }

    List<Integer> encode(String S) {
        List<Integer> letterCount = new ArrayList();
        int l = 0;
        char prev = S.charAt(0);
        for (int i = 0; i < S.length(); i++) {
            if (prev != S.charAt(i)) {
                int val = ((i - l)<<6) + (prev - 'a');
                letterCount.add(val);
                prev = S.charAt(i);
                l = i;
            }
        }
        letterCount.add(((S.length() - l)<<6) + (prev - 'a'));
        return letterCount;
    }
}
