/**
 * Given an array of integers, write a method - maxGain - that returns the maximum gain.
 * Maximum Gain is defined as the maximum difference between 2 elements in a list such that the
 * larger element appears after the smaller element. If no gain is possible, return 0.
 */
public class MaxGain {

  /**
   * Idea - because requirements are that max element will be after the min we can do left to right
   * one scan. Keep running max gain and the min element. For every next element check if the element
   * is lower than a min and if the gain (diff between current element and min) is higher than a
   * running max gain
   * @param a
   * @return
   */
  public static int maxGain(int[] a) {

    int min = a[0];
    int cur = a[1] - a[0];
    for (int i = 2; i < a.length; i++) {
      if (a[i] < min)
        min = a[i];

      int g = a[i] - min;
      if (g > cur)
        cur = g;
    }

    return cur;
  }

}
