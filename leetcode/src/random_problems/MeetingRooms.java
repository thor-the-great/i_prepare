package random_problems;

import util.Interval;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 252. Meeting Rooms
 * Easy
 *
 * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...]
 * (si < ei), determine if a person could attend all meetings.
 *
 * Example 1:
 *
 * Input: [[0,30],[5,10],[15,20]]
 * Output: false
 * Example 2:
 *
 * Input: [[7,10],[2,4]]
 * Output: true
 *
 */
public class MeetingRooms {

    /**
     * Idea - sort by start date, check if current end is < previous start
     * @param intervals
     * @return
     */
    public boolean canAttendMeetings(Interval[] intervals) {
        if (intervals == null || intervals.length <= 1)
            return true;

        Comparator<Interval> comp = (Interval v1, Interval v2) -> (Integer.compare(v1.start, v2.start));

        Arrays.sort(intervals, comp);
        int prevEnd = intervals[0].end;
        for (int i = 1; i < intervals.length; i++) {
            Interval interval = intervals[i];
            if (interval.start >= prevEnd) {
                prevEnd = interval.end;
            }
            else
                return false;
        }

        return true;
    }
}
