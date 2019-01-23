package graphs;

import cracking.trees_graphs.DiGraph;

import java.util.*;

/**
 * Do topologocal sorting in DAG
 */
public class TopologicalSort {

    void doTopologicalSort(DiGraph g) {
        List<Integer> topoOrder = new ArrayList<>();
        //find all inorders
        int[] inOrder = new int[g.getV()];
        List<DiGraph.Edge> allEdges = g.adjAllEdges();
        for (DiGraph.Edge e : allEdges) {
            inOrder[e.to]++;
        }
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[g.getV()];
        for (int i = 0; i < inOrder.length; i++) {
            if (inOrder[i] != 0) continue;
            q.add(i);
            visited[i] = true;
        }

        while (!q.isEmpty()) {
            int v = q.poll();
            topoOrder.add(v);
            for (int adj : g.adj(v)) {
                if (!visited[adj]) {
                    inOrder[adj]--;
                    if (inOrder[adj] == 0) {
                        q.add(adj);
                        visited[adj] = true;
                    }
                }
            }
        }

        System.out.print("Topological sort : ");
        for (int v : topoOrder) {
            System.out.print(v + ", ");
        }
    }

    public static void main(String[] args) {
        TopologicalSort obj = new TopologicalSort();
        obj.doTopologicalSort(GraphUtils.getTopoOrderGraph1());
        System.out.print("\n");
        obj.doTopologicalSort(GraphUtils.getDiGraphWeighted1());
    }
}
