import cracking.trees_graphs.DiGraph;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class GraphAlgos {

    void doDFS() {
        DiGraph g = getDiGraph1();
        System.out.println("DFS graph : " + g.toString());
        //do the DFS
        int startVertex = 0;
        boolean[] visited = new boolean[g.getV()];
        Stack<Integer> stack = new Stack();
        stack.push(startVertex);
        while(!stack.isEmpty()) {
            int v = stack.pop();
            if (!visited[v]) {
                visitVertex(v);
                visited[v] = true;
                if (g.adj(v) != null && !g.adj(v).isEmpty()) {
                    for(int adjV : g.adj(v))
                    stack.push(adjV);
                }
            }
        }
    }

    void doDFSRecursive() {
        DiGraph g = getDiGraph1();
        System.out.println("\nDFS recursive graph : " + g.toString());
        //do the DFS
        boolean[] visited = new boolean[g.getV()];
        int startVertex = 0;
        doDFSRecursiveHelper(g, visited, startVertex);
    }

    void doDFSRecursiveHelper(DiGraph g, boolean[] visited, int v) {
        if (!visited[v]) {
            visitVertex(v);
            visited[v] = true;
            if (g.adj(v) != null && !g.adj(v).isEmpty()) {
                //for(int adjV : g.adj(v))
                for (int i = g.adj(v).size() - 1; i >= 0; i--) {
                    int adjV = g.adj(v).get(i);
                    doDFSRecursiveHelper(g, visited, adjV);
                }
            }
        }
    }

    void doBFS() {
        DiGraph g = getDiGraph1();
        System.out.println("BFS graph : " + g.toString());
        //do the BFS
        int startVertex = 0;
        boolean[] visited = new boolean[g.getV()];
        Queue<Integer> stack = new LinkedList<>();
        stack.add(startVertex);
        while(!stack.isEmpty()) {
            int v = stack.poll();
            if (!visited[v]) {
                visitVertex(v);
                visited[v] = true;
                if (g.adj(v) != null && !g.adj(v).isEmpty()) {
                    for(int adjV : g.adj(v))
                        stack.add(adjV);
                }
            }
        }
    }

    void doTopological() {
        DiGraph g = getDiGraph1();
        System.out.println("Topological sort : " + g.toString());
        //do the DFS
        boolean[] visited = new boolean[g.getV()];
        int startVertex = 0;
        Stack<Integer> stack = new Stack<>();
        doTopoRecursiveHelper(g, visited, startVertex, stack);

        System.out.print("topo sorted: ");
        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + ", ");
        }
    }

    void doTopoRecursiveHelper(DiGraph g, boolean[] visited, int v, Stack<Integer> stack) {
        if (!visited[v]) {
            //visitVertex(v);
            visited[v] = true;
            if (g.adj(v) != null && !g.adj(v).isEmpty()) {
                for (int i = g.adj(v).size() - 1; i >= 0; i--) {
                    int adjV = g.adj(v).get(i);
                    doTopoRecursiveHelper(g, visited, adjV, stack);
                }
            }
            stack.push(v);
        }
    }

    private void visitVertex(int v) {
        System.out.print("vertex " + v +", ");
    }

    private DiGraph getDiGraph1() {
        DiGraph g = new DiGraph(11);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(2, 3);
        g.addEdge(3, 4);
        g.addEdge(1, 5);
        g.addEdge(1, 6);
        g.addEdge(6, 7);
        g.addEdge(6, 8);
        g.addEdge(2, 9);
        g.addEdge(9, 10);
        return g;
    }

    public static void main(String[] args) {
        GraphAlgos obj = new GraphAlgos();
        //obj.doDFS();
        //obj.doBFS();
        obj.doDFSRecursive();
        obj.doTopological();
    }
}
