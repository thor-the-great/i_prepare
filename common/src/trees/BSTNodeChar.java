package trees;

public class BSTNodeChar {
    public char val;
    public BSTNodeChar left, right;

    public BSTNodeChar (char val) {
        this.val = val;
    }

    public BSTNodeChar (char val, BSTNodeChar left, BSTNodeChar right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
