package arrays;

import java.util.Arrays;

/**
 * 135. Candy
 * Hard
 *
 * There are N children standing in a line. Each child is assigned a rating value.
 *
 * You are giving candies to these children subjected to the following requirements:
 *
 * Each child must have at least one candy.
 * Children with a higher rating get more candies than their neighbors.
 * What is the minimum candies you must give?
 *
 * Example 1:
 *
 * Input: [1,0,2]
 * Output: 5
 * Explanation: You can allocate to the first, second and third child with 2, 1, 2 candies respectively.
 * Example 2:
 *
 * Input: [1,2,2]
 * Output: 4
 * Explanation: You can allocate to the first, second and third child with 1, 2, 1 candies respectively.
 *              The third child gets 1 candy because it satisfies the above two conditions.
 */
public class Candy {

    /**
     * Use two passes strategy. Starting from all kids have 1.
     * in one pass moving from left to right, filling +1 for those that have rat[i] > rat[i - 1].
     * in second pass moving from right to left, if found rat[i] > rat[i + 1] assign
     * cand[i] = max(cand[i], cand[i + 1] + 1)
     * @param ratings
     * @return
     */
    public int candy(int[] ratings) {
        int N = ratings.length;
        int[] arr = new int[N];
        Arrays.fill(arr, 1);
        for (int i = 1; i < N; i++) {
            if (ratings[i] > ratings[i - 1])
                arr[i] = arr[i - 1] + 1;
        }
        int sum = arr[N - 1];
        for (int i = N - 2; i>=0; i++) {
            if (ratings[i] > ratings[i + 1])
                arr[i] = Math.max(arr[i], arr[i + 1] + 1);
            sum+=arr[i];
        }
        return sum;
    }

}
