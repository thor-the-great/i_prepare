import diff_problems.TreeNode;
import linked_list.ListNode;
import linked_list.ListUtils;
import utils.StringUtils;

import java.util.*;
import java.util.stream.IntStream;

public class SolutionDailyCodingDecember2018 {

    /**
     * This question was asked by Apple.
     *
     * Given a binary tree, find a minimum path sum from root to a leaf.
     *
     * For example, the minimum path in this tree is [10, 5, 1, -1], which has sum 15.
     *
     *   10
     *  /  \
     * 5    5
     *  \     \
     *    2    1
     *        /
     *      -1
     *
     * @param root
     * @return
     */
    public int minPath(TreeNode root) {
        minPath = Integer.MAX_VALUE;
        if (root == null)
            return Integer.MIN_VALUE;
        minPathHelper(root, 0);
        return minPath;
    }

    public int minPath = Integer.MAX_VALUE;

    void minPathHelper(TreeNode n, int path) {
        int a = path + n.val;
        if (n.left == null && n.right == null) {
            minPath = Math.min(minPath, a);
            return;
        }
        if (n.left != null) {
            minPathHelper(n.left, a);
        }
        if (n.right != null) {
            minPathHelper(n.right, a);
        }
    }

    public int maximalRectangle(char[][] matrix) {
        int N = matrix[0].length;
        int[] flat = new int[N];
        for (int i =0; i < N; i++ ) {
            flat[i] = matrix[0][i] - '0';
        }
        int res = getMaxArea(flat, N);
        for (int r = 1; r < matrix.length; r++) {
            recalcFlat(flat, matrix[r]);
            res = Math.max(res, getMaxArea(flat, N));
        }
        return res;
    }

    void recalcFlat(int[] flat, char[] nextRow) {
        for (int i = 0; i < flat.length; i++) {
            if (nextRow[i] == '1')
                flat[i] += 1;
            else
                flat[i] = 0;
        }
    }

    int getMaxArea(int[] arr, int N) {
        Stack<Integer> s = new Stack<>();
        s.push(-1);
        int res = 0;
        for(int i = 0; i < N; i++) {
            while(s.peek() != -1 && arr[i] <= arr[s.peek()]) {
                int p = s.pop();
                res = Math.max(res, arr[p] * (i - s.peek() - 1));
            }
            s.push(i);
        }
        while (s.peek() != -1) {
            int p = s.pop();
            res = Math.max(res, arr[p] * (N - s.peek() - 1));
        }
        return res;
    }

    public void partitionArray(int[] arr, int pivot) {
        int N = arr.length;
        int l = 0, r = N - 1, mid = 0;
        while (mid <= r) {
            int num = arr[mid];
            if (num < pivot) {
                swap(arr, mid, l);
                mid++;
                l++;
            } else if (num > pivot) {
                swap(arr, mid, r);
                r--;
            } else{
                mid++;
            }
        }
    }

    void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    public static void main(String[] args) {
        SolutionDailyCodingDecember2018 obj = new SolutionDailyCodingDecember2018();

        System.out.println("---- find min path in BST (from root to any of leaves) ----");
        TreeNode root;

        root = new TreeNode(5,
                new TreeNode(7),
                new TreeNode(1,
                        new TreeNode(4), new TreeNode(3)));
        System.out.println(obj.minPath(root));

        root = new TreeNode(5,
                new TreeNode(7),
                new TreeNode(1,
                        new TreeNode(4,
                                new TreeNode(2), null
                                ),
                        null));
        System.out.println(obj.minPath(root));

        root = new TreeNode(6,
                new TreeNode(5,
                        new TreeNode(4), null),
                new TreeNode(3,
                        new TreeNode(6,
                                new TreeNode(2), new TreeNode(1)),
                        new TreeNode(4)));
        System.out.println(obj.minPath(root));

        System.out.println("---- find min path in BST (from root to any of leaves) ----");
        System.out.println(obj.maximalRectangle(new char[][] {
                {'1', '0'}
        }));

        System.out.println("--- partitiion array around pivot ----");
        int[] arr;
        arr = new int[] {9, 3, 12, 10, 5, 10, 16};
        obj.partitionArray(arr, 10);
        System.out.println(StringUtils.intArrayToString(arr));
    }
}
