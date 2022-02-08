package stack;

import java.util.Stack;

/**
 * 739. Daily Temperatures
Medium

Given an array of integers temperatures represents the daily temperatures, return an array answer such that answer[i] is the number of days you have to wait after the ith day to get a warmer temperature. If there is no future day for which this is possible, keep answer[i] == 0 instead.

Example 1:

Input: temperatures = [73,74,75,71,69,72,76,73]
Output: [1,1,4,2,1,1,0,0]
Example 2:

Input: temperatures = [30,40,50,60]
Output: [1,1,1,0]
Example 3:

Input: temperatures = [30,60,90]
Output: [1,1,0]
 

Constraints:

1 <= temperatures.length <= 105
30 <= temperatures[i] <= 100

 * https://leetcode.com/problems/daily-temperatures/
 */
public class DailyTemperatures {

    /**
     * Idea - we fill result array from the back. We know if the temp[j] > temp[i] then we need 
     * to wait at least res[i] days because there are no warmer temp in res[i] + i days. 
     * @param temperatures
     * @return
     */
    public int[] dailyTemperaturesConstSpace(int[] temperatures) {
        int N = temperatures.length;
        int[] res = new int[N];
        
        if (N < 2) {
            return res;
        }
        
        for (int i = N - 2; i >= 0; i--) {
            int backIdx = i + 1;
            while (temperatures[i] >= temperatures[backIdx] && res[backIdx] > 0) {
                backIdx += res[backIdx];
            }
            if (temperatures[backIdx] > temperatures[i]) {
                res[i] = backIdx - i;
            }
        }
        
        return res;
    }


    /**
     * Idea - keep stack of days, on each new day checking if it's warmer than ones in stack
     * starting from the top. If this is the case - pop out the day from the stack and save the 
     * result for THAT DAY as difference between this index and index of THAT day.
     * This way day only push and popped out of the stack at most once.
     * 
     * O(n) time
     * O(n) space
     */
    public int[] dailyTemperaturesStack(int[] temperatures) {
        int N = temperatures.length;
        int[] res = new int[N];
        Stack<Integer> stack = new Stack();
        for (int i = 0; i < N; i++) {
            while(!stack.isEmpty() && temperatures[stack.peek()] < temperatures[i]) {
                int day = stack.pop();
                res[day] = i - day;
            }
            stack.push(i);
        }
        return res;
    }
}
