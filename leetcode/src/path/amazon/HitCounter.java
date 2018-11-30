package path.amazon;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Idea is to save on object for every timestamp value, so there will be not more than 300 objects max in the queue.
 */
public class HitCounter {

    Deque<int[]> q;
    int maxTTL = 300;
    int total;

    /**
     * Initialize your data structure here.
     */
    public HitCounter() {
        q = new LinkedList();
        total = 0;
    }

    /**
     * Record a hit.
     *
     * @param timestamp - The current timestamp (in seconds granularity).
     */
    public void hit(int timestamp) {
        if (!q.isEmpty() && q.peekLast()[0] == timestamp) {
            q.peekLast()[1] += 1;
        } else {
            q.addLast(new int[]{timestamp, 1});
        }
        total++;
        int min = timestamp - maxTTL;
        while (!q.isEmpty() && q.peekFirst()[0] < min) {
            total -= q.pollFirst()[1];
        }
    }

    /**
     * Return the number of hits in the past 5 minutes.
     *
     * @param timestamp - The current timestamp (in seconds granularity).
     */
    public int getHits(int timestamp) {
        int min = timestamp - maxTTL;
        while (!q.isEmpty() && q.peekFirst()[0] <= min) {
            total -= q.pollFirst()[1];
        }
        return total;
    }
}
