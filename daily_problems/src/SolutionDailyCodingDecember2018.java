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

    /**
     * This problem was asked by Amazon.
     *
     * Given a pivot x, and a list lst, partition the list into three parts.
     *
     * The first part contains all elements in lst that are less than x
     * The second part contains all elements in lst that are equal to x
     * The third part contains all elements in lst that are larger than x
     * Ordering within a part can be arbitrary.
     *
     * For example, given x = 10 and lst = [9, 12, 3, 5, 14, 10, 10], one partition may be `[9, 3, 5, 10, 10, 12, 14].
     *
     * @param arr
     * @param pivot
     */
    public void partitionArray(int[] arr, int pivot) {
        //this solution is for array based on deque
        //- first create |...|equals|greater|
        //- then on second pass fill less part
        //after that generate resulting array from the deque
        int N = arr.length;
        Deque<Integer> dq = new LinkedList<>();
        for (int i = 0; i < N; i ++) {
            if (arr[i] == pivot)
                dq.addFirst(arr[i]);
            else if (arr[i] > pivot)
                dq.addLast(arr[i]);
        }
        for (int i = N - 1; i >= 0; i --) {
            if (arr[i] < pivot)
                dq.addFirst(arr[i]);
        }
        int i = 0;
        while (i < N) {
            arr[i] = dq.pollFirst();
            i++;
        }
    }

    public void partitionList(ListNode list, int pivot) {
        ListNode less = null;
        ListNode eq = null;

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
        obj.partitionArray(arr, 10); // 9, 3, 5, 10, 10, 12, 16
        System.out.println(StringUtils.intArrayToString(arr));

        arr = new int[] {10, 1, 13, 9, 3, 12, 10, 5, 10, 11};
        obj.partitionArray(arr, 10); // 1, 9, 3, 5, 10, 10, 10, 13, 12, 11
        System.out.println(StringUtils.intArrayToString(arr));
    }
}
