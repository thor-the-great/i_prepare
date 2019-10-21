package arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 1229. Meeting Scheduler
 * Difficulty: Medium
 * Given the availability time slots arrays slots1 and slots2 of two people and a meeting duration duration, return the
 * earliest time slot that works for both of them and is of duration duration.
 *
 * If there is no common time slot that satisfies the requirements, return an empty array.
 *
 * The format of a time slot is an array of two elements [start, end] representing an inclusive time range from start
 * to end.
 *
 * It is guaranteed that no two availability slots of the same person intersect with each other. That is, for any two
 * time slots [start1, end1] and [start2, end2] of the same person, either start1 > end2 or start2 > end1.
 *
 * Example 1:
 *
 * Input: slots1 = [[10,50],[60,120],[140,210]], slots2 = [[0,15],[60,70]], duration = 8
 * Output: [60,68]
 * Example 2:
 *
 * Input: slots1 = [[10,50],[60,120],[140,210]], slots2 = [[0,15],[60,70]], duration = 12
 * Output: []
 *
 *
 * Constraints:
 *
 * 1 <= slots1.length, slots2.length <= 10^4
 * slots1[i].length, slots2[i].length == 2
 * slots1[i][0] < slots1[i][1]
 * slots2[i][0] < slots2[i][1]
 * 0 <= slots1[i][j], slots2[i][j] <= 10^9
 * 1 <= duration <= 10^6
 */
public class MeetingScheduler {

    /**
     * Sort by start time, then scan. Check for intersection, if no - advance one that ends sooner
     * @param slots1
     * @param slots2
     * @param duration
     * @return
     */
    public List<Integer> minAvailableDuration(int[][] slots1, int[][] slots2, int duration) {
        List<Integer> res = new ArrayList();
        //sort both arrays as per starting time of every interval
        Comparator<int[]> comp = Comparator.comparingInt(i->i[0]);
        Arrays.sort(slots1, comp);
        Arrays.sort(slots2, comp);
        //start scanning, keep pointer of every slot array
        int p1 = 0, p2 = 0;
        while (p1 < slots1.length && p2 < slots2.length) {
            int[] slot1 = slots1[p1];
            int[] slot2 = slots2[p2];
            //check if there is intersection
            int start = Math.max(slot1[0], slot2[0]);
            int end = Math.min(slot1[1], slot2[1]);
            //if intersection if long enough - this is our answer
            if (end - start >= duration) {
                res.add(start);
                res.add(start + duration);
                break;
            }
            //if no good intersection - move to the next slot in array that has the slot that
            //ends earlier
            if (slot1[1] < slot2[1])
                p1++;
            else
                p2++;
        }

        return res;
    }
}
