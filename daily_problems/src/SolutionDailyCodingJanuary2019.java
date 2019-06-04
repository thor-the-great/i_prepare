import graphs.DiGraph;
import trees.TreeNode;
import trees.TreeUtils;
import utils.StringUtils;

import java.util.*;
import java.util.stream.IntStream;

public class SolutionDailyCodingJanuary2019 {

    /**
     * This problem was asked by Google.
     *
     * Given an array of integers, return a new array where each element in the new array is the number of smaller
     * elements to the right of that element in the original input array.
     *
     * For example, given the array [3, 4, 9, 6, 1], return [1, 1, 2, 1, 0], since:
     *
     * There is 1 smaller element to the right of 3
     * There is 1 smaller element to the right of 4
     * There are 2 smaller elements to the right of 9
     * There is 1 smaller element to the right of 6
     * There are no smaller elements to the right of 1
     * @param nums
     * @return
     */
    public List<Integer> countSmaller(int[] nums) {
        //idea - we use merge sort algo, those left elements that will have smaller elements from right - +1 for every
        //such element. Rest is optimization. O(nlogn).
        int n = nums.length;
        int[][] arr = new int[n][2];
        int[][] aux = new int[n][2];
        for (int i = 0; i < n; i++) {
            arr[i] = new int[] {nums[i], i};
        }
        int[] count = new int[n];
        sort(arr, count, 0, n - 1, aux);
        List<Integer> list = new ArrayList();
        for (int i = 0; i < n; i++){
            list.add(count[i]);
        }
        return list;
    }

    private void sort(int[][] arr, int[] count, int lo, int hi, int[][] aux) {
        if (lo >= hi) return;
        int mid = lo + (hi - lo) / 2;
        sort(arr, count, lo, mid, aux);
        sort(arr, count, mid + 1, hi, aux);
        for (int i = lo; i <= hi; i++) {
            aux[i] = arr[i];
        }
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i == mid + 1) { arr[k] = aux[j++]; }
            else if (j == hi + 1 || aux[i][0] <= aux[j][0]) {
                count[aux[i][1]] += j - (mid + 1);
                arr[k] = aux[i++];
            } else {
                arr[k] = aux[j++];
            }
        }
    }

    /**
     * You are given a list of data entries that represent entries and exits of groups of people into a building. An
     * entry looks like this:
     *
     * {"timestamp": 1526579928, count: 3, "type": "enter"}
     *
     * This means 3 people entered the building. An exit looks like this:
     *
     * {"timestamp": 1526580382, count: 2, "type": "exit"}
     *
     * This means that 2 people exited the building. timestamp is in Unix time.
     *
     * Find the busiest period in the building, that is, the time with the most people in the building. Return it as a
     * pair of (start, end) timestamps. You can assume the building always starts off and ends up empty, i.e. with 0
     * people inside.
     * @param entries
     * @return
     */
    List<int[]> busiestTime(List<int[]> entries) {
        //each entry is in form [time, numOfPeople, type]
        //type is 1 - enter, 2 - exit
        List<int[]> res = new LinkedList();
        List<int[]> sortedIntervals = new ArrayList<>();
        for (int[] entry : entries) {
            int idx = entry[1];
            if (entry[2] == 2)
                idx = -idx;
            int[] e = new int[] {entry[0], idx};
            sortedIntervals.add(e);
        }
        sortedIntervals.sort(Comparator.comparingInt(e->e[0]));
        int max = 0;
        int numOfPeople = 0;
        int[] oneRes = new int[]{-1, -1};
        for (int[] e : sortedIntervals) {
            if (max == numOfPeople && e[1] < 0) {
                oneRes[1] = e[0];
                res.add(Arrays.copyOf(oneRes, oneRes.length));
            }
            numOfPeople += e[1];
            if (numOfPeople >= max) {
                if (numOfPeople > max)
                    res.clear();
                max = numOfPeople;
                oneRes[0] = e[0];
            }
        }
        return res;
    }

    /**
     * This problem was asked by Google.
     *
     * You are given a starting state start, a list of transition probabilities for a Markov chain, and a number of
     * steps num_steps. Run the Markov chain starting from start for num_steps and compute the number of times we
     * visited each state.
     *
     * For example, given the starting state a, number of steps 5000, and the following transition probabilities:
     *
     * [
     *   ('a', 'a', 0.9),
     *   ('a', 'b', 0.075),
     *   ('a', 'c', 0.025),
     *   ('b', 'a', 0.15),
     *   ('b', 'b', 0.8),
     *   ('b', 'c', 0.05),
     *   ('c', 'a', 0.25),
     *   ('c', 'b', 0.25),
     *   ('c', 'c', 0.5)
     * ]
     * One instance of running this Markov chain might produce { 'a': 3012, 'b': 1656, 'c': 332 }.
     */
    public void calculateMarkovState() {
        int num = 3;
        double[][] stateTrans = new double[][] {
                {0.9,   0.075,  0.025},
                {0.15,  0.8,    0.05},
                {0.25,  0.25,   0.5}
        };
        double[] initState = new double[] {
                1.0, 0.0, 0.0
        };
        Map<Integer, Double> c = new HashMap<>();
        for (int i = 0; i < 5000; i++) {
            initState = nextState(initState, stateTrans);
            for (int j =0; j < initState.length; j++) {
                c.put(j, c.getOrDefault(j, 0.0) + initState[j]);
            }
        }
        for(int i =0; i< initState.length; i++) {
            System.out.println(i + " = " + Math.floor(c.get(i)) +", ");
        }
    }

    private double[] nextState(double[] state, double[][] transition) {
        double[] next = new double[state.length];
        for (int c = 0; c < state.length; c++) {
            double el = 0;
            for (int cc = 0; cc < state.length; cc++) {
                el += transition[cc][c] * state[cc];
            }
            next[c] = el;
        }
        return next;
    }

    /**
     * This problem was asked by Google.
     *
     * Given the sequence of keys visited by a postorder traversal of a binary search tree, reconstruct the tree.
     *
     * For example, given the sequence 2, 4, 3, 8, 7, 5, you should construct the following tree:
     *
     *     5
     *    / \
     *   3   7
     *  / \   \
     * 2   4   8
     * @param postorder
     * @return
     */
    TreeNode constructTreeFromPostorder(int[] postorder) {
        //return constructTreeFromPostorderScanSlow(postorder);
        return constructTreeFromPostorderIntervalsFast(postorder);
    }
    class PostOrderIndex {
        int idx;
        PostOrderIndex(int i) {
            idx = i;
        }
    }
    TreeNode constructTreeFromPostorderIntervalsFast(int[] postorder) {
        return helperIntervals(Integer.MIN_VALUE, Integer.MAX_VALUE, postorder, new PostOrderIndex(postorder.length - 1));
    }

    TreeNode helperIntervals(int min, int max, int[] postorder, PostOrderIndex idx) {
        if (idx.idx < 0)
            return null;
        TreeNode node = null;
        int val = postorder[idx.idx];
        if (val > min && val < max) {
            node = new TreeNode(val);
            idx.idx -= 1;
            node.right = helperIntervals(val, max, postorder, idx);
            node.left = helperIntervals(min, val, postorder, idx);
        }
        return node;
    }

    TreeNode constructTreeFromPostorderScanSlow(int[] postorder) {
        TreeNode root = helper(postorder, 0, postorder.length - 1);
        return root;
    }

    TreeNode helper(int[] postorder, int start, int end) {
        if (start == end)
            return new TreeNode(postorder[start]);
        else if (start > end)
            return null;
        int rootVal = postorder[end];
        TreeNode root = new TreeNode(rootVal);
        int i = end - 1;
        while (i >= start) {
            if (postorder[i] < rootVal) {
                break;
            }
            i--;
        }
        root.left = helper(postorder, start, i);
        root.right = helper(postorder, i + 1, end - 1);
        return root;
    }

    /**
     * This problem was asked by Google.
     *
     * Given a stack of N elements, interleave the first half of the stack with the second half reversed using only one
     * other queue. This should be done in-place.
     *
     * Recall that you can only push or pop from a stack, and enqueue or dequeue from a queue.
     *
     * For example, if the stack is [1, 2, 3, 4, 5], it should become [1, 5, 2, 4, 3]. If the stack is [1, 2, 3, 4],
     * it should become [1, 4, 2, 3].
     *
     * Hint: Try working backwards from the end state.
     */
    public void interleaveStackWithReversedHalf(Stack<Integer> stack) {
        //idea - just borengly put elements back and forth
        Queue<Integer> q = new LinkedList<>();
        int N = stack.size();
        boolean isEven = N % 2 == 0;
        stackToQueue(stack, q, N);
        //here for odd must be less, so keep it
        queueToStack(q, stack, N/ 2);

        stackToQueue(stack, q, stack.size());
        //here must be 1 more for odd
        queueToStack(q, stack, isEven ? N/2 : 1 + (N/2));

        stackToQueue(stack, q, stack.size());
        queueToStack(q, stack, N/2);
        //final step - combine. Small catch for odd num of elements
        int i = isEven ? N/2 : 1 + (N/2);
        while (i > 0) {
            q.add(q.poll());
            if (!stack.isEmpty()) {
                q.add(stack.pop());
            }
            i--;
        }
        queueToStack(q, stack, N);
    }

    void stackToQueue(Stack<Integer> s, Queue<Integer> q, int i) {
        while (i > 0) {
            q.add(s.pop());
            i--;
        }
    }

    void queueToStack(Queue<Integer> q, Stack<Integer> s, int i) {
        while (i > 0) {
            s.push(q.poll());
            i--;
        }
    }

    /**
     * A graph is minimally-connected if it is connected and there is no edge that can be removed while still leaving
     * the graph connected. For example, any binary tree is minimally-connected.
     *
     * Given an undirected graph, check if the graph is minimally-connected. You can choose to represent the graph as
     * either an adjacency matrix or adjacency list.
     *
     * @param g
     * @return
     */
    int minGraphEdges = 0;
    boolean checkGraphMinConnected(DiGraph g) {
        //check for cycles, count edges at the same time. Idea is following - in minimal graph there must be num of
        //edges = num of vertexes - 1. If there are no cycles we just count edges and compare. Check cycles via DFS
        Set<Integer> visited = new HashSet<>();
        Set<Integer> recursionStack = new HashSet<>();
        minGraphEdges = 0;
        if (checkCycle(g, 0, visited, recursionStack)) {
           return false;
        } else {
            return minGraphEdges == g.getV() - 1;
        }
    }

    private boolean checkCycle(DiGraph g, int v, Set<Integer> visited, Set<Integer> recursionStack) {
        if (recursionStack.contains(v))
            return true;
        if (visited.contains(v))
            return false;

        visited.add(v);
        recursionStack.add(v);
        List<DiGraph.Edge> adjList = g.adjEdges(v);
        if (!adjList.isEmpty()) {
            for (DiGraph.Edge e: adjList ) {
                minGraphEdges++;
                if (checkCycle(g, e.to, visited, recursionStack))
                    return true;
            }
        }
        recursionStack.remove(v);
        return false;
    }

    /**
     * This problem was asked by Google.
     *
     * Given an array of elements, return the length of the longest subarray where all its elements are distinct.
     *
     * For example, given the array [5, 1, 3, 5, 2, 3, 4, 1], return 5 as the longest subarray of distinct elements is
     * [5, 2, 3, 4, 1].
     *
     * @param arr
     * @return
     */
    public int longestSubarrayOfDistinct(int[] arr) {
        Map <Integer, Integer> m = new HashMap();
        int head = 0, tail = 0;
        int res = 0;

        for (; tail < arr.length; tail++) {
            if (m.containsKey(arr[tail])) {
                res = Math.max(res, tail - head);
                int duplIdx = m.get(arr[tail]);
                for (; head <= duplIdx; head++) {
                    m.remove(arr[head]);
                }
            }
            m.put(arr[tail], tail);
        }
        res = Math.max(res, tail - head);
        return res;
    }

    /**
     * This problem was asked by Google.
     *
     * Let A be an N by M matrix in which every row and every column is sorted.
     *
     * Given i1, j1, i2, and j2, compute the number of elements of M smaller than M[i1, j1] and larger than M[i2, j2].
     *
     * For example, given the following matrix:
     *
     * [[1, 3, 7, 10, 15, 20],
     *  [2, 6, 9, 14, 22, 25],
     *  [3, 8, 10, 15, 25, 30],
     *  [10, 11, 12, 23, 30, 35],
     *  [20, 25, 30, 35, 40, 45]]
     *
     * And i1 = 1, j1 = 1, i2 = 3, j2 = 3, return 15 as there are 15 numbers in the matrix smaller than 6 or greater
     * than 23.
     *
     * @param matrix
     * @param r1
     * @param c1
     * @param r2
     * @param c2
     * @return
     */
    public int numOfElementsBetween(int[][] matrix, int r1, int c1, int r2, int c2) {
        int rows = matrix.length;
        if (rows == 0)
            return 0;
        int cols = matrix[0].length;

        int ls = matrix[r1][c1];
        int gt = matrix[r2][c2];

        int count = 0;
        for (int[] row : matrix) {
            if (row[0] < ls) {
                int lsP = Arrays.binarySearch(row, ls);
                if (lsP >= 0)
                    count += lsP;
                else {
                    lsP = -lsP - 1;
                    count += lsP;
                }
            }
            if (row[cols - 1] > gt) {
                int gtP = Arrays.binarySearch(row, gt);
                if (gtP >= 0)
                    count += (cols - gtP - 1);
                else {
                    gtP = -gtP - 1;
                    if (gtP < cols) {
                        count += (cols - gtP);
                    }
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        SolutionDailyCodingJanuary2019 obj = new SolutionDailyCodingJanuary2019();

        System.out.println("---- count smaller numbers of self ----");
        int[] arr;
        List<Integer> smallers;
        arr = new int[] {5,2,6,1};
        smallers = obj.countSmaller(arr);
        System.out.print(StringUtils.listToString(smallers));//[2, 1, 1, 0]

        System.out.println("\n---- count busiest time in the building ----");
        //each entry is in form [time, numOfPeople, type], type is 1 - enter, 2 - exit
        List<int[]> entries;
        entries = new LinkedList<>();
        entries.add(new int[] {1, 2, 1});
        entries.add(new int[] {3, 3, 1});
        entries.add(new int[] {4, 1, 2});
        entries.add(new int[] {5, 1, 1});
        entries.add(new int[] {8, 3, 1});
        entries.add(new int[] {20, 8, 2});
        entries.add(new int[] {25, 4, 1});
        entries.add(new int[] {28, 4, 1});
        entries.add(new int[] {40, 5, 2});
        entries.add(new int[] {45, 3, 2});

        List<int[]> time = obj.busiestTime(entries);
        for (int[] t : time) {
            System.out.println("[" + t[0] + ", " + t[1] + "] , ");
        }

        System.out.println("--- calculate markov state ----");
        obj.calculateMarkovState();

        System.out.println("--- construct binary search tree from postorder traversal ---");
        TreeNode bst = obj.constructTreeFromPostorder(new int[] {2, 4, 3, 8, 7, 5});
        System.out.println(TreeUtils.binaryTreeToString(bst));
        /**
         *      10
         *    /   \
         *   5     40
         *  /  \      \
         * 1    7      50
         */
        bst = obj.constructTreeFromPostorder(new int[] {1, 7, 5, 50, 40, 10});
        System.out.println(TreeUtils.binaryTreeToString(bst));
        /**
         *                25
         *              /     \
         *          15          50
         *       /    \        /    \
         *     10      22    35      70
         *    / \     / \    / \     / \
         *  4   12   18 24  31  44  66  90
         */
        bst = obj.constructTreeFromPostorder(new int[] {4, 12, 10, 18, 24, 22, 15, 31, 44, 35, 66, 90, 70, 50, 25});
        System.out.println(TreeUtils.binaryTreeToString(bst));

        System.out.println("--- interleave the first half of the stack with the second half reversed ---");
        Stack<Integer> stack = new Stack<>();
        IntStream.range(1, 5).forEach(i->stack.push(i));
        System.out.println("Initial stack state " + stack);
        obj.interleaveStackWithReversedHalf(stack);
        System.out.println("Interleaved stack " + stack);

        stack.clear();
        IntStream.range(1, 6).forEach(i->stack.push(i));
        System.out.println("Initial stack state " + stack);
        obj.interleaveStackWithReversedHalf(stack);
        System.out.println("Interleaved stack " + stack);

        stack.clear();
        IntStream.range(1, 10).forEach(i->stack.push(i));
        System.out.println("Initial stack state " + stack);
        obj.interleaveStackWithReversedHalf(stack);
        System.out.println("Interleaved stack " + stack);

        System.out.println("--- given undirected graph check if it's minimally-connected ---");
        DiGraph g = new DiGraph(5);
        g.addEdge(0, 1);
        g.addEdge(0, 4);
        g.addEdge(1, 2);
        g.addEdge(1, 3);
        System.out.println(obj.checkGraphMinConnected(g));//true

        g = new DiGraph(5);
        g.addEdge(0, 1);
        g.addEdge(0, 4);
        g.addEdge(1, 2);
        g.addEdge(3, 0);
        g.addEdge(1, 3);
        System.out.println(obj.checkGraphMinConnected(g));//false

        g = new DiGraph(7);
        g.addEdge(0, 1);
        g.addEdge(0, 4);
        g.addEdge(1, 2);
        g.addEdge(1, 3);
        g.addEdge(5, 6);
        System.out.println(obj.checkGraphMinConnected(g));//false

        System.out.println("--- length of longest sub-array of distinct elements ---");

        System.out.println(obj.longestSubarrayOfDistinct(new int[] {5, 1, 3, 5, 2, 3, 4, 1}));//5

        System.out.println(obj.longestSubarrayOfDistinct(new int[] {10, 12, 11}));//3

        System.out.println(obj.longestSubarrayOfDistinct(new int[] {14, 12, 11, 20}));//4

        System.out.println(obj.longestSubarrayOfDistinct(new int[] {4, 5, 1, 12, 3, 5, 2, 10, 4, 1, 7, 8, 6}));//10

        System.out.println("--- num of min max elements in a sorted rows/cols matrix ---");
        int[][] matrix;

        matrix = new int[][] {
                {1, 3, 7, 10, 15, 20},
                {2, 6, 9, 14, 22, 25},
                {3, 8, 10, 15, 25, 30},
                {10, 11, 12, 23, 30, 35},
                {20, 25, 30, 35, 40, 45}
        };
        System.out.println(obj.numOfElementsBetween(matrix, 1,1,3,3));
    }
}
