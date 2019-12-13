package arrays;

/**
 * 243. Shortest Word Distance
 * Easy
 *
 * Given a list of words and two words word1 and word2, return the shortest distance between these two words in the list.
 *
 * Example:
 * Assume that words = ["practice", "makes", "perfect", "coding", "makes"].
 *
 * Input: word1 = “coding”, word2 = “practice”
 * Output: 3
 * Input: word1 = "makes", word2 = "coding"
 * Output: 1
 * Note:
 * You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.
 *
 */
public class ShortestWordsDistance {

    /**
     * Greedy approach will work - the min distance will be between two words next to each other, so we can keep
     * pointer to the last seen for each word.
     * @param words
     * @param word1
     * @param word2
     * @return
     */
    public int shortestDistance(String[] words, String word1, String word2) {
        int d = Integer.MAX_VALUE;
        //last seen positions for each word
        int p1 = -1, p2 = -1;

        for (int i = 0; i < words.length; i++) {
            String w = words[i];
            //if this is word1 - save its position and calculate if the distance is min
            if (word1.equals(w)) {
                p1 = i;
                if (p2 != -1)
                    d = Math.min(d, p1 - p2);
            } else if (word2.equals(w)) {
                //do the same for word2
                p2 = i;
                if (p1 != -1)
                    d = Math.min(d, p2 - p1);
            }
        }
        return d;
    }
}
