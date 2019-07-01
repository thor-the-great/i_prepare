package graphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by thor on 1/8/17.
 */
public class DiGraph {
    List<Integer>[] vertices;
    List<Edge>[] edges;
    int E;

    public DiGraph(int V) {
        vertices = new ArrayList[V];
        for(int i = 0 ; i < V; i++) {
            vertices[i] = new ArrayList<>();
        }
        edges = new ArrayList[V];
        for(int i = 0 ; i < V; i++) {
            edges[i] = new ArrayList<>();
        }
    }

    public void addEdge(int v, int v2) {
        addEdge(v, v2, 1);
    }

    public void addEdge(int v, int v2, int w) {
        checkVertexNumber(v);
        checkVertexNumber(v2);
        vertices[v].add(v2);
        edges[v].add(new Edge(v, v2, w));
        E++;
    }

    public List<Integer> adj(int v) {
        checkVertexNumber(v);
        return  vertices[v];
    }

    public List<Edge> adjEdges(int v) {
        checkVertexNumber(v);
        return  edges[v];
    }

    public List<Edge> adjAllEdges() {
        List<Edge> allEdges = new LinkedList<>();
        for (int i = 0; i < getV(); i++) {
            allEdges.addAll(edges[i]);
        }
        return allEdges;
    }

    private void checkVertexNumber(int v) {
        if (v > getV()) {
            throw new RuntimeException("Incorrect vertex number");
        }
    }

    public int getV() {
        return vertices.length;
    }

    public int getE() {
        return E;
    }

    public List<Edge>[] getEdges() {
        return edges;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Directed graph has " + getV() + " vertices and " + getE() + " edges\n");
        int vertexCounter = 0;
        for (List<Integer> adj: vertices) {
            if (adj.size() > 0 ) {
                sb.append(vertexCounter).append(" -> ");
                for (Integer adjVertex : adj) {
                    sb.append(adjVertex).append(", ");
                }
            } else {
                sb.append(vertexCounter);
            }
            sb.append("\n");
            vertexCounter++;
        }
        return sb.toString();
    }

    public class Edge {
        public int from, to;
        public int weight;
        Edge(int u, int v, int w) {
            this.from = u;
            this.to = v;
            this.weight = w;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("( ").append(from).append(" -> ").append(to).append(", ").append(weight).append(" )");
            return sb.toString();
        }
    }

    public static void main(String[] args) {
        DiGraph G = new DiGraph(6);
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