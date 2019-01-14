package random_problems;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 857. Minimum Cost to Hire K Workers
 * Hard
 *
 * There are N workers.  The i-th worker has a quality[i] and a minimum wage expectation wage[i].
 *
 * Now we want to hire exactly K workers to form a paid group.  When hiring a group of K workers, we must pay them
 * according to the following rules:
 *
 * Every worker in the paid group should be paid in the ratio of their quality compared to other workers in the paid
 * group.
 * Every worker in the paid group must be paid at least their minimum wage expectation.
 * Return the least amount of money needed to form a paid group satisfying the above conditions.
 *
 *
 *
 * Example 1:
 *
 * Input: quality = [10,20,5], wage = [70,50,30], K = 2
 * Output: 105.00000
 * Explanation: We pay 70 to 0-th worker and 35 to 2-th worker.
 * Example 2:
 *
 * Input: quality = [3,1,10,10,1], wage = [4,8,2,2,7], K = 3
 * Output: 30.66667
 * Explanation: We pay 4 to 0-th worker, 13.33333 to 2-th and 3-th workers seperately.
 *
 *
 * Note:
 *
 * 1 <= K <= N <= 10000, where N = quality.length = wage.length
 * 1 <= quality[i] <= 10000
 * 1 <= wage[i] <= 10000
 * Answers within 10^-5 of the correct answer will be considered correct.
 */
public class MinCostHireKWorkers {

    /**
     * Idea: sort workers by rate - wage/quality. Then iterating over workers in that order use MaxHeap to get next
     * quality, and keep the sum of K qualities. When reach K - calculate sum by qualities*rate, keep running min.
     * @param quality
     * @param wage
     * @param K
     * @return
     */
    public double mincostToHireWorkers(int[] quality, int[] wage, int K) {
        double res = 1e9;

        Worker[] w = new Worker[wage.length];
        for (int i = 0; i < wage.length; i++) {
            Worker worker = new Worker(quality[i], wage[i]);
            w[i] = worker;
        }
        Arrays.sort(w, new Comparator<Worker>() {
            @Override
            public int compare(Worker o1, Worker o2) {
                return Double.compare(o1.rate, o2.rate);
            }
        });

        PriorityQueue<Integer> pq = new PriorityQueue();
        int qSum = 0;
        for (Worker worker : w) {
            qSum += worker.q;
            pq.add(-worker.q);
            if ( pq.size() > K ) {
                qSum += pq.poll();
            }
            if (pq.size() == K) {
                double candidate = worker.rate * qSum;
                res = Math.min(candidate, res);
            }
        }
        return res;
    }

    class Worker {
        double rate;
        int q;
        int w;

        Worker(int q, int w) {
            this.q = q;
            this.w = w;
            this.rate = (double) w/q;
        }
    }
}
