package graphs;

import java.util.ArrayList;
import java.util.List;

/**
 * 2101. Detonate the Maximum Bombs
Medium

You are given a list of bombs. The range of a bomb is defined as the area where its effect can be felt. This area is in the shape of a circle with the center as the location of the bomb.

The bombs are represented by a 0-indexed 2D integer array bombs where bombs[i] = [xi, yi, ri]. xi and yi denote the X-coordinate and Y-coordinate of the location of the ith bomb, whereas ri denotes the radius of its range.

You may choose to detonate a single bomb. When a bomb is detonated, it will detonate all bombs that lie in its range. These bombs will further detonate the bombs that lie in their ranges.

Given the list of bombs, return the maximum number of bombs that can be detonated if you are allowed to detonate only one bomb.

 

Example 1:


Input: bombs = [[2,1,3],[6,1,4]]
Output: 2
Explanation:
The above figure shows the positions and ranges of the 2 bombs.
If we detonate the left bomb, the right bomb will not be affected.
But if we detonate the right bomb, both bombs will be detonated.
So the maximum bombs that can be detonated is max(1, 2) = 2.
Example 2:


Input: bombs = [[1,1,5],[10,10,5]]
Output: 1
Explanation:
Detonating either bomb will not detonate the other bomb, so the maximum number of bombs that can be detonated is 1.
Example 3:


Input: bombs = [[1,2,3],[2,3,1],[3,4,2],[4,5,3],[5,6,4]]
Output: 5
Explanation:
The best bomb to detonate is bomb 0 because:
- Bomb 0 detonates bombs 1 and 2. The red circle denotes the range of bomb 0.
- Bomb 2 detonates bomb 3. The blue circle denotes the range of bomb 2.
- Bomb 3 detonates bomb 4. The green circle denotes the range of bomb 3.
Thus all 5 bombs are detonated.
 

Constraints:

1 <= bombs.length <= 100
bombs[i].length == 3
1 <= xi, yi, ri <= 105

 * 
 * https://leetcode.com/problems/detonate-the-maximum-bombs
 */
public class DetonateMaximumBombs {
    /**
     * Condition to check - get distance between bomb's centers and compare to radius of explosion.
     * 
     * Create directed graph, where there is an edge between a and b bombs if exposion of a fires up b. 
     * Traverse graph from every vertex and accumulate number of bombs (number of vertexes reached). Compare for each starting point
     * 
     * Optimization compare if max bombs equals to total number of bombs, if so we can exit immidiatelly.
     * 
     * O(n^2) time to create graph, O(n^2) to do n bfs
     * O(n^2) space to keep the graph and O(n) for queue on traversal
     */
    public int maximumDetonation(int[][] bombs) {
        int N = bombs.length;
        List<Integer>[] graph = new ArrayList[N];
        
        for (int i = 0; i < N; i++) {
            graph[i] = new ArrayList();
        }
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i == j) {
                    continue;
                }
                //checking connection from i to j
                int[] iBomb = bombs[i], jBomb = bombs[j];
                long x = (long) iBomb[0], y = (long) iBomb[1], r = (long) iBomb[2];
                
                if ((long)((x-jBomb[0])*(x-jBomb[0]) + (y-jBomb[1])*(y-jBomb[1]))
                   <= r*r) {
                    graph[i].add(j);
                }
            }
        }
        
        int res = 0;
        boolean[] visited = new boolean[N];
        for (int i = 0; i < N; i++) {
            visited = new boolean[N];
            Queue<Integer> q = new LinkedList();
            q.add(i);
            int cur = 0;
            while (!q.isEmpty()) {
                int c = q.poll();
                if (visited[c]) {
                    continue;
                }
                visited[c] = true;
                cur++;
                for (int adj : graph[c]) {
                    if (!visited[adj])
                    q.add(adj);
                }
            }
            res = Math.max(res, cur);
            if (res == N) {
                return res;
            }
        }
        return res;
    }
}
