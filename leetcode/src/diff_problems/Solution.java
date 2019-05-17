package diff_problems;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
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
     * https://leetcode.com/contest/weekly-contest-105/problems/reverse-only-letters/
     *
     * Given a string S, return the "reversed" string where all characters that are not a letter stay in the same place,
     * and all letters reverse their positions.
     *
     *
     *
     * Example 1:
     *
     * Input: "ab-cd"
     * Output: "dc-ba"
     * Example 2:
     *
     * Input: "a-bC-dEf-ghIj"
     * Output: "j-Ih-gfE-dCba"
     * Example 3:
     *
     * Input: "Test1ng-Leet=code-Q!"
     * Output: "Qedo1ct-eeLg=ntse-T!"
     *
     *
     * Note:
     *
     * S.length <= 100
     * 33 <= S[i].ASCIIcode <= 122
     * S doesn't contain \ or "
     *
     * @param S
     * @return
     */
    public String reverseOnlyLetters(String S) {
        if (S.length() < 2)
            return S;
        int n = S.length();
        int l = 0, r = n - 1;
        char[] newString = S.toCharArray();
        while (l <= r) {
            while (l < n && !Character.isLetter(S.charAt(l))) l++;
            while (r >= 0 && !Character.isLetter(S.charAt(r))) r--;
            if (r < 0 || l >= n) break;
            newString[l] = S.charAt(r);
            newString[r] = S.charAt(l);
            r--;
            l++;
        }
        return new String(newString);
    }

    /**
     * 918. Maximum Sum Circular Subarray
     *
     * Given a circular array C of integers represented by A, find the maximum possible sum of a non-empty subarray of C.
     *
     * Here, a circular array means the end of the array connects to the beginning of the array.  (Formally, C[i] = A[i]
     * when 0 <= i < A.length, and C[i+A.length] = C[i] when i >= 0.)
     *
     * Also, a subarray may only include each element of the fixed buffer A at most once.  (Formally, for a subarray
     * C[i], C[i+1], ..., C[j], there does not exist i <= k1, k2 <= j with k1 % A.length = k2 % A.length.)
     *
     *
     *
     * Example 1:
     *
     * Input: [1,-2,3,-2]
     * Output: 3
     * Explanation: Subarray [3] has maximum sum 3
     * Example 2:
     *
     * Input: [5,-3,5]
     * Output: 10
     * Explanation: Subarray [5,5] has maximum sum 5 + 5 = 10
     * Example 3:
     *
     * Input: [3,-1,2,-1]
     * Output: 4
     * Explanation: Subarray [2,-1,3] has maximum sum 2 + (-1) + 3 = 4
     * Example 4:
     *
     * Input: [3,-2,2,-3]
     * Output: 3
     * Explanation: Subarray [3] and [3,-2,2] both have maximum sum 3
     * Example 5:
     *
     * Input: [-2,-3,-1]
     * Output: -1
     * Explanation: Subarray [-1] has maximum sum -1
     *
     *
     * Note:
     *
     * -30000 <= A[i] <= 30000
     * 1 <= A.length <= 30000
     *
     * @param A
     * @return
     */
    public int maxSumCircularSubarray(int[] A) {
        int maxDirectSum = maxKadane(A);
        int maxWrapSum = 0;
        int[] negated = new int[A.length];
        for (int i = 0; i < A.length; i++) {
            maxWrapSum += A[i];
            negated[i] = -A[i];
        }
        maxWrapSum = maxWrapSum + maxKadane(negated);
        return maxDirectSum < 0 ? Math.min(maxDirectSum, maxWrapSum) : Math.max(maxDirectSum, maxWrapSum);
    }

    int maxKadane(int[] A){
        int max_to_here = A[0];
        int max = A[0];
        for (int i = 1; i < A.length; i++) {
            max_to_here = Math.max(A[i], max_to_here + A[i]);
            max = Math.max(max, max_to_here);
        }
        return max;
    }

    /**
     * 922. Sort Array By Parity II
     *
     * Given an array A of non-negative integers, half of the integers in A are odd, and half of the integers are even.
     *
     * Sort the array so that whenever A[i] is odd, i is odd; and whenever A[i] is even, i is even.
     *
     * You may return any answer array that satisfies this condition.
     *
     *
     *
     * Example 1:
     *
     * Input: [4,2,5,7]
     * Output: [4,5,2,7]
     * Explanation: [4,7,2,5], [2,5,4,7], [2,7,4,5] would also have been accepted.
     *
     * Note:
     *
     * 2 <= A.length <= 20000
     * A.length % 2 == 0
     * 0 <= A[i] <= 1000
     *
     * @param A
     * @return
     */
    public int[] sortArrayByParityII(int[] A) {
        int[] arr = new int[A.length];
        int odd = 1;
        int even = 0;
        for (int i = 0; i < A.length; i++) {
            int num = A[i];
            if (num % 2 == 0) {
                arr[even] = num;
                even += 2;
            } else {
                arr[odd] = num;
                odd += 2;
            }
        }
        return arr;
    }

    /**
     * 923. 3Sum With Multiplicity
     * Difficulty: Medium
     * Given an integer array A, and an integer target, return the number of tuples i, j, k  such that i < j < k and
     * A[i] + A[j] + A[k] == target.
     *
     * As the answer can be very large, return it modulo 10^9 + 7.
     *
     * Example 1:
     *
     * Input: A = [1,1,2,2,3,3,4,4,5,5], target = 8
     * Output: 20
     * Explanation:
     * Enumerating by the values (A[i], A[j], A[k]):
     * (1, 2, 5) occurs 8 times;
     * (1, 3, 4) occurs 8 times;
     * (2, 2, 4) occurs 2 times;
     * (2, 3, 3) occurs 2 times.
     * Example 2:
     *
     * Input: A = [1,1,2,2,2,2], target = 5
     * Output: 12
     * Explanation:
     * A[i] = 1, A[j] = A[k] = 2 occurs 12 times:
     * We choose one 1 from [1,1] in 2 ways,
     * and two 2s from [2,2,2,2] in 6 ways.
     *
     * Note:
     *
     * 3 <= A.length <= 3000
     * 0 <= A[i] <= 100
     * 0 <= target <= 300
     *
     * @param A
     * @param target
     * @return
     */
    public int threeSumMulti(int[] A, int target) {
        int n = A.length;
        Map<Integer, Integer> m = new HashMap();
        int res = 0;
        int mod = 1000000007;
        for (int i = 0; i < n; i++) {
            res = (res + m.getOrDefault(target - A[i], 0)) % mod;
            for (int j = 0; j < i; j++ ) {
                int tmp = A[i] + A[j];
                m.put(tmp, m.getOrDefault(tmp, 0) + 1 );
            }
        }
        return res;
    }

    /**
     * 924. Minimize Malware Spread
     * Difficulty: Hard
     * In a network of nodes, each node i is directly connected to another node j if and only if graph[i][j] = 1.
     *
     * Some nodes initial are initially infected by malware.  Whenever two nodes are directly connected and at least
     * one of those two nodes is infected by malware, both nodes will be infected by malware.  This spread of malware
     * will continue until no more nodes can be infected in this manner.
     *
     * Suppose M(initial) is the final number of nodes infected with malware in the entire network, after the spread
     * of malware stops.
     *
     * We will remove one node from the initial list.  Return the node that if removed, would minimize M(initial).
     * If multiple nodes could be removed to minimize M(initial), return such a node with the smallest index.
     *
     * Note that if a node was removed from the initial list of infected nodes, it may still be infected later as a
     * result of the malware spread.
     *
     *
     *
     * Example 1:
     *
     * Input: graph = [[1,1,0],[1,1,0],[0,0,1]], initial = [0,1]
     * Output: 0
     * Example 2:
     *
     * Input: graph = [[1,0,0],[0,1,0],[0,0,1]], initial = [0,2]
     * Output: 0
     * Example 3:
     *
     * Input: graph = [[1,1,1],[1,1,1],[1,1,1]], initial = [1,2]
     * Output: 1
     *
     *
     * Note:
     *
     * 1 < graph.length = graph[0].length <= 300
     * 0 <= graph[i][j] == graph[j][i] <= 1
     * graph[i][i] = 1
     * 1 <= initial.length < graph.length
     * 0 <= initial[i] < graph.length
     *
     * @param graph
     * @param initial
     * @return
     */
    public int minMalwareSpread(int[][] graph, int[] initial) {
        //idea is following - divide graph on sub-graphs (by doing DFS like traversal) and do following on every sub-graph:
        //- calculate num of nodes infected. If it's > 1 just keep old min node to del
        //- if infected nodes in graph == 1 - by illuminating it we can potentially rescue the whole graph
        //  so we calc number of nodes in graph, and compare it, those with max wins
        //
        //global set of visited nodes
        Set<Integer> visited = new HashSet();
        //find minimum dle node so far
        int delNode = Arrays.stream(initial).min().getAsInt();
        //min subgraph length
        int subgraphLen = 0;
        //iterate over each node, check if it not visited
        for (int i = 0; i < graph.length; i++) {
            if (!visited.contains(i)) {
                //visit every node in sub-graph via DFS
                Set<Integer> vis = new HashSet();
                vis.add(i);
                dfs(vis, graph, i);
                //count infectedCount and minNode in the intersection of vis (visited in this sub-graph) and initial
                int infectedCount = 0;
                int minInfectedNode = Integer.MAX_VALUE;
                for (int initialEl : initial) {
                    if (vis.contains(initialEl)) {
                        infectedCount++;
                        if (minInfectedNode > initialEl) minInfectedNode = initialEl;
                    }
                }
                //if infectedCount > 1 - keep previous results, otherwise - check number of nodes in rescued sub-graph
                if (infectedCount == 1) {
                    if (vis.size() > subgraphLen || (vis.size() == subgraphLen && minInfectedNode < delNode)) {
                        delNode = minInfectedNode;
                        subgraphLen = vis.size();
                    }
                }
                //add all visited from this sub-graph to the global set
                visited.addAll(vis);
            }
        }
        return delNode;
    }

    /**
     * Standard DFS impl
     */
    void dfs(Set<Integer> visited, int[][] graph, int node) {
        int[] adj = graph[node];
        for (int i = 0; i < adj.length; i++) {
            if (i != node && adj[i] == 1 && !visited.contains(i)) {
                visited.add(i);
                dfs(visited, graph, i);
            }
        }
    }

    /**
     * Your friend is typing his name into a keyboard.  Sometimes, when typing a character c, the key might get long
     * pressed, and the character will be typed 1 or more times.
     *
     * You examine the typed characters of the keyboard.  Return True if it is possible that it was your friends name,
     * with some characters (possibly none) being long pressed.
     *
     *
     *
     * Example 1:
     *
     * Input: name = "alex", typed = "aaleex"
     * Output: true
     * Explanation: 'a' and 'e' in 'alex' were long pressed.
     * Example 2:
     *
     * Input: name = "saeed", typed = "ssaaedd"
     * Output: false
     * Explanation: 'e' must have been pressed twice, but it wasn't in the typed output.
     * Example 3:
     *
     * Input: name = "leelee", typed = "lleeelee"
     * Output: true
     * Example 4:
     *
     * Input: name = "laiden", typed = "laiden"
     * Output: true
     * Explanation: It's not necessary to long press any character.
     *
     *
     * Note:
     *
     * name.length <= 1000
     * typed.length <= 1000
     * The characters of name and typed are lowercase letters.
     *
     * @param name
     * @param typed
     * @return
     */
    public boolean isLongPressedName(String name, String typed) {
        int nameL = name.length();
        int typedL = typed.length();
        int typedP = 0;
        for (int p = 0; p < nameL; p++) {
            if (typedP >= typedL)
                return false;
            char nCh = name.charAt(p);
            char tCh = typed.charAt(typedP);
            if (nCh != tCh) {
                if (typedP == 0 || typed.charAt(typedP - 1) != tCh) return false;
                while (tCh != nCh && typedP + 1 < typedL) {
                    typedP++;
                    tCh = typed.charAt(typedP);
                }
                if (tCh != nCh) return false;
            }
            typedP++;
        }
        return true;
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
        String res = obj.reverseOnlyLetters("Test1ng-Leet=code-Q!");
        String golden = "Qedo1ct-eeLg=ntse-T!";
        System.out.println(res + "?" + golden +" " + res.equals(golden));

        res = obj.reverseOnlyLetters("7_28]");
        golden = "7_28]";
        System.out.println(res + " ? " + golden +" " + res.equals(golden));

        res = obj.reverseOnlyLetters(";1yDV");
        golden = ";1VDy";
        System.out.println(res + " ? " + golden +" " + res.equals(golden));

        res = obj.reverseOnlyLetters("ab-cd");
        golden = "dc-ba";
        System.out.println(res + " ? " + golden +" " + res.equals(golden));

        res = obj.reverseOnlyLetters("a-bC-dEf-ghIj");
        golden = "j-Ih-gfE-dCba";
        System.out.println(res + " ? " + golden +" " + res.equals(golden));

        System.out.println("---- max sum of circular subarray ------");
        System.out.println(obj.maxSumCircularSubarray(new int[]{ 3, -3, 1}));//4
        System.out.println(obj.maxSumCircularSubarray(new int[]{-2,-3,-1}));//-1
        System.out.println(obj.maxSumCircularSubarray(new int[] {1,-2,3,-2})); //3
        System.out.println(obj.maxSumCircularSubarray(new int[] {5,-3,5})); //10
        System.out.println(obj.maxSumCircularSubarray(new int[] {3,-1,2,-1})); //4
        System.out.println(obj.maxSumCircularSubarray(new int[] {3,-2,2,-3})); //3

        System.out.println("---- 3Sum With Multiplicity -------");
        System.out.println(obj.threeSumMulti(new int[]{1,1,2,2,2,2}, 5));
        System.out.println(obj.threeSumMulti(new int[]{1,1,2,2,3,3,4,4,5,5}, 8));

        System.out.println("---- Minimize Malware Spread ------");
        System.out.println(obj.minMalwareSpread( new int[][] {
                {1,1,1},
                {1,1,1},
                {1,1,1}
            }, new int[] {1,2}

        ));

        System.out.println("--- is long pressed in name -----");
        System.out.println(obj.isLongPressedName("saeed","ssaaedd"));
        System.out.println(obj.isLongPressedName("kcutalraxzsc","kcutallrraxxzs"));
    }
}
