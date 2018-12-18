package path.google;

import java.util.Arrays;

/**
 * 418. Sentence Screen Fitting
 * Medium
 *
 * Given a rows x cols screen and a sentence represented by a list of non-empty words, find how many times the given
 * sentence can be fitted on the screen.
 *
 * Note:
 *
 * A word cannot be split into two lines.
 * The order of words in the sentence must remain unchanged.
 * Two consecutive words in a line must be separated by a single space.
 * Total words in the sentence won't exceed 100.
 * Length of each word is greater than 0 and won't exceed 10.
 * 1 ≤ rows, cols ≤ 20,000.
 * Example 1:
 *
 * Input:
 * rows = 2, cols = 8, sentence = ["hello", "world"]
 *
 * Output:
 * 1
 *
 * Explanation:
 * hello---
 * world---
 *
 * The character '-' signifies an empty space on the screen.
 * Example 2:
 *
 * Input:
 * rows = 3, cols = 6, sentence = ["a", "bcd", "e"]
 *
 * Output:
 * 2
 *
 * Explanation:
 * a-bcd-
 * e-a---
 * bcd-e-
 *
 * The character '-' signifies an empty space on the screen.
 * Example 3:
 *
 * Input:
 * rows = 4, cols = 5, sentence = ["I", "had", "apple", "pie"]
 *
 * Output:
 * 1
 *
 * Explanation:
 * I-had
 * apple
 * pie-I
 * had--
 *
 * The character '-' signifies an empty space on the screen.
 */
public class SentenceScreenFiting {

    /**
     * Idea - try to fit words one by one bsed on length. Optimize with DP - overall state identified by the word index
     * and in case of many rows/cols there will be many repeated jobs. So on each new index we save result - number of
     * times with this start and next index position.
     * Then a lot of optimizations including packing two ints of state into one so 1d int array can be used for dp
     * (instead of Map)
     * @param sentence
     * @param rows
     * @param cols
     * @return
     */
    public int wordsTyping(String[] sentence, int rows, int cols) {
        int res = 0;
        int[] dp = new int[101];
        Arrays.fill(dp, -1);
        int mask = 1<<12;

        int idx = 0;
        int i, c;
        for (int r = 0; r < rows; r++) {
            if (dp[idx] != -1) {
                int val = dp[idx];
                idx = val % mask;
                res += val >> 12;
            } else {
                int idxKey = idx;
                i = 0;
                c = 0;
                while (i < cols) {
                    int l = sentence[idx].length();
                    if (i != 0)
                        l++;
                    if ((i + l) <= cols) {
                        i += l;
                        if (idx + 1 >= sentence.length) {
                            idx = 0;
                            c++;
                        } else
                            idx++;
                    } else
                        break;
                }
                int val = c;
                val = val<<12;
                val += idx;
                dp[idxKey]= val;
                res += c;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        SentenceScreenFiting obj = new SentenceScreenFiting();
        /*System.out.println(obj.wordsTyping(new String[] {
                "I", "had", "apple", "pie"
        }, 4, 5));
*/
        System.out.println(obj.wordsTyping(new String[] {
                "f","p","a"
        }, 8, 7)); //10
    }
}
