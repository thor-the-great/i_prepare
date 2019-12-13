package intervals;

import java.util.ArrayList;
import java.util.List;

/**
 * 1272. Remove Interval
 * Medium
 *
 * Given a sorted list of disjoint intervals, each interval intervals[i] = [a, b] represents the set of real numbers
 * x such that a <= x < b.
 *
 * We remove the intersections between any interval in intervals and the interval toBeRemoved.
 *
 * Return a sorted list of intervals after all such removals.
 *
 *
 *
 * Example 1:
 *
 * Input: intervals = [[0,2],[3,4],[5,7]], toBeRemoved = [1,6]
 * Output: [[0,1],[6,7]]
 * Example 2:
 *
 * Input: intervals = [[0,5]], toBeRemoved = [2,3]
 * Output: [[0,2],[3,5]]
 *
 *
 * Constraints:
 *
 * 1 <= intervals.length <= 10^4
 * -10^9 <= intervals[i][0] < intervals[i][1] <= 10^9
 */
public class RemoveInterval {

    /**
     * Iterate over the intervals, if there are no overlap - just add interval directly to result. If there is an
     * overlap - three options are possible - it overlaps partially from the left, overlap completely and overlap
     * from the right side. In first and latest case we need to do a merge. Check whichever end is earlier or start
     * is later and create a new interval. Catch is - for one interval we may have two overlaps - like [2,6] and
     * [3,4] results in [2,3] and [4,6].
     *
     * O(n) time - one pass over the intervals, O(1) space - no extra memory except for reslting list
     * @param intervals
     * @param toBeRemoved
     * @return
     */
    public List<List<Integer>> removeInterval(int[][] intervals, int[] toBeRemoved) {
        List<List<Integer>> res = new ArrayList();

        for (int[] interval : intervals) {
            if (interval[1] < toBeRemoved[0] || interval[0] > toBeRemoved[1]) {
                add(interval[0], interval[1], res);
                continue;
            }
            if (interval[1] > toBeRemoved[0] && interval[0] < toBeRemoved[0]) {
                add(interval[0], toBeRemoved[0], res);
            }
            if (interval[0] < toBeRemoved[1] && toBeRemoved[1] < interval[1]) {
                add(toBeRemoved[1], interval[1], res);
            }
        }
        return res;
    }

    void add(int start, int end, List<List<Integer>> res) {
        List<Integer> list = new ArrayList();
        list.add(start);
        list.add(end);
        res.add(list);
    }
}
