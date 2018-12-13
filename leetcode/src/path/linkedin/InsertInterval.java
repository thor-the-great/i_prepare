package path.linkedin;

import util.Interval;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 57. Insert Interval
 * Hard
 * Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).
 *
 * You may assume that the intervals were initially sorted according to their start times.
 *
 * Example 1:
 *
 * Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
 * Output: [[1,5],[6,9]]
 * Example 2:
 *
 * Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 * Output: [[1,2],[3,10],[12,16]]
 * Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].
 */
public class InsertInterval {
    /**
     * Idea is - find the last interval that starts before our new. Then leave everything that is before, do the
     * merge, then add the rest
     * @param intervals
     * @param newInterval
     * @return
     */
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        int N = intervals.size();
        if (N == 0) {
            intervals.add(newInterval);
            return intervals;
        }

        int i = -1;
        while(i < N - 1 && intervals.get(i + 1).start <= newInterval.start) {
            i++;
        }
        List<Interval> res = new LinkedList();
        for (int j = 0; j < i; j++)
            res.add(intervals.get(j));
        int start = newInterval.start;
        if (i >= 0 ) {
            if (intervals.get(i).end < newInterval.start)
                res.add(intervals.get(i));
            else
                start = intervals.get(i).start;
        }
        int end = newInterval.end;
        if ( i < 0) i = 0;
        if (newInterval.end >= intervals.get(i).start) {
            end = intervals.get(i).end;
            while(i < N && (intervals.get(i).end <= newInterval.end || intervals.get(i).start <= newInterval.end )) {
                end = intervals.get(i).end;
                i++;
            }
        }
        Interval mergedInterval = new Interval(start, Math.max(end, newInterval.end));
        res.add(mergedInterval);
        for (int j = i; j < N; j++)
            res.add(intervals.get(j));
        return res;
    }

    public static void main(String[] args) {
        InsertInterval obj = new InsertInterval();
        Interval[] intervals;
        List<Interval> list;
        List<Interval> res;
        intervals = new Interval[] {
                new Interval(1,2),
                new Interval(3,5),
                new Interval(6,7),
                new Interval(8,10),
                new Interval(12,16)
        };
        list = Arrays.asList(intervals);
        res = obj.insert(list, new Interval(4, 8));
        System.out.println("inserted intervals : ");
        res.forEach(i->System.out.print("[" + i.start + "," + i.end + "] "));

        intervals = new Interval[] {
                new Interval(1,5)
        };
        list = Arrays.asList(intervals);
        res = obj.insert(list, new Interval(0, 1));
        System.out.println("inserted intervals : ");
        res.forEach(i->System.out.print("[" + i.start + "," + i.end + "] "));
    }
}
