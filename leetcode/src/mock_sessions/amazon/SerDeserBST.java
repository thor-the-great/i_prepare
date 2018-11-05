package mock_sessions.amazon;

import diff_problems.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class SerDeserBST {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        Queue<TreeNode> q = new LinkedList();
        q.add(root);
        while(!q.isEmpty()) {
            TreeNode n = q.poll();
            if (n == null) {
                sb.append('n');
            } else {
                sb.append(n.val);
                q.add(n.left);
                q.add(n.right);
            }
            if (!q.isEmpty())
                sb.append(',');
        }
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null || data.isEmpty())
            return null;
        String[] splited = data.split(",");
        String rootS = splited[0];
        TreeNode root = getNode(splited[0]);
        if (root == null)
            return null;
        Queue<TreeNode> q = new LinkedList();
        q.add(root);
        int i = 1;
        while(!q.isEmpty()) {
            TreeNode n = q.poll();
            if (n == null)
                continue;
            String sL = splited[i];
            String sR = splited[i + 1];

            TreeNode left = getNode(sL);
            TreeNode right = getNode(sR);
            n.left = left;
            n.right = right;
            q.add(left);
            q.add(right);
            i +=2;
        }
        return root;
    }

    TreeNode getNode(String s) {
        if (s.equals("n"))
            return null;
        else
            return new TreeNode(Integer.parseInt(s));
    }

    public static void main(String[] args) {
        SerDeserBST obj = new SerDeserBST();
        TreeNode root = new TreeNode(2, new TreeNode(1), new TreeNode(3));
        String s = obj.serialize(root);
        System.out.println(s);
    }
}
