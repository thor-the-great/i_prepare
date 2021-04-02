package path.uber;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 354. Russian Doll Envelopes
 * Hard
 *
 * You have a number of envelopes with widths and heights given as a pair of integers (w, h). One envelope can fit
 * into another if and only if both the width and height of one envelope is greater than the width and height of the
 * other envelope.
 *
 * What is the maximum number of envelopes can you Russian doll? (put one inside other)
 *
 * Note:
 * Rotation is not allowed.
 *
 * Example:
 *
 * Input: [[5,4],[6,4],[6,7],[2,3]]
 * Output: 3
 * Explanation: The maximum number of envelopes you can Russian doll is 3 ([2,3] => [5,4] => [6,7]).
 */
public class RussianDollEnvelopes {

    /**
     * Idea: sort it, asc by arr[0] but desc by arr[1]. Then traverse based on 1..N, where N is max possible number of
     * envelopes.
     * Each time do binary search by the values of arr[1]
     * @param envelopes
     * @return
     */
    public int maxEnvelopes(int[][] envelopes) {
        int N = envelopes.length;
        if (N <= 1)
            return N;

        Comparator<int[]> comp = new Comparator<int[]>() {
            public int compare(int[] arr1, int[] arr2) {
                if (arr1[0] == arr2[0])
                    return arr2[1] - arr1[1];
                else
                    return arr1[0] - arr2[0];
            }
        };

        Arrays.sort(envelopes, comp);

        int len = 0;
        int[] dp = new int[N];
        for (int[] e : envelopes) {
            int num = e[1];
            int idx = Arrays.binarySearch(dp, 0, len, num);
            if (idx < 0)
                idx = -(idx + 1);
            dp[idx] = num;
            if (idx == len)
                len++;
        }
        return len;
    }

    /**
     * Simple DP, based on longest increasing subsequence. 
     * Going from left to right, each right pointer cares only of what was to it's left. Next element
     * can take that result from memo and count after one next element 
     * 
     * O(n^2) time
     * @param envelopes
     * @return
     */
    public int maxEnvelopesSimpleDP(int[][] envelopes) {
        Comparator<int[]> comp = (ar1, ar2) -> ar1[0] - ar2[0];
        Arrays.sort(envelopes, comp);
        int N = envelopes.length;
        int[] dp = new int[N];
        dp[0] = 1;
        int max = 1;
        for (int i = 1; i < N; i++) {
            int cur = 0;
            for (int j = 0; j < i; j++) {
                if (envelopes[i][0] > envelopes[j][0] && envelopes[i][1] > envelopes[j][1]) {
                    cur = Math.max(cur, dp[j]);
                }
            }
            dp[i] = cur + 1;
            max = Math.max(max, dp[i]);
        }
        return max;
    }

     public static void main(String[] args) {
        RussianDollEnvelopes obj = new RussianDollEnvelopes();
        System.out.println(obj.maxEnvelopes(new int[][] {
                {5,4},
                {6,4},
                {6,7},
                {2,3}
        }));
     }
}
