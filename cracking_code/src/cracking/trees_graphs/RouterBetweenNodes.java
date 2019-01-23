package cracking.trees_graphs;

import java.util.*;

public class RouterBetweenNodes {

    boolean isPath(DiGraph g, int p1, int p2) {
        //try DFS
        /*
        if (checkDFSPair(g, p1, p2))
            return true;
        else if (checkDFSPair(g, p2, p1))
                return true;
        return false;
        */

        //try BFS
        if (checkBFSPair(g, p1, p2))
            return true;
        else if (checkBFSPair(g, p2, p1))
            return true;
        return false;

    }

    boolean checkBFSPair(DiGraph g, int p1, int p2) {
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        //try point1
        queue.add(p1);
        while (!queue.isEmpty()) {
            int nextP = queue.poll();
            List<Integer> adjList = g.adj(nextP);
            if (adjList != null && !adjList.isEmpty()) {
                for (int adjP : adjList) {
                    if (!visited.contains(adjP)) {
                        if (adjP == p2)
                            return true;
                        queue.add(adjP);
                        visited.add(adjP);
                    }
                }
            }
        }
        return false;
    }

    boolean checkDFSPair(DiGraph g, int p1, int p2) {
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        //try point1
        queue.add(p1);
        while (!queue.isEmpty()) {
            int nextP = queue.poll();
            System.out.println(nextP);
            if (!visited.contains(nextP)) {
                if (nextP == p2)
                    return true;
                else {
                    for (int adjP: g.adj(nextP)) {
                        queue.add(adjP);
                    }
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        /**
         * Construct graph 0->1->2->3->10
         *                 |  |  ^  to
         *                 |  |  |  7->8->9
         *                 |  to  |  ^
         *                  ->4->5->6
         */
        DiGraph diGraph = new DiGraph(11);
        diGraph.addEdge(0, 1);
        diGraph.addEdge(0, 4);
        diGraph.addEdge(1, 4);
        diGraph.addEdge(1, 2);
        diGraph.addEdge(2, 3);
        diGraph.addEdge(3, 7);
        diGraph.addEdge(3, 10);
        diGraph.addEdge(4, 5);
        diGraph.addEdge(5, 6);
        diGraph.addEdge(5, 2);
        diGraph.addEdge(6, 7);
        diGraph.addEdge(7, 8);
        diGraph.addEdge(8, 9);
        System.out.println(diGraph.toString());

        RouterBetweenNodes obj = new RouterBetweenNodes();

        System.out.println(obj.isPath(diGraph, 0, 9));
        System.out.println(obj.isPath(diGraph, 10, 4));
        System.out.println(obj.isPath(diGraph, 10, 9));
    }
}
