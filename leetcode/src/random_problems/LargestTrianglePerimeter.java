package random_problems;

import java.util.Arrays;

/**
 * 976. Largest Perimeter Triangle
 * Easy
 *
 * Given an array A of positive lengths, return the largest perimeter of a triangle with non-zero area, formed from 3
 * of these lengths.
 *
 * If it is impossible to form any triangle of non-zero area, return 0.
 *
 *
 *
 * Example 1:
 *
 * Input: [2,1,2]
 * Output: 5
 * Example 2:
 *
 * Input: [1,2,1]
 * Output: 0
 * Example 3:
 *
 * Input: [3,2,3,4]
 * Output: 10
 * Example 4:
 *
 * Input: [3,6,2,3]
 * Output: 8
 *
 *
 * Note:
 *
 * 3 <= A.length <= 10000
 * 1 <= A[i] <= 10^6
 */
public class LargestTrianglePerimeter {

    /**
     * Idea - sum of every 2 sides of triangle must be greater than 3-rd one. Cause we are looking for a max perim
     * we have to start search from greatest sides. Thus sort the side values, then check condition with sum of sides
     * @param A
     * @return
     */
    public int largestPerimeter(int[] A) {
        Arrays.sort(A);
        for (int i = A.length - 1; i >= 2; i--) {
            int sum = A[i - 1] + A[i - 2];
            if (A[i] < sum)
                return sum + A[i];
        }
        return 0;
    }
}
