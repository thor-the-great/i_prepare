package trees;

public class BSTNode {
    public int val;
    public BSTNode right;
    public BSTNode left;

    BSTNode (int val, BSTNode right, BSTNode left) {
        this.val = val;
        this.right = right;
        this.left = left;
    }

    @Override
    public String toString() {
        return "" + val;
    }
}
