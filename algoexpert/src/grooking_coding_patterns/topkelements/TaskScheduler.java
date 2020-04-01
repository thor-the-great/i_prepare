package grooking_coding_patterns.topkelements;

import java.util.*;

/**
 * Problem Challenge 2
 *
 * Scheduling Tasks (hard) #
 * You are given a list of tasks that need to be run, in any order, on a server. Each task will take
 * one CPU interval to execute but once a task has finished, it has a cooling period during which it
 * can’t be run again. If the cooling period for all tasks is ‘K’ intervals, find the minimum number
 * of CPU intervals that the server needs to finish all tasks.
 *
 * If at any time the server can’t execute any task then it must stay idle.
 *
 * Example 1:
 *
 * Input: [a, a, a, b, c, c], K=2
 * Output: 7
 * Explanation: a -> c -> b -> a -> c -> idle -> a
 * Example 2:
 *
 * Input: [a, b, a], K=3
 * Output: 5
 * Explanation: a -> b -> idle -> idle -> a
 */
public class TaskScheduler {

  /**
   * Idea - create task->freq map, put entries to the priorityQueue. Start popping from the pq, decrement
   * qty and put to the queue of length at least k. If queue is longer than k - pop from queue and add to the
   * pq - there is no penalty for this. If pq is empty and queue size is < k - pay penalty of k - queue.size()
   * (this is idle cycles)
   * @param tasks
   * @param k
   * @return
   */
  public static int scheduleTasks(char[] tasks, int k) {
    Map<Character, Integer> map = new HashMap();

    for (char ch : tasks) map.put(ch, map.getOrDefault(ch, 0) + 1);

    PriorityQueue<Map.Entry<Character,Integer>> pq = new PriorityQueue<>(
            (e1, e2) -> e2.getValue() - e1.getValue());
    pq.addAll(map.entrySet());
    Queue<Map.Entry<Character,Integer>> queue = new LinkedList();
    int res = 0;
    while(!pq.isEmpty()) {
      Map.Entry<Character,Integer> cur = pq.poll();
      ++res;
      cur.setValue(cur.getValue() - 1);
      queue.add(cur);

      if( queue.size() <= k && pq.isEmpty()) {
        res+= (k - queue.size());
      }
      Map.Entry<Character,Integer> queued = queue.poll();
      if (queued.getValue() > 0) {
        pq.add(queued);
      }
    }

    return res;
  }

  public static void main(String[] args) {
    char[] tasks = new char[] { 'a', 'a', 'a', 'b', 'c', 'c' };
    System.out.println("Minimum intervals needed to execute all tasks: " + TaskScheduler.scheduleTasks(tasks, 2));

    tasks = new char[] { 'a', 'b', 'a' };
    System.out.println("Minimum intervals needed to execute all tasks: " + TaskScheduler.scheduleTasks(tasks, 3));
  }
}
