package graphs;

import cracking.trees_graphs.DiGraph;

public class GraphUtils {

    public static DiGraph getDiGraphWeighted1() {
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

    public static DiGraph getDiGraphWeighted2() {
        DiGraph g = new DiGraph(4);
        g.addEdge(0, 1, 3);
        g.addEdge(0, 2, 8);
        g.addEdge(1, 3, 1);
        g.addEdge(2, 1, 4);
        g.addEdge(3, 0, 2);
        g.addEdge(3, 2, -5);
        return g;
    }

    public static DiGraph getDiGraphWeighted3() {
        DiGraph g = new DiGraph(5);
        g.addEdge(0, 2, 2);
        g.addEdge(0, 3, 3);
        g.addEdge(1, 3, -1);
        g.addEdge(2, 1, 1);
        g.addEdge(3, 2, 2);
        g.addEdge(2, 4, 3);
        g.addEdge(3, 4, 4);
        return g;
    }

    public static DiGraph getDiGraphWeighted4() {
        DiGraph g = new DiGraph(4);
        g.addEdge(0, 1, 1);
        g.addEdge(0, 2, 4);
        g.addEdge(0, 3, 6);
        g.addEdge(1, 2, 2);
        g.addEdge(1, 3, 5);
        g.addEdge(2, 3, 2);
        return g;
    }

    public static DiGraph getDiGraphWeightedNeg1() {
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

    public static DiGraph getDiGraphWeightedNegCycle2() {
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

    public static DiGraph getTopoOrderGraph1() {
        DiGraph g = new DiGraph(6);
        g.addEdge(2, 3);
        g.addEdge(3, 1);
        g.addEdge(4, 0);
        g.addEdge(4, 1);
        g.addEdge(5, 0);
        g.addEdge(5, 2);

        return g;
    }
}
