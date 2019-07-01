package random_problems;

import java.util.ArrayList;
import java.util.List;

/**
 * 763. Partition Labels
 * Medium
 *
 * 989
 *
 * 54
 *
 * Favorite
 *
 * Share
 * A string S of lowercase letters is given. We want to partition this string into as many parts
 * as possible so that each letter appears in at most one part, and return a list of integers
 * representing the size of these parts.
 *
 * Example 1:
 * Input: S = "ababcbacadefegdehijhklij"
 * Output: [9,7,8]
 * Explanation:
 * The partition is "ababcbaca", "defegde", "hijhklij".
 * This is a partition so that each letter appears in at most one part.
 * A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it splits S into less
 * parts.
 * Note:
 *
 * S will have length in range [1, 500].
 * S will consist of lowercase letters ('a' to 'z') only.
 */
public class PartitionLabels {

  /**
   * Idea - we don't need to count every character. The partition can't end before the last character
   * position, so we only interested in the last occurance of every char.
   * If partition has several letters then it ends when the last (biggest) position among all it's
   * characters met. This leads to a logic - go over the string and keep the max index of chars we met.
   * If current index == that saved position - it means we met the end of the partition.
   * @param S
   * @return
   */
  public List<Integer> partitionLabels(String S) {
    int[] counts = new int[28];
    List<Integer> res = new ArrayList<>();
    int N = S.length();
    //count occurrences of every char in the string
    for (int i = 0; i < N; i++) {
      int idx = S.charAt(i) - 'a';
      counts[idx] = i;
    }

    int pos = 0;
    int lastEnd = -1;
    for (int i = 0; i < N; i++) {
      pos = Math.max(pos, counts[S.charAt(i) - 'a']);

      if (pos == i)  {
        res.add(pos - lastEnd);
        lastEnd = pos;
      }
    }

    return res;
  }

  public static void main(String[] args) {
    PartitionLabels obj = new PartitionLabels();
    System.out.println(obj.partitionLabels("ababcbacadefegdehijhklij"));

    System.out.println(obj.partitionLabels("ababcbacadefegdehijhklij"));
  }
}
