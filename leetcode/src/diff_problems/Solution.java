package diff_problems;

import linked_list.ListNode;
import linked_list.StringUtils;
import trees.BST;
import trees.BSTNode;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.concurrent.LinkedBlockingDeque;

public class Solution {

    /**
     * https://leetcode.com/contest/weekly-contest-94/problems/leaf-similar-trees/
     * @param root1
     * @param root2
     * @return
     */
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        Stack<TreeNode> firstT = new Stack();
        firstT.add(root1);
        Stack<TreeNode> secondT = new Stack();
        secondT.add(root2);
        boolean isCheckReady1 = false;
        boolean isCheckReady2 = false;
        int val1 = -1, val2 = -1;
        while (!firstT.isEmpty() || !secondT.isEmpty()) {
            if (!isCheckReady1) {
                TreeNode n1 = firstT.pop();
                if (n1.left == null && n1.right == null) {
                    val1 = n1.val;
                    isCheckReady1 = true;
                } else {
                    addNode(n1.left, firstT);
                    addNode(n1.right, firstT);
                }
            }
            if (!isCheckReady2) {
                TreeNode n2 = secondT.pop();
                if (n2.left == null && n2.right == null) {
                    val2 = n2.val;
                    isCheckReady2 = true;
                } else {
                    addNode(n2.left, secondT);
                    addNode(n2.right, secondT);
                }
            }

            if (isCheckReady1 && isCheckReady2) {
                if (val1 != val2)
                    return false;
                isCheckReady1 = false;
                isCheckReady2 = false;
            }
        }
        return true;
    }

    private void addNode(TreeNode node, Stack stack) {
        if (node != null)
            stack.push(node);
    }

    /**
     * https://leetcode.com/contest/weekly-contest-94/problems/walking-robot-simulation/
      * @param commands
     * @param obstacles
     * @return
     */
    public int robotSim(int[] commands, int[][] obstacles) {
        //map of dirrections
        // 0 ^, 1 >, 2 down, 3 <
        int currentDirection = 0;
        int[] dirX = new int[] {0, 1, 0, -1};
        int[] dirY = new int[] {1, 0, -1, 0};
        int x = 0, y = 0;
        Set<Long> obstacleSet = new HashSet<>();
        for (int j=0; j<obstacles.length; j++) {
            int[] nextObst = obstacles[j];
            long obstacleCoded = encode(nextObst[0], nextObst[1]);
            obstacleSet.add(obstacleCoded);
        }
        int maxDistance = 0;
        for (int i=0; i < commands.length; i++) {
            int command = commands[i];
            if (command == -2) {
                currentDirection = currentDirection == 0 ? 3 : currentDirection-1;
                continue;
            }
            if (command == -1) {
                currentDirection = currentDirection == 3 ? 0 : currentDirection+1;
                continue;
            }

            //now go step by step
            int dx = dirX[currentDirection];
            int dy = dirY[currentDirection];
            for (int s = 1; s <= command; s++) {
                int newX = x + dx;
                int newY = y + dy;
                long newCoordinatesCoded = encode(newX, newY);
                if (!obstacleSet.contains(newCoordinatesCoded)) {
                    x = newX;
                    y = newY;
                }
                else
                    break;
            }
            int newPossibleDistance = ((x*x) + (y*y));
            if (maxDistance < newPossibleDistance) {
                maxDistance = newPossibleDistance;
            }
        }
        return maxDistance;
    }

    private long encode(int x, int y) {
        return (((long) x + 30000) << 16) + ((long) y + 30000);
    }

    /**
     * https://leetcode.com/contest/weekly-contest-94/problems/koko-eating-bananas/
     * @param H
     * @param piles
     * @return
     */
    public int minEatingSpeed(int[] piles, int H) {
        //max should not be higher than greatest element in array
        int max = 0;
        for (int i = 0; i < piles.length; i++) {
            if(piles[i] > max)
                max = piles[i];
        }
        //now do the binary search on possible solutions. there is a linear dependency - the lower is k the longer (higher H)
        //it will take
        int min = 1;
        while (max > min) {
            int med = (max + min) /2;
            if (isBelowH(H, med, piles)) {
                max = med;
            } else {
                min = med + 1;
            }
        }
        return min;
    }

    boolean isBelowH(int H, int k, int[] piles) {
        int counter = 0;
        for(int i = 0; i < piles.length; i++) {
            counter = counter + (piles[i]-1)/k+1;
        }
        return (counter <= H);
    }

    /**
     * https://leetcode.com/contest/weekly-contest-94/problems/length-of-longest-fibonacci-subsequence/
     * @param A
     * @return
     */
    public int lenLongestFibSubseq(int[] A) {
        //create set of numbers so we can lookup efficiently
        Set<Integer> set = new HashSet();
        for (int i = 0; i < A.length; i++) {
            set.add(A[i]);
        }
        //iterate over numbers in array, i represents first and j - second number to add for Fibo check
        int result = 0;
        for (int i =0 ; i < A.length; i++) {
            for (int j = i + 1 ; j < A.length; j++) {
                int cur = A[j];
                int next = A[i] + A[j];
                int len = 2;
                //go over values in array, if the number is in array - increment len
                while(set.contains(next)) {
                    //do the shift of numbers for next possible Fibo number
                    //cur,next -> next, next+cur
                    int tmp = next;
                    next = next + cur;
                    cur = tmp;
                    len++;
                }
                //save the len if it's higher than already saved result
                if (len > result)
                    result = len;
            }
        }
        return result >= 3 ? result : 0;
    }

    /**
     * This problem was asked by Facebook.
     *
     * Given the mapping a = 1, b = 2, ... z = 26, and an encoded message, count the number of ways it can be decoded.
     *
     * For example, the message '111' would give 3, since it could be decoded as 'aaa', 'ka', and 'ak'.
     *
     * You can assume that the messages are decodable. For example, '001' is not allowed.
     *
     * @param message
     * @return
     */
    int getNumberOfEncodings(char[] message) {
        //return internalMsg(message, message.length);
        return internalMsg2(message);
    }

    //first approach - naive iteration, exponential O
    int internalMsg(char[] m, int n) {
        //base case, wraps up the recursion
        if (n == 1 || n == 0)
            return 1;
        //start recursion on next symbol
        int count = internalMsg(m, n-1);
        //check last 2 numbers, if it's between 10 and 26 - add one more recursion iteration
        if ((m[n - 2] == '1' || m[n - 2] == '2') && m[n - 1] <= '6' )
            count += internalMsg(m, n -2);
        return count;
    }

    int internalMsg2(char[] m) {
        //results, index is represent number of chars checked in the message
        //memorize the number of decodings for each number of chars, then re-use it on next step (dynamic programming)
        int[] memoSols = new int[m.length +1];
        //base cases
        memoSols[0] = 1;
        memoSols[1] = 1;
        //iterate, start from numbers of decoding on previous step, then add delta on current step
        for (int i = 2; i <= m.length; i++) {
            memoSols[i] = memoSols[i - 1];
            if ((m[i - 2] == '1' || m[i - 2] == '2') && m[i - 1] <= '6' )
                memoSols[i] += memoSols[i -2];
        }
        return memoSols[m.length];
    }

    /**
     * This problem was asked by Amazon.
     *
     * There exists a staircase with N steps, and you can climb up either 1 or 2 steps at a time. Given N, write a function that returns the number of unique ways you can climb the staircase. The order of the steps matters.
     *
     * For example, if N is 4, then there are 5 unique ways:
     *
     * 1, 1, 1, 1
     * 2, 1, 1
     * 1, 2, 1
     * 1, 1, 2
     * 2, 2
     * What if, instead of being able to climb 1 or 2 steps at a time, you could climb any number from a set of positive integers X? For example, if X = {1, 3, 5}, you could climb 1, 3, or 5 steps at a time.
     *
     * @param N
     * @return
     */
    int getNumWays(int N) {
        //all solution is based on idea that there is a pattern that n of steps required to climb on staircase with i stairs = steps[i-1] + steps[i-2] -
        //sum of number of steps required to climb on staircase with i-1 and i-2 stairs
        //base case
        if (N == 0)
            return 1;
        //this is set of possible steps. When steps are fixed it's possible to
        //avoid nested loop and address memoized data directly by index -> code looks simpler
        int[] steps = new int[] {1, 2};
        //cache for DP memoization
        int[] memo = new int[N + 1];
        memo[0] = 1;
        for (int i = 1; i <= N; i++) {
            int total = 0;
            for (int j = 0; j < steps.length; j++) {
                if (i - steps[j]>=0)
                    total += memo[i-steps[j]];
            }
            memo[i] = total;
        }
        return memo[N];
    }

    /**
     * This problem was asked by Amazon.
     *
     * Given an integer k and a string s, find the length of the longest substring that contains at most k distinct characters.
     *
     * For example, given s = "abcba" and k = 2, the longest substring with k distinct characters is "bcb".
     *
     * @param s
     * @param k
     * @return
     */
    int longestSubstringLength(String s, int k) {
        int res = 0;
        int[] charCounts = new int[26];
        int left = 0;
        int currentIterationCount = 0;
        for (int i = 0; i < s.length(); i++) {
            int charIndex = s.charAt(i) - 97;
            if (charCounts[charIndex] == 0) {
                currentIterationCount++;
            }
            if (currentIterationCount <= k) {
                charCounts[charIndex]++;
            } else {
                res = Math.max(res, i - left);
                while(true) {
                    int leftIndex = s.charAt(left) - 97;
                    charCounts[leftIndex]--;
                    left++;
                    if (charCounts[leftIndex] == 0) {
                        currentIterationCount--;
                        break;
                    }
                }
                charCounts[charIndex]++;
            }
        }
        res = Math.max(res, s.length() - left);
        return res;
    }

    /**
     * This problem was asked by Google.
     *
     * The area of a circle is defined as πr^2. Estimate π to 3 decimal places using a Monte Carlo method.
     *
     * Hint: The basic equation of a circle is x2 + y2 = r2.
     *
     * @return
     */
    double getPi() {
        //idea is to use math property of the circle.
        //Let's take square 1*1, it's area is 1. Then let's inscribe a quadrant within it.
        //all points that are within that quadrant should fit the inequity x^2+y^2 < r^2 which in this case
        //transforms into x^2+y^2 < 1. So if we uniformly cover square with points and count how many are inside
        //the quadrant and how many are outside, then will have area of 1/4 of the circle = pi*r^2/4 = pi/4.
        //So pi in this case will be pi = 4*(area_of_quadrant)=4*(num_points_in_circle/total_number_of_points)
        int numOfTries = 25000;
        Random rand = new Random();
        int countInCircle = 0;
        for (int i = 0; i < numOfTries; i++) {
            double nextX = rand.nextDouble();
            double nextY = rand.nextDouble();
            double distance = Math.sqrt(nextX*nextX + nextY*nextY);
            if (distance < 1.0f)
                countInCircle++;
        }

        double pi = (countInCircle/(double)numOfTries)*4.0;
        return pi;
    }

    /**
     * This problem was asked by Facebook.
     *
     * Given a stream of elements too large to store in memory, pick a random element from the stream with uniform probability.
     *
     * @return
     */
    int pickRandom() {
        //reservoir sampling, on each next pick generate random number from 1 to current count, and compare with some constant (e.g. 1)
        //if they are equals - cunt this number as selected
        int pickedSymbol = 0;
        String fileName = "C:\\tmp\\99394_9142017_TEMPLATE_FULL.csv";
        try(InputStream is = new FileInputStream(fileName)) {
            int count = 1;
            int next = is.read();
            Random rand = new Random();
            while(next != -1) {
                int nextRand = rand.nextInt(count) + 1;
                if (nextRand == 1) {
                    pickedSymbol = next;
                    System.out.println("Picked next number " + pickedSymbol + " at count " + count);
                }
                next = is.read();
                count++;
            }
            System.out.println("Total number of symbols " + count);
            System.out.println("Direct random pick " + rand.nextInt(count + 1));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pickedSymbol;
    }

    /**
     * The N queens puzzle
     * The N queens puzzle is the classic backtracking problem. The question is this:
     *
     * You have an N by N board. Write a function that returns the number of possible arrangements of the board where
     * N queens can be placed on the board without threatening each other, i.e. no two queens share the same row,
     * column, or diagonal.
     *
     * @param n
     * @return
     */
    int nQueensPuzzle(int n) {
        //this is recursive backtracking approach
        return checkBoard(n, new ArrayList());
    }

    int checkBoard(int n, ArrayList board) {
        //base case
        if (n == board.size())
            return 1;

        //iterate over board. Board is a 1D array, where index is a row and value in the index is column positions
        int count =0;
        //jump to next row
        board.add(0);
        for (int i =0; i < n; i++) {
            //trying different columns for one row in sequence
            //replacing elements, should be more efficient comparing to add/remove cycle
            board.set(board.size() - 1, i);
            if (isValid(board)) {
                count += checkBoard(n, board);
            }
        }
        board.remove(board.size() -1);
        return count;
    }

    boolean isValid(ArrayList<Integer> board) {
        int currentQueenRow = board.size() - 1;
        int currentQueenColumn = board.get(currentQueenRow);
        for (int row = 0; row < board.size() - 1; row++) {
            int col = board.get(row);
            int diff = Math.abs(currentQueenColumn - col);
            if (diff == 0 || diff == (currentQueenRow - row)) {
                return false;
            }
        }
        return true;
    }

    /**
     * The flight itinerary problem is as follows:
     *
     * Given an unordered list of flights taken by someone, each represented as (origin, destination) pairs, and a starting airport, compute the person's itinerary. If no such itinerary exists, return null. All flights must be used in the itinerary.
     *
     * For example, given the following list of flights:
     *
     * HNL ➔ AKL
     * YUL ➔ ORD
     * ORD ➔ SFO
     * SFO ➔ HNL
     * and starting airport YUL, you should return YUL ➔ ORD ➔ SFO ➔ HNL ➔ AKL. (This also happens to be the actual itinerary for the trip I'm about to take.)
     *
     * @param flights
     * @param startPoint
     * @return
     */
    List<String> getItinerary(List<String[]> flights, String startPoint) {
        List<String> itinerary = new ArrayList<>();
        itinerary.add("YUL");
        return getItineraryInternal(flights, itinerary);
    }

    List<String> getItineraryInternal(List<String[]> flights, List<String> itinerary) {
        if (flights == null || flights.isEmpty())
            return itinerary;

        String lastStop = itinerary.get(itinerary.size() - 1);
        for (int i = 0; i < flights.size(); i++) {
            List<String[]> flightsMinusCurrent = new ArrayList<>();
            for (int j = 0; j < flights.size(); j++) {
                if(i != j) {
                    String[] flight = flights.get(j);
                    flightsMinusCurrent.add(flight);
                }
            }
            String origin = flights.get(i)[0];
            String dest = flights.get(i)[1];
            itinerary.add(dest);
            if (origin.equals(lastStop)) {
                return getItineraryInternal(flightsMinusCurrent, itinerary);
            }
            itinerary.remove(itinerary.size() - 1);
        }
        return null;
    }

    /**
     * This problem was asked by Google.
     *
     * Suppose we represent our file system by a string in the following manner:
     *
     * The string "dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext" represents:
     *
     * dir
     *     subdir1
     *     subdir2
     *         file.ext
     * The directory dir contains an empty sub-directory subdir1 and a sub-directory subdir2 containing a file file.ext.
     *
     * The string "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext" represents:
     *
     * dir
     *     subdir1
     *         file1.ext
     *         subsubdir1
     *     subdir2
     *         subsubdir2
     *             file2.ext
     * The directory dir contains two sub-directories subdir1 and subdir2. subdir1 contains a file file1.ext and an empty second-level sub-directory subsubdir1. subdir2 contains a second-level sub-directory subsubdir2 containing a file file2.ext.
     *
     * We are interested in finding the longest (number of characters) absolute path to a file within our file system. For example, in the second example above, the longest absolute path is "dir/subdir2/subsubdir2/file2.ext", and its length is 32 (not including the double quotes).
     *
     * Given a string representing the file system in the above format, return the length of the longest absolute path to a file in the abstracted file system. If there is no file in the system, return 0.
     *
     * Note:
     *
     * The name of a file contains at least a period and an extension.
     *
     * The name of a directory or sub-directory will not contain a period.
     *
     * @param pathString
     * @return
     */
    int findLongestPath(String pathString) {
        int maxPath = 0;
        String[] pathParts = pathString.split("\\n");
        FSNode root = new FSNode();
        root.isFolder = true;
        root.pathLength = 0;
        //this is indentation counter, this is how we can calculate level of the fs item
        int currentInt = -1;
        FSNode currentNode = root;
        for (int i = 0; i < pathParts.length; i++) {
            int lastTab = pathParts[i].lastIndexOf("\t");
            FSNode nextNode = new FSNode();
            String itemName = lastTab == -1 ? pathParts[i] : pathParts[i].substring(lastTab + 1);
            nextNode.isFolder = !itemName.contains(".");
            int nextInt = lastTab == -1 ? 0 : (lastTab + 1);
            //previous parent - identify it based on the current level (copare it via indentation counter)
            FSNode prevParentNode;
            if (nextInt > currentInt) { //this is when when we jump one level deeper
                prevParentNode = currentNode;
                if (currentInt > 0)
                    nextNode.pathLength++;
            } else if (nextInt == currentInt) { //same level - sibling
                prevParentNode = currentNode.parent;
                nextNode.pathLength++;
            } else { //if nextInt < currentInt - we going back on higher levels
                prevParentNode = currentNode.parent.parent;
                nextNode.pathLength++;
            }
            prevParentNode.child.add(nextNode);
            nextNode.parent = prevParentNode;
            nextNode.pathLength += prevParentNode.pathLength + itemName.length();
            currentNode = nextNode;
            currentInt = nextInt;
            //this is where we save current max path length
            if (!nextNode.isFolder && nextNode.pathLength > maxPath)
                maxPath = nextNode.pathLength;
        }
        return maxPath;
    }

    class FSNode {
        boolean isFolder;
        List<FSNode> child = new LinkedList<>();
        FSNode parent;
        int pathLength;
    }

    /**
     * This problem was asked by Google.
     *
     * Given an array of integers and a number k, where 1 <= k <= length of the array, compute the maximum values of each subarray of length k.
     *
     * For example, given array = [10, 5, 2, 7, 8, 7] and k = 3, we should get: [10, 7, 8, 8], since:
     *
     * 10 = max(10, 5, 2)
     * 7 = max(5, 2, 7)
     * 8 = max(2, 7, 8)
     * 8 = max(7, 8, 7)
     * Do this in O(n) time and O(k) space. You can modify the input array in-place and you do not need to store the results. You can simply print them out as you compute them.
     *
     * @param array
     * @param k
     */
    void getMaxValues(int[] array, int k) {
        //getMaxValuesMaxHeap(array, k);
        getMaxValuesDeque(array, k);
    }

    void getMaxValuesDeque(int[] array, int k) {
        //this one based on tricky invariant of array and two-ended (double-ended) queue. We keep in the queue only
        //indexes!! of those elements that are potentially valid to be selected. Left side (first) is greatest element
        //and right side (last) is smallest but potentially valuable element
        Deque<Integer> deque = new LinkedBlockingDeque<>();
        //init deque
        for (int i = 0; i < k; i++) {
            while (!deque.isEmpty() && array[i] >= array[deque.peekLast()]) {
                deque.pollLast();
            }
            deque.addLast(i);
        }

        for (int i = k; i < array.length; i++) {
            int nextMaxElement = array[deque.peekFirst()];
            System.out.println(nextMaxElement);
            //remove element of sub-array that are out of next subarray edge
            while (!deque.isEmpty() && deque.peekFirst() <= i - k)
                deque.pollFirst();
            //remove elements based on their value, compare to the next array element
            while (!deque.isEmpty() && array[i] >= array[deque.peekLast()])
                deque.pollLast();
            deque.addLast(i);
        }
        //last cycle is skipped because i reached end of the array. Need to catch up, simply get first element
        System.out.println(array[deque.peekFirst()]);
    }

    void getMaxValuesMaxHeap(int[] array, int k) {
        //using binary max-heap to get max element from sub-array. O(n) = N*logN, too slow
        PriorityQueue<Integer> pQueue = new PriorityQueue<>(array.length, (o1, o2) -> o2 - o1);
        //init heap for the firt sub-array, for k -1 elements
        for (int i=0; i < k - 1; i++) {
            pQueue.add(array[i]);
        }
        for (int j = k - 1; j < array.length; j++) {
            pQueue.add(array[j]);
            int elements = pQueue.peek();
            System.out.println("Max element : " + elements);
            //remove first element of sub-array
            pQueue.remove(array[j - k + 1]);
        }
    }

    /**
     * This problem was asked by Facebook.
     *
     * A builder is looking to build a row of N houses that can be of K different colors. He has a goal of minimizing
     * cost while ensuring that no two neighboring houses are of the same color.
     *
     * Given an N by K matrix where the nth row and kth column represents the cost to build the nth house with kth color,
     * return the minimum cost which achieves this goal.
     *
     * @param housePaintCost
     * @return
     */
    int getMinimumCost(int[][] housePaintCost) {
        int n = housePaintCost.length;
        int k = housePaintCost[0].length;
        int[] solutions = new int[k];

        for (int i = 0; i < n; i++) {
            int[] nextSolutions = new int[k];
            for (int j = 0; j < k; j++) {
                int min = Integer.MAX_VALUE;
                for (int l = 0; l < k; l++) {
                    if ((l != j) && (solutions[l] < min)){
                        min = solutions[l];
                    }
                }
                nextSolutions[j] = min + housePaintCost[i][j];
            }
            solutions = nextSolutions;
        }

        int min = Integer.MAX_VALUE;
        for(int i = 0; i < k; i++) {
            if (solutions[i] < min)
                min = solutions[i];
        }
        return min;
    }

    Node findTreeIntersection(Node treeA, Node treeB) {
        Node n = treeA;
        int countA = 0;
        while(n != null) {
            n = n.next;
            countA++;
        }

        int countB = 0;
        n = treeB;
        while(n != null) {
            n = n.next;
            countB++;
        }

        if (countA > countB)
            return findIntersection(treeA, treeB, countA - countB);
        else
            return findIntersection(treeB, treeA, countB - countA);
    }

    Node findIntersection(Node longerTree, Node shorterTree, int diff) {
        for (int i = 0 ; i < diff; i++) {
            longerTree = longerTree.next;
        }

        Node node1 = longerTree;
        Node node2 = shorterTree;
        while (node1 != null) {
            if (node1.val == node2.val)
                break;
            else {
                node1 = node1.next;
                node2 = node2.next;
            }
        }
        return node1;
    }

    static class Node {
        int val;
        Node next;
        Node(int val) {
            this.val = val;
        }

        @Override
        public String toString() {
            return Integer.toString(val);
        }
    }

    /**
     * This problem was asked by Snapchat.
     *
     * Given an array of time intervals (start, end) for classroom lectures (possibly overlapping), find the minimum number of rooms required.
     *
     * For example, given [(30, 75), (0, 50), (60, 150)], you should return 2.
     *
     * @param intervals
     * @return
     */
    int getMinRooms(List<int[]> intervals) {
        //return getMinRoomsBrute(intervals);
        return  getMinRoomsEffective(intervals);
    }

    int getMinRoomsEffective(List<int[]> intervals) {
        //trick is to sort times - separately start and end. This is n*log(n)
        int[] sortedStarts = new int[intervals.size()];
        int[] sortedEnds = new int[intervals.size()];
        int count = 0;
        for (int[] interval : intervals) {
            sortedStarts[count] = interval[0];
            sortedEnds[count] = interval[1];
            count++;
        }
        Arrays.sort(sortedStarts);
        Arrays.sort(sortedEnds);

        //now do the scan for both arrays at the same time. Compare current start and current end
        //if end > start - this is overlap. Record it and more to the next start/end element.
        int i = 0, j = 0;
        int currentMax = 0, currentOverlap = 0;

        while( i < sortedStarts.length && j < sortedEnds.length) {
            if (sortedStarts[i] > sortedEnds[j]) {
                currentOverlap--;
                j++;
            }
            else {
                currentOverlap++;
                currentMax = Math.max(currentMax, currentOverlap);
                i++;
            }
        }
        return currentMax;
    }

    //brute force solution - check intervals pairs one by one
    int getMinRoomsBrute(List<int[]> intervals) {
        int currentMax = 0;
        for (int i = 0; i < intervals.size(); i++) {
            int numOverlapping = 0;
            for (int j = 0; j < intervals.size(); j++) {
                if (i != j) {
                    int[] int1 = intervals.get(i);
                    int[] int2 = intervals.get(j);
                    if (isOverlaps(int1, int2))
                        numOverlapping++;
                }
            }
            currentMax = Math.max(currentMax, numOverlapping);
        }
        return currentMax;
    }

    boolean isOverlaps(int[] int1, int[] int2) {
        if ((int1[1] > int2[0] && int1[1] < int2[1])
                || (int2[1] > int1[0] && int2[1] < int1[1]))
            return true;
        else
            return false;
    }

    /**
     * This problem was asked by Microsoft.
     *
     * Given a dictionary of words and a string made up of those words (no spaces), return the original sentence in a list. If there is more than one possible reconstruction, return any of them. If there is no possible reconstruction, then return null.
     *
     * For example, given the set of words 'quick', 'brown', 'the', 'fox', and the string "thequickbrownfox", you should return ['the', 'quick', 'brown', 'fox'].
     *
     * Given the set of words 'bed', 'bath', 'bedbath', 'and', 'beyond', and the string "bedbathandbeyond", return either ['bed', 'bath', 'and', 'beyond] or ['bedbath', 'and', 'beyond'].
     *
     * @param s
     * @param words
     * @return
     */
    List<String> getSentenseFromString(String s, String[] words) {
        //return getSentenseFromStringBackTracking(s, words);
        return getSentenseFromStringDP(s, words);
    }

    List<String> getSentenseFromStringDP(String s, String[] words) {
        //this is DP solution. We craft array of Lists, where each list is possible words from start to this index.
        //if words are not possible array will be null
        List<String> dp[] = new ArrayList[s.length() + 1];
        dp[0] = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            if (dp[i] == null)
                continue;
            for (String word : words) {
                int wordLength = word.length();
                int end = i + wordLength;
                if (end > s.length())
                    continue;
                String stringPart = s.substring(i, end);
                if (stringPart.equals(word)){
                    if (dp[end] == null)
                        dp[end] = new ArrayList<>();
                    dp[end].add(word);
                }
            }
        }
        //here we construct at least one possible sentence (using element from index 0). Because we are going from the
        //end we need to reverse the list or insert elements to the head (using double-ended queue and addFirst method)
        LinkedList<String> result = new LinkedList<>();
        if (dp[s.length()] != null) {
            int pointer = s.length();
            while(pointer > 0) {
                String nextWord = dp[pointer].get(0);
                result.addFirst(nextWord);
                pointer -= nextWord.length();
            }
        }
        return result;
    }

    List<String> getSentenseFromStringBackTracking(String s, String[] words) {
        ReturnObj returnObj = checkStringInternal(s, words);
        if (returnObj.isValid)
            return returnObj.result;
        else
            return new LinkedList<>();
    }


    ReturnObj checkStringInternal(String s, String[] words) {
        if (s.length() == 0)
            return new ReturnObj(true, new LinkedList<>());

        for (int i = 0; i < s.length(); i++) {
            String suffix = s.substring(0, i);
            String prefix = s.substring(i);
            if (checkIfWordValid(words, prefix)) {
                ReturnObj suffixResult = checkStringInternal(suffix, words);
                if (suffixResult.isValid) {
                    suffixResult.result.add(prefix);
                    return suffixResult;
                }
            }
        }
        return new ReturnObj(false, new LinkedList<>());
    }

    boolean checkIfWordValid(String[] words, String word) {
        boolean res = false;
        for(String oneWord : words) {
            if (oneWord.equals(word)) {
                res = true;
                break;
            }
        }
        return res;
    }

    class ReturnObj {

        ReturnObj (boolean isValid, List<String> res) {
            this.isValid = isValid;
            this.result = res;
        }

        boolean isValid = false;
        List<String> result;
    }

    /**
     * This problem was asked by Google.
     *
     * You are given an M by N matrix consisting of booleans that represents a board. Each True boolean represents a wall. Each False boolean represents a tile you can walk on.
     *
     * Given this matrix, a start coordinate, and an end coordinate, return the minimum number of steps required to reach the end coordinate from the start. If there is no possible path, then return null. You can move up, left, down, and right. You cannot move through walls. You cannot wrap around the edges of the board.
     *
     * For example, given the following board:
     *
     * [[f, f, f, f],
     * [t, t, f, t],
     * [f, f, f, f],
     * [f, f, f, f]]
     * and start = (3, 0) (bottom left) and end = (0, 0) (top left), the minimum number of steps required to reach the end is 7, since we would need to go through (1, 2) because there is a wall everywhere else on the second row.
     *
     * @param board
     * @param start
     * @param end
     * @return
     */
    int getMinimumPath(boolean[][] board, int[] start, int[] end) {
        //doing BFS for the graph of board. keep tracking of what we seen in the separate matrix
        //get next possible pathes by getting valid neighbours.
        int N = board.length;
        int M = board[0].length;

        boolean[][] seen = new boolean[N][M];

        Stack<int[]> queuePoints = new Stack();
        Stack<Integer> queuePath = new Stack();
        queuePoints.push(start);
        queuePath.push(0);
        while(!queuePoints.empty()) {
            int[] point = queuePoints.pop();
            int pathL = queuePath.pop();
            if (point[0] == end[0] && point[1] == end[1]) {
                return pathL;
            }
            seen[point[0]][point[1]] = true;
            List<int[]> neighbours = validNeighbours(point[0], point[1], board);
            for (int[] nextPoint : neighbours) {
                if (!seen[nextPoint[0]] [nextPoint[1]]) {
                    seen[nextPoint[0]] [nextPoint[1]] = true;
                    queuePoints.push(nextPoint);
                    queuePath.push(pathL+1);
                }
            }
        }
        return 0;
    }


    List<int[]> validNeighbours(int x, int y, boolean[][] board) {
        List<int[]> result = new LinkedList();
        if (isValidStep(x + 1, y , board))
            result.add(new int[]{x + 1, y});
        if (isValidStep(x - 1, y , board))
            result.add(new int[]{x - 1, y});
        if (isValidStep(x , y + 1, board))
            result.add(new int[]{x, y + 1});
        if (isValidStep(x, y - 1, board))
            result.add(new int[]{x, y - 1});
        return result;
    }

    boolean isValidStep(int x, int y, boolean[][] board) {
        int N = board.length;
        int M = board[0].length;
        if (x >= 0 && x < N && y >=0 && y < M)
            return !board[x][y];
        else
            return false;
    }

    /**
     * This problem was asked by Google.
     *
     * Given a singly linked list and an integer k, remove the kth last element from the list. k is guaranteed to be smaller than the length of the list.
     *
     * The list is very long, so making more than one pass is prohibitively expensive.
     *
     * Do this in constant space and in one pass.
     *
     * @param root
     * @param k
     * @return
     */
    ListNode removeElementFromEnd(ListNode root, int k) {
        //two pointers, fist move forward fast one on i steps. Then start moving both pointers simultaneously
        //when fast pointer reaches the end, slow pointer points on the element we need to remove
        ListNode fast = root;
        for (int i = 0; i < k; i++) {
            fast = fast.next;
        }

        ListNode prev = null;
        ListNode slow = root;
        while(fast != null) {
            fast = fast.next;
            prev = slow;
            slow = slow.next;
        }

        prev.next = slow.next;
        return root;
    }

    /**
     * This problem was asked by Facebook.
     *
     * Implement regular expression matching with the following special characters:
     *
     * . (period) which matches any single character
     * * (asterisk) which matches zero or more of the preceding element
     * That is, implement a function that takes in a string and a valid regular expression and returns whether or not the string matches the regular expression.
     *
     * For example, given the regular expression "ra." and the string "ray", your function should return true. The same regular expression on the string "raymond" should return false.
     *
     * Given the regular expression ".*at" and the string "chat", your function should return true. The same regular expression on the string "chats" should return false.
     *
     * @param s
     * @param r
     * @return
     */
    public boolean isMatch(String s, String r) {
        //return isMatchRec(s, r);
        //return isMatchDP(s, r);
        return isMatchRecMy(s, r);
    }

    boolean isFirstMatch(String s, String r) {
        return (!s.isEmpty() &&
                (r.charAt(0) == s.charAt(0) || r.charAt(0) == '.'));
    }

    public boolean isMatchRecMy(String s, String r) {
        if (r.isEmpty())
            return s.isEmpty();

        boolean firstMatch = isFirstMatch(s, r);
        if (r.length() == 1 || r.charAt(1) != '*') {
            if( firstMatch)
                return isMatchRecMy(s.substring(1), r.substring(1));
            else
                return false;
        }
        else {
            if (isMatchRecMy(s, r.substring(2)))
                return true;
            int i = 0;
            while (isFirstMatch(s.substring(i), r)) {
                if (isMatchRecMy(s.substring(i+ 1), r.substring(2)))
                    return  true;
                i++;
            }
        }
        return false;
    }

    public boolean isMatchRec(String s, String r) {
        if (r.isEmpty())
            return s.isEmpty();
        boolean isFirstMatch = isFirstMatch(s, r);
        //try 2 symbols forward in regexp if r[1] is '*'
        if (r.length() >= 2 && r.charAt(1) == '*') {
            if (isMatch(s, r.substring(2)))
                return true;
            else {
                return isFirstMatch && isMatch(s.substring(1), r);
            }
        }
        else {
            //else trying next symbols in string and regexp
            if (!isFirstMatch)
                return false;
            else {
                boolean nextSymbolMatch = isMatch(s.substring(1), r.substring(1));
                return  nextSymbolMatch;
            }
        }
    }

    public boolean isMatchDP(String text, String pattern) {
        boolean[][] dp = new boolean[text.length() + 1][pattern.length() + 1];
        dp[text.length()][pattern.length()] = true;

        for (int i = text.length(); i >= 0; i--){
            for (int j = pattern.length() - 1; j >= 0; j--){
                boolean first_match = (i < text.length() &&
                        (pattern.charAt(j) == text.charAt(i) ||
                                pattern.charAt(j) == '.'));
                if (j + 1 < pattern.length() && pattern.charAt(j+1) == '*'){
                    dp[i][j] = dp[i][j+2] || first_match && dp[i+1][j];
                } else {
                    dp[i][j] = first_match && dp[i+1][j+1];
                }
            }
        }
        return dp[0][0];
    }

    boolean isBalanced(String s) {
        Stack<Integer> stack = new Stack();

        for (int i = 0; i < s.length(); i++) {
            int nextNum = 0;
            char nextChar = s.charAt(i);
            switch(nextChar) {
                case '[' : nextNum = 1; break;
                case '{' : nextNum = 2; break;
                case '(' : nextNum = 3; break;
                case ')' : nextNum = -3; break;
                case '}' : nextNum = -2; break;
                case ']' : nextNum = -1; break;
                default	 : nextNum = 0;
            }
            if (stack.isEmpty()) {
                if (nextNum < 0)
                    return false;
                else
                    stack.push(nextNum);
            }
            else {
                int prevNum = stack.peek();
                if (nextNum > 0)
                    stack.push(nextNum);
                else if (nextNum < 0) {
                    if (prevNum + nextNum != 0)
                        return false;
                    else
                        stack.pop();
                }
            }
        }
        return stack.size() == 0;
    }

    /**
     * This problem was asked by Palantir.
     *
     * Write an algorithm to justify text. Given a sequence of words and an integer line length k, return a list of strings which represents each line, fully justified.
     *
     * More specifically, you should have as many words as possible in each line. There should be at least one space between each word. Pad extra spaces when necessary so that each line has exactly length k. Spaces should be distributed as equally as possible, with the extra spaces, if any, distributed starting from the left.
     *
     * If you can only fit one word on a line, then you should pad the right-hand side with spaces.
     *
     * Each word is guaranteed not to be longer than k.
     *
     * For example, given the list of words ["the", "quick", "brown", "fox", "jumps", "over", "the", "lazy", "dog"] and k = 16, you should return the following:
     *
     * ["the  quick brown", # 1 extra space on the left
     * "fox  jumps  over", # 2 extra spaces distributed evenly
     * "the   lazy   dog"] # 4 extra spaces distributed evenly
     *
     * @param words
     * @param k
     * @return
     */
    List<String> justify(String[] words, int k) {
        List<String> result = new ArrayList();
        //we keep words that we processed in the queue FIFO, so we keep initial order in final line
        Queue<String> q = new LinkedList();
        //this is counter of chars in each line, this includes length of word plus one space between words (don't
        //include spaces before first and after last words)
        int c = 0;
        for (int i = 0; i < words.length; i++) {
            //calculate current length of each line
            int nextL = c + words[i].length() + (q.isEmpty() ? 0 : 1);
            if (nextL < k) {
                //if we didn't reach the limit - add word to queue and increment counter
                c = nextL;
            } else {
                //flush line into output and reset all counters
                addOneLine(result, k, c, q);
                c = words[i].length();
            }
            q.add(words[i]);
        }
        //after loop is over with still need to handle last line
        if (!q.isEmpty()) {
            addOneLine(result, k, c, q);
        }
        return result;
    }

    void addOneLine(List<String> res, int k, int c, Queue<String> q) {
        //count spaces that we have, do't forget to rollback spaces added between words
        int extraSpaces = k - c + (q.size() - 1);
        StringBuilder sb = new StringBuilder();
        //special case - pad string for the left
        if (q.size() == 1) {
            for (int j = 0; j < extraSpaces; j++) sb.append(" ");
            sb.append(q.poll());
        } else {
            //how many spaces between each word
            int spaceEachWord = extraSpaces / (q.size() - 1);
            //how many extra, only one max per word
            int spaceExtra = extraSpaces % (q.size() - 1);
            //go over queue and add each word with spaces after
            while (!q.isEmpty()) {
                sb.append(q.poll());
                //if it's not the last word - add spaces
                if (q.size() > 0) {
                    //between every word
                    for (int j = 0; j < spaceEachWord; j++) sb.append(" ");
                    //and extra if we have one
                    if (spaceExtra > 0) {
                        sb.append(" ");
                        spaceExtra--;
                    }
                }
            }
        }
        res.add(sb.toString());
    }

    /**
     * This problem was asked by Amazon.
     *
     * Run-length encoding is a fast and simple method of encoding strings. The basic idea is to represent repeated successive
     * characters as a single count and character. For example, the string "AAAABBBCCDAA" would be encoded as "4A3B2C1D2A".
     *
     * Implement run-length encoding and decoding. You can assume the string to be encoded have no digits and consists
     * solely of alphabetic characters. You can assume the string to be decoded is valid.
     *
     * @param s
     * @return
     */
    String encode(String s) {
        int c = 0;
        char ch = '0';
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char nextCh = s.charAt(i);
            if (nextCh != ch) {
                if (c == 0) {
                    ch = nextCh;
                    c++;
                } else {
                    sb.append(c).append(ch);
                    c = 1;
                    ch = nextCh;
                }
            }
            else
                c++;
        }
        if (c > 0)
            sb.append(c).append(ch);
        return sb.toString();
    }

    String decode(String s) {
        StringBuilder sb = new StringBuilder();
        String counterString = "";
        for (int i = 0; i < s.length(); i++) {
            char nextCh = s.charAt(i);
            //this is a number
            if (Character.isDigit(nextCh)) {
                counterString = counterString + nextCh;
            } else {
                int count = Integer.parseInt(counterString);
                for (int j = 0; j < count; j++)
                    sb.append(nextCh);
                counterString = "";
            }
        }
        return sb.toString();
    }

    /**
     * This problem was asked by Facebook.
     *
     * You are given an array of non-negative integers that represents a two-dimensional elevation map where each
     * element is unit-width wall and the integer is the height. Suppose it will rain and all spots between two walls
     * get filled up.
     *
     * Compute how many units of water remain trapped on the map in O(N) time and O(1) space.
     *
     * For example, given the input [2, 1, 2], we can hold 1 unit of water in the middle.
     *
     * Given the input [3, 0, 1, 3, 0, 5], we can hold 3 units in the first index, 2 in the second, and 3 in the fourth
     * index (we cannot hold 5 since it would run off to the left), so we can trap 8 units of water.
     *
     * @param arr
     * @return
     */
    int getWaterTrapped(int[] arr) {
        //return getCapacityWithExtraArray(arr);
        return capacity(arr);
    }

    int capacity(int[] arr) {
        if (arr == null || arr.length < 3)
            return 0;
        int maxVal = -1;
        int maxIndex = -1;
        //first find max element
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > maxVal) {
                maxVal = arr[i];
                maxIndex = i;
            }
        }
        //now iterate from the 0 to max. we know that for sure capacity is no less than the
        //maximum element from 0 to max index (exclusive). So it's sufficient to find only running
        //maximum
        int c = 0;
        int maxL = arr[0];
        for (int i =1; i < maxIndex; i++) {
            maxL = Math.max(maxL, arr[i]);
            c += maxL - arr[i];
        }
        //and from max to last - similar idea, just iterate from end to max index
        int maxR = arr[arr.length - 1];
        for (int i = arr.length - 2; i > maxIndex; i--) {
            maxR = Math.max(maxR, arr[i]);
            c += maxR - arr[i];
        }
        return c;
    }

    /**
     * this is non optimal implementation in terms of space - we need two extra arrays
     * @param arr
     * @return
     */
    private int getCapacityWithExtraArray(int[] arr) {
        if (arr == null || arr.length < 3)
            return 0;
        //fill columns of max capacity first from left and from right. Before the next max
        //all previous elements are filled with previous max
        //then iterate over both arrays and check for the min between max. Then subtract form it current array element
        //and this will be the capacity
        int[] lMaxArr = new int[arr.length];
        int lMax = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > lMax) lMax = arr[i];
            lMaxArr[i] = lMax;
        }
        int[] rMaxArr = new int[arr.length];
        int rMax = arr[0];
        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] > rMax) rMax = arr[i];
            rMaxArr[i] = rMax;
        }
        int c = 0;
        for (int i = 0; i < arr.length; i++) {
            c+= Math.min(rMaxArr[i], lMaxArr[i]) - arr[i];
        }
        return c;
    }

    /**
     * This problem was asked by Google.
     *
     * The edit distance between two strings refers to the minimum number of character insertions, deletions,
     * and substitutions required to change one string to the other. For example, the edit distance between “kitten” and
     * “sitting” is three: substitute the “k” for “s”, substitute the “e” for “i”, and append a “g”.
     *
     * Given two strings, compute the edit distance between them.
     * @param s1
     * @param s2
     * @return
     */
    int editDistance(String s1, String s2) {
        //return recursiveSlow(s1, s2);
        return getDistanceDynProg(s1, s2);
    }

    private int getDistanceDynProg(String s1, String s2) {
        //do dp approach - create matrix sol where [i][j] is edit distance between s1[i:] and s2[j:]
        //we need one extra col and row to store initial values
        int l1 = s1.length() + 1;
        int l2 = s2.length() + 1;

        int[][] sol = new int[l1][l2];
        //init matrix. for every first letter the distance will be the running +1 number due to difference in length
        for(int i = 0; i < l1; i++)
            sol[i][0] = i;
        for(int i = 0; i < l2; i++)
            sol[0][i] = i;

        //main cycle
        for (int i = 1; i < l1; i++) {
            for (int j = 1; j < l2; j++) {
                //if symbols are identical in both strings - carry over value from previous cell
                if (s1.charAt(i - 1) == s2.charAt(j - 1))
                    sol[i][j] = sol[i - 1][j - 1];
                else {
                    //if symbols are not equal - calculate minimum (we need min distance) between following:
                    //- previous in both strings
                    //- current with s2 string and previous with s1
                    //- current with s1 string and previous with s2
                    //need to add 1 because we need to change current symbol
                    sol[i][j] = 1 + Math.min(sol[i - 1][j - 1],
                            Math.min(sol[i - 1][j], sol[i][j - 1] ));
                }
            }
        }
        return sol[l1 - 1][l2 - 1];
    }

    private int recursiveSlow(String s1, String s2) {
        if (s1.length() == 0 || s2.length() == 0)
            return Math.max(s1.length(), s2.length());

        int len1 = recursiveSlow(s1.substring(1), s2.substring(1));
        if (s1.charAt(0) != s2.charAt(0))
            len1++;
        int len2 = recursiveSlow(s1.substring(1), s2) + 1;
        int len3 = recursiveSlow(s1, s2.substring(1)) + 1;
        return Math.min(len1, Math.min(len2, len3));
    }

    /**
     * This problem was asked by Jane Street.
     *
     * Suppose you are given a table of currency exchange rates, represented as a 2D array. Determine whether there
     * is a possible arbitrage: that is, whether there is some sequence of trades you can make, starting with some
     * amount A of any currency, so that you can end up with some amount greater than A of that currency.
     *
     * There are no transaction costs and you can trade fractional quantities.
     *
     * @param rates
     * @return
     */
    boolean isProfit(float[][] rates) {
        int n = rates.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                rates[i][j] = -(float) Math.log(rates[i][j]);
            }
        }
        float[] minDinstances = new float[n];
        Arrays.fill(minDinstances, Float.POSITIVE_INFINITY);
        minDinstances[0] = 0;

        List<String> profitPath = new LinkedList<>();

        for (int c = 0; c < n - 1; c++) {
            for (int i = 0; i < n; i++) {
                String point = "";
                for (int j = 0; j < n; j++) {
                    if (minDinstances[j] > minDinstances[i] + rates[i][j]) {
                        minDinstances[j] = minDinstances[i] + rates[i][j];
                        point = "" + i + "," + j;
                    }
                }
                profitPath.add(point);
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (minDinstances[j] > minDinstances[i] + rates[i][j]) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * This problem was asked by Microsoft.
     *
     * Compute the running median of a sequence of numbers. That is, given a stream of numbers, print out the median
     * of the list so far on each new element.
     *
     * Recall that the median of an even-numbered list is the average of the two middle numbers.
     *
     * For example, given the sequence [2, 1, 5, 7, 2, 0, 5], your algorithm should print out:
     *
     * 2
     * 1.5
     * 2
     * 3.5
     * 2
     * 2
     * 2
     * @param arr
     * @return
     */
    List<String> runningMedian(int[] arr) {
        //two heaps - min-heap and max-heap, that keep between then median. Trick is to keep it balanced
        List<String> med = new LinkedList();
        PriorityQueue<Integer> minQ = new PriorityQueue();
        //this is max heap, java has only min-heap by default
        PriorityQueue<Integer> maxQ = new PriorityQueue<>((x, y) -> y - x);
        for (int i = 0; i < arr.length; i++) {
            //special case - both heaps are empty
            if (minQ.size() == 0 && maxQ.size() == 0) {
                maxQ.add(arr[i]);
                med.add(String.valueOf(arr[i]));
                continue;
            }
            //decide to which heap we put element. min-heap is out default for equals
            float maxMed = maxQ.peek();
            if (arr[i] < maxMed) {
                maxQ.add(arr[i]);
            } else {
                minQ.add(arr[i]);
            }
            //now balance queues as per size and get our median
            if (maxQ.size() == minQ.size())
                med.add(String.valueOf(getMedainFromHeaps(minQ, maxQ)));
            else if (maxQ.size() == minQ.size() + 1)
                med.add(String.valueOf(maxQ.peek()));
            else if (maxQ.size() > minQ.size() + 1) {
                minQ.add(maxQ.poll());
                med.add(String.valueOf(getMedainFromHeaps(minQ, maxQ)));
            } else if (minQ.size() == maxQ.size() + 1)
                med.add(String.valueOf((float)minQ.peek()));
            else if (minQ.size() > maxQ.size() + 1) {
                maxQ.add(minQ.poll());
                med.add(String.valueOf(getMedainFromHeaps(minQ, maxQ)));
            }
        }
        return med;
    }

    private float getMedainFromHeaps(PriorityQueue<Integer> minQ, PriorityQueue<Integer> maxQ) {
        return (float) (maxQ.peek() + minQ.peek()) / 2;
    }

    /**
     * This problem was asked by Quora.
     *
     * Given a string, find the palindrome that can be made by inserting the fewest number of characters as
     * possible anywhere in the word. If there is more than one palindrome of minimum length that can be made,
     * return the lexicographically earliest one (the first one alphabetically).
     *
     * For example, given the string "race", you should return "ecarace", since we can add three letters to it
     * (which is the smallest amount to make a palindrome). There are seven other palindromes that can be made from
     * "race" by adding three letters, but "ecarace" comes first alphabetically.
     *
     * As another example, given the string "google", you should return "elgoogle".
     * @param s
     * @return
     */
    String makePalindrome(String s) {
        //checking for palindrome, need to check 2 cases because we can either append from the begging or to the end
        //for appending to an end: need two pointers, first at 0, second at last char. Then checking for equal chars,
        //if they are equals - move both pointers to the center. If not - insert to the end char from the left pointer
        //do this till pointers will met
        //then do the same but inserting missing symbols to the beginning
        //then with two palindroms pick the shortest one, one in case of draw - one lexicographically smaller
        String palindrome1 = createPalindrome(s, true);
        String palindrome2 = createPalindrome(s, false);
        if (palindrome1.length() < palindrome2.length())
            return palindrome1;
        else if (palindrome1.length() > palindrome2.length())
            return palindrome2;
        else if (palindrome1.charAt(0) < palindrome2.charAt(0))
            return palindrome1;
        else
            return palindrome2;
    }

    String createPalindrome(String s, boolean isAppendToEnd) {
        //flag isAppendToEnd indicates whether we append letters to the tail or to the head of initial string
        StringBuilder sb = new StringBuilder(s);
        //two pointers technique - one points to 1-st, second - to the last char of string
        int l = 0, r = sb.length() - 1;
        while (l < r) {
            //if symbols of both edges are equal - palindrome invariant kept so just move both pointers
            if (sb.charAt(l) == sb.charAt(r)) {
                l++;
                r--;
            } else {
                //if invariant not kept - insert letter to make string 1 symbol more palindromic. We don't need
                //to increment right pointer because letter has been inserted so all chars shifted
                if (isAppendToEnd)
                    sb.insert(r + 1, sb.charAt(l));
                else
                    sb.insert(l, sb.charAt(r));
                l++;
            }
        }
        return sb.toString();
    }

    /**
     * This problem was asked by Google.
     *
     * Given an array of strictly the characters 'R', 'G', and 'B', segregate the values of the array so that all the
     * Rs come first, the Gs come second, and the Bs come last. You can only swap elements of the array.
     *
     * Do this in linear time and in-place.
     *
     * For example, given the array ['G', 'B', 'R', 'R', 'B', 'R', 'G'], it should become ['R', 'R', 'R', 'G', 'G', 'B', 'B'].
     * @param arr
     */
    void sortArray(char[] arr) {
        //do just counting, B counter start from the end, R starts from the beginning. Pointer for G sarts from the
        //beginning as well, but it's a running pointer. Note how we skip m++ for 'B'
        // We also check in swap method that elements are the same - in
        //this case we skip the swap.

        // l - 'R', m - 'G', r - 'B'
        int l = 0, m = 0, r = arr.length - 1;
        while (m <= r) {
            switch (arr[m]) {
                case 'R':
                    swap(arr, l, m);
                    l++;
                    m++;
                    break;
                case 'G':
                    m++;
                    break;
                case 'B':
                    swap(arr, r, m);
                    r--;
                    break;
                default:
                    break;
            }
        }
    }

    void swap(char[] arr, int i, int j) {
        if (arr[i] != arr[j]) {
            char temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }


    boolean isArbitragePossible(float[][] rates) {
        float[][] logRates = new float[rates.length][rates[0].length];

        for (int i = 0; i < rates.length; i++) {
            for (int j = 0; j < rates[0].length; j++) {
                logRates[i][j] = (float) - Math.log(rates[i][j]);
            }
        }
        int n = rates.length;
        float[] minDist = new float[n];
        Arrays.fill(minDist, Float.MAX_VALUE);
        minDist[0] = 0;
        //doing bellman-ford v - 1 times
        for (int i = 0; i < n - 1; i++) {
            for (int k = 0; k < n; k++) {
                for (int m = 0; m < n; m++) {
                    if (minDist[m] > minDist[k] + logRates[k][m]) {
                        minDist[m] = minDist[k] + logRates[k][m];
                    }
                }
            }
        }

        for (int k = 0; k < n; k++) {
            for (int m = 0; m < n; m++) {
                if (minDist[m] > minDist[k] + logRates[k][m]) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * This problem was asked by Dropbox.
     *
     * Given the root to a binary search tree, find the second largest node in the tree.
     *
     * @param root
     * @return
     */
    int getSecondMax(BSTNode root) {
        //idea is to use inorder traversal. This order gives us sorted order of elements, where we need to pick one
        //as per condition - second in our case
        //there are two ways to do the traversal - iterative and recursive
        //return getSecondMaxRecursive(root);
        return getSecondMaxIterative(root);
    }

    int getSecondMaxIterative(BSTNode root) {
        int count = 0;
        int maxElement = -1;
        Stack<BSTNode> stack = new Stack<>();
        BSTNode current = root.right;
        while ((!stack.isEmpty() || current != null) && count < 2) {
            while (current != null) {
                stack.push(current);
                current = current.right;
            }

            current = stack.pop();
            maxElement = current.val;
            count++;
            current = current.left;
        }
        return maxElement;
    }

    int getSecondMaxRecursive(BSTNode root) {
        List<Integer> inOrderValues = new LinkedList();
        inOrder(root, inOrderValues);
        if (inOrderValues.size() >= 2)
            return inOrderValues.get(1);
        else
            return -1;
    }

    void inOrder(BSTNode node, List<Integer> inOrderValues) {
        if (node == null || inOrderValues.size() >= 2)
            return;
        inOrder(node.right, inOrderValues);
        inOrderValues.add(node.val);
        inOrder(node.left, inOrderValues);
    }

    /**
     * This problem was asked by Google.
     *
     * The power set of a set is the set of all its subsets. Write a function that, given a set, generates its power set.
     *
     * For example, given the set {1, 2, 3}, it should return {{}, {1}, {2}, {3}, {1, 2}, {1, 3}, {2, 3}, {1, 2, 3}}.
     *
     * You may also use a list or array to represent a set.
     *
     * @param array
     * @return
     */
    List<List<Integer>> getPowerSet(int[] array) {
        //return getPowerSetExtraMemmory(array);
        return getPowerSetLongVector(array);
    }

    List<List<Integer>> getPowerSetLongVector(int[] array) {
        List<List<Integer>> result = new LinkedList<>();
        //this is how many elements will be in the power set
        long powerSetSize = (long) Math.pow(2, array.length);
        for (int c = 0; c < powerSetSize; c++) {
            List<Integer> l = new ArrayList<>();
            for (int i = 0; i < array.length; i++) {
                //check the i-th bit in the counter is set, if so - print i-th element from the array to this set
                //example - set {1, 2, 3}, counter = 000
                //000 -> {}
                //001 -> {a}
                //010 -> {b}
                //100 -> {c}
                //101 -> {a, c}
                //110 -> {c, b}
                //111 -> {c, b, a}
                if ((c & (1 << i)) > 0) {
                    l.add(array[i]);
                    //System.out.println("bngo " + c + ", " + i+ ", " + (c & (1 << i)));
                }
                else {
                    //System.out.println("hm " + c + ", " + i + ", " + (c & (1 << i)));
                }
            }
            result.add(l);
        }
        return result;
    }

    List<List<Integer>> getPowerSetExtraMemmory(int[] array) {
        List<List<Integer>> result = new LinkedList<>();

        for (int i = 0; i < array.length; i++) {
            List<List<Integer>> temp = new LinkedList<>();
            for (List<Integer> a : result) {
                ArrayList<Integer> b = new ArrayList<>(a);
                b.add(array[i]);
                temp.add(b);
            }
            for(List<Integer> t : temp) {
                result.add(t);
            }
            ArrayList<Integer> singleElement = new ArrayList<>();
            singleElement.add(array[i]);
            result.add(singleElement);
        }
        result.add(new ArrayList());

        return result;
    }

    public static void main(String[] args) {
        Solution obj = new Solution();
        //[3,5,1,6,2,9,8,null,null,7,4]
        /*TreeNode three = new TreeNode(3);
        TreeNode five = new TreeNode(5);
        TreeNode one = new TreeNode(1);
        three.left = five;
        three.right = one;
        TreeNode six = new TreeNode(6);
        TreeNode two = new TreeNode(2);
        five.left = six;
        five.right = two;*/

        /*System.out.println(obj.robotSim(new int[] {4,-1,4,-2,4}, new int[][]{{2,4}}));

        System.out.println(obj.robotSim(new int[] {7,-2,-2,7,5}, new int[][] {
                {-3, 2},
                {-2, 1},
                {0, 1},
                {-2, 4},
                {-1, 0},
                {-2, -3},
                {0, -3},
                {4, 4},
                {-3, 3},
                {2, 2}
        }));*/

        //System.out.println(obj.minEatingSpeed(new int[]{3,6,7,11}, 8));

        //System.out.println(obj.getNumberOfEncodings("111".toCharArray()));
        //System.out.println(obj.getNumberOfEncodings("2712211".toCharArray()));

        System.out.println(obj.getNumWays(15));

        //System.out.println(obj.longestSubstringLength("abbgbbcba", 2));

        //System.out.println(obj.getPi());

        //System.out.println(obj.pickRandom());

        /*for (int i =0; i < 100; i++) {
            System.out.println("Checking n = " + i + " " + obj.nQueensPuzzle(i));
        }*/

        //flights
        /*List<String[]> flights = new ArrayList<>();
        flights.add(new String[] {"HNL", "AKL"});
        flights.add(new String[] {"YUL", "ORD"});
        flights.add(new String[] {"ORD", "SFO"});
        flights.add(new String[] {"SFO", "HNL"});



        List<String> itinerary = obj.getItinerary(flights, "YUL");
        System.out.println(StringUtils.listStringsToString(itinerary));*/

        /*String pathString = "dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext";
        System.out.println(pathString +"\n longest path is : " + obj.findLongestPath(pathString));

        pathString = "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext";
        System.out.println(pathString +"\n longest path is : " + obj.findLongestPath(pathString));
*/
        //obj.getMaxValues(new int[] {10, 5, 2, 7, 8, 7}, 3);

        //int[][] paitingCost = new int[][] {{6, 5, 2}, { 1, 7, 2}, {20, 30, 40}, {100, 100, 100}};
        //System.out.println("Min cost is : " + obj.getMinimumCost(paitingCost));

        Node zero = new Node(0);
        Node one = new Node(1);
        Node two = new Node(2);
        Node three = new Node(3);
        Node four = new Node(4);
        Node five = new Node(5);
        Node six = new Node(6);
        Node seven = new Node(7);
        Node nine = new Node(9);
        Node ten = new Node(10);

        zero.next = one;
        one.next = two;
        two.next = three;
        three.next = four;
        four.next = five;

        six.next = seven;
        seven.next = nine;
        nine.next = ten;
        ten.next = two;

        //System.out.println(obj.findTreeIntersection(one, six));

        List<int[]> intervals = new ArrayList<>();
        intervals.add(new int[]{ 30, 75 });
        intervals.add(new int[]{  0, 50 });
        intervals.add(new int[]{ 60, 150});
        intervals.add(new int[]{200, 250});
        intervals.add(new int[]{140, 210});
        intervals.add(new int[]{135, 215});
        //System.out.println(obj.getMinRooms(intervals));

        /*String[] words = new String[] { "quick", "brown", "fox", "the"};
        List<String> sentence = obj.getSentenseFromString("thequickbrownfox", words);
        System.out.println(StringUtils.listStringsToString(sentence));

        words = new String[] { "bed", "bath", "bedbath", "and", "beyond"};
        sentence = obj.getSentenseFromString("bedbathandbeyond", words);
        System.out.println(StringUtils.listStringsToString(sentence));

        words = new String[] { "bed", "bath", "bedbath", "and", "beyond"};
        sentence = obj.getSentenseFromString("123dfw45sdf", words);
        System.out.println(StringUtils.listStringsToString(sentence));

        words = new String[] { "the", "theremin"};
        sentence = obj.getSentenseFromString("theremin", words);
        System.out.println(StringUtils.listStringsToString(sentence));*/

        boolean[][] board = new boolean[][] {
                {false, false,  false,  false},
                {true,  true, false,   true},
                {false,  false, false,   false},
                {false,  false, false,   false}
        };

        System.out.println(obj.getMinimumPath(board, new int[]{3, 0}, new int[]{0, 0}));

        ListNode oneLN = new ListNode(1);
        ListNode twoLN = new ListNode(2);
        ListNode threeLN = new ListNode(3);
        ListNode fourLN = new ListNode(4);
        ListNode fiveLN = new ListNode(5);
        ListNode sixLN = new ListNode(6);
        oneLN.next = twoLN;
        twoLN.next = threeLN;
        threeLN.next = fourLN;
        fourLN.next = fiveLN;
        fiveLN.next = sixLN;

        //System.out.println(StringUtils.singlyListNodeToString(oneLN));
        //System.out.println(StringUtils.singlyListNodeToString(obj.removeElementFromEnd(oneLN, 4)));

        /*
        System.out.println(obj.isMatch("ray", "ra."));
        System.out.println(obj.isMatch("raymond", "ra."));
        System.out.println(obj.isMatch("chat", ".*at"));
        System.out.println(obj.isMatch("chats", ".*at"));
        System.out.println(obj.isMatch("this is very expensive algorithm to check matches in a strange useless string", ".*this is very expensive."));
        */

        /*System.out.println(obj.isBalanced("([])[]({})"));
        System.out.println(obj.isBalanced("([)]"));
        System.out.println(obj.isBalanced("([{[]}])[]({{}})"));
        System.out.println(obj.isBalanced("((()"));*/
        //System.out.println(StringUtils.singlyListNodeToString(oneLN));
        //System.out.println(StringUtils.singlyListNodeToString(obj.removeElementFromEnd(oneLN, 4)));

        List<String> lines = obj.justify(new String[] {"the", "quick", "brown", "fox", "jumps", "over", "the", "lazy", "dog"}, 10);
        //System.out.print(StringUtils.listStringsToString(lines));

        /*String s = "AAABBBDDDFFFGGGSSSSAAAAAAAAAAAAAAAAAABD";
        String encoded = obj.encode(s);
        System.out.println(s);
        System.out.println("encoded: " + encoded);
        String decoded = obj.decode(encoded);
        System.out.println("decoded: " + decoded);
        System.out.print("result : " + s.equals(decoded));*/

        /*System.out.println(obj.getWaterTrapped(new int[]{2, 1, 2}));
        System.out.println(obj.getWaterTrapped(new int[]{1, 2, 4, 2}));
        System.out.println(obj.getWaterTrapped(new int[]{3, 0, 1, 3, 0, 5}));*/

        /*System.out.println(obj.editDistance("kitten", "sitting")); //3
        System.out.println(obj.editDistance("table", "tablet")); //1
        System.out.println(obj.editDistance("ruler", "person")); //6
        System.out.println(obj.editDistance("edit distance", "we define")); //6
        System.out.println(obj.editDistance("edit distance haha", "we define huhu"));
        System.out.println(obj.editDistance("The edit distance between two strings refers to the minimum number of character insertions, deletions, and substitutions ",
                "First, notice that we can probably define this problem recursively. How can we notice this? If we look at the example (kitten ")); //101, first solution goes to infinity loop
                */

        /*System.out.println(obj.isArbitragePossible(new float[][] {
                        {1.0f, 1.1086f, 1.2826f, 142.5609f},
                        {0.902f, 1.0f, 1.1569f, 128.5942f},
                        {0.7797f, 0.8644f, 1.0f, 111.1525f},
                        {0.007f, 0.0078f, 0.009f, 1.0f}
                }));

        System.out.println(obj.isArbitragePossible(new float[][] {
                {1.0f, 0.5f},
                {1.5f, 1.0f}
        }));*/

        BST bst = new BST();
        Random rand = new Random();
        for (int i = 0; i < 20; i++) {
            bst.add(rand.nextInt(100));
        }
        System.out.println(bst.toString());

        System.out.println(obj.getSecondMax(bst.getRoot()));
        System.out.println("recursive : " + obj.getSecondMaxRecursive(bst.getRoot()));

        /*System.out.println(obj.isProfit(new float[][] {
                {1.0f, 6.8890f, 7.7666f},
                {0.1452f, 1.0f, 1.2740f},
                {0.1288f, 0.8870f, 1.0f}
        }));

        System.out.println(obj.isProfit(new float[][] {
                {1.0f, 1.2f, 1.0f},
                {.9f, 1.0f, 1.0f},
                {1.0f, 1.0f, 1.0f}
        }));

        System.out.println(obj.isProfit(new float[][] {
                {1.0f, 2.0f},
                {.45f, 1.0f}
        }));*/
        /*
        List<String> runningMeds = obj.runningMedian(new int[]{2, 1, 5, 7, 2, 0, 5});
        System.out.println(StringUtils.listStringsToString(runningMeds));

        runningMeds = obj.runningMedian(new int[]{5, 1, 2, 7, 2, 10, 5});
        System.out.println(StringUtils.listStringsToString(runningMeds));
        */

        /*System.out.println(obj.makePalindrome("abcba"));
        System.out.println(obj.makePalindrome("abc"));
        System.out.println(obj.makePalindrome("google"));
        System.out.println(obj.makePalindrome("elboogle"));
        System.out.println(obj.makePalindrome("race"));*/

        /*char[] arr = new char[] {'G', 'B', 'R', 'R', 'G', 'G', 'G', 'B', 'R', 'R', 'R', 'G', 'B', 'G', 'B', 'R', 'R', 'G',
                'G', 'G', 'B', 'R', 'R', 'R', 'G', 'B', 'G', 'B', 'R', 'R', 'G', 'G', 'G', 'B', 'R', 'R', 'R', 'G', 'B'};
        obj.sortArray(arr);
        System.out.println(new String(arr));
        */

        List<List<Integer>> powerSet = obj.getPowerSet(new int[]{1, 2, 3});
        for (List<Integer> l1: powerSet ) {
            System.out.print("{ ");
            for (Integer i: l1) {
                System.out.print(i + ",");
            }
            System.out.println(" }");
        }
    }
}
