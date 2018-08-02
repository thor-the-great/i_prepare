package diff_problems;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

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

        System.out.println(obj.getNumberOfEncodings("111".toCharArray()));
        System.out.println(obj.getNumberOfEncodings("2712211".toCharArray()));
    }
}
