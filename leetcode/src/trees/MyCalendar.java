
package trees;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * 729. My Calendar I
 * Medium
 *
 * Implement a MyCalendar class to store your events. A new event can be added if adding the
 * event will not cause a double booking.
 *
 * Your class will have the method, book(int start, int end). Formally, this represents a booking
 * on the half open interval [start, end), the range of real numbers x such that start <= x < end.
 *
 * A double booking happens when two events have some non-empty intersection (ie., there is some
 * time that is common to both events.)
 *
 * For each call to the method MyCalendar.book, return true if the event can be added to the
 * calendar successfully without causing a double booking. Otherwise, return false and do not add
 * the event to the calendar.
 *
 * Your class will be called like this: MyCalendar cal = new MyCalendar(); MyCalendar.book(start,
 * end)
 * Example 1:
 *
 * MyCalendar();
 * MyCalendar.book(10, 20); // returns true
 * MyCalendar.book(15, 25); // returns false
 * MyCalendar.book(20, 30); // returns true
 * Explanation:
 * The first event can be booked.  The second can't because time 15 is already booked by another
 * event.
 * The third event can be booked, as the first event takes every time less than 20, but not
 * including 20.
 *
 *
 * Note:
 *
 * The number of calls to MyCalendar.book per test case will be at most 1000.
 * In calls to MyCalendar.book(start, end), start and end are integers in the range [0, 10^9].
 */

/**
 * Approach 1 - store intervals as int[] in the list. Search all pairs from the list, if found overlap
 * just return false; if no overlaps found - add new pair to the list
 *
 * Approach 2 - use sorted tree, keep sorted as per start point, then search two closest to start,
 * check end of first and start of a second
 */
public class MyCalendar {
  List<int[]> intervals;

  public MyCalendar() {
    intervals = new ArrayList();
  }

  public boolean book(int start, int end) {
    for (int[] interval : intervals) {
      if (interval[0] < end && interval[1] > start) {
        return false;
      }
    }
    intervals.add(new int[]{start, end});
    return true;
  }

  TreeMap<Integer, Integer> intervalsTree;

  public void MyCalendarTree() {
    intervalsTree = new TreeMap();
  }

  public boolean bookTree(int start, int end) {
    Integer lower = intervalsTree.floorKey(start);
    Integer higher = intervalsTree.ceilingKey(start);

    if ((lower == null || intervalsTree.get(lower) <= start)
        && (higher == null || higher >= end)) {
      intervalsTree.put(start, end);
      return true;
    }

    return false;
  }
}
