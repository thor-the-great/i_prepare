package dp;

/**
 * Given an array of integers, find the subset of non-adjacent elements with the maximum sum. Calculate the sum of that subset. It is possible that the maximum sum is , the case when all elements are negative.

For example, given an array  we have the following possible subsets. These exclude the empty subset and single element subsets which are also valid.

Subset      Sum
[-2, 3, 5]   6
[-2, 3]      1
[-2, -4]    -6
[-2, 5]      3
[1, -4]     -3
[1, 5]       6
[3, 5]       8
Our maximum subset sum is . Note that any individual element is a subset as well.

As another example, . In this case, it is best to choose no element: return .

Function Description

Complete the  function in the editor below. It should return an integer representing the maximum subset sum for the given array.

maxSubsetSum has the following parameter(s):

arr: an array of integers
Input Format

The first line contains an integer, .
The second line contains  space-separated integers .

Constraints

Output Format

Return the maximum sum described in the statement.

Sample Input 0

5
3 7 4 6 5
Sample Output 0

13
Explanation 0

Our possible subsets are  and . The largest subset sum is  from subset 

Sample Input 1

5
2 1 5 8 4
Sample Output 1

11
Explanation 1

Our subsets are  and . The maximum subset sum is  from the first subset listed.

Sample Input 2

5
3 5 -7 8 10
Sample Output 2

15
Explanation 2

Our subsets are  and . The maximum subset sum is  from the fifth subset listed.

https://www.hackerrank.com/challenges/max-array-sum/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=dynamic-programming
 */
public class MaxArrayNonadjacentSum {
    
    /**
     * DP approach - keep two values - previous and previous previous of the current element.
     * For each element there are two cases possible:
     * - we take previous previous and the current element 
     * - we skip current and take previous element (as adjacent elements are not allowed)
     * at the end we take max of two
     * catch - 0 can be greater than both values
     * 
     * O(n) time
     * O(1) space
     * s
     * @param arr
     * @return
     */
    private int maxSubsetSum(int[] arr) {
        int N = arr.length;
        if (N == 0)
            return 0;
        if (N == 1)
            return Math.max(0, arr[0]);
            
        int prev = Math.max(0, arr[0]), prevPrev = 0;
        for (int i = 1; i < N; i++) {
            int cur = Math.max(0, 
                Math.max(prevPrev + arr[i], prev));
            prevPrev = prev;
            prev = cur;
        }
        
        return Math.max(prev, prevPrev);
    }
}
