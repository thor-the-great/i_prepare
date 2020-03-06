package grooking_coding_patterns;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class AllTaskSchedulingOrders {
    public static void printOrders(int tasks, int[][] prerequisites) {
        List<Integer>[] g = new ArrayList[tasks];
        int[] indegree = new int[tasks];
        for (int[] p : prerequisites) {
            if (g[p[0]] == null) {
                g[p[0]] = new ArrayList();
            }
            g[p[0]].add(p[1]);
            ++indegree[p[1]];
        }

        Queue<Integer> q = new LinkedList();
        for (int i = 0; i < tasks; i++) {
            if (indegree[i] == 0)
                q.add(i);
        }

        helper(g, q, indegree, new ArrayList());
    }

    static void helper(List<Integer>[] g, Queue<Integer> q, int[] indegree, List<Integer> cur) {
        for (Integer v : q) {;
            cur.add(v);
            Queue q2 = new LinkedList();
            for (int vv : q) {
                if (vv != v)
                    q2.add(vv);
            }
            if (g[v] != null) {
                for (int adj : g[v]) {
                    --indegree[adj];
                    if (indegree[adj] == 0)
                        q2.add(adj);
                }
            }
            helper(g, q2, indegree, cur);
            //rolling back
            cur.remove(v);
            if (g[v] != null) {
                for (int adj : g[v]) {
                    ++indegree[adj];
                }
            }
        }
        if (cur.size() == g.length)
            System.out.println(cur);
    }

    public static void main(String[] args) {
        /*AllTaskSchedulingOrders.printOrders(3, new int[][] { new int[] { 0, 1 }, new int[] { 1, 2 } });
        System.out.println();
*/
        AllTaskSchedulingOrders.printOrders(4,
                new int[][] { new int[] { 3, 2 }, new int[] { 3, 0 }, new int[] { 2, 0 }, new int[] { 2, 1 } });
        System.out.println();

        AllTaskSchedulingOrders.printOrders(6, new int[][] { new int[] { 2, 5 }, new int[] { 0, 5 }, new int[] { 0, 4 },
                new int[] { 1, 4 }, new int[] { 3, 2 }, new int[] { 1, 3 } });
        System.out.println();
    }
}
