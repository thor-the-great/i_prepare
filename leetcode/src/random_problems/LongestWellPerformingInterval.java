package random_problems;

import java.util.HashMap;
import java.util.Map;

public class LongestWellPerformingInterval {
  public int longestWPI(int[] hours) {
    Map<Integer, Integer> m = new HashMap();
    int cur = 0;
    int res = 0;

    for (int i = 0; i < hours.length; i++) {
      cur += (hours[i] > 8) ? 1 : -1;
      if (cur > 0) {
        res = i + 1;
      } else {
        if (!m.containsKey(cur)) {
          m.put(cur, i);
        }
        if (m.containsKey(cur - 1)) {
          res = Math.max(res, i - m.get(cur - 1));
        }
      }
    }

    return res;
  }
}
