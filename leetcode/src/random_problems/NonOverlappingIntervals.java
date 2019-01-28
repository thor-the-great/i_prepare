package random_problems;

import util.Interval;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 435. Non-overlapping Intervals
 * Medium
 *
 * Given a collection of intervals, find the minimum number of intervals you need to remove to make the rest of the
 * intervals non-overlapping.
 *
 * Note:
 * You may assume the interval's end point is always bigger than its start point.
 * Intervals like [1,2] and [2,3] have borders "touching" but they don't overlap each other.
 * Example 1:
 * Input: [ [1,2], [2,3], [3,4], [1,3] ]
 *
 * Output: 1
 *
 * Explanation: [1,3] can be removed and the rest of intervals are non-overlapping.
 * Example 2:
 * Input: [ [1,2], [1,2], [1,2] ]
 *
 * Output: 2
 *
 * Explanation: You need to remove two [1,2] to make the rest of intervals non-overlapping.
 * Example 3:
 * Input: [ [1,2], [2,3] ]
 *
 * Output: 0
 *
 * Explanation: You don't need to remove any of the intervals since they're already non-overlapping.
 */
public class NonOverlappingIntervals {

    /**
     * Idea: Greedy approach, sort first based on start point, then analyse. Save previous interval. There are 3 cases
     * : either they are not overlapping - just take next interval as previous. If they are overlap - we definitely
     * need to remove one interval. Just pick whichever ends earlier - this will allow to keep more next intervals
     *
     * @param intervals
     * @return
     */
    public int eraseOverlapIntervals(Interval[] intervals) {
        int N = intervals.length;
        if (N <= 1)
            return 0;
        int res = 0;
        Arrays.sort(intervals, new Comparator<Interval>() {
            public int compare(Interval a, Interval b) {
                return a.start - b.start;
            }});

        Interval prev = intervals[0];
        int prevEnd = prev.end;
        for (int i = 1; i < N; i++) {
            Interval next = intervals[i];
            if (prevEnd <= next.start) {
                prevEnd = next.end;
                continue;
            } else {
                if (next.end <= prevEnd) {
                    prevEnd = next.end;
                }
                res++;
            }
        }
        return res;
    }
}
