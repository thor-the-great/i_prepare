import linked_list.ListNode;
import linked_list.StringUtils;
import trees.BSTNode;

import java.util.*;

public class SolutionDailyCodingOctober2018 {

    /**
     * This problem was asked by Apple.
     *
     * Suppose you have a multiplication table that is N by N. That is, a 2D array where the value at the i-th row and
     * j-th column is (i + 1) * (j + 1) (if 0-indexed) or i * j (if 1-indexed).
     *
     * Given integers N and X, write a function that returns the number of times X appears as a value in an N by N
     * multiplication table.
     *
     * For example, given N = 6 and X = 12, you should return 4, since the multiplication table looks like this:
     *
     * | 1 | 2 | 3 | 4 | 5 | 6 |
     *
     * | 2 | 4 | 6 | 8 | 10 | 12 |
     *
     * | 3 | 6 | 9 | 12 | 15 | 18 |
     *
     * | 4 | 8 | 12 | 16 | 20 | 24 |
     *
     * | 5 | 10 | 15 | 20 | 25 | 30 |
     *
     * | 6 | 12 | 18 | 24 | 30 | 36 |
     *
     * And there are 4 12's in the table.
     *
     * @param N
     * @param X
     * @return
     *
     */
    int numInGrid(int N, int X) {
        int count = 0;
        for (int i = 1; i <= N; i++) {
            if (X % i == 0 && X / i <= N)
                count++;
        }
        return count;
        //return seiveBasedComplex(N, X);
    }

    private int seiveBasedComplex(int N, int X) {
        //calculate all prime numbers using seive method
        boolean[] seive = new boolean[X + 1];
        Arrays.fill(seive, true);
        //primes will be marked as true, then eliminate not primes
        for (int i = 2; i*i < X; i++) {
            if (seive[i]) {
                for (int j = i*2; j < X; j += i) {
                    seive[j] = false;
                }
            }
        }
        //now iterate over primes, divisors are factors of primes
        int numOfDivisors = 1;
        Set<Integer> usedNums = new HashSet<>();
        int n = X;
        for (int p = 2; p <= N; p++) {
            if (seive[p]) {
                int count = 0;
                if (X % p == 0) {
                    usedNums.add(p);
                    while (n % p == 0 ) {
                        n = n / p;
                        if (!usedNums.contains(n) && n <= N && X/n <= N) {
                            count++;
                            usedNums.add(n);
                        }
                    }
                    if (count > 0)
                        numOfDivisors *= count;
                }
            }
        }
        int result = 2*numOfDivisors;
        //check that it's not in the table diagonal
        double sqrt = Math.sqrt(X);
        if (sqrt - Math.floor(sqrt) == 0 && result > 0) {
            result -= 1;
        }
        return result;
    }

    /**
     * This problem was asked by Microsoft.
     *
     * Given an array of numbers, find the length of the longest increasing subsequence in the array.
     * The subsequence does not necessarily have to be contiguous.
     *
     * For example, given the array [0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15], the longest increasing
     * subsequence has length 6: it is 0, 2, 6, 9, 11, 15.
     *
     * @param arr
     * @return
     */
    int getLIS(int[] arr) {
        //return getLISRecursiveSlow(arr);
        //return getLISDP(arr);
        return getLISBSDP(arr);
    }

    private int getLISBSDP(int[] arr) {
        //idea is to construct the array with increasing sequence. The array is not correct, on every iteration we can
        //replace some elements. Position of the element to replace is given by the binary search.
        //time - O(log(n)) BS + O(1), n times => O(n*logn)
        int n = arr.length;
        int[] increasingArr = new int[n];
        int rightPointer = 0;
        for( int arrayElement : arr) {
            int indexToInsert = -(Arrays.binarySearch(increasingArr, 0, rightPointer, arrayElement)) - 1;
            increasingArr[indexToInsert] = arrayElement;
            if (indexToInsert == rightPointer)
                rightPointer++;
        }
        return rightPointer;
    }

    private int getLISDP(int[] arr) {
        //dp solution based on optimized recursive solution
        int n = arr.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        int max = 0;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        for (int dpEl : dp) if (dpEl > max) max = dpEl;
        return max;
    }

    private int getLISRecursiveSlow(int[] arr) {
        max = 1;
        check(arr, arr.length);
        return max;
    }

    int check(int[] arr, int index) {
        if (index == 1) return 1;
        int res, max_here = 1;
        for (int i = 1; i < index; i++) {
            res = check(arr, i);
            if (arr[i - 1] < arr[index - 1] && res + 1 > max_here){
                max_here = res + 1;
            }
            if (max_here > max) max = max_here;
        }
        return max_here;
    }

    int max;

    /**
     * This problem was asked by Google.
     *
     * You are given an N by M 2D matrix of lowercase letters. Determine the minimum number of columns that can be
     * removed to ensure that each row is ordered from top to bottom lexicographically. That is, the letter at each
     * column is lexicographically later as you go down each row. It does not matter whether each row itself is ordered
     * lexicographically.
     *
     * For example, given the following table:
     *
     * cba
     * daf
     * ghi
     * This is not ordered because of the a in the center. We can remove the second column to make it ordered:
     *
     * ca
     * df
     * gi
     * So your function should return 1, since we only needed to remove 1 column.
     *
     * As another example, given the following table:
     *
     * abcdef
     * Your function should return 0, since the rows are already ordered (there's only one row).
     *
     * As another example, given the following table:
     *
     * zyx
     * wvu
     * tsr
     * Your function should return 3, since we would need to remove all the columns to order it.
     *
     * @param matrix
     * @return
     */
    public int columnsToRemove(char[][] matrix) {
        int count = 0;
        int rows = matrix.length;
        int cols = matrix[0].length;
        if (rows == 0 || cols == 0)
            return 0;
        for (int col = 0; col < cols; col++) {
            char currentChar = matrix[0][col];
            for (int row = 1; row < rows; row++) {
                if (matrix[row][col] > currentChar) {
                    currentChar = matrix[row][col];
                }
                else {
                    count++;
                    break;
                }
            }
        }
        return count;
    }

    /**
     * This problem was asked by Snapchat.
     *
     * Given a list of possibly overlapping intervals, return a new list of intervals where all overlapping intervals
     * have been merged.
     *
     * The input list is not necessarily ordered in any way.
     *
     * For example, given [(1, 3), (5, 8), (4, 10), (20, 25)], you should return [(1, 3), (4, 10), (20, 25)].
     *
     * @param intervals
     * @return
     */
    List<int[]> mergeIntervals(List<int[]> intervals) {
        //idea is to sort intervals (per start time) and then iterate, comparing current interval with previously saved.
        //by sorting original list we save on time it's O(1)
        //sorting gives O(n*log(n)) running time, iteration - O(n), so overall - O(n*log(n))
        List<int[]> res = new ArrayList<>();
        int n = intervals.size();
        if (n == 0)
            return res;
        //sorting, lambda notation
        Collections.sort(intervals, (o1, o2) -> (o1[0] - o2[0]));
        int sPointer = 1;
        int[] mergedInterval = new int[2];
        mergedInterval[0] = intervals.get(0)[0];
        mergedInterval[1] = intervals.get(0)[1];
        while(sPointer < n) {
            if (mergedInterval[1] < intervals.get(sPointer)[0]) {
                res.add(mergedInterval);
                mergedInterval = new int[2];
                mergedInterval[0] = intervals.get(sPointer)[0];
                mergedInterval[1] = intervals.get(sPointer)[1];
            } else if (mergedInterval[1] < intervals.get(sPointer)[1]) {
                mergedInterval[1] = intervals.get(sPointer)[1];
            }
            sPointer++;
        }
        //after last iteration we haven't added array, so do it
        res.add(mergedInterval);
        return res;
    }

    /**
     * This problem was asked recently by Google.
     *
     * Given k sorted singly linked lists, write a function to merge all the lists into one sorted singly linked list.
     *
     * @param lists
     * @return
     */
    ListNode mergeToSorted(List<ListNode> lists) {
        //idea is - use mixHeap, put head of each list in it, then poll min element sequentially and add next to the
        //minHeap. If list is exhausted next will be null so no elements from this list stay in the heap
        PriorityQueue<ListNode> minQueue = new PriorityQueue<>(Comparator.comparingInt(o -> o.val));
        for(int i = 0; i < lists.size(); i++) {
            ListNode head = lists.get(i);
            minQueue.add(head);
        }
        ListNode firstMinNode = minQueue.poll();
        ListNode curr = firstMinNode;
        ListNode head = firstMinNode;
        if (curr.next != null)
            minQueue.add(curr.next);
        while(!minQueue.isEmpty()) {
            ListNode nextMinNode = minQueue.poll();
            curr.next = nextMinNode;
            curr = nextMinNode;
            if (nextMinNode.next != null) {
                minQueue.add(nextMinNode.next);
            }
        }
        return head;
    }

    /**
     * This problem was asked by Facebook.
     *
     * Given an array of integers, write a function to determine whether the array could become non-decreasing by
     * modifying at most 1 element.
     *
     * For example, given the array [10, 5, 7], you should return true, since we can modify the 10 into a 1 to make
     * the array non-decreasing.
     *
     * Given the array [10, 5, 1], you should return false, since we can't modify any one element to get a
     * non-decreasing array.
     *
     * @param nums
     * @return
     */
    boolean isNonDecreasingPossible(int[] nums) {
        //idea is to catch those increasing/decreasing points. If there are two or more of those - we definitely can't
        // do the requirement
        //otherwise it depends - if it's at certain index like 0 or last - 1 - we can do it. Otherwise need to check
        //surrounders of that index. if nums[index - 1] <= nums[index + 1] or nums[index] <= nums[index + 2] we can do it.
        //edge index cases covered by previous if
        //time - O(n) - full array scan
        //space - O(1) - just couple of extra variables
        int index = -1;
        int n = nums.length;
        if (n < 2)
            return true;
        for (int i = 0; i < n - 1; i++) {
            if (nums[i] > nums[i + 1]) {
                if (index != -1)
                    return false;
                else
                    index = i;
            }
        }
        if (index == -1 || index == 0|| index == n - 2) return true;
        return nums[index - 1] <= nums[index + 1] || nums[index] <= nums[index + 2];
    }

    BSTNode getDeepest(BSTNode root) {
        //do the in-level traversal and count levels as well. Keep track of deeper level and node we've seen so far
        //if we meet deeper level - update our tracking vars
        BSTNode deepestNode = root;
        int deepestLevel = 1;
        Queue<BSTNode> nodeQ = new LinkedList();
        Queue<Integer> levels = new LinkedList();
        nodeQ.add(root);
        levels.add(1);
        while (!nodeQ.isEmpty()) {
            BSTNode node = nodeQ.poll();
            int level = levels.poll();
            if (deepestLevel < level) {
                deepestLevel = level;
                deepestNode = node;
            }
            if (node.left != null) {
                nodeQ.add(node.left);
                levels.add(level + 1);
            }
            if (node.right != null) {
                nodeQ.add(node.right);
                levels.add(level + 1);
            }
        }

        return deepestNode;
    }

    /**
     * This problem was asked by Yelp.
     *
     * Given a mapping of digits to letters (as in a phone number), and a digit string, return all possible letters the
     * number could represent. You can assume each valid number in the mapping is a single digit.
     *
     * For example if {“2”: [“a”, “b”, “c”], 3: [“d”, “e”, “f”], …} then “23” should return
     * [“ad”, “ae”, “af”, “bd”, “be”, “bf”, “cd”, “ce”, “cf"].
     *
     * @param mapping
     * @return
     */
    List<String> possibleCombinations(Map<Integer, List<Character>> mapping) {
        return possibleCombinationsRecusrsive(mapping);
    }

    List<String> possibleCombinationsRecusrsive(Map<Integer, List<Character>> mapping) {
        int[] digits = new int[mapping.keySet().size()];
        int count = 0;
        for(int digit :  mapping.keySet()) {
            digits[count] = digit;
            count++;
        }
        List<String> res = new ArrayList();
        helperRec(mapping, res, "", digits, 0);
        return res;
    }

    void helperRec(Map<Integer, List<Character>> mapping, List<String> res, String s, int[] digits, int index) {
        if (index >= digits.length) {
            res.add(s);
            return;
        }

        for(int i =0; i < mapping.get(digits[index]).size(); i++) {
            char nextChar = mapping.get(digits[index]).get(i);
            helperRec(mapping, res, s + nextChar, digits, index + 1);
        }
    }

    /**
     * This problem was asked Microsoft.
     *
     * Using a read7() method that returns 7 characters from a file, implement readN(n) which reads n characters.
     *
     * For example, given a file with the content “Hello world”, three read7() returns “Hello w”, “orld” and then “”.
     *
     * @param n
     * @return
     */
    String readN(int n) {
        // idea is simple - count number of full reads and count of remaining chars.
        // do the n full reads and then 1 read is remainder > 0, then substring if needed. On every step check for empty
        // string
        pointer = 0;
        int evenReads = n / 7;
        int moduloChars = n % 7;

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= evenReads; i++) {
            String s = read7();
            if (s.isEmpty())
                return sb.toString();
            sb.append(s);
        }
        if (moduloChars > 0) {
            String s = read7();
            if (!s.isEmpty())
                sb.append(s, 0, moduloChars);
        }
        return sb.toString();
    }

    String readFileString = "Hello World! This is test string for the problem";
    int pointer = 0;

    //this fucntion required to test the solution
    String read7() {
        int startPointer = Math.min(readFileString.length(), pointer);
        int endPointer = Math.min(readFileString.length(), startPointer + 7);
        pointer = endPointer;
        return readFileString.substring(startPointer, endPointer);
    }

    /**
     * This problem was asked by Google.
     *
     * Invert a binary tree.
     *
     * For example, given the following tree:
     *
     *     a
     *    / \
     *   b   c
     *  / \  /
     * d   e f
     * should become:
     *
     *   a
     *  / \
     *  c  b
     *  \  / \
     *   f e  d
     *
     * @param root
     * @return
     */
    BSTNode reverseBinaryTree(BSTNode root) {
        //straightforward approach - do the level-order traversal and change node's children in place
        Queue<BSTNode> q = new LinkedList();
        q.add(root);
        while (!q.isEmpty()) {
            BSTNode n = q.poll();
            if (n.left != null) q.add(n.left);
            if (n.right != null) q.add(n.right);
            BSTNode tmp = n.left;
            n.left = n.right;
            n.right = tmp;
        }
        return root;
    }

    /**
     * This problem was asked by Amazon.
     *
     * Given a matrix of 1s and 0s, return the number of "islands" in the matrix. A 1 represents land and 0 represents
     * water, so an island is a group of 1s that are neighboring and their perimiter is surrounded by water.
     *
     * For example, this matrix has 4 islands.
     *
     * 1 0 0 0 0
     * 0 0 1 1 0
     * 0 1 1 0 0
     * 0 0 0 0 0
     * 1 1 0 0 1
     * 1 1 0 0 1
     *
     * @param grid
     * @return
     */
    public int islands(int[][] grid) {
        //do BFS for every 1 in the grid, find the land and mark it visited
        int rows = grid.length;
        int cols = grid[0].length;
        Set<LandCell> visited = new HashSet<>();
        int[][] moves = new int[][] {
                {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}
        };
        int islandsCount = 0;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                LandCell nextCell = new LandCell(r, c);
                if (grid[r][c] == 1 &&  !visited.contains(nextCell)) {
                    //starting BFS
                    Queue<LandCell> q = new LinkedList();
                    q.add(new LandCell(r, c));
                    while (!q.isEmpty()) {
                        LandCell cell = q.poll();
                        if (grid[cell.row][cell.col] == 1) {
                            visited.add(cell);
                            for (int i =0; i < moves.length; i++) {
                                int pointRNew = cell.row + moves[i][0];
                                int pointCNew = cell.col + moves[i][1];
                                LandCell newCell = new LandCell(pointRNew, pointCNew);
                                if (pointRNew >= 0 && pointRNew < rows && pointCNew >=0 && pointCNew < cols
                                        && grid[pointRNew][pointCNew] == 1 && !visited.contains(newCell)) {
                                    q.add(new LandCell(pointRNew, pointCNew));
                                    visited.add(newCell);
                                }
                            }
                        }
                    }
                    islandsCount++;
                }
            }
        }
        return islandsCount;
    }

    class LandCell {
        int row, col;

        LandCell(int r, int c) {
            row = r;
            col = c;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof LandCell) {
                LandCell l = (LandCell) obj;
                return (l.row == this.row && l.col == this.col);
            }
            return false;
        }

        @Override
        public int hashCode() {
            return row*31^col;
        }
    }

    /**
     * This problem was asked by Facebook.
     *
     * Given three 32-bit integers x, y, and b, return x if b is 1 and y if b is 0, using only mathematical or bit
     * operations. You can assume b can only be 1 or 0.
     *
     * @param x
     * @param y
     * @param b
     * @return
     */
    int chooseNum(int x, int y, int b) {
        //x*b gives us half of the solution. Now we need to set y to 0 in case b = 0 and we can add it to first expr
        //same happens with x when b = 1. Need to invert b, for that using XOR (^):
        //b = 1, b^1 = 0; b = 0, b^1 = 1
        return x*b + y*(b^1);
    }

    /**
     * This problem was asked by Google.
     *
     * Given a string of parentheses, write a function to compute the minimum number of parentheses to be removed to
     * make the string valid (i.e. each open parenthesis is eventually closed).
     *
     * For example, given the string "()())()", you should return 1. Given the string ")(", you should return 2,
     * since we must remove all of them.
     *
     * @param s
     * @return
     */
    int numExtraParentheses(String s) {
        //idea is to count num of opened and closed paren. In case it starts closing without opened ones - need to count
        //each such entry. If after all iterations we'll have num of opened paren without closed - count those as well.
        if (s == null)
            return 0;
        int res = 0;
        int opened = 0, closed = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(' ) opened++;
            else closed++;
            if (closed > opened) {
                res++;
                closed -=1;
            }
        }
        if (opened > closed)
            res += opened - closed;
        return res;
    }

    /**
     * This question was asked by ContextLogic.
     *
     * Implement division of two positive integers without using the division, multiplication, or modulus operators.
     * Return the quotient as an integer, ignoring the remainder.
     *
     * @param a
     * @param b
     * @return
     */
    int div(int a, int b) {
        //return bruteForceSlow(a, b);
        return divBitShifts(a, b);
    }

    int divBitShifts(long x, long y) {
        //doing long division approach but in 2-base system. Starting from left to right check how many whole
        //y-s are fit and carry remainder
        //start from the max number - y^32, must be long to fit in
        int res = 0;
        int power = 32;
        long rem = x;
        long yPow = y << power;
        //do the main loop unless remainder is lower than y (0 if it's divisible evenly)
        while (rem >= y) {
            //keep shifting bits (divide by 2) while yPow became <= remainder, then accumulate result in res. We need to
            //add to result 2^power = do bit shift
            while (yPow > rem) {
                yPow = yPow >> 1;
                power--;
            }
            res = res + (1 << power);
            //calculate new remainder by subtracting yPow
            rem = rem - yPow;
        }
        return res;
    }

    private int bruteForceSlow(int a, int b) {
        //just use addition - start adding b unless we reach a and count number of times we run the loop
        if (a == 0 || b == 0) return 0;
        int res = 0;
        int tmp = 0;
        while(tmp <= a) {
            tmp += b;
            res++;
        }
        return res - 1;
    }

    public static void main(String[] args) {
        SolutionDailyCodingOctober2018 obj = new SolutionDailyCodingOctober2018();

        System.out.println("------ find number of times number X is in grid of multiplication N by N ---------");
        System.out.println("N=6, X=12; " + obj.numInGrid(6, 12));
        System.out.println("N=6, X=6; " + obj.numInGrid(6, 6));
        System.out.println("N=6, X=8; " + obj.numInGrid(6, 8));
        System.out.println("N=6, X=30; " + obj.numInGrid(6, 30));
        System.out.println("N=8, X=16; " + obj.numInGrid(8, 16));
        System.out.println("N=10, X=24; " + obj.numInGrid(10, 24));
        System.out.println("N=15, X=20; " + obj.numInGrid(15, 20));

        System.out.println("------ get Longest increasing sequence (LIS) ---------");
        System.out.println(obj.getLIS(new int[] {3, 1, 4}));
        System.out.println(obj.getLIS(new int[] {3, 2, 4, 6, 1, 9, 5}));
        System.out.println(obj.getLIS(new int[] {3, 2}));
        System.out.println(obj.getLIS(new int[] {8, 6, 4, 2, 1}));
        System.out.println(obj.getLIS(new int[] {1, 3, 2, 4, 5, 6, 7}));
        System.out.println(obj.getLIS(new int[] {10, 7, 2, 6, 1, 0}));

        System.out.println("--- num of unsorted cols in matrix ----");
        char[][] charMatrix;
        charMatrix = new char[][] {
                {'c', 'b', 'a'},
                {'d', 'a', 'f'},
                {'g', 'h', 'i'}
        };
        System.out.println(obj.columnsToRemove(charMatrix));

        charMatrix = new char[][] {
                {'a', 'b', 'c', 'd', 'e', 'f'}
        };
        System.out.println(obj.columnsToRemove(charMatrix));

        charMatrix = new char[][] {
                {'z', 'y', 'x'},
                {'w', 'v', 'u'},
                {'t', 's', 'r'}
        };
        System.out.println(obj.columnsToRemove(charMatrix));

        System.out.println("---- return list of merged overlapped intervals -----");
        List<int[]> intervals = new ArrayList<>();
        intervals.add(new int[] {1, 3});
        intervals.add(new int[] {5, 8});
        intervals.add(new int[] {4, 10});
        intervals.add(new int[] {20, 25});

        List<int[]> merged = obj.mergeIntervals(intervals);

        for (int [] array : merged ) {
            System.out.print("[" + array[0] + ", "+array[1] + "], ");
        }
        System.out.println("");

        intervals = new ArrayList<>();
        intervals.add(new int[] {1, 3});
        intervals.add(new int[] {2, 7});
        intervals.add(new int[] {5, 8});
        intervals.add(new int[] {4, 10});
        intervals.add(new int[] {20, 25});
        merged = obj.mergeIntervals(intervals);
        for (int [] array : merged ) {
            System.out.print("[" + array[0] + ", "+array[1] + "], ");
        }
        System.out.println("");

        intervals = new ArrayList<>();
        intervals.add(new int[] {2, 8});
        intervals.add(new int[] {1, 7});
        intervals.add(new int[] {5, 8});
        intervals.add(new int[] {4, 10});
        intervals.add(new int[] {20, 25});
        merged = obj.mergeIntervals(intervals);
        for (int [] array : merged ) {
            System.out.print("[" + array[0] + ", "+array[1] + "], ");
        }
        System.out.println("");

        intervals = new ArrayList<>();
        intervals.add(new int[] {2, 8});
        merged = obj.mergeIntervals(intervals);
        for (int [] array : merged ) {
            System.out.print("[" + array[0] + ", "+array[1] + "], ");
        }
        System.out.println("");

        intervals = new ArrayList<>();
        merged = obj.mergeIntervals(intervals);
        for (int [] array : merged ) {
            System.out.print("[" + array[0] + ", "+array[1] + "], ");
        }
        System.out.println("");

        System.out.println("----- merge k sorted lists --------");
        ListNode headOne = new ListNode(2, new ListNode(4, new ListNode(8)));
        ListNode headTwo = new ListNode(1, new ListNode(6, new ListNode(12, new ListNode(15))));
        ListNode headThree = new ListNode(0, new ListNode(4, new ListNode(7, new ListNode(9, new ListNode(11)))));
        List<ListNode> lists = new ArrayList<>();
        lists.add(headOne);
        lists.add(headTwo);
        lists.add(headThree);
        ListNode sortedMerged = obj.mergeToSorted(lists);
        System.out.println(StringUtils.singlyListNodeToString(sortedMerged));

        System.out.println("----- is non-decreasing array possible with one replacement max -------");
        System.out.println(obj.isNonDecreasingPossible(new int[] {10, 5, 7}));//true
        System.out.println(obj.isNonDecreasingPossible(new int[] {10, 5, 1}));//false
        System.out.println(obj.isNonDecreasingPossible(new int[] {-5, 1, 2, 3, 2, 3, 6}));//true
        System.out.println(obj.isNonDecreasingPossible(new int[] {5, 1, 2, 3, 2, 3, 6}));//false
        System.out.println(obj.isNonDecreasingPossible(new int[] {3, 4, 2, 3}));//false

        System.out.println("---- deepest node in binary tree ------");
        /*
         *               1
         *              /  \
         *            2      3
         *          /  \     / \
         *        4     5   6   7
         *         \    /\      /\
         *          8  9  10  11  12
         *                /    \
         *               13    14
         */

        BSTNode root = new BSTNode(1,
                new BSTNode(2,
                        new BSTNode(4,
                                null,
                                new BSTNode(8, null, null)),
                        new BSTNode(5,
                                new BSTNode(9, null, null),
                                new BSTNode(10, new
                                        BSTNode(13, null, null), null))),
                new BSTNode(3,
                        new BSTNode(6, null, null),
                        new BSTNode(7,
                                new BSTNode(11,
                                        null, new BSTNode(14, null, null)),
                                new BSTNode(12, null, null))));

        System.out.println(obj.getDeepest(root));//13

        /*
         *               1
         *              /  \
         *            2      3
         *             \       \
         *              4       5
         *                      /
         *                     6
         */

        root = new BSTNode(1,
                new BSTNode(2,
                        null,
                        new BSTNode(4,null,null)),
                new BSTNode(3,
                        null,
                        new BSTNode(5,
                                new BSTNode(6, null, null),
                                null)));
        System.out.println(obj.getDeepest(root));//6

        System.out.println("---- possible combinations for keypad (custom) ------\n");
        Map<Integer, List<Character>> map = new HashMap<>();
        map.put(2, Arrays.asList(new Character[] {'a', 'b', 'c'}));
        map.put(3, Arrays.asList(new Character[] {'d', 'e', 'f'}));
        map.put(4, Arrays.asList(new Character[] {'g', 'h', 'i'}));
        map.put(5, Arrays.asList(new Character[] {'j', 'k', 'l'}));
        List<String> possibleWords = obj.possibleCombinations(map);
        for (String s : possibleWords) {
            System.out.print(s + ", ");
        }

        System.out.println("----- read n chars from file using read7 function ------");
        System.out.println(obj.readN(10));
        System.out.println(obj.readN(3));
        System.out.println(obj.readN(20));

        System.out.println("---- invert binary tree ----");
        BSTNode root2 = new BSTNode(1,
                new BSTNode(2,
                        new BSTNode(4,null,null),
                        new BSTNode(5,null,null)),
                new BSTNode(3,
                        new BSTNode(6, null, null),
                        new BSTNode(7, null, null)
                        ));
        System.out.print("Original tree  : \n" + utils.StringUtils.binaryTreeToString(root2));
        obj.reverseBinaryTree(root2);
        System.out.print("\nInverted : \n" + utils.StringUtils.binaryTreeToString(root2));
        BSTNode root3 = new BSTNode(1,
                new BSTNode(2,
                        new BSTNode(4,
                                new BSTNode(7, null, null),
                                null),
                        new BSTNode(5,null,null)),
                new BSTNode(3,
                        new BSTNode(6, null, null),
                        null
                ));
        System.out.print("\nOriginal tree  : \n" + utils.StringUtils.binaryTreeToString(root3));
        obj.reverseBinaryTree(root3);
        System.out.print("\nInverted : \n" + utils.StringUtils.binaryTreeToString(root3));

        System.out.println("\n------ count islands ------------");
        int[][] grid = new int[][]{{}};
        grid = new int[][] {
                {1, 0, 0, 0, 0},
                {0, 0, 1, 1, 0},
                {0, 1, 1, 0, 0},
                {0, 0, 0, 0, 0},
                {1, 1, 0, 0, 1},
                {1, 1, 0, 0, 1}
        };
        System.out.println(obj.islands(grid));

        grid = new int[][] {
                {1, 0, 0, 0, 0},
                {0, 0, 1, 0, 1},
                {0, 1, 0, 0, 0},
                {0, 0, 0, 1, 1},
                {1, 1, 0, 0, 0},
                {1, 0, 0, 0, 1}
        };
        System.out.println(obj.islands(grid));

        grid = new int[][] {
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0}
        };
        System.out.println(obj.islands(grid));

        grid = new int[][] {
                {1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1}
        };
        System.out.println(obj.islands(grid));

        System.out.println("---- choose number from two depending on the flag ----");
        System.out.println(obj.chooseNum(4, 16, 1));
        System.out.println(obj.chooseNum(4, 16, 0));
        System.out.println(obj.chooseNum(-54, 20, 1));
        System.out.println(obj.chooseNum(-54, 20, 0));

        System.out.println("---- num of parentheses to remove for ideal string -----");
        System.out.println(obj.numExtraParentheses("(())()"));
        System.out.println(obj.numExtraParentheses("(()())"));
        System.out.println(obj.numExtraParentheses("())()"));//1
        System.out.println(obj.numExtraParentheses("(()()"));//1
        System.out.println(obj.numExtraParentheses(")("));//2
        System.out.println(obj.numExtraParentheses(")))())))"));//6
        System.out.println(obj.numExtraParentheses("))()("));//3
        System.out.println(obj.numExtraParentheses("(()((()"));//3

        System.out.println("---- division without using the division, multiplication, or modulus operators ----");
        System.out.println(obj.div(15, 6));
        System.out.println(obj.div(15, 3));
        System.out.println(obj.div(0, 4));
        System.out.println(obj.div(1000000000, 40));
    }
}
