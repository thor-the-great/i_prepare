package path.google;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 210. Course Schedule II
 * Medium
 *
 * There are a total of n courses you have to take, labeled from 0 to n-1.
 *
 * Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is
 * expressed as a pair: [0,1]
 *
 * Given the total number of courses and a list of prerequisite pairs, return the ordering of courses you should
 * take to finish all courses.
 *
 * There may be multiple correct orders, you just need to return one of them. If it is impossible to finish all
 * courses, return an empty array.
 *
 * Example 1:
 *
 * Input: 2, [[1,0]]
 * Output: [0,1]
 * Explanation: There are a total of 2 courses to take. To take course 1 you should have finished
 *              course 0. So the correct course order is [0,1] .
 * Example 2:
 *
 * Input: 4, [[1,0],[2,0],[3,1],[3,2]]
 * Output: [0,1,2,3] or [0,2,1,3]
 * Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both
 *              courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0.
 *              So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3] .
 * Note:
 *
 * The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a
 * graph is represented.
 * You may assume that there are no duplicate edges in the input prerequisites.
 */
public class CourseSchedule2 {

    /**
     * Similar to first problem - use indegree and topological sort to find our the sequence. First get the in degrees
     * for all courses (nodes), and build an adj list form of graph.
     * Then add all indegree == 0 vertices to the queue and start loop. On every loop pool from the queue and add to
     * the topological order,
     * visit all adjacent vertices,
     * decrement indegree and check if it became 0. If so - add it to the queue.
     * keep doing this until the queue is not empty
     *
     * 4, [[1,0],[2,0],[3,1],[3,2]]
     * 0---->1
     * |     |
     * v     v
     * 2---->3
     *
     * 0->1->2->3
     *
     * indegree 	[ 0, 1, 1, 2]
     * graph   	[[1, 2], [3], [3], []]
     * queue	 	[0]
     * count		1
     *
     * 1. queue[]-> 0, adj[1,2] -> indegree [ 0, 0, 0, 2] count+=2 = 3 queue[1,2]
     * 2  queue[2]->1  adj[3]   -> indegree [ 0, 0, 0, 1] count=3 queue[2]
     * 3  queue[]->2   adj[3]   -> indegree [ 0, 0, 0, 0] count=4 queue[3]
     * 4  queue[]->3   adj[] -> continue
     * 5  queue isEmpty -> break
     * count == numCourses = return true;
     *
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] indegree = new int[numCourses];
        List<Integer>[] graph = new ArrayList[numCourses];
        int[] topoOrder = new int[numCourses];

        for (int[] prereq : prerequisites) {
            ++indegree[prereq[0]];
            //add adjacent vertices
            if (graph[prereq[1]] == null)
                graph[prereq[1]] = new ArrayList();
            graph[prereq[1]].add(prereq[0]);
        }
        //add all independent courses
        Queue<Integer> q = new LinkedList();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0)
                q.add(i);
        }
        int i = 0;
        while(!q.isEmpty()) {
            int course = q.poll();
            topoOrder[i++] = course;
            //visit next courses
            if (graph[course] != null) {
                for (int adj : graph[course]) {
                    --indegree[adj];
                    if (indegree[adj] == 0) {
                        q.add(adj);
                    }
                }
            }
        }
        if (i < numCourses)
            return new int[0];
        return topoOrder;
    }
}
