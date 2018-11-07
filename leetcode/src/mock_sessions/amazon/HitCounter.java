package mock_sessions.amazon;

import java.util.Deque;
import java.util.LinkedList;

public class HitCounter {
    Deque<Integer> q;
    int maxTTL = 300;

    /** Initialize your data structure here. */
    public HitCounter() {
        q = new LinkedList();
    }

    /** Record a hit.
     @param timestamp - The current timestamp (in seconds granularity). */
    public void hit(int timestamp) {
        int min = timestamp - maxTTL;
        while (!q.isEmpty() && q.peekFirst() < min) {
            q.pollFirst();
        }
        q.addLast(timestamp);
    }

    /** Return the number of hits in the past 5 minutes.
     @param timestamp - The current timestamp (in seconds granularity). */
    public int getHits(int timestamp) {
        int min = timestamp - maxTTL;
        while (!q.isEmpty() && q.peekFirst() <= min) {
            q.pollFirst();
        }
        return q.size();
    }
}
