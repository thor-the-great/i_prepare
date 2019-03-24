import cracking.trees_graphs.DiGraph;
import diff_problems.TreeNode;
import graphs.GraphUtils;
import util.NaryTreeNode;
import utils.ArrayUtil;
import utils.StringUtils;

import java.util.*;

public class SolutionDailyCodingMarch2019 {

    /**
     * This problem was asked by Amazon.
     *
     * Given a sorted array, find the smallest positive integer that is not the sum of a subset of the array.
     *
     * For example, for the input [1, 2, 3, 10], you should return 7.
     *
     * Do this in O(N) time.
     *
     * @param arr
     * @return
     */
    public int smallestNonSum(int[] arr) {
        /**
         * Idea - start from smallest number 1. Check every next array element two options:
         * - if arr[i] > res - this means res can't be represented neither by this element nor by next one (because all
         * next elements are greater). So we just return res
         * - else we add arr[i] to res and continue to next i.
         */
        int N = arr.length;
        int res = 1;
        for (int i = 0; i < N; i++) {
            if (arr[i] > res)
                break;
            res += arr[i];
        }

        return res;
    }

    /**
     * Absolute Value Sort
     * Given an array of integers arr, write a function absSort(arr), that sorts the array according to the absolute
     * values of the numbers in arr. If two numbers have the same absolute value, sort them according to sign, where
     * the negative numbers come before the positive numbers.
     *
     * Examples:
     *
     * input:  arr = [2, -7, -2, -2, 0]
     * output: [0, -2, -2, 2, -7]
     *
     * @param arr
     * @return
     */
    public int[] absSort(int[] arr) {


        for (int i = 0; i < arr.length - 1; i++) {
            int tmp = i;
            for (int j = i; j < arr.length; j++) {
                if (check(arr[j], arr[tmp]))
                    tmp = j;
            }
            int t = arr[tmp];
            arr[tmp] = arr[i];
            arr[i] = t;
        }
        return arr;
    }

    boolean check(int a, int b) {
        int abs1 = Math.abs(a);
        int abs2 = Math.abs(b);
        if (abs1 == abs2)
            return a < b;
        else
            return abs1 < abs2;
    }

    public Integer[] absSortComp(Integer[] arr) {

        Comparator<Integer> comp = (i1, i2) -> {
                if (Math.abs(i1) != Math.abs(i2))
                    return Math.abs(i1)-Math.abs(i2);
                else
                    return i1 - i2;
        };

        Arrays.sort(arr, comp);

        return arr;
    }

    /**
     * This problem was asked by Bloomberg.
     *
     * There are N prisoners standing in a circle, waiting to be executed. The executions are carried out starting
     * with the kth person, and removing every successive kth person going clockwise until there is no one left.
     *
     * Given N and k, write an algorithm to determine where a prisoner should stand in order to be the last survivor.
     *
     * For example, if N = 5 and k = 2, the order of executions would be [2, 4, 1, 5, 3], so you should return 3.
     *
     * Bonus: Find an O(log N) solution if k = 2.
     * @return
     */
    public int positionToExecuteLast(int N, int k) {
        if (N == 1)
            return 1;

        return ((positionToExecuteLast(N - 1, k) + k - 1 )% N) + 1 ;
    }

    /**
     * This problem was asked by Bloomberg.
     *
     * There are N prisoners standing in a circle, waiting to be executed. The executions are carried out starting
     * with the kth person, and removing every successive kth person going clockwise until there is no one left.
     *
     * Given N and k, write an algorithm to determine where a prisoner should stand in order to be the last survivor.
     *
     * For example, if N = 5 and k = 2, the order of executions would be [2, 4, 1, 5, 3], so you should return 3.
     *
     * Bonus: Find an O(log N) solution if k = 2.
     * @return
     */
    public int positionToExecuteLastTwo(int N) {
        if (N == 1)
            return 1;

        if (N % 2 == 0)
            return 2*positionToExecuteLastTwo(N / 2) - 1;
        else
            return 2*positionToExecuteLastTwo(N / 2) + 1;
    }

    /**
     * 909. Snakes and Ladders
     * Medium
     *
     * On an N x N board, the numbers from 1 to N*N are written boustrophedonically starting from the bottom left of
     * the board, and alternating direction each row.  For example, for a 6 x 6 board, the numbers are written as
     * follows:
     *
     *
     * You start on square 1 of the board (which is always in the last row and first column).  Each move, starting
     * from square x, consists of the following:
     *
     * You choose a destination square S with number x+1, x+2, x+3, x+4, x+5, or x+6, provided this number is <= N*N.
     * (This choice simulates the result of a standard 6-sided die roll: ie., there are always at most 6 destinations.)
     * If S has a snake or ladder, you move to the destination of that snake or ladder.  Otherwise, you move to S.
     * A board square on row r and column c has a "snake or ladder" if board[r][c] != -1.  The destination of that
     * snake or ladder is board[r][c].
     *
     * Note that you only take a snake or ladder at most once per move: if the destination to a snake or ladder is
     * the start of another snake or ladder, you do not continue moving.  (For example, if the board is
     * `[[4,-1],[-1,3]]`, and on the first move your destination square is `2`, then you finish your first move at `3`,
     * because you do not continue moving to `4`.)
     *
     * Return the least number of moves required to reach square N*N.  If it is not possible, return -1.
     *
     * Example 1:
     *
     * Input: [
     * [-1,-1,-1,-1,-1,-1],
     * [-1,-1,-1,-1,-1,-1],
     * [-1,-1,-1,-1,-1,-1],
     * [-1,35,-1,-1,13,-1],
     * [-1,-1,-1,-1,-1,-1],
     * [-1,15,-1,-1,-1,-1]]
     * Output: 4
     * Explanation:
     * At the beginning, you start at square 1 [at row 5, column 0].
     * You decide to move to square 2, and must take the ladder to square 15.
     * You then decide to move to square 17 (row 3, column 5), and must take the snake to square 13.
     * You then decide to move to square 14, and must take the ladder to square 35.
     * You then decide to move to square 36, ending the game.
     * It can be shown that you need at least 4 moves to reach the N*N-th square, so the answer is 4.
     * Note:
     *
     * 2 <= board.length = board[0].length <= 20
     * board[i][j] is between 1 and N*N or is equal to -1.
     * The board square with number 1 has no snake or ladder.
     * The board square with number N*N has no snake or ladder.
     *
     * @param board
     * @return
     */
    public int snakesAndLadders(int[][] board) {
        int N = board.length;
        int finalPos = N * N;
        Map<Integer, Integer> m = new HashMap();
        m.put(1, 0);

        Queue<Integer> q = new LinkedList();
        q.add(1);

        while (!q.isEmpty()) {
            int pos = q.poll();
            int numOfSteps = -1;
            if (m.containsKey(pos))
                numOfSteps = m.get(pos);
            if (pos == finalPos) {
                return numOfSteps;
            }

            for (int i = pos + 1; i <= Math.min(pos + 6, finalPos); i++) {
                int nextR = N - 1 - ((i - 1) / N);
                int nextC = nextR % 2 != N % 2 ? (i - 1) % N : (N - 1 - ((i - 1)%N));

                int next = board[nextR][nextC];
                if (next == -1)
                    next = i;
                if (!m.containsKey(next)) {
                    m.put(next, numOfSteps + 1);
                    q.add(next);
                }
            }
        }

        return -1;
    }

    /**
     * This problem was asked by Goldman Sachs.
     *
     * You are given N identical eggs and access to a building with k floors. Your task is to find the lowest floor
     * that will cause an egg to break, if dropped from that floor. Once an egg breaks, it cannot be dropped again.
     * If an egg breaks when dropped from the xth floor, you can assume it will also break when dropped from any
     * floor greater than x.
     *
     * Write an algorithm that finds the minimum number of trial drops it will take, in the worst case, to identify
     * this floor.
     *
     * For example, if N = 1 and k = 5, we will need to try dropping the egg at every floor, beginning with the first,
     * until we reach the fifth floor, so our solution will be 5.
     * @param eggs
     * @param flrs
     */
    int eggDrop(int eggs, int flrs) {
        //Idea - write recursive solution, then make dp from it.
        //for recursion - we drop from f floor. If egg breaks - it's a problem (e - 1, f -1) else - (e, F - f)
        /*if (flrs == 0 || flrs == 1)
            return flrs;
        //base case - if 1 egg only - num of tries == num of eggs
        if (eggs == 1)
            return flrs;

        int res = Integer.MAX_VALUE;
        for (int f = 1; f <= flrs; f++) {
            res = Math.min(res,
                    Math.max(eggDrop(eggs - 1, f - 1), eggDrop(eggs, flrs - f)));
        }
        return res + 1;*/

        int[][] dp = new int[eggs + 1][flrs + 1];

        //fill base cases
        //when we have 0 or 1 floors
        for (int e = 1; e <= eggs; e++) {
            dp[e][0] = 0;
            dp[e][1] = 1;
        }

        for (int f = 1; f <= flrs; f++)
            dp[1][f] = f;

        for (int e = 2; e <= eggs; e++) {
            for (int f = 2; f <= flrs; f++) {
                int res = Integer.MAX_VALUE;
                for (int ff = 1; ff <= f; ff++) {
                    res = Math.min(res,
                            Math.max(dp[e - 1][ff - 1], dp[e][f - ff]));
                }
                dp[e][f] = res + 1;
            }
        }

        return dp[eggs][flrs];
    }

    /**
     * This problem was asked by Apple.
     *
     * Implement the function fib(n), which returns the nth number in the Fibonacci sequence, using only O(1) space.
     * @param N
     * @return
     */
    public int fibonacci(int N) {
        int p0 = 0;
        if (N == 0)
            return p0;
        int p1 = 1;
        if (N == 1)
            return p1;
        int res = 0;
        for (int i = 3; i <= N; i++) {
            res = p0 + p1;
            p0 = p1;
            p1 = res;
        }
        return res;
    }

    /**
     * This problem was asked by Facebook.
     *
     * Given an array of numbers of length N, find both the minimum and maximum using less than 2 * (N - 2) comparisons
     * @param arr
     * @return
     */
    public int[] getMinMaxInLessComparisions(int[] arr) {
        //idea - divide and conq approach. Divide array in halves, then get min/max of the half and then
        //get min and max of each half.
        //Base cases - if n = 1 - element is min and max itself, if n == 2 - do one comparision to define
        //min and max
        return minMaxHelper(arr, 0, arr.length - 1);
    }

    int[] minMaxHelper(int[] arr, int l, int r) {
        if (l == r)
            return new int[] {arr[l], arr[l]};

        if (r - l == 1) {
            if (arr[l] > arr[r])
                return new int[] {arr[r], arr[l]};
            else
                return new int[] {arr[l], arr[r]};
        }

        int mid = l + (r - l) /2;
        int[] minMaxLeft = minMaxHelper(arr, l, mid);
        int[] minMaxRight = minMaxHelper(arr, mid + 1, r);

        int[] res = new int[2];
        if (minMaxLeft[0] < minMaxRight[0])
            res[0] = minMaxLeft[0];
        else
            res[0] = minMaxRight[0];

        if (minMaxLeft[1] > minMaxRight[1])
            res[1] = minMaxLeft[1];
        else
            res[1] = minMaxRight[1];
        return res;
    }

    /**
     * This problem was asked by Amazon.
     *
     * A tree is symmetric if its data and shape remain unchanged when it is reflected about the root node.
     * The following tree is an example:
     *
     *         4
     *       / | \
     *     3   5   3
     *   /           \
     * 9              9
     * Given a k-ary tree, determine whether it is symmetric.
     * @param n
     * @return
     */
    public boolean isSymmetrical(NaryTreeNode n) {
        if (n == null)
            return true;
        List<NaryTreeNode> children = n.children;
        if (children != null && !children.isEmpty()) {
            int l = 0, r = children.size() - 1;
            while (l <= r) {
                NaryTreeNode left = children.get(l);
                NaryTreeNode right = children.get(r);

                if (!helper(left, right))
                    return false;

                l++;
                r--;
            }
        }

        return true;
    }

    boolean helper(NaryTreeNode n1, NaryTreeNode n2) {
        if (n1 == null && n2 == null)
            return true;

        if (n1 == null || n2 == null)
            return false;

        if (n1.val != n2.val)
            return false;

        List<NaryTreeNode> children1 = n1.children;
        List<NaryTreeNode> children2 = n2.children;

        if (children1 == null && children2 == null)
            return true;

        if (children1 == null || children2 == null)
            return false;

        if (children1.size() != children2.size())
            return false;

        int N = children1.size();
        for (int i = 0; i < N; i++) {
            if (!helper(children1.get(i), children2.get(N - 1 - i)))
                return false;
        }

        return true;
    }

    /**
     * This problem was asked by Etsy.
     *
     * Given an array of numbers N and an integer k, your task is to split N into k partitions such that the maximum
     * sum of any partition is minimized. Return this sum.
     *
     * For example, given N = [5, 1, 2, 7, 3, 4] and k = 3, you should return 8, since the optimal partition is
     * [5, 1, 2], [7], [3, 4].
     *
     * @param arr
     * @param K
     * @return
     */
    public int minPartitionSum(int[] arr, int K) {
        int N = arr.length;
        if (N == 0 || K == 0)
            return 0;

        int sum = 0;
        for (int n : arr)
            sum += n;

        if (K == 1)
            return sum;

        int l = sum / K;
        int r = sum;

        while ( l < r) {
            int m = l + (r - l) / 2;

            int cur = 0;
            int buckets = 1;
            boolean tooSmall = false;
            for (int n : arr) {
                if (n > m) {
                    tooSmall = true;
                    break;
                }
                if (cur  + n > m) {
                    buckets++;
                    cur = 0;
                }
                cur += n;
            }

            if (buckets > K || tooSmall)
                l = m + 1;
            else
                r = m;
        }

        return l;
    }

    /**
     * This problem was asked by Dropbox.
     *
     * Given a list of words, determine whether the words can be chained to form a circle. A word X can be placed in
     * front of another word Y in a circle if the last character of X is same as the first character of Y.
     *
     * For example, the words ['chair', 'height', 'racket', touch', 'tunic'] can form the following circle:
     * chair --> racket --> touch --> height --> tunic --> chair.
     *
     * @param arr
     * @return
     */
    public boolean chainToCircle(String[] arr) {

        /**
         * Idea: criteria for condition to be true: if we create DAG from characters, vertexes are first and
         * last ones, link will be based on word, from first to last letter
         * - if in degree equals out degree for every character
         * - we can visit all and every vertex start from any vertex (simple DFS, count size of visited)
         */
        Map<Character, int[]> inOut = new HashMap<>();
        Map<Character, List<Character>> graph = new HashMap<>();

        for (String s : arr) {
            char start = s.charAt(0);
            char end = s.charAt(s.length() - 1);

            if (!inOut.containsKey(start))
                inOut.put(start, new int[2]);

            inOut.get(start)[1]++;

            if (!inOut.containsKey(end))
                inOut.put(end, new int[2]);

            inOut.get(end)[0]++;

            if(!graph.containsKey(start))
                graph.put(start, new ArrayList<>());
            if (!graph.get(start).contains(end))
                graph.get(start).add(end);

            if(!graph.containsKey(end))
                graph.put(end, new ArrayList<>());

        }

        Set<Character> vertexes = inOut.keySet();

        for(char ch : vertexes) {
            int[] inOutPair = inOut.get(ch);
            if (inOutPair[0] != inOutPair[1])
                return false;
        }

        Set<Character> visited = new HashSet<>();
        Stack<Character> stack = new Stack<>();
        stack.push(arr[0].charAt(0));

        while(!stack.isEmpty()) {
            char next = stack.pop();
            if (!visited.contains(next)) {
                visited.add(next);
                List<Character> adjs = graph.get(next);
                if (adjs != null && !adjs.isEmpty()) {
                    for (char adj : adjs) {
                        stack.push(adj);
                    }
                }
            }
        }

        return vertexes.size() == visited.size();
    }

    public static void main(String[] args) {
        SolutionDailyCodingMarch2019 obj = new SolutionDailyCodingMarch2019();

        System.out.println("---- smallest int that is not a sum of subset, array is sorted ----");
        int[] arr;
        arr = new int[] {1, 1, 3, 4, 5};
        System.out.println(obj.smallestNonSum(arr));

        arr = new int[] {1, 3, 4};
        System.out.println(obj.smallestNonSum(arr));//2

        arr = new int[] {1, 1, 3, 4};
        System.out.println(obj.smallestNonSum(arr));//10

        arr = new int[] {1, 1, 1, 1};
        System.out.println(obj.smallestNonSum(arr));//5

        arr = new int[] {1, 2, 5, 10, 20, 40};
        System.out.println(obj.smallestNonSum(arr));//4

        System.out.println("--- position to be executed last ---");
        System.out.println(obj.positionToExecuteLast(5, 2)); //3

        System.out.println(obj.positionToExecuteLast(7, 3)); //4

        System.out.println(obj.positionToExecuteLastTwo(5));//3
        System.out.println(obj.positionToExecuteLastTwo(6));//5

        System.out.println("--- min number of tries to drop eggs ---");
        System.out.println(obj.eggDrop(1, 5));//5
        System.out.println(obj.eggDrop(2, 12));//5
        System.out.println(obj.eggDrop(2, 36));//5

        System.out.println("--- N-th fibonacci number in constant space ---");
        System.out.println(obj.fibonacci(6));
        System.out.println(obj.fibonacci(20));

        System.out.println("--- Find min and max in array with minimum comparision (less than 2N - 2 )---");

        arr = ArrayUtil.getRandomIntArray(10, 100);
        System.out.println(StringUtils.intArrayToString(arr));
        int[] minMaxArr = obj.getMinMaxInLessComparisions(arr);
        System.out.println("Min and max are : " + minMaxArr[0] +", " + minMaxArr[1]);

        System.out.println("--- check in nary tree is symmetrical ---");
        NaryTreeNode root1Nary = new NaryTreeNode(3);
        root1Nary.children = Arrays.asList(new NaryTreeNode[] {
                new NaryTreeNode(5), new NaryTreeNode(7), new NaryTreeNode(5)});
        System.out.println(obj.isSymmetrical(root1Nary));

        NaryTreeNode root2Nary = new NaryTreeNode(3);
        root2Nary.children = Arrays.asList(new NaryTreeNode[] {
                new NaryTreeNode(5), new NaryTreeNode(7), new NaryTreeNode(8)});
        System.out.println(obj.isSymmetrical(root2Nary));

        NaryTreeNode root3Nary = new NaryTreeNode(3);
        root3Nary.children = Arrays.asList(new NaryTreeNode[] {
                new NaryTreeNode(5, Arrays.asList(new NaryTreeNode[] {
                        new NaryTreeNode(2), null
                })),
                new NaryTreeNode(7),
                new NaryTreeNode(5, Arrays.asList(new NaryTreeNode[] {
                        null, new NaryTreeNode(2)
                }))});
        System.out.println(obj.isSymmetrical(root3Nary));

        System.out.println("--- minimize max sum of each of K partition of array arr ---");
        arr = new int[] {5, 1, 4, 2, 7, 5};
        System.out.println(obj.minPartitionSum(arr, 3));
        arr = new int[] {5, 1, 2, 7, 3, 4};
        System.out.println(obj.minPartitionSum(arr, 3));
        arr = new int[] {1, 2, 15, 7, 5, 14, 3, 4};
        System.out.println(obj.minPartitionSum(arr, 3));
        arr = new int[] {5, 1, 4, 2, 7, 5};
        System.out.println(obj.minPartitionSum(arr, 2));
        arr = new int[] {5, 1, 4, 2, 7, 5};
        System.out.println(obj.minPartitionSum(arr, 1));

        Integer[] arrInt = new Integer[] {
                Integer.valueOf(5),
                Integer.valueOf(-2),
                Integer.valueOf(4),
                Integer.valueOf(-4),
                Integer.valueOf(10)
        };

        System.out.println("--- chain a circle from words based on start and end letters ---");
        String[] words;
        words = new String[] {
            "geek", "king"
        };
        System.out.println(obj.chainToCircle(words));

        words = new String[] {
                "geek", "kind", "dumb"
        };
        System.out.println(obj.chainToCircle(words));

        words = new String[] {
                "aab", "bfcfc", "cgba"
        };
        System.out.println(obj.chainToCircle(words));

        words = new String[] {
                "aa", "bb"
        };
        System.out.println(obj.chainToCircle(words));

        words = new String[] {
                "aaa"
        };
        System.out.println(obj.chainToCircle(words));

        words = new String[] {
                "awb", "bwd", "dwg", "dwf", "fera"
        };
        System.out.println(obj.chainToCircle(words));

        words = new String[] {
                "aab", "bac", "aaa", "cda"
        };
        System.out.println(obj.chainToCircle(words));

    }
}
