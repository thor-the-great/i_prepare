import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Copyright (c) 2019 Ariba, Inc. All rights reserved. Patents pending.
 */

public class LargestRange {

  public static int[] largestRange(int[] array) {
    Set<Integer> set = new HashSet();
    int[] res = new int[2];
    int l = 0;
    for (int num : array) {
      set.add(num);
    }

    for (int num : array) {
      if (!set.contains(num - 1)) {
        //this is the left edge of the set
        int i = num + 1;
        while (set.contains(i)) {
          i++;
        }

        if ((i - num) > l) {
          res = new int[] {num, i - 1};
          l = i - num;
        }
      }
    }
    return res;
  }

  public static void main(String[] args) {
    System.out.println(Arrays.toString(LargestRange.largestRange(new int[] {1, 11, 3, 0, 15, 5, 2, 4, 10, 7, 12, 6})));

    System.out.println(Arrays.toString(LargestRange.largestRange(new int[] {2, 3, 4, 5, 6})));
  }
}
