import cracking.trees_graphs.DiGraph;
import cracking.trees_graphs.SimpleGraph;

import java.util.*;

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
        Queue<Integer> q = new LinkedList<>();
        q.add(startVertex);
        while(!q.isEmpty()) {
            int v = q.poll();
            if (!visited[v]) {
                visitVertex(v);
                visited[v] = true;
                if (g.adj(v) != null && !g.adj(v).isEmpty()) {
                    for(int adjV : g.adj(v))
                        q.add(adjV);
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

    /**
     * Detect cycles in digraph by usage of DFS. Keep recursion stack set of nodes, if some node met second time - this
     * is a cycle
     * @param g
     * @param startVertex
     */
    void detectCycleDiGraphDFS(DiGraph g, int startVertex) {
        Set<Integer> visited = new HashSet<>();
        Set<Integer> recStack = new HashSet<>();

        if (dfsCycle(visited, recStack, startVertex, g)) {
            System.out.println("\nCycle detected !!!");
            recStack.forEach(i -> System.out.print(i + " -> "));
        } else
            System.out.print("\nNo cycles detected");
    }

    void detectCycleDiGraphDFS(DiGraph g) {
        detectCycleDiGraphDFS(g, 0);
    }

    boolean dfsCycle(Set<Integer> visited, Set<Integer> recStack, int vertex, DiGraph graph) {
        if (recStack.contains(vertex))
            return true;

        if (visited.contains(vertex))
            return false;

        recStack.add(vertex);
        visited.add(vertex);
        List<Integer> adjList = graph.adj(vertex);
        if ( adjList != null && !adjList.isEmpty()) {
            for (int adj : adjList) {
                if (dfsCycle(visited, recStack, adj, graph))
                    return true;
            }
        }
        recStack.remove(vertex);
        return false;
    }

    public void detectCycleUndirGraphUF(SimpleGraph uniGraph) {
        UnionFind uf = new UnionFind(uniGraph.getV());
        for(SimpleGraph.Edge e : uniGraph.getEdges()) {
            int u = e.u;
            int v = e.v;
            if (uf.find(v) == uf.find(u)) {
                System.out.println("\nCycle detected!!");
                return;
            }
            uf.union(u, v);
        }
        System.out.println("\nNo cycles detected");
    }

    long[] shortestPathsDjikstra(DiGraph graph, int source) {
        boolean[] visited = new boolean[graph.getV()];
        long[] paths = new long[graph.getV()];
        Arrays.fill(paths, Integer.MAX_VALUE);
        paths[source] = 0;
        int start = source;
        while (!visited[start]) {
            visited[start] = true;
            List<DiGraph.Edge> adjEdges = graph.adjEdges(start);
            if (!adjEdges.isEmpty()) {
                for(DiGraph.Edge edge : adjEdges) {
                    int adjVertex = edge.v;
                    if (paths[adjVertex] > paths[start] + edge.weight) {
                        paths[adjVertex] = paths[start] + edge.weight;
                    }
                }
            }
            long d = Integer.MAX_VALUE;
            for (int i = 0; i < graph.getV(); i++) {
                if (!visited[i] && d > paths[i]) {
                    d = paths[i];
                    start = i;
                }
            }
        }
        return paths;
    }

    long[] shortestPathsBellmanFord(DiGraph graph, int source) {
        long[] paths = new long[graph.getV()];
        Arrays.fill(paths, Integer.MAX_VALUE);
        paths[source] = 0;
        for (int i = 0; i < graph.getV() - 1; i++) {
            for(DiGraph.Edge edge : graph.adjAllEdges()) {
                int u = edge.u;
                int v = edge.v;
                if (paths[u] + edge.weight < paths[v])
                    paths[v] = paths[u] + edge.weight;
            }
        }
        return paths;
    }

    boolean negativeCycleDetectBellmanFord(DiGraph graph) {
        int source = 0;
        long[] paths = new long[graph.getV()];
        Arrays.fill(paths, Integer.MAX_VALUE);
        paths[source] = 0;
        //do the BellmanFord n - 1 times
        for (int i = 0; i < graph.getV() - 1; i++) {
            for(DiGraph.Edge edge : graph.adjAllEdges()) {
                int u = edge.u;
                int v = edge.v;
                if (paths[u] + edge.weight < paths[v])
                    paths[v] = paths[u] + edge.weight;
            }
        }

        for(DiGraph.Edge edge : graph.adjAllEdges()) {
            int u = edge.u;
            int v = edge.v;
            if (paths[u] + edge.weight < paths[v])
                return true;
        }
        return false;
    }

    long[] shortestPathsFloydWarshall(DiGraph graph, int source) {
        long[][] dp = new long[graph.getV()][graph.getV()];
        for (int i = 0; i < graph.getV(); i++) {
            List<DiGraph.Edge> edges = graph.adjEdges(i);
            for (int j = 0; j < graph.getV(); j++) {
                if (i != j) {
                    boolean found = false;
                    for (DiGraph.Edge e : edges) {
                        if (e.v == j) {
                            dp[i][j] = e.weight;
                            found = true;
                            break;
                        }
                    }
                    if (!found)
                        dp[i][j] = Integer.MAX_VALUE;
                }
            }
        }

        for (int k = 0; k < graph.getV(); k++) {
            for (int i = 0; i < graph.getV(); i++) {
                for (int j = 0; j < graph.getV(); j++) {
                    if (dp[i][k] + dp[k][j] < dp[i][j]) {
                        dp[i][j] = dp[i][k] + dp[k][j];
                    }
                }
            }
        }
        return dp[source];
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

    private DiGraph getDiGraph2() {
        DiGraph g = new DiGraph(7);
        g.addEdge(0, 1);
        g.addEdge(1, 2);
        g.addEdge(1, 4);
        g.addEdge(2, 5);
        g.addEdge(4, 5);
        g.addEdge(3, 1);
        g.addEdge(5, 6);
        g.addEdge(6, 3);
        return g;
    }

    private DiGraph getDiGraph3() {
        DiGraph g = new DiGraph(7);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        return g;
    }

    private SimpleGraph getUniGraph1() {
        SimpleGraph g = new SimpleGraph(7);
        g.addEdge(0, 3);
        g.addEdge(1, 3);
        g.addEdge(0, 2);
        g.addEdge(2, 4);
        g.addEdge(2, 5);
        g.addEdge(2, 6);
        return g;
    }

    private SimpleGraph getUniGraph2() {
        SimpleGraph g = new SimpleGraph(3);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(2, 1);
        return g;
    }

    private DiGraph getDiGraphWeighted1() {
        DiGraph g = new DiGraph(6);
        g.addEdge(0, 1, 6);
        g.addEdge(0, 2, 2);
        g.addEdge(2, 1, 1);
        g.addEdge(1, 3, 1);
        g.addEdge(1, 4, 3);
        g.addEdge(2, 3, 4);
        g.addEdge(2, 4, 6);
        g.addEdge(3, 5, 4);
        g.addEdge(3, 4, 2);
        g.addEdge(4, 5, 1);
        return g;
    }

    private DiGraph getDiGraphWeighted2() {
        DiGraph g = new DiGraph(4);
        g.addEdge(0, 1, 3);
        g.addEdge(0, 2, 8);
        g.addEdge(1, 3, 1);
        g.addEdge(2, 1, 4);
        g.addEdge(3, 0, 2);
        g.addEdge(3, 2, -5);
        return g;
    }

    private DiGraph getDiGraphWeightedNeg1() {
        DiGraph g = new DiGraph(5);
        g.addEdge(0, 1, 6);
        g.addEdge(0, 2, 7);
        g.addEdge(1, 2, 8);

        g.addEdge(1, 3, 5);
        g.addEdge(1, 4, -4);

        g.addEdge(2, 3, -3);
        g.addEdge(2, 4, 9);

        g.addEdge(3, 1, -2);

        g.addEdge(4, 0, 2);
        g.addEdge(4, 3, 7);
        return g;
    }

    private DiGraph getDiGraphWeightedNegCycle2() {
        DiGraph g = new DiGraph(5);
        g.addEdge(0, 1, 6);
        g.addEdge(0, 2, 7);
        g.addEdge(1, 2, 8);

        g.addEdge(1, 3, -5);
        g.addEdge(1, 4, -4);

        g.addEdge(2, 3, -3);
        g.addEdge(2, 4, 9);

        g.addEdge(3, 1, -2);

        g.addEdge(4, 0, 2);
        g.addEdge(4, 3, 7);
        return g;
    }

    public static void main(String[] args) {
        GraphAlgos obj = new GraphAlgos();
        //obj.doDFS();
        //obj.doBFS();
        obj.doDFSRecursive();
        obj.doTopological();

        obj.detectCycleDiGraphDFS(obj.getDiGraph1());
        obj.detectCycleDiGraphDFS(obj.getDiGraph2(), 4);
        obj.detectCycleDiGraphDFS(obj.getDiGraph3());

        obj.detectCycleUndirGraphUF(obj.getUniGraph1());//no cycle
        obj.detectCycleUndirGraphUF(obj.getUniGraph2());//cycle

        long[] paths = obj.shortestPathsDjikstra(obj.getDiGraphWeighted1(), 0);
        System.out.print("\nShortest paths getDiGraphWeighted1 [0] : ");
        Arrays.stream(paths).forEach(i-> System.out.print(i + " "));

        paths = obj.shortestPathsBellmanFord(obj.getDiGraphWeighted1(), 0);
        System.out.print("\nShortest paths Bellman-Ford getDiGraphWeighted1 [0] : ");
        Arrays.stream(paths).forEach(i-> System.out.print(i + " "));

        paths = obj.shortestPathsFloydWarshall(obj.getDiGraphWeighted1(), 0);
        System.out.print("\nShortest paths Floyd Warshall getDiGraphWeighted1 [0] : ");
        Arrays.stream(paths).forEach(i-> System.out.print(i + " "));

        paths = obj.shortestPathsDjikstra(obj.getDiGraphWeighted1(), 2);
        System.out.print("\nShortest paths getDiGraphWeighted1 [2] : ");
        Arrays.stream(paths).forEach(i-> System.out.print(i + " "));

        paths = obj.shortestPathsDjikstra(obj.getDiGraphWeightedNeg1(), 0);
        System.out.print("\nShortest paths getDiGraphWeightedNeg1 [0] : ");
        Arrays.stream(paths).forEach(i-> System.out.print(i + " "));

        paths = obj.shortestPathsFloydWarshall(obj.getDiGraphWeightedNeg1(), 0);
        System.out.print("\nShortest paths Floyd Warshall getDiGraphWeightedNeg1 [0] : ");
        Arrays.stream(paths).forEach(i-> System.out.print(i + " "));

        paths = obj.shortestPathsBellmanFord(obj.getDiGraphWeightedNeg1(), 0);
        System.out.print("\nShortest paths Bellman-Ford getDiGraphWeightedNeg1 [0] : ");
        Arrays.stream(paths).forEach(i-> System.out.print(i + " "));

        paths = obj.shortestPathsDjikstra(obj.getDiGraphWeightedNeg1(), 3);
        System.out.print("\nShortest paths getDiGraphWeightedNeg1 [3] : ");
        Arrays.stream(paths).forEach(i-> System.out.print(i + " "));

        paths = obj.shortestPathsBellmanFord(obj.getDiGraphWeightedNeg1(), 1);
        System.out.print("\nShortest paths Bellman-Ford getDiGraphWeightedNeg1 [1] : ");
        Arrays.stream(paths).forEach(i-> System.out.print(i + " "));

        paths = obj.shortestPathsFloydWarshall(obj.getDiGraphWeighted2(), 0);
        System.out.print("\nShortest paths Floyd Warshall getDiGraphWeighted2 [0] : ");
        Arrays.stream(paths).forEach(i-> System.out.print(i + " "));

        System.out.print("\nCycle detection Floyd Warshall getDiGraphWeightedNeg1 : " +
                obj.negativeCycleDetectBellmanFord(obj.getDiGraphWeightedNeg1()));
        System.out.print("\nCycle detection Floyd Warshall getDiGraphWeightedNegCycle2 : " +
                obj.negativeCycleDetectBellmanFord(obj.getDiGraphWeighted2()));
        System.out.print("\nCycle detection Floyd Warshall getDiGraphWeighted2 : " +
                obj.negativeCycleDetectBellmanFord(obj.getDiGraphWeightedNegCycle2()));
    }
}

class UnionFind {
    int[] sets;
    int[] rank;

    UnionFind(int numOfSets) {
        sets = new int[numOfSets];
        for (int i = 0; i < sets.length; i++) {
            sets[i] = i;
        }
        rank = new int[numOfSets];
    }

    int find(int set) {
        while(sets[set] != set) {
            set = sets[set];
        }
        return set;
    }

    void union(int set1, int set2) {
        int set1Parent = find(set1);
        int set2Parent = find(set2);
        if (set1Parent != set2Parent) {
            int set1Rank = rank[set1Parent];
            int set2Rank = rank[set2Parent];
            if (set1Rank > set2Rank)
                sets[set2Parent] = set1Parent;
            else if (set2Rank > set1Rank)
                sets[set1Parent] = set2Parent;
            else {
                rank[set1Parent]+=1;
                sets[set2Parent] = set1Parent;
            }
        }
    }
}
