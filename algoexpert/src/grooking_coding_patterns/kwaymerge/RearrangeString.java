package grooking_coding_patterns.kwaymerge;

import java.util.*;

/**
 * Rearrange String (hard)
 * <p>
 * Given a string, find if its letters can be rearranged in such a way that no two same characters come next to
 * each other.
 * <p>
 * Example 1:
 * <p>
 * Input: "aappp"
 * Output: "papap"
 * Explanation: In "papap", none of the repeating characters come next to each other.
 * Example 2:
 * <p>
 * Input: "Programming"
 * Output: "rgmrgmPiano" or "gmringmrPoa" or "gmrPagimnor", etc.
 * Explanation: None of the repeating characters come next to each other.
 * Example 3:
 * <p>
 * Input: "aapa"
 * Output: ""
 * Explanation: In all arrangements of "aapa", atleast two 'a' will come together e.g., "apaa", "paaa".
 */
public class RearrangeString {

  // aapa  a-3; p-1
  // a-3  [a] p-1 a=2
  // p-1  [ap] [a-2] a=1
  // a-2  [apa]
  // gmrgmrnaiPo   Programming

  /**
   * Idea create a heap of char->qty, sorted by qty. Poll from heap, decrement and if it's still
   * > 0 - keep it. Then on next loop poll next char and add the prev one back. If at the end
   * we still have prev qty then it's impossible
   * @param str
   * @return
   */
  public static String rearrangeString(String str) {
    StringBuilder sb = new StringBuilder();

    PriorityQueue<int[]> pq = new PriorityQueue<>((a1, a2) -> a2[1] - a1[1]);
    int[] count = new int[128];
    for (char ch : str.toCharArray()) {
      count[ch]++;
    }

    for (int i = 0; i < 128; i++) {
      if (count[i] > 0) {
        pq.add(new int[]{i, count[i]});
      }
    }

    int[] prev = new int[]{-1, -1};
    while (!pq.isEmpty()) {
      int[] next = pq.poll();
      //check if possible
      if (prev[0] != -1 && prev[0] == next[0])
        return "";
      if (prev[0] != -1)
        pq.add(prev);
      sb.append((char) next[0]);
      if (next[1] - 1 > 0) {
        prev = next;
        --prev[1];
      } else {
        prev = new int[]{-1, -1};
      }
    }
    if (prev[0] != -1 && prev[1] > 0)
      return "";
    return sb.toString();
  }

  public static void main(String[] args) {
    System.out.println("Rearranged string: " + RearrangeString.rearrangeString("aappp"));
    System.out.println("Rearranged string: " + RearrangeString.rearrangeString("Programming"));
    System.out.println("Rearranged string: " + RearrangeString.rearrangeString("aapa"));
  }
}
