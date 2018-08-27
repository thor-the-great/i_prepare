package cracking.trees_graphs;

import java.util.*;

/**
 * Created by thor on 1/8/17.
 */
public class DiGraphTwoNodesPath<V> {

    boolean isPath(DiGraph graph, int v1, int v2) {
        //return isPathDFS(graph, v1, v2);
        //return isPathBFS(graph, v1, v2);
        List<Integer> path = getPathFromTo(graph, v1, v2);
        return  path.size() > 0 ;
    }

    boolean isPath (DiGraphGeneric graph, V v1, V v2) {
        //return isPathDFS(graph, v1, v2);
        //return isPathBFS(graph, v1, v2);
        List<V> path = getPathFromTo(graph, v1, v2);
        return  path.size() > 0 ;
    }

    List<V> getPathFromTo(DiGraphGeneric<V> graph, V v1, V v2) {
        //based on BFS traversal
        boolean isPath = false;
        boolean visitedVertices[] = new boolean[graph.getV()];
        Map<Integer, V> maping = new HashMap();
        int fromV = -1;
        int toV = -1;
        for (int i = 0; i < graph.vertices.size(); i++) {
            maping.put(i, graph.vertices.get(i));
            if (v1.equals(graph.vertices.get(i))) {
                fromV = i;
            }
            if (v2.equals(graph.vertices.get(i))) {
                toV = i;
            }
        }
        Map<V, Integer> mapingBack = new HashMap();
        for (int i = 0; i < graph.vertices.size(); i++) {
            mapingBack.put(graph.vertices.get(i), i);
        }
        //keeps direct path between nodes in BFS
        int edgeTo[] = new int[graph.getV()];
        Arrays.fill(edgeTo, -1);
        visitedVertices[fromV] = true;
        Queue<Integer> vertices = new LinkedList<>();
        vertices.add(fromV);
        while (!vertices.isEmpty()) {
            int vertex = vertices.poll();
            V vertexType = maping.get(vertex);
            List<V> adjToV = graph.adj(vertexType);
            for (V adjVertex : adjToV) {
                int adjVertexInt = mapingBack.get(adjVertex);
                if (!visitedVertices[adjVertexInt]) {
                    edgeTo[adjVertexInt] = vertex;
                    if (adjVertex == v2) {
                        isPath = true;
                        break;
                    }
                    vertices.add(adjVertexInt);
                    visitedVertices[adjVertexInt] = true;
                }
            }
        }
        if (!isPath) {
            return Collections.EMPTY_LIST;
        }
        int startPoint = toV;
        Stack<Integer> pathStack = new Stack<>();
        pathStack.add(toV);
        while (edgeTo[startPoint] != -1) {
            pathStack.add(edgeTo[startPoint]);
            startPoint = edgeTo[startPoint];
        }
        List<V> path = new ArrayList();
        while (!pathStack.isEmpty()) {
            path.add(maping.get(pathStack.pop()));
        }
        return path;
    }

    List<Integer> getPathFromTo(DiGraph graph, int v1, int v2) {
        //based on BFS traversal
        boolean isPath = false;
        boolean visitedVertices[] = new boolean[graph.getV()];
        //keeps direct path between nodes in BFS
        int edgeTo[] = new int[graph.getV()];
        Arrays.fill(edgeTo, -1);
        visitedVertices[v1] = true;
        Queue<Integer> vertices = new LinkedList<>();
        vertices.add(v1);
        while (!vertices.isEmpty()) {
            int vertex = vertices.poll();
            List<Integer> adjToV = graph.adj(vertex);
            for (int adjVertex : adjToV) {
                if (!visitedVertices[adjVertex]) {
                    edgeTo[adjVertex] = vertex;
                    if (adjVertex == v2) {
                        isPath = true;
                        break;
                    }
                    vertices.add(adjVertex);
                }
            }
        }
        if (!isPath) {
            return Collections.EMPTY_LIST;
        }
        int startPoint = v2;
        Stack<Integer> pathStack = new Stack<>();
        pathStack.add(v2);
        while (edgeTo[startPoint] != -1) {
            pathStack.add(edgeTo[startPoint]);
            startPoint = edgeTo[startPoint];
        }
        List path = new ArrayList();
        while (!pathStack.isEmpty())
            path.add(pathStack.pop());
        return path;
    }

    boolean isPathDFS(DiGraph graph, int v1, int v2) {
        boolean isPath = false;
        boolean visitedVertices[] = new boolean[graph.getV()];
        visitedVertices[v1] = true;
        Stack<Integer> vertices = new Stack();
        vertices.push(v1);
        while (!vertices.empty()) {
            int vertex = vertices.pop();
            List<Integer> adjToV = graph.adj(vertex);
            for (int adjVertex : adjToV) {
                if (!visitedVertices[adjVertex]) {
                    if (adjVertex == v2) {
                        isPath = true;
                        break;
                    }
                    vertices.push(adjVertex);
                }
            }
        }
        return isPath;
    }

    boolean isPathBFS(DiGraph graph, int v1, int v2) {
        boolean isPath = false;
        boolean visitedVertices[] = new boolean[graph.getV()];
        visitedVertices[v1] = true;
        Queue<Integer> vertices = new LinkedList<>();
        vertices.add(v1);
        while (!vertices.isEmpty()) {
            int vertex = vertices.poll();
            List<Integer> adjToV = graph.adj(vertex);
            for (int adjVertex : adjToV) {
                if (!visitedVertices[adjVertex]) {
                    if (adjVertex == v2) {
                        isPath = true;
                        break;
                    }
                    vertices.add(adjVertex);
                }
            }
        }
        return isPath;
    }

    public static void main(String[] args) {
        //DiGraph G = new DiGraph(6);
        DiGraphGeneric<Integer> G = new DiGraphGeneric();
        G.addVertex(0);
        G.addVertex(1);
        G.addVertex(2);
        G.addVertex(3);
        G.addVertex(4);
        G.addVertex(5);

        G.addEdge(0, 1);
        G.addEdge(0, 4);
        G.addEdge(0, 5);
        G.addEdge(1, 4);
        G.addEdge(1, 3);
        G.addEdge(2, 1);
        G.addEdge(3, 2);
        G.addEdge(3, 4);

        System.out.print(G);
        DiGraphTwoNodesPath<Integer> obj = new DiGraphTwoNodesPath();
        int node1 = 0;
        int node2 = 4;
        boolean isPath = obj.isPath(G, node1, node2);
        System.out.println("Find path between points " + node1 + " and " + node2 + ". Path exists " + isPath);
        printPath(G, obj, node1, node2, isPath);

        node1 = 0;
        node2 = 3;
        isPath = obj.isPath(G, node1, node2);
        System.out.println("Find path between points " + node1 + " and " + node2 + ". Path exists " + isPath);
        printPath(G, obj, node1, node2, isPath);

        node1 = 4;
        node2 = 2;
        isPath = obj.isPath(G, node1, node2);
        System.out.println("Find path between points " + node1 + " and " + node2 + ". Path exists " + isPath);
        printPath(G, obj, node1, node2, isPath);

        node1 = 2;
        node2 = 5;
        isPath = obj.isPath(G, node1, node2);
        System.out.println("Find path between points " + node1 + " and " + node2 + ". Path exists " + isPath);
        printPath(G, obj, node1, node2, isPath);


        node1 = 0;
        node2 = 2;
        isPath = obj.isPath(G, node1, node2);
        System.out.println("Find path between points " + node1 + " and " + node2 + ". Path exists " + isPath);
        printPath(G, obj, node1, node2, isPath);
    }

    private static void printPath(DiGraphGeneric g, DiGraphTwoNodesPath obj, int node1, int node2, boolean isPath) {
        if (isPath) {
            List<Integer> path = obj.getPathFromTo(g, node1, node2);
            System.out.print("Path is : ");
            if (!path.isEmpty()) {
                for (int i =0; i < path.size(); i++) {
                    System.out.print(path.get(i));
                    if ( i < path.size() - 1) {
                        System.out.print(" -> ");
                    } else {
                        System.out.print("\n");
                    }
                }
            }
        }
    }
}
