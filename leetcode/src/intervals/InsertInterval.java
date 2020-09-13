package intervals;

import util.Interval;

import java.util.ArrayList;
import java.util.List;

/**
 * Insert Interval (medium)
 *
 * Given a list of non-overlapping intervals sorted by their start time, insert a given interval at
 * the correct position and merge all necessary intervals to produce a list that has only mutually
 * exclusive intervals.
 *
 * Example 1:
 *
 * Input: Intervals=[[1,3], [5,7], [8,12]], New Interval=[4,6]
 * Output: [[1,3], [4,7], [8,12]]
 * Explanation: After insertion, since [4,6] overlaps with [5,7], we merged them into one [4,7].
 * Example 2:
 *
 * Input: Intervals=[[1,3], [5,7], [8,12]], New Interval=[4,10]
 * Output: [[1,3], [4,12]]
 * Explanation: After insertion, since [4,10] overlaps with [5,7] & [8,12], we merged them into [4,12].
 * Example 3:
 *
 * Input: Intervals=[[2,3],[5,7]], New Interval=[1,4]
 * Output: [[1,4], [5,7]]
 * Explanation: After insertion, since [1,4] overlaps with [2,3], we merged them into one [1,4].
 *
 */
public class InsertInterval {

  /**
   * First add intervals before the newInterval, then merge overlapping ones and conclude with those that
   * are after the newInterval
   * @param intervals
   * @param newInterval
   * @return
   */
  public static List<Interval> insert(List<Interval> intervals, Interval newInterval) {
    List<Interval> mergedIntervals = new ArrayList<>();

    if (intervals == null || intervals.isEmpty())
      return mergedIntervals;

    int p = 0;
    while(p < intervals.size() && intervals.get(p).end < newInterval.start) {
      mergedIntervals.add(intervals.get(p++));
    }

    while(p < intervals.size() && intervals.get(p).start <= newInterval.end) {
      newInterval.start = Math.min(newInterval.start, intervals.get(p).start);
      newInterval.end = Math.max(newInterval.end, intervals.get(p).end);
      ++p;
    }
    mergedIntervals.add(newInterval);

    while(p < intervals.size()) {
      mergedIntervals.add(intervals.get(p++));
    }

    return mergedIntervals;
  }

  /**
   * Same algorithm but just for 2D array
   * @param intervals
   * @param newInterval
   * @return
   */
  public int[][] insert(int[][] intervals, int[] newInterval) {
    List<int[]> list = new ArrayList();
    int p = 0, N = intervals.length;

    while (p < N && intervals[p][1] < newInterval[0]) {
      list.add(intervals[p++]);
    }

    if (p == N) {
      list.add(newInterval);
    } else {
      while (p < N && intervals[p][0] <= newInterval[1]) {
        newInterval[0] = Math.min(newInterval[0], intervals[p][0]);
        newInterval[1] = Math.max(newInterval[1], intervals[p][1]);
        p++;
      }
      list.add(newInterval);

      while (p < N) {
        list.add(intervals[p++]);
      }
    }

    int[][] res = new int[list.size()][2];
    for (int i = 0; i < list.size(); i++) {
      res[i] = list.get(i);
    }
    return res;
  }
}
