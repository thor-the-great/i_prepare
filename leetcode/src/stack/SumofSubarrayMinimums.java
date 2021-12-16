package stack;

import java.util.Stack;

/**
 * 
 * 907. Sum of Subarray Minimums
Medium

Given an array of integers arr, find the sum of min(b), where b ranges over every (contiguous) subarray of arr. Since the answer may be large, return the answer modulo 109 + 7.

 

Example 1:

Input: arr = [3,1,2,4]
Output: 17
Explanation: 
Subarrays are [3], [1], [2], [4], [3,1], [1,2], [2,4], [3,1,2], [1,2,4], [3,1,2,4]. 
Minimums are 3, 1, 2, 4, 1, 1, 2, 1, 1, 1.
Sum is 17.
Example 2:

Input: arr = [11,81,94,43,3]
Output: 444
 

Constraints:

1 <= arr.length <= 3 * 104
1 <= arr[i] <= 3 * 104

https://leetcode.com/problems/sum-of-subarray-minimums/
 */
public class SumofSubarrayMinimums {
    
    /**
     * Idea - if we count arrays not as actual ones but based on number of elements that are 
     * greater to the left of the current element  + 1 (to account the lement itself or rather array that contains only that one lement)
     * and to the right of the element, 
     * and number of subarrays is the product of those 2 numbers:
     * left[i] = 1 + num of elements to the left > element
     * right[i] = 1 + num of elements to the right >= element
     * num_of_arrays[i] = left[i]*right[i]
     * num_of_arrays[i] = left[i]*right[i]
     * res[i] = num_of_array[i]*array[i] (cause it's how many that array element is the minimum)
     * 
     * how to count how many elements are greater - similar to DailyTemperatures use Stack, in stack we keep
     * element value + result for that element. For each element we poping from the stack until elements are >,
     * for each such pop accumulate it's result. Starting from 1 to account element itself.
     * 
     * O(n) time - iterate over array few times
     * O(n) space - need 2 arrays and 2 stacks
     */
    public int sumSubarrayMins(int[] arr) {
        int N = arr.length;
        int[] left = new int[N], right = new int[N];
        Stack<int[]> leftStack = new Stack(), rightStack = new Stack();
        
        for (int i = 0; i < N; i++) {
            int count = 1;
            while(!leftStack.isEmpty() && leftStack.peek()[0] > arr[i]) {
                count+=leftStack.pop()[1];
            }
            left[i] = count;
            leftStack.push(new int[] {arr[i], count});
        }
        
        for (int i = N - 1; i >= 0; i--) {
            int count = 1;
            while(!rightStack.isEmpty() && rightStack.peek()[0] >= arr[i]) {
                count+=rightStack.pop()[1];
            }
            right[i] = count;
            rightStack.push(new int[] {arr[i], count});
        }
        long res = 0, mod = 1_000_000_007;
        for (int i = 0; i < N; i++) {
            res+=((long)arr[i]*left[i]*right[i]);
            res%=mod;
        }
        return (int)res;
    }
}
