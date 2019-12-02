package random_problems;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 1086. High Five
 * Easy
 *
 * Given a list of scores of different students, return the average score of each student's top five scores in the order of each student's id.
 *
 * Each entry items[i] has items[i][0] the student's id, and items[i][1] the student's score.  The average score is calculated using integer division.
 *
 *
 *
 * Example 1:
 *
 * Input: [[1,91],[1,92],[2,93],[2,97],[1,60],[2,77],[1,65],[1,87],[1,100],[2,100],[2,76]]
 * Output: [[1,87],[2,88]]
 * Explanation:
 * The average of the student with id = 1 is 87.
 * The average of the student with id = 2 is 88.6. But with integer division their average converts to 88.
 *
 *
 * Note:
 *
 * 1 <= items.length <= 1000
 * items[i].length == 2
 * The IDs of the students is between 1 to 1000
 * The score of the students is between 1 to 100
 * For each student, there are at least 5 scores
 */
public class HighFive {

    /**
     * Use PQ to collect 5 best marks - if size > 5 poll the min element.
     * After all marks are collected - count avg of elements from each PQ
     * @param items
     * @return
     */
    public int[][] highFive(int[][] items) {
        List<PriorityQueue<Integer>> m = new ArrayList();
        //collect marks for each student
        for (int[] item : items) {
            if (m.size() < item[0]) {
                m.add(new PriorityQueue<Integer>());
            }
            PriorityQueue pq = m.get(item[0] - 1);
            pq.offer(item[1]);
            //if we have more than 5 marks - throw away the min one
            if (pq.size() > 5)
                pq.poll();
        }

        int[][] res = new int[m.size()][2];
        //for each pq (each student) calculate avg
        for (int i = 0; i < m.size(); i++) {
            PriorityQueue<Integer> pq = m.get(i);
            int c = pq.size(), sum = 0;
            while (!pq.isEmpty()) {
                sum += pq.poll();
            }
            res[i] = new int[]{i + 1, sum/c};
        }
        return res;
    }
}
