package cracking.trees_graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by thor on 1/8/17.
 */
public class DiGraphGeneric<V> {
    List<V> vertices;
    Map<V, List<Edge<V>>> edges;
    int E;

    DiGraphGeneric() {
        vertices = new ArrayList<>();
        edges = new HashMap<>();
    }

    void addVertex(V vertex) {
        vertices.add(vertex);
    }

    void addEdge(V v1, V v2) {
        checkVertex(v1);
        checkVertex(v2);
        Edge<V> newEdge = new Edge<>(v1, v2);
        List<Edge<V>> edgeForVertex = edges.get(v1);
        if (edgeForVertex == null) {
            edgeForVertex = new ArrayList<>();
            edges.put(v1, edgeForVertex);
        }
        edgeForVertex.add(newEdge);
        E++;
    }

    List<V> adj(V vertex) {
        checkVertex(vertex);
        List<Edge<V>> edgesForVertex = edges.get(vertex);
        List<V> adjList = new ArrayList<>();
        if (edgesForVertex != null) {
            for (Edge<V> oneEdge : edgesForVertex) {
                adjList.add(oneEdge.to);
            }
        }
        return  adjList;
    }

    private void checkVertex(V vertex) {
        if (!vertices.contains(vertex)) {
            throw new RuntimeException("Incorrect vertex number");
        }
    }

    int getV() {
        return vertices.size();
    }

    int getE() {
        return E;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Directed graph has " + getV() + " vertices and " + getE() + " edges\n");
        //int vertexCounter = 0;
        for (V vertex : vertices) {
            List<Edge<V>> edgesAdj = edges.get(vertex);
            if (edgesAdj == null || edgesAdj.size() == 0) {
                sb.append(vertex);
            } else {
                sb.append(vertex).append(" -> ");
                for (Edge<V> adjEdge : edgesAdj) {
                    sb.append(adjEdge.to).append(", ");
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    class Edge <V> {
        V from;
        V to;
        Edge(V from, V to) {
            this.from = from;
            this.to = to;
        }
    }

    public static void main(String[] args) {
        DiGraphGeneric<Integer> G = new DiGraphGeneric();
        G.addVertex(0);
        G.addVertex(1);
        G.addVertex(2);
        G.addVertex(3);
        G.addVertex(4);
        G.addVertex(5);

        G.addEdge(0,1);
        G.addEdge(0,4);
        G.addEdge(0,5);
        G.addEdge(1,4);
        G.addEdge(1,3);
        G.addEdge(2,1);
        G.addEdge(3,2);
        G.addEdge(3,4);

        System.out.print(G);
    }
}