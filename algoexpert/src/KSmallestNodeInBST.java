import java.util.Stack;
import trees.TreeNode;

/**
 * Copyright (c) 2019 Ariba, Inc. All rights reserved. Patents pending.
 */

public class KSmallestNodeInBST {

  public TreeNode findKthSmallest(TreeNode root, int k) {
    if (root == null)
      return null;

    Stack<TreeNode> s = new Stack();
    TreeNode cur = root;

    while(!s.isEmpty() || cur != null) {
      while(cur != null) {
        s.push(cur);
        cur = cur.left;
      }
      cur = s.pop();
      k--;
      if (k == 0)
        return cur;
      cur = cur.right;
    }

    return null;
  }
}
