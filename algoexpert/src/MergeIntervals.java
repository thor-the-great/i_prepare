import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Mobile Game Range Module - Merging Ranges New
 *     Sorting Algorithms Arrays Numbers
 *
 * A Range Module is a module that tracks ranges of numbers. Range modules are used extensively
 * when designing scalable online game maps with millions of players. Your task is to write a
 * method - mergeIntervals that takes in an ArrayList of integer Interval s (aka ranges), and
 * returns an ArrayList of sorted Interval s where all overlapping intervals have been merged.
 * The Interval class is available by clicking Use Me.
 *
 * Note:
 * a) [1,3] represents an interval that includes 1, 2 and 3.
 * b) Intervals should be sorted based on the value of start
 *
 * Examples:
 * Input: [ [1,3], [2,5] ], Output: [ [1,5] ]
 * Input: [ [3,5], [1,2] ], Output: [ [1,2], [3,5] ]
 */
public class MergeIntervals {

  /**
   * Sort by the start first. Then iterate and compare current end with the next start. If start is
   * earlier (smaller) - we can merge this interval, otherwise - add the current one to the result
   * and keep next as a new current
   *
   * @param intervalsList
   * @return
   */
  public static ArrayList<Interval> mergeIntervals(ArrayList<Interval> intervalsList) {
    ArrayList<Interval> res = new ArrayList();
    if (intervalsList == null || intervalsList.isEmpty())
      return res;

    Comparator<Interval> comp = Comparator.comparingInt(i -> i.start);
    Collections.sort(intervalsList,  comp);

    Interval interval = intervalsList.get(0);

    for (int i = 1; i < intervalsList.size(); i++) {
      Interval next = intervalsList.get(i);
      //merging with current
      if (next.start <= interval.end) {
        interval.end = Math.max(next.end, interval.end);
      }
      //no merging
      else {
        res.add(interval);
        interval = next;
      }
    }
    res.add(interval);
    return res;
  }
}

class Interval {
  int start;
  int end;
  Interval(int start, int end) {
    this.start = start;
    this.end = end;
  }
}
