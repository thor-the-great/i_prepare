package cracking.trees_graphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by thor on 1/8/17.
 */
public class SimpleGraph {
    List<Integer>[] vertices;
    List<Edge> edges;
    int E;

    public SimpleGraph(int V) {
        vertices = new ArrayList[V];
        for(int i = 0 ; i < V; i++) {
            vertices[i] = new ArrayList<Integer>();
        }
        edges = new LinkedList();
    }

    public void addEdge(int v, int v2) {
        checkVertexNumber(v);
        checkVertexNumber(v2);
        vertices[v].add(v2);
        vertices[v2].add(v);
        E++;
        this.edges.add(new Edge(v, v2));
    }

    public List<Integer> adj(int v) {
        checkVertexNumber(v);
        return  vertices[v];
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

    public List<Edge> getEdges() {
        return this.edges;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Graph has " + getV() + " vertices and " + getE() + " edges\n");
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

    public static void main(String[] args) {
        SimpleGraph G = new SimpleGraph(6);
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

    public class Edge {
        public int u, v;
        Edge(int u, int v) {
            this.u = u;
            this.v = v;
        }
    }
}
