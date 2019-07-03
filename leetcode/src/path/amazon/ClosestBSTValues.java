package path.amazon;

import trees.TreeNode;

public class ClosestBSTValues {
    public int closestValue(TreeNode root, double target) {
        int res = root.val;
        while (root != null) {
            if (Math.abs(target - res) > Math.abs(root.val - target)) {
                res = root.val;
            }
            root = (root.val > target) ? root.left : root.right;
        }
        return res;
    }

    public static void main(String[] args) {
        ClosestBSTValues obj = new ClosestBSTValues();
        TreeNode root = new TreeNode(5,
                new TreeNode(3,
                        new TreeNode(1), new TreeNode(4)),
                new TreeNode(8,
                    new TreeNode(6), null));

        System.out.println(obj.closestValue(root, 2.345));
        System.out.println(obj.closestValue(root, 13.5));
        System.out.println(obj.closestValue(root, 7.123));
    }
}
