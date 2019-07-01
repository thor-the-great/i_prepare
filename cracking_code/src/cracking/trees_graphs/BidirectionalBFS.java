package cracking.trees_graphs;

import java.util.*;
import graphs.SimpleGraph;

/**
 * Bidirectional BFS search of path in graph. Shortest path in O(k^(q/2)) time
 */
public class BidirectionalBFS {

    LinkedList<Integer> findPath(SimpleGraph gPeople, int source, int destination) {
        BFSData sourceData = new BFSData(source);
        BFSData destinationData = new BFSData(destination);

        while (!destinationData.isFinished() && !sourceData.isFinished()) {
            int collision = searchLevel(sourceData, destinationData, gPeople);

            if (collision != -1)
                return mergePath(sourceData, destinationData, collision);

            collision = searchLevel(destinationData, sourceData, gPeople);

            if (collision != -1)
                return mergePath(sourceData, destinationData, collision);
        }
        return null;
    }

    LinkedList<Integer> mergePath(BFSData bfs1, BFSData bfs2, int connection) {
        PathNode end1 = bfs1.visited.get(connection);
        PathNode end2 = bfs2.visited.get(connection);
        LinkedList<Integer> pathOne = end1.collapse(false);
        LinkedList<Integer> pathTwo = end2.collapse(true);
        pathTwo.removeFirst();
        pathOne.addAll(pathTwo);
        return pathOne;
    }

    int searchLevel(BFSData primary, BFSData secondary, SimpleGraph graph) {
        int count = primary.toVisit.size();
        for (int i = 0; i < count; i++) {
            PathNode pathNode = primary.toVisit.poll();
            int personId = pathNode.getVal();

            if (secondary.visited.containsKey(personId))
                return pathNode.getVal();

            int person = pathNode.getVal();
            List<Integer> friends = graph.adj(person);
            for (int friend : friends) {
                if(!primary.visited.containsKey(friend)) {
                    PathNode next = new PathNode(friend, pathNode);
                    primary.visited.put(friend, next);
                    primary.toVisit.add(next);
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        BidirectionalBFS obj = new BidirectionalBFS();
        SimpleGraph graph = new SimpleGraph(20);
        graph.addEdge(0, 4);
        graph.addEdge(1, 4);
        graph.addEdge(4, 6);
        graph.addEdge(5, 6);
        graph.addEdge(6, 7);
        graph.addEdge(7, 8);

        graph.addEdge(8, 9);
        graph.addEdge(8, 10);
        graph.addEdge(10, 13);
        graph.addEdge(9, 11);

        graph.addEdge(4, 14);
        graph.addEdge(14, 15);
        graph.addEdge(15, 16);
        graph.addEdge(16, 17);
        graph.addEdge(17, 18);
        graph.addEdge(18, 19);
        graph.addEdge(19, 11);

        List<Integer> list = obj.findPath(graph, 0, 11);
        if (list == null)
            System.out.println("no path found");
        else {
            System.out.println("path found: ");
            for (int node : list)
                System.out.print(node + ", ");
        }
    }

}

class PathNode {
    Integer val;
    PathNode prevNode = null;
    PathNode(int val, PathNode prevNode) {
        this.val = val;
        this.prevNode = prevNode;
    }

    int getVal() {
        return this.val;
    }

    LinkedList<Integer> collapse(boolean startsWithRoot) {
        LinkedList<Integer> path = new LinkedList();
        PathNode node = this;
        while (node != null) {
            if (startsWithRoot)
                path.addLast(node.val);
            else
                path.addFirst(node.val);
            node = node.prevNode;
        }
        return path;
    }
}

class BFSData {
    Queue<PathNode> toVisit = new LinkedList();
    HashMap<Integer, PathNode> visited = new HashMap<>();

    BFSData(Integer root) {
        PathNode soursePath = new PathNode(root, null);
        toVisit.add(soursePath);
        visited.put(root, soursePath);
    }

    boolean isFinished() {
        return toVisit.isEmpty();
    }
}