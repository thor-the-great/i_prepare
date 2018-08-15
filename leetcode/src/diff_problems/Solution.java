package diff_problems;

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

        //System.out.println(obj.getNumWays(4));

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
        obj.getMaxValues(new int[] {10, 5, 2, 7, 8, 7}, 3);
    }
}
