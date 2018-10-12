package utils;

import trees.BSTNode;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class StringUtils {
    public static String intArrayToString(int[] a) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for(int i =0;i < a.length; i++) {
            sb.append(a[i]);
            if (i < a.length - 1)
                sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    public static String int2DArrayToString(int[][] arr) {
        StringBuilder sb = new StringBuilder();
        sb.append("[\n");
        for(int row =0;row < arr.length; row++) {
            sb.append("{ ");
            for (int col = 0; col < arr[0].length; col++) {
                sb.append(arr[row][col]);
                if (col < arr[0].length - 1)
                    sb.append(", ");
            }
            sb.append(" }, \n");
        }
        sb.append("]");
        return sb.toString();
    }


    public static String listToString(List<Integer> a) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for(int i =0;i < a.size(); i++) {
            sb.append(a.get(i));
            if (i < a.size() - 1)
                sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    public static String binaryTreeToString(BSTNode node) {
        StringBuilder sb = new StringBuilder();
        Queue<BSTNode> q = new LinkedList<>();
        Queue<Integer> levels = new LinkedList<>();
        q.add(node);
        int l = 1;
        levels.add(l);
        while(!q.isEmpty()) {
            BSTNode n = q.poll();
            int level = levels.poll();
            if (l != level) {
                sb.append("\n");
                l = level;
            }
            sb.append(n).append(", ");
            if (n.left != null) {
                q.add(n.left);
                levels.add(level + 1);
            }
            if (n.right != null) {
                q.add(n.right);
                levels.add(level + 1);
            }
        }
        return sb.toString();
    }
}
