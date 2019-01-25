package random_problems;

import java.util.HashMap;
import java.util.Map;

/**
 * 737. Sentence Similarity II
 * Medium
 *
 * Given two sentences words1, words2 (each represented as an array of strings), and a list of similar word pairs
 * pairs, determine if two sentences are similar.
 *
 * For example, words1 = ["great", "acting", "skills"] and words2 = ["fine", "drama", "talent"] are similar, if the
 * similar word pairs are pairs = [["great", "good"], ["fine", "good"], ["acting","drama"], ["skills","talent"]].
 *
 * Note that the similarity relation is transitive. For example, if "great" and "good" are similar, and "fine" and
 * "good" are similar, then "great" and "fine" are similar.
 *
 * Similarity is also symmetric. For example, "great" and "fine" being similar is the same as "fine" and "great" being
 * similar.
 *
 * Also, a word is always similar with itself. For example, the sentences words1 = ["great"], words2 = ["great"],
 * pairs = [] are similar, even though there are no specified similar word pairs.
 *
 * Finally, sentences can only be similar if they have the same number of words. So a sentence like words1 = ["great"]
 * can never be similar to words2 = ["doubleplus","good"].
 *
 * Note:
 *
 * The length of words1 and words2 will not exceed 1000.
 * The length of pairs will not exceed 2000.
 * The length of each pairs[i] will be 2.
 * The length of each words[i] and pairs[i][j] will be in the range [1, 20].
 *
 */
public class SentenceSimilarityII {

    /**
     * Idea: main thing this transitive property for similar words. To track that we use Union-Find, union similar words
     * then use find to check if words are similar. Assign index to each word, index is an incrementing sequence.
     * @param words1
     * @param words2
     * @param pairs
     * @return
     */
    public boolean areSentencesSimilarTwo(String[] words1, String[] words2, String[][] pairs) {
        int N1 = words1.length;
        int N2 = words2.length;
        if (N1 != N2)
            return false;
        Map<String, Integer> countMap = new HashMap();
        int count = 0;
        UF uf = new UF(2*pairs.length);
        for(String[] pair : pairs) {
            int idxP0, idxP1;
            if (countMap.containsKey(pair[0])) {
                idxP0 = countMap.get(pair[0]);
            } else {
                countMap.put(pair[0], count);
                idxP0 = count;
                count++;
            }

            if (countMap.containsKey(pair[1])) {
                idxP1 = countMap.get(pair[1]);
            } else {
                countMap.put(pair[1], count);
                idxP1 = count;
                count++;
            }

            uf.union(idxP0, idxP1);
        }

        for (int i =0; i < N1; i++) {
            String w1 = words1[i];
            String w2 = words2[i];
            if (w1.equals(w2))
                continue;

            if (!countMap.containsKey(w1) || !countMap.containsKey(w2) || (uf.find(countMap.get(w1)) != uf.find(countMap.get(w2))))
                return false;
        }
        return true;
    }

    class UF {
        int[] rank;
        int[] parents;

        UF(int size) {
            rank = new int[size];
            parents = new int[size];
            for (int i = 0; i < size; i++) {
                parents[i] = i;
            }
        }


        int find(int i) {
            if (parents[i] != i) {
                parents[i] = find(parents[i]);
            }
            return parents[i];
        }

        void union(int i, int j) {
            int pI = find(i);
            int pJ = find(j);
            if (pI != pJ) {
                if (rank[pI] > rank[pJ]) {
                    parents[pJ] = pI;
                } else if (rank[pI] < rank[pJ]) {
                    parents[pI] = pJ;
                } else {
                    parents[pI] = pJ;
                    rank[pJ]++;
                }
            }
        }
    }
}
