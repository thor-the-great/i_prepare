package trees;

import diff_problems.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class TreeUtils {

    public static String binaryTreeToString(TreeNode node) {
        StringBuilder sb = new StringBuilder();
        Queue<TreeNode> q = new LinkedList();
        Queue<Integer> qLevel = new LinkedList<>();
        if (node != null) {
            q.add(node);
            qLevel.add(1);
            int level = 0;
            while(!q.isEmpty()) {
                int l = qLevel.poll();
                TreeNode n = q.poll();
                if (l > level) {
                    sb.append("\n");
                    level = l;
                }
                if (n == null) {
                    sb.append("null").append(" (").append(level).append(") ");
                    continue;
                }
                sb.append(n.val).append(" (").append(level).append(") ");
                if (n.left == null && n.right == null) continue;
                //if (n.left != null) {
                    q.add(n.left);
                    qLevel.add(level + 1);
                //}
                //if (n.right != null) {
                    q.add(n.right);
                    qLevel.add(level + 1);
                //}
            }
        }
        return sb.toString();
    }
}
