package path.linkedin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 244. Shortest Word Distance II
 * Medium
 *
 * Design a class which receives a list of words in the constructor, and implements a method that takes two words word1
 * and word2 and return the shortest distance between these two words in the list. Your method will be called
 * repeatedly many times with different parameters.
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
 */
public class ShortestWordDistance2 {

    class WordDistance {

        Map<String, List<Integer>> m;

        public WordDistance(String[] words) {
            m = new HashMap();
            for (int i =0; i < words.length; i++) {
                String word = words[i];
                if (!m.containsKey(word)) {
                    List<Integer> list = new ArrayList();
                    list.add(i);
                    m.put(word, list);
                } else {
                    m.get(word).add(i);
                }
            }
        }

        public int shortest(String word1, String word2) {
            int dist = Integer.MAX_VALUE;
            List<Integer> w1Idx = m.get(word1);
            List<Integer> w2Idx = m.get(word2);
            int w1P = 0, w2P = 0;
            int w1 = -1, w2 = -1;
            while(w1P < w1Idx.size() && w2P < w2Idx.size()) {
                w1 = w1Idx.get(w1P);
                w2 = w2Idx.get(w2P);
                if (w1 < w2) {
                    dist = Math.min(dist, w2 - w1);
                    w1P++;
                }
                else {
                    dist = Math.min(dist, w1 - w2);
                    w2P++;
                }
            }
            return dist;
        }
    }
}
