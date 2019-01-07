package random_problems;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 621. Task Scheduler
 * Medium
 *
 * Given a char array representing tasks CPU need to do. It contains capital letters A to Z where different letters
 * represent different tasks. Tasks could be done without original order. Each task could be done in one interval.
 * For each interval, CPU could finish one task or just be idle.
 *
 * However, there is a non-negative cooling interval n that means between two same tasks, there must be at least n
 * intervals that CPU are doing different tasks or just be idle.
 *
 * You need to return the least number of intervals the CPU will take to finish all the given tasks.
 *
 *
 *
 * Example:
 *
 * Input: tasks = ["A","A","A","B","B","B"], n = 2
 * Output: 8
 * Explanation: A -> B -> idle -> A -> B -> idle -> A -> B.
 *
 *
 * Note:
 *
 * The number of tasks is in the range [1, 10000].
 * The integer n is in the range [0, 100].
 */
public class TaskScheduler {

    /**
     * Idea : use PQ to keep order of most expensive tasks. Optimal way to order tasks is to insert most
     * expensive and then insert others until reach n
     * O(n) - number of iterations
     * O(1) - PQ will be constant proportional to 26
     * @param tasks
     * @param n
     * @return
     */
    public int leastInterval(char[] tasks, int n) {
        int[] counts = new int[26];
        for (char task : tasks) {
            counts[task - 'A'] += 1;
        }
        PriorityQueue<Integer> pq = new PriorityQueue(Collections.reverseOrder());
        for (int c : counts)
            if (c > 0)
                pq.add(c);

        int t = 0;
        int i = 0;
        List<Integer> list = new ArrayList();
        while (!pq.isEmpty()) {
            i = 0;
            list.clear();
            while(i <= n) {
                if (!pq.isEmpty()) {
                    int max = pq.poll();
                    if (max > 1)
                        list.add(max - 1);
                }
                t++;
                if (list.isEmpty() && pq.isEmpty())
                    break;
                i++;
            }
            for(int retTask : list)
                pq.add(retTask);
        }
        return t;
    }
}
