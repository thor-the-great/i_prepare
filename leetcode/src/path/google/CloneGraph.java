package path.google;

import util.UndirectedGraphNode;

import java.util.*;

/**
 * 133. Clone Graph
 * Medium
 * Given the head of a graph, return a deep copy (clone) of the graph. Each node in the graph contains a label (int)
 * and a list (List[UndirectedGraphNode]) of its neighbors. There is an edge between the given node and each of the
 * nodes in its neighbors.
 *
 *
 * OJ's undirected graph serialization (so you can understand error output):
 * Nodes are labeled uniquely.
 *
 * We use # as a separator for each node, and , as a separator for node label and each neighbor of the node.
 *
 *
 * As an example, consider the serialized graph {0,1,2#1,2#2,2}.
 *
 * The graph has a total of three nodes, and therefore contains three parts as separated by #.
 *
 * First node is labeled as 0. Connect node 0 to both nodes 1 and 2.
 * Second node is labeled as 1. Connect node 1 to node 2.
 * Third node is labeled as 2. Connect node 2 to node 2 (itself), thus forming a self-cycle.
 *
 *
 * Visually, the graph looks like the following:
 *
 *        1
 *       / \
 *      /   \
 *     0 --- 2
 *          / \
 *          \_/
 * Note: The information about the tree serialization is only meant so that you can understand error output if you get
 * a wrong answer. You don't need to understand the serialization to solve the problem.
 */
public class CloneGraph {
    /**
     * Do the DFS. If not is not yet visited it will be not cloned, so utilize cloneMap as visited array
     * @param node
     * @return
     */
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if (node == null)
            return null;
        Map<UndirectedGraphNode, UndirectedGraphNode> cloneMap = new HashMap();
        Stack<UndirectedGraphNode> s = new Stack();
        s.push(node);
        cloneMap.put(node,  new UndirectedGraphNode(node.label));
        while (!s.isEmpty()) {
            UndirectedGraphNode n = s.pop();
            for (UndirectedGraphNode neighbor : n.neighbors) {
                UndirectedGraphNode cloneN = cloneMap.get(neighbor);
                if (cloneN == null) {
                    cloneN = new UndirectedGraphNode(neighbor.label);
                    cloneMap.put(neighbor, cloneN);
                    s.push(neighbor);
                }
                cloneMap.get(n).neighbors.add(cloneN);
            }
        }
        return cloneMap.get(node);
    }

    public Node cloneGraph(Node node) {
        if (node == null)
            return null;

        Map<Node, Node> m = new HashMap();
        //do the BFS to create clone for each node
        Queue<Node> q = new LinkedList();
        q.add(node);
        Set<Node> seen = new HashSet();

        while(!q.isEmpty()) {
            Node n = q.poll();
            if (!seen.contains(n)) {
                seen.add(n);
                //put clone of the currently visited element
                m.put(n, new Node(n.val, new ArrayList()));
                for (Node neighbor : n.neighbors) {
                    q.add(neighbor);
                }
            }
        }
        //reset our queue for the second iteration
        q = new LinkedList();
        q.add(node);
        seen = new HashSet();
        //do the BFS second time and fill the neighbors now
        while(!q.isEmpty()) {
            Node n = q.poll();
            if (!seen.contains(n)) {
                seen.add(n);
                Node clone = m.get(n);
                for (Node neighbor : n.neighbors) {
                    q.add(neighbor);
                    clone.neighbors.add(m.get(neighbor));
                }
            }
        }
        return m.get(node);
    }
}

class Node {
    public int val;
    public List<Node> neighbors;

    public Node() {}

    public Node(int _val,List<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
