package path.google;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Course Schedule
 * There are a total of n courses you have to take, labeled from 0 to n-1.
 *
 * Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is
 * expressed as a pair: [0,1]
 *
 * Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?
 *
 * Example 1:
 *
 * Input: 2, [[1,0]]
 * Output: true
 * Explanation: There are a total of 2 courses to take.
 *              To take course 1 you should have finished course 0. So it is possible.
 * Example 2:
 *
 * Input: 2, [[1,0],[0,1]]
 * Output: false
 * Explanation: There are a total of 2 courses to take.
 *              To take course 1 you should have finished course 0, and to take course 0 you should
 *              also have finished course 1. So it is impossible.
 * Note:
 *
 * The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a
 * graph is represented.
 * You may assume that there are no duplicate edges in the input prerequisites.
 */
public class CourseSchedule {

    /**
     * idea - use Topological sort and BFS. Count all vertexes (courses) with in-degree == 0, then start BFS. For every
     * visited course decrement it's in-degree. If it became 0 - add it to the list of nodes to visit (meaning we can
     * take the course now, there are no pre-requisites. In the end compare number of such course taken with number
     * of all courses.
     *
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[] inDegree = new int[numCourses];
        List<Integer>[] vertexes = new LinkedList[numCourses];
        //count inDegree for every vertex and build graph
        for (int[] preReqPair : prerequisites) {
            inDegree[preReqPair[0]]++;
            if (vertexes[preReqPair[1]] == null) {
                vertexes[preReqPair[1]] = new LinkedList();
            }
            vertexes[preReqPair[1]].add(preReqPair[0]);
        }
        //put inDegree points as our starting points for BFS
        Queue<Integer> q = new LinkedList();
        for (int i =0; i < inDegree.length; i++) {
            if (inDegree[i] == 0)
                q.add(i);
        }
        //count courses that we can take - initially this is number of course with indegree = 0
        int c = q.size();
        //do BFS
        while(!q.isEmpty()) {
            int vertex = q.poll();
            List<Integer> adj = vertexes[vertex];
            if (adj != null) {
                for (int adjVertex : adj) {
                    inDegree[adjVertex]--;
                    //if we took all pre-req course - we have in-degree = 0 and we can take this course
                    if (inDegree[adjVertex] == 0) {
                        q.add(adjVertex);
                        c++;
                    }
                }
            }
        }
        return c == numCourses;
    }

    /**
     * similar as firts task but need to return order of courses. Same algorithm, just save points as we visit.
     * fun way to return array without helping ArrayList
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] inDegree = new int[numCourses];
        List<Integer>[] vertexes = new LinkedList[numCourses];
        //count inDegree for every vertex and build graph
        for (int[] preReqPair : prerequisites) {
            inDegree[preReqPair[0]]++;
            if (vertexes[preReqPair[1]] == null) {
                vertexes[preReqPair[1]] = new LinkedList();
            }
            vertexes[preReqPair[1]].add(preReqPair[0]);
        }
        //put inDegree points as our starting points for BFS
        Queue<Integer> q = new LinkedList();
        for (int i =0; i < inDegree.length; i++) {
            if (inDegree[i] == 0)
                q.add(i);
        }
        //count courses that we can take - initially this is number of course with indegree = 0
        int[] order = new int[numCourses];
        int i = 0;
        //do BFS
        while(!q.isEmpty()) {
            int vertex = q.poll();
            order[i] = vertex;
            i++;
            List<Integer> adj = vertexes[vertex];
            if (adj != null) {
                for (int adjVertex : adj) {
                    inDegree[adjVertex]--;
                    //if we took all pre-req course - we have in-degree = 0 and we can take this course
                    if (inDegree[adjVertex] == 0) {
                        q.add(adjVertex);
                    }
                }
            }
        }
        if (i != numCourses) return new int[0];
        else return order;
    }
}
