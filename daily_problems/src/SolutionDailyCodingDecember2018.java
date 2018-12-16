import diff_problems.TreeNode;
import linked_list.ListNode;
import linked_list.ListUtils;
import utils.StringUtils;

import javax.sound.midi.SysexMessage;
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

    /**
     * This problem was asked by Google.
     *
     * Given an array of numbers and an index i, return the index of the nearest larger number of the number at index
     * i, where distance is measured in array indices.
     *
     * For example, given [4, 1, 3, 5, 6] and index 0, you should return 3.
     *
     * If two distances to larger numbers are the equal, then return any one of them. If the array at i doesn't have
     * a nearest larger integer, then return null.
     *
     * Follow-up: If you can preprocess the array, can you do this in constant time?
     *
     * @param arr
     * @param i
     * @return
     */
    public int nextGreaterElementByIndexDistance(int[] arr, int i) {
        return oneScan(arr, i);
        //return constantWithPreprocessing(arr, i);
    }

    private int oneScan(int[] arr, int i) {
        int N = arr.length;
        int el = arr[i];

        int r = i + 1, l = i - 1;
        while (l >= 0 || r < N) {
            if (l >= 0) {
                if(arr[l] > el)
                    return l;
                else
                    l--;
            }
            if (r < N) {
                if(arr[r] > el)
                    return r;
                else
                    r++;
            }
        }
        return -1;
    }

    /*private int constantWithPreprocessing(int[] arr, int i) {
    }*/

    class IndexDistance {
        int index;
        int val;
        IndexDistance(int index, int val) {
            this.index = index;
            this.val = val;
        }
    }

    public void pancakeSorting(int[] nums) {
        int N = nums.length;
        int i = N - 1;
        while (i >= 0 ) {
            int maxIdx = findMax(nums, i);
            if (maxIdx != i) {
                reverse(nums, 0, maxIdx);
                reverse(nums, 0, i);
            }
            i--;
        }
    }

    int findMax(int[] nums, int idx) {
        int maxIdx = 0;
        for (int i = 1; i <= idx; i++) {
            if (nums[i] > nums[maxIdx]) {
                maxIdx = i;
            }
        }
        return maxIdx;
    }

    void reverse(int[] nums, int start, int end) {
        int tmp = 0;
        while (start <= end) {
            tmp = nums[start];
            nums[start] = nums[end];
            nums[end] = tmp;
            start++;
            end--;
        }
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

        System.out.println("--- next greater element by index distance ---");
        arr = new int[] {4, 1, 3, 5, 6};
        System.out.println(obj.nextGreaterElementByIndexDistance(arr, 0));//3
        arr = new int[] {4, 2, 1, 3, 5, 6, 9, 2};
        System.out.println(obj.nextGreaterElementByIndexDistance(arr, 1));//0
        System.out.println(obj.nextGreaterElementByIndexDistance(arr, 6));//-1
        System.out.println(obj.nextGreaterElementByIndexDistance(arr, 7));//6

        System.out.println("---  reverse pancake sorting ----");
        int numOfElements = 50_000;
        int[] nums = new int[numOfElements];
        Random rand = new Random();
        IntStream.range(0, numOfElements).forEach(i-> nums[i] = rand.nextInt(4*numOfElements));
        System.out.println(StringUtils.intArrayToString(nums));
        obj.pancakeSorting(nums);
        System.out.println(StringUtils.intArrayToString(nums));
    }
}
