package grooking_coding_patterns.topkelements;

import java.util.*;

/**
 * Problem Challenge 1
 *
 * Rearrange String K Distance Apart (hard) #
 * Given a string and a number ‘K’, find if the string can be rearranged such that the same
 * characters are at least ‘K’ distance apart from each other.
 *
 * Example 1:
 *
 * Input: "mmpp", K=2
 * Output: "mpmp" or "pmpm"
 * Explanation: All same characters are 2 distance apart.
 * Example 2:
 *
 * Input: "Programming", K=3
 * Output: "rgmPrgmiano" or "gmringmrPoa" or "gmrPagimnor" and a few more
 * Explanation: All same characters are 3 distance apart.
 * Example 3:
 *
 * Input: "aab", K=2
 * Output: "aba"
 * Explanation: All same characters are 2 distance apart.
 * Example 4:
 *
 * Input: "aappa", K=3
 * Output: ""
 * Explanation: We cannot find an arrangement of the string where any two 'a' are 3 distance apart.
 */
public class RearrangeStringKDistanceApart {

  /**
   * Idea - keep the map of freq anb chars in pq. When poll put the char lest to the queue, then
   * when queue.size reaches k = poll from that queue and add back to the pq
   *
   * Time complexity #
   * The time complexity of the above algorithm is O(N*logN) where ‘N’ is the number of
   * characters in the input string.
   *
   * Space complexity #
   * The space complexity will be O(N), as in the worst case, we need to store all the ‘N’
   * characters in the HashMap.
   * @param str
   * @param k
   * @return
   */
  public static String reorganizeString(String str, int k) {
    PriorityQueue<int[]> pq = new PriorityQueue<>((a1,a2) -> a2[1] - a1[1]);
    int[] count = new int[128];
    for (char ch : str.toCharArray()) {
      ++count[ch];
    }

    for (int i = 0; i < 128; i++) {
      if (count[i] > 0) {
        pq.add(new int[]{i, count[i]});
      }
    }
    StringBuilder sb = new StringBuilder();
    Queue<int[]> prevPq = new LinkedList();
    while(!pq.isEmpty()) {
      int[] next = pq.poll();
      sb.append((char)next[0]);
      --next[1];
      prevPq.add(next);
      if (prevPq.size() == k) {
        int[] val = prevPq.poll();
        if (val[1] > 0)
          pq.add(val);
      }
    }

    if (sb.length() < str.length())
      return "";

    return sb.toString();
  }

  public static void main(String[] args) {
    System.out.println("Reorganized string: " +
        RearrangeStringKDistanceApart.reorganizeString("mmpp", 2));
    System.out.println("Reorganized string: " +
        RearrangeStringKDistanceApart.reorganizeString("Programming", 3));
    System.out.println("Reorganized string: " +
        RearrangeStringKDistanceApart.reorganizeString("aab", 2));
    System.out.println("Reorganized string: " +
        RearrangeStringKDistanceApart.reorganizeString("aappa", 3));
  }
}