package trees;

import java.util.List;
import java.util.Stack;

/**
 * 559. Maximum Depth of N-ary Tree
 * Easy
 *
 * Given a n-ary tree, find its maximum depth.
 *
 * The maximum depth is the number of nodes along the longest path from the root node down to the
 * farthest leaf node.
 *
 * For example, given a 3-ary tree:
 *
 *
 *
 *
 *
 *
 * We should return its max depth, which is 3.
 *
 *
 *
 * Note:
 *
 * The depth of the tree is at most 1000.
 * The total number of nodes is at most 5000.
 */
public class MaximumDepthofNaryTree {

  int res = 0;

  /**
   * Do the DFS (iteratively or recursively) and increment depth at every step. When reach the tree
   * leaf - compare current depth with max so far.
   * @param root
   * @return
   */
  public int maxDepth(Node root) {
    return maxDepthRecursive(root);
  }

  public int maxDepthRecursive(Node root) {
    if (root == null)
      return 0;
    dfs(root, 0);
    return res;
  }

  public int maxDepthIterative(Node root) {
    if (root == null)
      return 0;
    Stack<Pair> nodes = new Stack<>();
    nodes.push(new Pair(root, 0));
    int res = 0;
    while(!nodes.empty()) {
      Pair p = nodes.pop();
      int d = p.depth + 1;
      if (p.node.children == null || p.node.children.isEmpty()) {
        res = Math.max(res, d);
      }
      else {
        for (Node child : p.node.children) {
          nodes.push(new Pair(child, d));
        }
      }
    }
    return res;
  }

  class Pair {
    Node node;
    int depth;

    Pair(Node n, int d) {
      node = n;
      depth = d;
    }
  }

  void dfs(Node n, int d) {
    d++;
    if (n.children == null || n.children.isEmpty()) {
      res = Math.max(res, d);
      return;
    }
    for (Node child : n.children) {
      dfs(child, d);
    }
  }
}

class Node {
  public int val;
  public List<Node> children;

  public Node() {}

  public Node(int _val,List<Node> _children) {
    val = _val;
    children = _children;
  }
};