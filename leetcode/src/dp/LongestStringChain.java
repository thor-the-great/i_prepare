package dp;

/**
 * 1048. Longest String Chain
 * Medium
 *
 * Given a list of words, each word consists of English lowercase letters.
 *
 * Let's say word1 is a predecessor of word2 if and only if we can add exactly one letter
 * anywhere in word1 to make it equal to word2.  For example, "abc" is a predecessor of "abac".
 *
 * A word chain is a sequence of words [word_1, word_2, ..., word_k] with k >= 1, where word_1 is
 * a predecessor of word_2, word_2 is a predecessor of word_3, and so on.
 *
 * Return the longest possible length of a word chain with words chosen from the given list of
 * words.
 *
 *
 *
 * Example 1:
 *
 * Input: ["a","b","ba","bca","bda","bdca"]
 * Output: 4
 * Explanation: one of the longest word chain is "a","ba","bda","bdca".
 *
 *
 * Note:
 *
 * 1 <= words.length <= 1000
 * 1 <= words[i].length <= 16
 * words[i] only consists of English lowercase letters.
 */
public class LongestStringChain {

  /**
   *   Solution is based on DP.
   *   If we sort the array then sequence can only increase if we go from 0 to len(array). Also
   *   if we checked this word previously then we can re-use it's sequence score if this word can
   *   be formed from longer word.
   *
   *   These are key ideas, now build the solution. Sort words by length, then start iterating on
   *   word's array. For every word we met store it's best scoe in the map <word>-<score>. For
   *   every word remove it chars one by one and check if met this new word before, if so - for
   *   current word the score will be score<shorter_word> + 1. Otherwise this word starts the new
   *   sequence so the score is 1. In Java we can remove one char effectively using StringBuilder.
   *
   *   We keep the overall best score and check for every word from array if it can give us
   *   better one.
   *
   *   Complexity - O(nlgn) for sorting, O(n x avg_len) for checking every char in every word, so
   *   the main factor will be O(n x avg_len). We need O(n x avg_len) memory to store scores for
   *   every sub-word.
   * @param words
   * @return
   */
    public int longestStrChain(String[] words) {
        //sort words by length
        Arrays.sort(words,  new Comparator<String>(){
            public int compare(String s0, String s1) {
                return s0.length() - s1.length();
            }
        });
        //map is required to store the best sequence length for each word
        Map<String, Integer> map = new HashMap();
        //final result
        int res = 0;
        //iterate over words, because they are sorted we may assume that at each next word
        //can only increase the sequence
        for (String word : words) {
            int cur = 1;
            //try to remove every char in word one by one, check in the map for every removed char
            StringBuilder sb = new StringBuilder(word);
            int N = word.length();
            for (int i = 0; i < N; i++) {
                char ch = sb.charAt(i);
                sb.deleteCharAt(i);
                //this is our word without char at position i-th
                String possible = sb.toString();
                //if we met this word before - get it's sequence count from map, otherwise it's 1
                if (map.containsKey(possible)) {
                    cur = Math.max(cur, map.get(possible) + 1);
                }
                //this is to restore deleted char for next iteration
                if (i < N - 1)
                    sb.insert(i, ch);
            }
            //store the best sequence score for this word
            map.put(word, cur);
            //update overall result if needed
            res = Math.max(res, cur);
        }
        return res;
    }
}
