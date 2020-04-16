package bfs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * 854. K-Similar Strings
 * Hard
 *
 * Strings A and B are K-similar (for some non-negative integer K) if we can swap the positions
 * of two letters in A exactly K times so that the resulting string equals B.
 *
 * Given two anagrams A and B, return the smallest K for which A and B are K-similar.
 *
 * Example 1:
 *
 * Input: A = "ab", B = "ba"
 * Output: 1
 * Example 2:
 *
 * Input: A = "abc", B = "bca"
 * Output: 2
 * Example 3:
 *
 * Input: A = "abac", B = "baca"
 * Output: 2
 * Example 4:
 *
 * Input: A = "aabc", B = "abca"
 * Output: 2
 * Note:
 *
 * 1 <= A.length == B.length <= 20
 * A and B contain only lowercase letters from the set {'a', 'b', 'c', 'd', 'e', 'f'}
 */
public class KSimilarStrings {

  /**
   * Idea - we can create graph where each node is a one permutation of a previous string and
   * edge means 1 such permutation as a length of path.
   * Then we can generate next "level" of such graph in runtime, we do it unless one of the generated
   * nodes will be A (or we out of nodes which means it's not possible).
   * Trick is how to generate such strings.
   * For every pair we check until we find the first mismatched char. Then we swap this char with
   * same one from second string.
   * Import is to check for visited strings as soon as possible.
   *
   *
   * @param A
   * @param B
   * @return
   */
  public int kSimilarity(String A, String B) {
    if (A == null || B == null || A.length() != B.length())
      return -1;
    Queue<String> q = new LinkedList();
    q.add(A);
    Set<String> seen = new HashSet();
    seen.add(A);
    int level = 0;
    while(!q.isEmpty()) {
      int size = q.size();
      for (int i = 0; i < size; i++) {
        String next = q.poll();
        if (next.equals(B))
          return level;
        for (String adj : adjucents(next, B, seen)) {
          seen.add(adj);
          q.add(adj);
        }
      }
      level++;
    }
    return -1;
  }

  List<String> adjucents(String s, String B, Set<String> seen) {
    int dif = 0;
    char[] sStrAsChars = s.toCharArray();
    for (; dif < sStrAsChars.length; dif++) {
      if (sStrAsChars[dif] != B.charAt(dif))
        break;
    }
    List<String> adjs = new ArrayList();

    for (int j = dif + 1; j < s.length(); j++) {
      if (sStrAsChars[j] == B.charAt(dif)) {
        swap(sStrAsChars, j, dif);
        String newWord = new String(sStrAsChars);
        if (!seen.contains(newWord))
          adjs.add(newWord);
        swap(sStrAsChars, j, dif);
      }
    }
    return adjs;
  }

  void swap(char[] strChar, int i, int j) {
    char tmp = strChar[i];
    strChar[i] = strChar[j];
    strChar[j] = tmp;
  }
}
