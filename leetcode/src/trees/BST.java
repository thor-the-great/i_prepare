package trees;

public class BST {
    BSTNode root;

    public BSTNode getRoot(){
        return root;
    }

    public void add(int val) {
        if (root == null) {
            BSTNode node = new BSTNode(val, null, null );
            root = node;
            return;
        }
        boolean inserted = false;
        BSTNode n = root;
        while(!inserted) {
            if (val < n.val) {
                if (n.left == null) {
                    BSTNode newNode = new BSTNode(val, null, null);
                    n.left = newNode;
                    inserted = true;
                } else {
                    n = n.left;
                }
            } else {
                if (n.right == null) {
                    BSTNode newNode = new BSTNode(val, null, null);
                    n.right = newNode;
                    inserted = true;
                } else {
                    n = n.right;
                }
            }
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        int h = getHeight(root);
        for (int i = 1; i <= h; i++) {
            sb.append(i).append(": ");
            printOneLevel(sb, i, root);
            sb.append("\n");
        }
        return sb.toString();
    }

    void printOneLevel(StringBuilder sb, int level, BSTNode n) {
        if (n == null) {
            sb.append("null ");
            return;
        }
        if (level == 1)
            sb.append(n.val + " ");
        else {
            printOneLevel(sb, level - 1, n.left);
            printOneLevel(sb, level - 1, n.right);
        }
    }

    private int getHeight(BSTNode n) {
        if (n == null) return 0;
        else {
            int lHeight = getHeight(n.left);
            int rHeight = getHeight(n.right);
            if (lHeight > rHeight)
                return lHeight+1;
            else
                return rHeight+1;
        }
    }
}

