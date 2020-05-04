package arrays;

public class MaximumScoreAfterSplittingString {

  /**
   * Count all the ones, assume that all are to the right
   * Start iterating over the chars every time when we met 0 - add zero to left count,
   * if 1 - decrease right count
   * @param s
   * @return
   */
  public int maxScore(String s) {
    int cur = 0;
    for (char ch : s.toCharArray()) {
      if (ch == '1')
        ++cur;
    }

    int max = 0;
    for (int i = 0; i < s.length() - 1; i++) {
      if (s.charAt(i) == '0') {
        ++cur;
      } else {
        --cur;
      }
      max = Math.max(max, cur);
    }
    return max;
  }
}
