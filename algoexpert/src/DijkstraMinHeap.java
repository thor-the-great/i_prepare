import java.util.*;

/**
 * Implement the Djikstra Shortest Path Algorithm in a Graph New
 *     Graphs Trees Search Algorithms
 *
 * Implement a method to compute the shortest path from source to target in a graph using Djikstra Algorithm.
 * The method should return a List of Vertices denoting the optimal path. Click "Use Me" to understand the Vertex and
 * Edge classes.
 */
public class DijkstraMinHeap {
    /**
     *
     * @param source
     * @param target
     * @return
     */
    public static List<Vertex> getShortestPath(Vertex source, Vertex target) {
        LinkedList<Vertex> res = new LinkedList();
        source.minDistance = 0.0;
        Set<Vertex> added = new HashSet();
        PriorityQueue<Vertex> pq = new PriorityQueue<>();
        Stack<Vertex> s = new Stack();
        s.push(source);
        //put all vertexes to the pq. Use set to count those that we have seen
        while(!s.isEmpty()) {
            Vertex next = s.pop();
            if (!added.contains(next)) {
                added.add(next);
                pq.add(next);
                for (Edge adj : next.adjacencies) {
                    s.push(adj.target);
                }
            }
        }
        //relax edge one by one
        while(!pq.isEmpty()) {
            Vertex cur = pq.poll();
            for (Edge adjEdge : cur.adjacencies) {
                //calculate distance to adj using this edge
                double tempDist = cur.minDistance + adjEdge.weight;
                Vertex adj = adjEdge.target;
                //check if new distance is shorted than the one we previously had
                if (tempDist < adj.minDistance) {
                    //if new distance is better - update the vertex in the pq
                    adj.minDistance =  tempDist;
                    adj.previous = cur;
                    //recompute pq
                    pq.remove(adj);
                    pq.add(adj);
                }
            }
        }
        //construct the result path
        Vertex prev = target;
        while (!prev.name.equals(source.name)) {
            res.addFirst(prev);
            prev = prev.previous;
        }
        res.addFirst(prev);

        return res;
    }
}

// Some methods and signatures you'll find useful.
// Click 'Hide' to hide me.


class Vertex implements Comparable<Vertex> {
    public final String name;
    public Edge[] adjacencies;
    public double minDistance = Double.POSITIVE_INFINITY;
    public Vertex previous; // Previous optimal path node

    public Vertex(String argName) {
        name = argName;
    }

    public String toString() {
        return name;
    }

    public int compareTo(Vertex other) {
        return Double.compare(minDistance, other.minDistance);
    }
}

class Edge {
    public final Vertex target; // Target node of the edge
    public final double weight; // Edge weight

    public Edge(Vertex argTarget, double argWeight) {
        target = argTarget;
        weight = argWeight;
    }
}

