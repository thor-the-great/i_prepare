package trees;

public class BSTNode {
    public int val;
    public BSTNode right;
    public BSTNode left;

    public BSTNode(int val) {
        this.val = val;
    }

    public BSTNode(int val, BSTNode left, BSTNode right) {
        this.val = val;
        this.right = right;
        this.left = left;
    }

    @Override
    public String toString() {
        return "" + val;
    }
}
