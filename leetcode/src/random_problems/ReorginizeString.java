package random_problems;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 767. Reorganize String
 * Medium
 *
 * Given a string S, check if the letters can be rearranged so that two characters that are adjacent to each other
 * are not the same.
 *
 * If possible, output any possible result.  If not possible, return the empty string.
 *
 * Example 1:
 *
 * Input: S = "aab"
 * Output: "aba"
 * Example 2:
 *
 * Input: S = "aaab"
 * Output: ""
 * Note:
 *
 * S will consist of lowercase letters and have length in range [1, 500].
 */
public class ReorginizeString {

    /**
     * Idea: reassignment is not possible only if count of one character is > (n + 1) / 2, so collect count of each one
     * and store.
     * Then do greedy approach - start checking for each character starting from most frequent one. When found one and
     * it's not one already used on previous step - insert it and decrement count. If same as on prev step - read next
     * most frequent one.
     * Save characters along with it's count in minheap
     * @param S
     * @return
     */
    public String reorganizeString(String S) {
        int[] counts = new int[26];
        for (char ch : S.toCharArray()) {
            counts[ch - 'a'] += 1;
        }

        Comparator<int[]> comp = new Comparator<int[]>() {
            public int compare(int[] a1, int[] a2) {
                return a2[0] - a1[0];
            }
        };
        int limit = (S.length()+ 1)/2;
        PriorityQueue<int[]> pq = new PriorityQueue(comp);
        for (int i = 0; i < counts.length; i++) {
            int count = counts[i];
            if (count > limit)
                return "";
            if(count > 0)
                pq.add(new int[] {count, 'a' + i});
        }

        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            //in this case we use most frequent char
            if (sb.length() == 0 || sb.charAt(sb.length() - 1) != (char)cur[1]) {
                sb.append((char)cur[1]);
                if (cur[0] > 1) {
                    cur[0] -= 1;
                    pq.add(cur);
                }
            //if most frequent one has been used previously - use next one from the min heap
            } else {
                int[] p = pq.poll();
                sb.append((char)p[1]);
                if (p[0] > 1) {
                    p[0] -= 1;
                    pq.add(p);
                }
                //put back most frequent one for next iterations
                pq.add(cur);
            }
        }
        return sb.toString();
    }
}
