package narry_tree;

import util.Node;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * N-ary Tree Preorder Traversal
 *
 * Given an n-ary tree, return the preorder traversal of its nodes' values.
 *
 * For example, given a 3-ary tree:
 *
 * Return its preorder traversal as: [1,3,5,6,2,4].
 *
 * Note:
 *
 * Recursive solution is trivial, could you do it iteratively?
 *
 */
public class NaryTreeTraversals {

    public List<Integer> preorder(Node root) {
        List<Integer> list = new ArrayList();
        if (root == null)
            return list;

        Stack<Node> s = new Stack();
        s.push(root);
        while(!s.isEmpty()) {
            Node n = s.pop();
            list.add(n.val);

            Collections.reverse(n.children);

            for(Node ch : n.children)
                s.push(ch);
        }

        return list;
    }

}
