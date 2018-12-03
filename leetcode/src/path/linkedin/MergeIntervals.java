package path.linkedin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 56. Merge Intervals
 * Medium
 *
 *
 * Given a collection of intervals, merge all overlapping intervals.
 *
 * Example 1:
 *
 * Input: [[1,3],[2,6],[8,10],[15,18]]
 * Output: [[1,6],[8,10],[15,18]]
 * Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
 * Example 2:
 *
 * Input: [[1,4],[4,5]]
 * Output: [[1,5]]
 * Explanation: Intervals [1,4] and [4,5] are considered overlapping.
 *
 */
public class MergeIntervals {

    /**
     * Idea is - sort intervals by start, then start to compare. If overlapping - change end (catch - keep max between
     * current and previous).
     *
     * @param intervals
     * @return
     */
    public List<Interval> merge(List<Interval> intervals) {
        if (intervals.isEmpty())
            return intervals;
        Collections.sort(intervals, Comparator.comparingInt(o -> o.start));
        List<Interval> res = new ArrayList();
        Interval newInt = null;
        for (Interval next : intervals) {
            if (newInt == null) {
                newInt = new Interval(next.start, next.end);
                continue;
            }
            if (newInt.end < next.start) {
                res.add(newInt);
                newInt = new Interval(next.start, next.end);
            } else {
                newInt.end = Math.max(next.end, newInt.end);
            }
        }
        res.add(newInt);
        return res;
    }

     class Interval {
        int start;
        int end;
        Interval() { start = 0; end = 0; }
        Interval(int s, int e) { start = s; end = e; }
  }
}
