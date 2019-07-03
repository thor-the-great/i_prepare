package random_problems;

import java.util.LinkedList;
import java.util.List;

public class PathInZigZagLabeledTree {

    public List<Integer> pathInZigZagTree(int label) {
        LinkedList<Integer> res = new LinkedList();
        res.addFirst(label);
        //saves power of depth (level)
        int powOfDepth = -1;

        while (label > 1) {
            //calculate depth as log2 for label
            int depth = log2(label);
            //2 to the power of depth + 1
            if (powOfDepth == -1)
                powOfDepth = pow2(depth + 1);
            //offset for the depth + 1
            int offset = powOfDepth - 1 - label;
            //power of (depth - 1)
            powOfDepth = powOfDepth>>1;
            //calculate label of the part leaf
            label = (powOfDepth + offset) / 2;
            res.addFirst(label);
        }
        return res;
    }

    int log2(int n) {
        int r = 0;
        while (n > 1) {
            r++;
            n >>= 1;
        }

        return r;
    }

    int pow2(int n) {
        int r = 1;
        while (n > 0) {
            n--;
            r <<= 1;
        }

        return r;
    }

    public static void main(String[] args) {
        PathInZigZagLabeledTree obj = new PathInZigZagLabeledTree();
        System.out.println(obj.pathInZigZagTree(43));
    }
}
