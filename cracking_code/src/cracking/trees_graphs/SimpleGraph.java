package cracking.trees_graphs;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by thor on 1/8/17.
 */
public class SimpleGraph {
    List<Integer>[] vertices;
    int E;

    SimpleGraph(int V) {
        vertices = new ArrayList[V];
        for(int i = 0 ; i < V; i++) {
            vertices[i] = new ArrayList<Integer>();
        }
    }

    void addEdge(int v, int v2) {
        checkVertexNumber(v);
        checkVertexNumber(v2);
        vertices[v].add(v2);
        vertices[v2].add(v);
        E++;
    }

    List<Integer> adj(int v) {
        checkVertexNumber(v);
        return  vertices[v];
    }

    private void checkVertexNumber(int v) {
        if (v > getV()) {
            throw new RuntimeException("Incorrect vertex number");
        }
    }

    int getV() {
        return vertices.length;
    }

    int getE() {
        return E;
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
}
