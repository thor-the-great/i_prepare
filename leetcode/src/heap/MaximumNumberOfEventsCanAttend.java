package heap;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 1353. Maximum Number of Events That Can Be Attended
 * Medium
 *
 * Given an array of events where events[i] = [startDayi, endDayi]. Every event i starts at
 * startDayi and ends at endDayi.
 *
 * You can attend an event i at any day d where startTimei <= d <= endTimei. Notice that you can
 * only attend one event at any time d.
 *
 * Return the maximum number of events you can attend.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: events = [[1,2],[2,3],[3,4]]
 * Output: 3
 * Explanation: You can attend all the three events.
 * One way to attend them all is as shown.
 * Attend the first event on day 1.
 * Attend the second event on day 2.
 * Attend the third event on day 3.
 * Example 2:
 *
 * Input: events= [[1,2],[2,3],[3,4],[1,2]]
 * Output: 4
 * Example 3:
 *
 * Input: events = [[1,4],[4,4],[2,2],[3,4],[1,1]]
 * Output: 4
 * Example 4:
 *
 * Input: events = [[1,100000]]
 * Output: 1
 * Example 5:
 *
 * Input: events = [[1,1],[1,2],[1,3],[1,4],[1,5],[1,6],[1,7]]
 * Output: 7
 *
 *
 * Constraints:
 *
 * 1 <= events.length <= 10^5
 * events[i].length == 2
 * 1 <= events[i][0] <= events[i][1] <= 10^5
 */
public class MaximumNumberOfEventsCanAttend {

  /**
   * problem constrain is - days 1...100_000. We can iterate over every day, sort the array of
   * events by start date. For each day:
   * - find all events that start at that day, add the end day of the event to the pq
   * - remove events that are finished before this day (by checking pq.peekk and pq.poll)
   * - at the end for each day attend one event
   * @param events
   * @return
   */
  public int maxEvents(int[][] events) {
    if (events == null || events.length == 0)
      return 0;

    Arrays.sort(events, (a1, a2) -> a1[0] - a2[0]);

    PriorityQueue<Integer> pq = new PriorityQueue();

    int res = 0, idx = 0;
    for (int day = 1; day <= 100_000; day++) {
      while (!pq.isEmpty() && pq.peek() < day)
        pq.poll();

      while (idx < events.length && events[idx][0] == day)
        pq.add(events[idx++][1]);

      if (!pq.isEmpty()) {
        pq.poll();
        ++res;
      }
    }
    return res;
  }
}
