package arrays;

import java.util.Arrays;

/**
 * 2195. Append K Integers With Minimal Sum
Medium

You are given an integer array nums and an integer k. Append k unique positive integers that do not appear in 
nums to nums such that the resulting total sum is minimum.

Return the sum of the k integers appended to nums.

Example 1:

Input: nums = [1,4,25,10,25], k = 2
Output: 5
Explanation: The two unique positive integers that do not appear in nums which we append are 2 and 3.
The resulting sum of nums is 1 + 4 + 25 + 10 + 25 + 2 + 3 = 70, which is the minimum.
The sum of the two integers appended is 2 + 3 = 5, so we return 5.

Example 2:

Input: nums = [5,6], k = 6
Output: 25
Explanation: The six unique positive integers that do not appear in nums which we append are 1, 2, 3, 4, 7, and 8.
The resulting sum of nums is 5 + 6 + 1 + 2 + 3 + 4 + 7 + 8 = 36, which is the minimum. 
The sum of the six integers appended is 1 + 2 + 3 + 4 + 7 + 8 = 25, so we return 25.

 

Constraints:

    1 <= nums.length <= 105
    1 <= nums[i] <= 109
    1 <= k <= 108

    https://leetcode.com/problems/append-k-integers-with-minimal-sum/
 */
public class AppendKIntegersWithMinimalSum {
    
    /**
     * Idea - if we know interval start and and then sum is (n*(n + 1))/2. Break arrays into such intervals and count
     * sum for intevals between two next array elements
     * 
     * O(n) time
     * O(1) space
     */
    public long minimalKSum(int[] nums, int k) {
        long sum = 0;
        Arrays.sort(nums);
        int N = nums.length;
        //check before nums[0]
        int prev = 0; int idx = 0;
        while (k > 0 && idx < N) {
            if (nums[idx] - prev > 1) {
                int count = nums[idx] - prev - 1;
                count = Math.min(k, count);
                long limit = prev + count; 
                sum += ((long)prev + 1 + limit) * count / 2;
                k-=count;          
            }
            prev = nums[idx];
            idx++;
        }
        
        if (k > 0) {
            sum += ((long)(2*nums[N - 1] + 1 + k) * k) / 2;  
        }
        return sum;
    }

    public static void main(String[] args) {
        AppendKIntegersWithMinimalSum instance = new AppendKIntegersWithMinimalSum();
        int[] nums = new int[] {
            96,44,99,25,61,84,88,18,19,33,60,86,52,19,32,47,35,50,94,17,29,98,22,21,72,100,40,84
        };
        int k = 35;
        long res = instance.minimalKSum(nums, k);
        System.out.println(res);
    }
}
