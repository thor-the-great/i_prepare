package bfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 1306. Jump Game III
Medium

Given an array of non-negative integers arr, you are initially positioned at start index of the array. When you are at index i, you can jump to i + arr[i] or i - arr[i], check if you can reach to any index with value 0.

Notice that you can not jump outside of the array at any time.

 

Example 1:

Input: arr = [4,2,3,0,3,1,2], start = 5
Output: true
Explanation: 
All possible ways to reach at index 3 with value 0 are: 
index 5 -> index 4 -> index 1 -> index 3 
index 5 -> index 6 -> index 4 -> index 1 -> index 3 
Example 2:

Input: arr = [4,2,3,0,3,1,2], start = 0
Output: true 
Explanation: 
One possible way to reach at index 3 with value 0 is: 
index 0 -> index 4 -> index 1 -> index 3
Example 3:

Input: arr = [3,0,2,1,2], start = 2
Output: false
Explanation: There is no way to reach at index 1 with value 0.
 

Constraints:

1 <= arr.length <= 5 * 104
0 <= arr[i] < arr.length
0 <= start < arr.length

https://leetcode.com/problems/jump-game-iii/
 */
public class JumpGame3 {

    /**
     * Do BFS for the array as it's a graph - keep queue of current indexes and array of visited ones.
     * On each step do left and right of the current position by adding/substracting value at current index.
     * Keep next step within the array size 
     */
    public boolean canReach(int[] arr, int start) {
        boolean[] visited = new boolean[arr.length];
        Queue<Integer> q = new LinkedList();
        q.add(start);
        
        while(!q.isEmpty()) {
            int curIndex = q.poll();
            if (visited[curIndex]) {
                continue;
            }
            
            if (arr[curIndex] == 0) {
                //we have reach the goal
                return true;
            }
            visited[curIndex] = true;
            //now probing into both directions from current position
            if (curIndex - arr[curIndex] >= 0) {
                q.add(curIndex - arr[curIndex]);
            }
            if (curIndex + arr[curIndex] < arr.length) {
                q.add(curIndex + arr[curIndex]);
            }
        }
        
        return false;
    }
}
