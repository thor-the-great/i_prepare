import java.util.HashMap;
import java.util.Map;

/**
 * Copyright (c) 2019 Ariba, Inc. All rights reserved. Patents pending.
 */

public class CoupleSum {

  public static int[] coupleSum(int[] numbers, int target) {
    Map<Integer, Integer> m = new HashMap();
    int[] res = new int[2];

    for (int i = 0; i < numbers.length; i++) {
      int compl = target - numbers[i];

      if (m.containsKey(compl)) {
        res[0] = m.get(compl) + 1;
        res[1] = i + 1;
        break;
      }

      m.put(numbers[i], i);
    }

    return res;
  }
}
