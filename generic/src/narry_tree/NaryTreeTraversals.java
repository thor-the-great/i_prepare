package narry_tree;

import util.NaryTreeNode;

import java.util.*;

public class NaryTreeTraversals {

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
    public List<Integer> preorder(NaryTreeNode root) {
        List<Integer> list = new ArrayList();
        if (root == null)
            return list;

        Stack<NaryTreeNode> s = new Stack();
        s.push(root);
        while(!s.isEmpty()) {
            NaryTreeNode n = s.pop();
            list.add(n.val);

            Collections.reverse(n.children);

            for(NaryTreeNode ch : n.children)
                s.push(ch);
        }

        return list;
    }

    public List<Integer> postorder(NaryTreeNode root) {
        LinkedList<Integer> res = new LinkedList();
        if (root == null)
            return res;

        Stack<NaryTreeNode> s = new Stack();
        s.push(root);

        while(!s.isEmpty()) {
            NaryTreeNode n = s.pop();
            res.addFirst(n.val);

            for (NaryTreeNode child : n.children) {
                s.push(child);
            }
        }

        return res;
    }

    List<List<Integer>> res;

    public List<List<Integer>> levelOrder(NaryTreeNode root) {
        res = new LinkedList();

        if (root == null)
            return res;

        helper(0, root);

        return res;
    }

    void helper(int level, NaryTreeNode n) {
        if (res.size() - 1 < level) {
            res.add(new ArrayList());
        }
        res.get(level).add(n.val);

        for (NaryTreeNode child : n.children) {
            helper(level + 1, child);
        }
    }

}
