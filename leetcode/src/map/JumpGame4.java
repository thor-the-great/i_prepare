package map;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * 1345. Jump Game IV
Hard

Share
Given an array of integers arr, you are initially positioned at the first index of the array.

In one step you can jump from index i to index:

i + 1 where: i + 1 < arr.length.
i - 1 where: i - 1 >= 0.
j where: arr[i] == arr[j] and i != j.
Return the minimum number of steps to reach the last index of the array.

Notice that you can not jump outside of the array at any time.

 

Example 1:

Input: arr = [100,-23,-23,404,100,23,23,23,3,404]
Output: 3
Explanation: You need three jumps from index 0 --> 4 --> 3 --> 9. Note that index 9 is the last index of the array.
Example 2:

Input: arr = [7]
Output: 0
Explanation: Start index is the last index. You do not need to jump.
Example 3:

Input: arr = [7,6,9,6,9,6,9,7]
Output: 1
Explanation: You can jump directly from index 0 to index 7 which is last index of the array.
 

Constraints:

1 <= arr.length <= 5 * 104
-108 <= arr[i] <= 108

https://leetcode.com/problems/jump-game-iv/
 */
public class JumpGame4 {
    
    /**
     * Create graph of possible connected indexes, emulate jumping with BFS
     */
    public int minJumps(int[] arr) {
        Map<Integer, List<Integer>> map = new HashMap();
        int N = arr.length;
        for (int i = 0; i < N; i++) {
            if (!map.containsKey(arr[i])) {
                map.put(arr[i], new ArrayList());
            }
            map.get(arr[i]).add(i);
        }
        
        Queue<Integer> q = new LinkedList();
        q.add(0);
        int steps = 0;
        boolean[] seen = new boolean[N];
        
        while (!q.isEmpty()) {
            int size = q.size();
            for (int s = 0; s < size; s++) {
                int next = q.poll();
                seen[next] = true;
                if (next == N - 1) {
                    return steps;
                }     
                if (map.containsKey(arr[next])) {
                    for (int adj : map.get(arr[next])) {
                        if (!seen[adj]) {
                            q.add(adj);
                        }
                    }
                    map.get(arr[next]).clear();
                }
                if (next - 1 >= 0 && !seen[next - 1]) {
                    q.add(next - 1);
                }
                if (next + 1 < N && !seen[next + 1]) {
                    q.add(next + 1);
                }
            }
            steps++;
        }
        
        return 0;
    }
}
