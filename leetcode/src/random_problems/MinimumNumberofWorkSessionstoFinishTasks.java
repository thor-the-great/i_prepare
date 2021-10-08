package random_problems;

import java.util.Arrays;


/**
 * 1986. Minimum Number of Work Sessions to Finish the Tasks
Medium

There are n tasks assigned to you. The task times are represented as an integer array tasks of length n, where the ith task 
takes tasks[i] hours to finish. A work session is when you work for at most sessionTime consecutive hours and then take a break.

You should finish the given tasks in a way that satisfies the following conditions:

    If you start a task in a work session, you must complete it in the same work session.
    You can start a new task immediately after finishing the previous one.
    You may complete the tasks in any order.

Given tasks and sessionTime, return the minimum number of work sessions needed to finish all the tasks following 
the conditions above.

The tests are generated such that sessionTime is greater than or equal to the maximum element in tasks[i].

Example 1:

Input: tasks = [1,2,3], sessionTime = 3
Output: 2
Explanation: You can finish the tasks in two work sessions.
- First work session: finish the first and the second tasks in 1 + 2 = 3 hours.
- Second work session: finish the third task in 3 hours.

Example 2:

Input: tasks = [3,1,3,1,1], sessionTime = 8
Output: 2
Explanation: You can finish the tasks in two work sessions.
- First work session: finish all the tasks except the last one in 3 + 1 + 3 + 1 = 8 hours.
- Second work session: finish the last task in 1 hour.

Example 3:

Input: tasks = [1,2,3,4,5], sessionTime = 15
Output: 1
Explanation: You can finish all the tasks in one work session.

 

Constraints:

    n == tasks.length
    1 <= n <= 14
    1 <= tasks[i] <= 10
    max(tasks[i]) <= sessionTime <= 15
 * 
 * https://leetcode.com/problems/minimum-number-of-work-sessions-to-finish-the-tasks/
 */
public class MinimumNumberofWorkSessionstoFinishTasks {
    
    /**
     * Do DFS and backtracking. Only catch that makes it non m^n solution is smart prunning
     * of the decision tree. It makes sense to try to put first i tasks into first i sessions because
     * the rest would be repetition of the same permutations. So if there are still unassigned tasks and 
     * i-th task goes to i+1 session - drop that branch.
     * 
     * Another optimization is initial reverse sorting of the tasks array.
     */
    public int minSessions(int[] tasks, int st) {
        int n = tasks.length;
        reverseSort(tasks);
        for (int i = 1; i < n; i++) {
            int[] remain = new int[i];
            Arrays.fill(remain, st);
            if (helper(tasks, remain, 0)) {
                return i;
            }
        }
        return n;
    }
    
    boolean helper(int[] tasks, int[] remain, int idx) {
        if (idx == tasks.length) {
            return true;
        }
        for (int i = 0; i < remain.length; i++) {
            if (i > idx) {
                continue;
            }
            if (remain[i] >= tasks[idx]) {
                remain[i]-=tasks[idx];
                if (helper(tasks, remain, idx + 1)) {
                    return true;
                }
                remain[i]+=tasks[idx];
            }
        }
        return false;
    }
    
    void reverseSort(int[] tasks) {
        Arrays.sort(tasks);
        
        for(int i = 0; i < tasks.length / 2; i++) {
            int temp = tasks[i];
            tasks[i] = tasks[tasks.length - i - 1];
            tasks[tasks.length - i - 1] = temp;
        }
    }
}
