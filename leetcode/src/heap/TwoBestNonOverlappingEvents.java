package heap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 2054. Two Best Non-Overlapping Events
Medium

You are given a 0-indexed 2D integer array of events where events[i] = [startTimei, endTimei, valuei]. The ith event 
starts at startTimei and ends at endTimei, and if you attend this event, you will receive a value of valuei. You can 
choose at most two non-overlapping events to attend such that the sum of their values is maximized.

Return this maximum sum.

Note that the start time and end time is inclusive: that is, you cannot attend two events where one of them starts and 
the other ends at the same time. More specifically, if you attend an event with end time t, the next event must start 
at or after t + 1.

Example 1:

Input: events = [[1,3,2],[4,5,2],[2,4,3]]
Output: 4
Explanation: Choose the green events, 0 and 1 for a sum of 2 + 2 = 4.

Example 2:
Example 1 Diagram

Input: events = [[1,3,2],[4,5,2],[1,5,5]]
Output: 5
Explanation: Choose event 2 for a sum of 5.

Example 3:

Input: events = [[1,5,3],[1,5,1],[6,6,5]]
Output: 8
Explanation: Choose events 0 and 2 for a sum of 3 + 5 = 8.

 

Constraints:

    2 <= events.length <= 105
    events[i].length == 3
    1 <= startTimei <= endTimei <= 109
    1 <= valuei <= 106

Accepted
2,657
Submissions

https://leetcode.com/problems/two-best-non-overlapping-events/
 */
public class TwoBestNonOverlappingEvents {
    
    /**
     * sort by start time first. Keep min heap with events and durations. On each next event check if min heap
     * all events that could end before this one start, update that max duration. then sum duration of current event with
     * best max of all previous ones. Update running max
     */
    public int maxTwoEvents(int[][] events) {
        int res = 0;
        Comparator<int[]> comp = (a1, a2) -> a1[0] == a2[0] ? a1[1] - a2[1] : a1[0] - a2[0];
        Arrays.sort(events, comp);
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((a1, a2) -> a1[0] - a2[0]);
        int maxDur = 0;
        for (int[] ev : events) {
            //for current interval - check all events that finished before this one's start, update max duration
            while (!pq.isEmpty() && pq.peek()[0] < ev[0]) {
                maxDur = Math.max(maxDur, pq.poll()[1]);
            }
            //now combine current and max of all previous ones
            res = Math.max(res, maxDur + ev[2]);
            //add current event to the pq
            pq.add(new int[] {ev[1], ev[2]});
        }
        
        return res;
    }
}
