package random_problems;

import java.util.ArrayList;
import java.util.List;

/**
 * 967. Numbers With Same Consecutive Differences
 * Medium
 *
 * Return all non-negative integers of length N such that the absolute difference between every two consecutive digits
 * is K.
 *
 * Note that every number in the answer must not have leading zeros except for the number 0 itself. For example, 01
 * has one leading zero and is invalid, but 0 is valid.
 *
 * You may return the answer in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: N = 3, K = 7
 * Output: [181,292,707,818,929]
 * Explanation: Note that 070 is not a valid number, because it has leading zeroes.
 * Example 2:
 *
 * Input: N = 2, K = 1
 * Output: [10,12,21,23,32,34,43,45,54,56,65,67,76,78,87,89,98]
 *
 *
 * Note:
 *
 * 1 <= N <= 9
 * 0 <= K <= 9
 */
public class NumbersSameConsecutiveDifferences {

    int N;
    int K;
    List<Integer> res;

    /**
     * Simple idea - get next num by checking + and - conditions
     * @param N
     * @param K
     * @return
     */
    public int[] numsSameConsecDiff(int N, int K) {
        this.N = N;
        this.K = K;
        res = new ArrayList();
        for (int i = 1; i <= 9; i++) {
            helper(i, 1, i);
        }
        if (N == 1)
            res.add(0);
        int[] ans = new int[res.size()];
        for(int i =0; i < res.size(); i++) {
            ans[i] = res.get(i);
        }
        return ans;
    }

    void helper(int n, int nn, int last) {
        if (nn == N) {
            res.add(n);
            return;
        }
        int l1 = last - K;
        if (l1 >= 0 )
            helper(n * 10 + l1, nn + 1, l1);
        if (K != 0 ) {
            int l2 = last + K;
            if (l2 <= 9 )
                helper(n * 10 + l2, nn + 1, l2);
        }
    }
}
