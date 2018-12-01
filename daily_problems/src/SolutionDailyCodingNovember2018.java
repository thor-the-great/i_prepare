import diff_problems.TreeNode;
import linked_list.ListNode;
import linked_list.ListUtils;
import sun.reflect.generics.tree.Tree;
import trees.BSTNode;
import utils.StringUtils;

import javax.transaction.TransactionRequiredException;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class SolutionDailyCodingNovember2018 {

    public boolean isPalindrome2(ListNode head) {
        if (head == null) return true;

        ListNode slow = head;
        ListNode fast = head;

        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        if (fast == slow && slow.next == null) return true;

        ListNode secondHead = slow.next;
        slow.next = null;

        ListNode p1 = secondHead;
        ListNode p2 = p1.next;

        while (p2 != null) {
            ListNode tmp = p2.next;
            p2.next = p1;
            p1 = p2;
            p2 = tmp;
        }
        secondHead.next = null;
        ListNode right = p2 == null ? p1 : p2;
        ListNode left = head;

        while (right != null) {
            if (left.val != right.val)
                return false;
            right = right.next;
            left = left.next;
        }
        return true;
    }

    public boolean isPalindrome(ListNode head) {
        return isPalindrome2(head);
    }

    /**
     * This problem was asked by Pinterest.
     *
     * Given an integer list where each number represents the number of hops you can make, determine whether you can
     * reach to the last index starting at index 0.
     *
     * For example, [2, 0, 1, 0] returns true while [1, 1, 0, 1] returns false.
     *
     * @param hops
     * @return
     */
    public boolean hopToLast(int[] hops) {
        //idea is simple - there are only two ways of going from the index, so if we reach it for the second
        //time - we only can go the same way, so doesn't make sense to repeat any of visited steps.
        //thus we keep list of visited indexes, and visit only unvisited ones.
        //this leads to a recursive implementation
        //O(n) - every element visited n times
        //O(1) - if we re-using same array to store visited state, if we can't change it - O(n) for extra array
        if (hops == null || hops.length == 0)
            return false;
        this.hops = hops;
        return helper(0);
    }

    int[] hops;

    boolean helper(int index) {
        if (index == hops.length - 1)
            return true;
        if (index < 0 || index >= hops.length || hops[index] == Integer.MIN_VALUE)
            return false;
        int nextIndex = hops[index];
        hops[index] = Integer.MIN_VALUE;
        return helper(index + nextIndex) || helper(index - nextIndex);
    }

    List<Integer> binaryTreeLevelOrder(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            TreeNode n = q.poll();
            res.add(n.val);
            if (n.left != null)
                q.add(n.left);
            if (n.right != null)
                q.add(n.right);
        }
        return res;
    }

    boolean shiftPossible(String A, String B) {
        if (A == null && B == null)
            return true;
        if (A.length() != B.length())
            return false;
        String doubleString = B + B;
        return (doubleString.contains(A));
    }

    /**
     * This problem was asked by Cisco.
     *
     * Given an unsigned 8-bit integer, swap its even and odd bits. The 1st and 2nd bit should be swapped, the
     * 3rd and 4th bit should be swapped, and so on.
     *
     * For example, 10101010 should be 01010101. 11100010 should be 11010001.
     *
     * Bonus: Can you do this in one line?
     * @param num
     * @return
     */
    int swapBits(int num) {
        //idea is - we need to shift right all even bits and shift left all odd bits
        //create mask first for every bit type, then shift separately and combine
        /*int oddBits = num & 85; //85 is 01010101)
        int evenBits = num & 170; //170 is 10101010)
        evenBits >>= 1; //shift even bits to the left
        oddBits <<= 1; //shift odd bits to the right
        return evenBits | oddBits; //combine both shifts*/
        //short version of the same
        return ((num & 0b1010101) << 1) | ((num & 0b10101010) >> 1);
    }

    /**
     * This problem was asked by Facebook.
     *
     * Given a binary tree, return all paths from the root to leaves.
     *
     * For example, given the tree
     *
     *    1
     *   / \
     *  2   3
     *     / \
     *    4   5
     * it should return [[1, 2], [1, 3, 4], [1, 3, 5]].
     *
     * @param root
     * @return
     */
    List<List<Integer>> getBTPaths(TreeNode root) {
        btPaths = new ArrayList();
        if (root != null) {
            List<Integer> path = new ArrayList();
            path.add(root.val);
            helper(root, path);
        }
        return btPaths;
    }

    List<List<Integer>> btPaths = new ArrayList();

    void helper(TreeNode n, List<Integer> path) {
        if (n.left == null && n.right == null) {
            List<Integer> pathToRes = new ArrayList(path);
            btPaths.add(pathToRes);
            return;
        }
        if (n.left != null) {
            path.add(n.left.val);
            helper(n.left, path);
            path.remove(path.size() - 1);
        }
        if (n.right != null) {
            path.add(n.right.val);
            helper(n.right, path);
            path.remove(path.size() - 1);
        }
    }

    /**
     * 438. Find All Anagrams in a String
     *
     * Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.
     *
     * Strings consists of lowercase English letters only and the length of both strings s and p will not be larger than 20,100.
     *
     * The order of output does not matter.
     *
     * Example 1:
     *
     * Input:
     * s: "cbaebabacd" p: "abc"
     *
     * Output:
     * [0, 6]
     *
     * Explanation:
     * The substring with start index = 0 is "cba", which is an anagram of "abc".
     * The substring with start index = 6 is "bac", which is an anagram of "abc".
     * Example 2:
     *
     * Input:
     * s: "abab" p: "ab"
     *
     * Output:
     * [0, 1, 2]
     *
     * Explanation:
     * The substring with start index = 0 is "ab", which is an anagram of "ab".
     * The substring with start index = 1 is "ba", which is an anagram of "ab".
     * The substring with start index = 2 is "ab", which is an anagram of "ab".
     *
     * @param s
     * @param p
     * @return
     */
    public List<Integer> findAnagrams(String s, String p) {
            List<Integer> res = new LinkedList();
            if (s == null || s.isEmpty())
                return res;
            int[] pCount = new int[26];
            int finalCount = 0;
            for (int i =0; i < p.length(); i++) {
                if (pCount[p.charAt(i) - 'a'] == 0)
                    finalCount++;
                pCount[p.charAt(i) - 'a'] += 1;
            }
            int[] sCount = new int[26];
            int c = 0;
            int start = -1;
            for (int i = 0; i < s.length(); i++) {
                char ch = s.charAt(i);
                if (pCount[ch - 'a'] == 0) {
                    Arrays.fill(sCount, 0);
                    c = 0;
                    start = -1;
                    continue;
                }
                if (start == -1)
                    start = i;
                int newCount = sCount[ch - 'a']  + 1;
                sCount[ch - 'a'] = newCount;
                if (newCount == pCount[ch - 'a'])
                    c++;
                else if (sCount[ch - 'a'] > pCount[ch - 'a']) {
                    while(s.charAt(start) != ch) {
                        char startChar = s.charAt(start);
                        if (sCount[startChar - 'a'] == pCount[startChar - 'a']) {
                            c--;
                        }
                        sCount[startChar - 'a'] -= 1;
                        start++;
                    }
                    char startChar = s.charAt(start);
                    if (sCount[startChar - 'a'] == pCount[startChar - 'a']) {
                        c--;
                    }
                    sCount[startChar - 'a'] -= 1;
                    start++;
                }
                if (c == finalCount) {
                    res.add(start);
                    sCount[s.charAt(start) - 'a'] -= 1;
                    c--;
                    start++;
                }
            }
            return res;
        }

    /**
     * This problem was asked by Google.
     *
     * Given a string of words delimited by spaces, reverse the words in string. For example, given "hello world
     * here", return "here world hello"
     *
     * Follow-up: given a mutable string representation, can you perform this operation in-place?
     *
      * @param s
     * @return
     */
    String reverse(String s) {
        //main idea is - reverse each word individually and then reverse the whole string as one word.
        //this way double-reverse will give us same sequence in result string.
        char[] sArray = s.toCharArray();
        int l = 0, r =0;
        while(r < s.length()) {
            if (sArray[r] == ' ') {
                reverse(sArray, l, r - 1);
                l = r + 1;
            } else if (r == s.length() - 1) {
                reverse(sArray, l, r);
            }
            r++;
        }
        reverse(sArray, 0, sArray.length - 1);
        return new String(sArray);
    }

    void reverse(char[] s, int l, int r) {
        while(l < r) {
            char t = s[l];
            s[l] = s[r];
            s[r] = t;
            l++;
            r--;
        }
    }

    /**
     * This problem was asked by Twitter.
     *
     * Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree. Assume that each
     * node in the tree also has a pointer to its parent.
     *
     * According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes v
     * and was the lowest node in T that has both v and w as descendants (where we allow a node to be a descendant
     * of itself).”
     *
     * @param n1
     * @param n2
     * @return
     */
    TreeNode lowestCommonAncestor(TreeNode n1, TreeNode n2) {
        //return lcaExtraSetOfRootPath(n1, n2);
        return  lcaTwoPathTraversal(n1, n2);
    }

    TreeNode lcaTwoPathTraversal(TreeNode n1, TreeNode n2) {
        //idea is same as in problem - find first common node in path for two linked lists - treat path to root as
        //linked list. Compute length of both paths, get longest, traverse first only for the amount of steps = diff
        //in path length. Then start traversing both path simultaneously and compare next nodes. If nodes are from the
        //same tree - it will eventually give the match. If no match - nodes are not from the same tree
        int pathN1Length = pathLength(n1);
        int pathN2Length = pathLength(n2);
        if (pathN2Length > pathN1Length)
            return lcaTwoPathTraversal(n2, n1);
        int diff = pathN1Length - pathN2Length;
        TreeNode n1c = n1;
        while (diff > 0) {
            n1c = n1c.parent;
            diff--;
        }
        TreeNode n2c = n2;
        while (n1c != null) {
            if (n1c == n2c)
                return n1c;
            n1c = n1c.parent;
            n2c = n2c.parent;
        }
        return null;
    }

    int pathLength(TreeNode root) {
        int c = 0;
        TreeNode n = root;
        while(n != null) {
            c++;
            n = n.parent;
        }
        return c;
    }

    TreeNode lcaExtraSetOfRootPath(TreeNode n1, TreeNode n2) {
        //idea is - store path n1 to root (using parent pointers) to the set. Then start traversing from n2 to root and
        //check every next node, if it's in the set - we have found lca.
        if (n1 == n2) return n1;
        Set<TreeNode> pathToRootN1 = new HashSet();
        TreeNode n = n1;
        while(n != null) {
            pathToRootN1.add(n);
            n = n.parent;
        }
        n = n2;
        while (n != null) {
            if (pathToRootN1.contains(n))
                return n;
            else
                n = n.parent;
        }
        return null;
    }

    /**
     * This problem was asked by Facebook.
     *
     * Given a string and a set of delimiters, reverse the words in the string while maintaining the relative order of
     * the delimiters. For example, given "hello/world:here", return "here/world:hello"
     *
     * Follow-up: Does your solution work for the following cases: "hello/world:here/", "hello//world:here"
     *
     * @param s
     * @param delims
     * @return
     */
    String reverse(String s, String delims) {
        String delimString = "[" + delims + "]";
        String words[] = s.split(delimString);
        Set<Character> delimSet = new HashSet<>();
        for(char ch : delims.toCharArray()) delimSet.add(ch);
        boolean isDelim = true;
        int wordReverseIndex = words.length - 1;
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < s.length(); i++) {
            char next = s.charAt(i);
            if (delimSet.contains(next)) {
                isDelim = true;
                sb.append(next);
            } else {
                if (isDelim || (i == s.length() - 1 && wordReverseIndex >= 0)) {
                    sb.append(words[wordReverseIndex]);
                    wordReverseIndex--;
                    isDelim = false;
                }
            }
        }
        return sb.toString();
    }

    /**
     * This problem was asked by Google.
     *
     * Given two non-empty binary trees s and t, check whether tree t has exactly the same structure and node values
     * with a subtree of s. A subtree of s is a tree consists of a node in s and all of this node's descendants.
     * The tree s could also be considered as a subtree of itself.
     *
     * @param n1
     * @param n2
     * @return
     */
    public boolean checkSubTree(TreeNode n1, TreeNode n2) {
        //return checkSubTreeRecursiveCheckChildNodes(n1, n2);
        return checkSubTreeSubstringInPreorder(n1, n2);
    }

    private boolean checkSubTreeRecursiveCheckChildNodes(TreeNode n1, TreeNode n2) {
        //idea is to traverse every node and it's children, checking if they are the same.
        //O is O(N*M) <n of nodes in n1 * n of node in n2> - potentially need to check every combination
        if (n1 == null) return false;
        return helper(n1, n2) || helper(n1.left, n2) || helper(n1.right, n2);
    }

    private boolean helper(TreeNode n1, TreeNode n2) {
        if (n1 == null && n2 == null) return true;
        if (n1 == null || n2 == null) return false;
        return n1.val == n2.val && helper(n1.left, n2.left) && helper(n1.right, n2.right);
    }

    private boolean checkSubTreeSubstringInPreorder(TreeNode n1, TreeNode n2) {
        String preOrder1 = getPreOrderTraversal(n1);
        String preOrder2 = getPreOrderTraversal(n2);
        return  (preOrder1.contains(preOrder2));
    }

    String getPreOrderTraversal(TreeNode root) {
        Stack<TreeNode> s = new Stack<>();
        s.push(root);
        StringBuilder sb = new StringBuilder();
        sb.append(',');
        while(!s.isEmpty()) {
            TreeNode n = s.pop();
            if (n != null) {
                sb.append(n.val);
                s.push(n.right);
                s.push(n.left);
            }
            else
                sb.append("$");
            sb.append(",");
        }
        return sb.toString();
    }

    /**
     * This problem was asked by Facebook.
     *
     * Given a binary tree, return the level of the tree with minimum sum.
     *
     * @param root
     * @return
     */
    int levelMinSum(TreeNode root) {
        //do level order traversal and keep sum of every level. Keep running min of sum
        Queue<TreeNode> nodesQ = new LinkedList();
        Queue<Integer> levelQ = new LinkedList();

        int minLevel = 1;
        int minSum = root.val;

        nodesQ.add(root);
        levelQ.add(1);

        int l = 0;
        int sum = Integer.MAX_VALUE;

        while (!nodesQ.isEmpty()) {
            TreeNode n = nodesQ.poll();
            int level = levelQ.poll();

            if (level != l) {
                if (sum < minSum) {
                    minSum = sum;
                    minLevel = l;
                }
                l = level;
                sum = n.val;
            } else {
                sum += n.val;
            }

            if (n.left != null) {
                nodesQ.add(n.left);
                levelQ.add(l+ 1);
            }
            if (n.right != null) {
                nodesQ.add(n.right);
                levelQ.add(l+ 1);
            }
        }

        if (sum < minSum) {
            minLevel = l;
        }
        return minLevel;
    }

    /**
     * This problem was asked by Google.
     *
     * Given a sorted list of integers, square the elements and give the output in sorted order.
     *
     * For example, given [-9, -2, 0, 2, 3], return [0, 4, 4, 9, 81].
     *
     * @param arr
     * @return
     */
    public int[] sortedArrayOfSquares(int[] arr) {
        //return sortedArrayOfSquaresStack(arr);
        return sortedArrayOfSquares2PointersFromCenter(arr);
    }

    public int[] sortedArrayOfSquares2PointersFromCenter(int[] arr) {
        //idea is to use 2 pointers teq going from center outside. Going from center (gives us sorted (by abs value)
        //order
        if (arr == null || arr.length == 0)
            return arr;
        int N = arr.length;
        int[] res = new int[N];
        int r = N;
        if (arr[N - 1] >= 0) {
            while (r - 1>=0 && arr[r - 1] >= 0) {
                r--;
            }
        }
        int l = r - 1;
        int i = 0;
        while (i < N) {
            int nextRes;
            if (r < N && l >= 0) {
                if (Math.abs(arr[l]) < Math.abs(arr[r])) {
                    nextRes = arr[l];
                    l--;
                } else {
                    nextRes = arr[r];
                    r++;
                }
            } else if (r < N) {
                nextRes = arr[r];
                r++;
            } else {
                nextRes = arr[l];
                l--;
            }
            res[i] = nextRes * nextRes;
            i++;
        }
        return res;
    }

    public int[] sortedArrayOfSquaresStack(int[] arr) {
        //idea is to use 2 pointers teq and add elements to stack as per their abs value;
        //then iterate over stack and put element's squares back to array
        if (arr == null || arr.length == 0)
            return arr;
        int N = arr.length;
        int l = 0, r = N - 1;
        Stack<Integer> s = new Stack<>();
        while(l <= r) {
            if (Math.abs(arr[l]) >= Math.abs(arr[r])) {
                s.push(arr[l]);
                l++;
            } else {
                s.push(arr[r]);
                r--;
            }
        }

        int i = 0;
        while(!s.isEmpty()) {
            int el = s.pop();
            arr[i] = el * el;
            i++;
        }
        return arr;
    }

    /**
     * This problem was asked by Google.
     *
     * Given a set of closed intervals, find the smallest set of numbers that covers all the intervals. If there are
     * multiple smallest sets, return any of them.
     *
     * For example, given the intervals [0, 3], [2, 6], [3, 4], [6, 9], one set of numbers that covers all these
     * intervals is {3, 6}.
     *
     * @param sets
     * @return
     */
    public List<Integer> intersectionSet(List<int[]> sets) {
        //idea is - check set one by one for the intersection. Sort sets first so we have following criteria -
        //if no intersection found then next set will be next intersection part, no backup cases
        List<Integer> res = new ArrayList<>();
        sets.sort(Comparator.comparingInt(x->x[0]));
        int i = 0;
        while (i < sets.size()) {
            //get next interval for comparision. If previous intersection fails - this will be previous set
            int[] interval = sets.get(i);
            while (i < sets.size() && intersects(sets.get(i), interval)) {
                //construct next set to search -
                // 0th point is max of current and previous set starts
                // 1-th point - min of current and previous set ends
                interval = new int[] {
                        Math.max(sets.get(i)[0], interval[0]),
                        Math.min(sets.get(i)[1], interval[1])
                    };
                i++;
            }
            res.add(interval[1]);
        }
        return res;
    }

    boolean intersects(int[] a, int[] b) {
        //strong condition - one end of a must be outside of b
        return (a[0] <= b[1] && b[0] <= a[1]);
    }

    /**
     * This problem was asked by Google.
     *
     * Given a string which we can delete at most k, return whether you can make a palindrome.
     *
     * For example, given 'waterrfetawx' and a k of 2, you could delete f and x to get 'waterretaw'.
     *
     * @param s
     * @param k
     * @return
     */
    public boolean checkKPalindrome(String s, int k) {
        //return checkKPalindromeRecusive(s, k);
        return checkPalindromeDP(s, k);
    }

    boolean checkPalindromeDP(String s, int k) {
        //construct same recursive algorithm but in bottom-up DP style
        char[] origin = s.toCharArray();
        int N = s.length();
        if (k > N) return false;
        //reverse string
        char[] reverse = new char[N];
        for (int i = N - 1; i >=0; i--) {
            reverse[(N - 1) - i] = origin[i];
        }
        //build table of states
        int[][] dp = new int[N + 1][N + 1];
        IntStream.range(0, N).forEach(i->{
            dp[0][i] = i;
            dp[i][0] = i;
        });
        //do bottom-up DP
        for (int i = 1; i <= N; i++) {
            for(int j = 1; j <= N; j++) {
                if (origin[i - 1] == reverse[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 1 + Math.min(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        int doubleEditDist = dp[N][N];
        return (doubleEditDist <= 2*k);
    }

    boolean checkKPalindromeRecusive(String s, int k) {
        char[] origin = s.toCharArray();
        int N = s.length();
        if (k > N) return false;
        char[] reverse = new char[N];
        for (int i = N - 1; i >=0; i--) {
            reverse[(N - 1) - i] = origin[i];
        }
        int doubleEditDist = helper(origin, reverse, N, N);
        return (doubleEditDist <= 2*k);
    }

    int helper(char[] s1, char[] s2, int i, int j) {
        if (i == 0)
            return j;
        if (j == 0)
            return i;

        if (s1[i - 1] == s2[j - 1])
            return helper(s1, s2, i - 1, j - 1);

        return 1 + Math.min(
                helper(s1, s2, i - 1, j),
                helper(s1, s2, i, j - 1)
        );
    }

    /**
     * This question was asked by Zillow.
     *
     * You are given a 2-d matrix where each cell represents number of coins in that cell. Assuming we start at
     * matrix[0][0], and can only move right or down, find the maximum number of coins you can collect by the bottom
     * right corner.
     *
     * For example, in this matrix
     *
     * 0 3 1 1
     * 2 0 0 4
     * 1 5 3 1
     * The most we can collect is 0 + 2 + 1 + 5 + 3 + 1 = 12 coins.
     *
     * @param grid
     * @return
     */
    public int maxCoinsCollect(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        int[][] dp = new int[rows][cols];
        IntStream.range(0, rows).forEach(r->{
            dp[r][0] = grid[r][0] + ((r - 1) >= 0 ? dp[r - 1][0] : 0);
        });
        IntStream.range(0, cols).forEach(c->{
            dp[0][c] = grid[0][c] + ((c - 1) >= 0 ? dp[0][c - 1] : 0);
        });

        for (int r = 1; r < rows; r++) {
            for (int c = 1; c < cols; c++) {
                dp[r][c] = grid[r][c] +
                        Math.max(dp[r - 1][c], dp[r][c - 1]);
            }
        }
        return dp[rows - 1][cols - 1];
    }

    /**
     * This problem was asked by Microsoft.
     *
     * You have 100 fair coins and you flip them all at the same time. Any that come up tails you set aside. The
     * ones that come up heads you flip again. How many rounds do you expect to play before only one coin remains?
     *
     * Write a function that, given n, returns the number of rounds you'd expect to play until one coin remains.
     * @param n
     * @return
     */
    public int flipCoins(int n) {
        int x = (int) Math.ceil(Math.log10(n)/Math.log10(2));
        return x;
    }

    /**
     * This problem was asked by Facebook.
     *
     * Write a function that rotates a list by k elements. For example, [1, 2, 3, 4, 5, 6] rotated by two becomes
     * [3, 4, 5, 6, 1, 2]. Try solving this without creating a copy of the list.
     *
     * @param nums
     * @param k
     */
    public void rotateArray(int[] nums, int k) {
        int N = nums.length;
        if (N == 0 || k == 0)
            return;
        k = k % N;
        rotate(0, N - 1, nums);
        rotate(0, k - 1, nums);
        rotate(k, N- 1, nums);
    }

    void rotate(int l, int r, int[] nums) {
        int tmp;
        while(l < r) {
            tmp = nums[l];
            nums[l] = nums[r];
            nums[r] = tmp;
            l++;
            r--;
        }
    }

    /**
     * This problem was asked by Microsoft.
     *
     * Let's represent an integer in a linked list format by having each node represent a digit in the number.
     * The nodes make up the number in reversed order.
     *
     * For example, the following linked list:
     *
     * 1 -> 2 -> 3 -> 4 -> 5
     * is the number 54321.
     *
     * Given two linked lists in this format, return their sum in the same linked list format.
     *
     * For example, given
     *
     * 9 -> 9
     * 5 -> 2
     * return 124 (99 + 25) as:
     *
     * 4 -> 2 -> 1
     *
     * @return
     */
    public ListNode addNumbers(ListNode l1, ListNode l2) {
        ListNode fakeHead = new ListNode(-1);
        ListNode curr = fakeHead;
        int carry = 0;
        while (l1 != null || l2 != null) {
            int nextVal = carry;
            if (l1 != null) {
                nextVal += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                nextVal += l2.val;
                l2 = l2.next;
            }
            carry = nextVal / 10;
            nextVal %= 10;
            ListNode n = new ListNode(nextVal);
            curr.next = n;
            curr = curr.next;
        }
        if (carry > 0 ) {
            ListNode n = new ListNode(carry);
            curr.next = n;
        }
        return fakeHead.next;
    }

    /**
     * The Tower of Hanoi is a puzzle game with three rods and n disks, each a different size.
     *
     * All the disks start off on the first rod in a stack. They are ordered by size, with the largest disk on the
     * bottom and the smallest one at the top.
     *
     * The goal of this puzzle is to move all the disks from the first rod to the last rod while following these rules:
     *
     * You can only move one disk at a time.
     * A move consists of taking the uppermost disk from one of the stacks and placing it on top of another stack.
     * You cannot place a larger disk on top of a smaller disk.
     * Write a function that prints out all the steps necessary to complete the Tower of Hanoi. You should assume that
     * the rods are numbered, with the first rod being 1, the second (auxiliary) rod being 2, and the last (goal)
     * rod being 3.
     *
     * For example, with n = 3, we can do this in 7 moves:
     *
     * Move 1 to 3
     * Move 1 to 2
     * Move 3 to 2
     * Move 1 to 3
     * Move 2 to 1
     * Move 2 to 3
     * Move 1 to 3
     *
     * @param n
     * @return
     */
    public List<String> hanoiTowers(int n) {
        //return hanoiTowersOOP(n);
        return hanoiTowerRecursive(n);
    }

    /**
     * Super simple idea:
     * thnik of task recursively -
     * - if there are 0 disks - we don't do anything - base case for recursion
     * - if there is 1 disk - move it from source to destination directly
     * - for anything more that 1 - we move all disks except last one (recursively) from source to tmp, then do actual
     * move of biggest disk from source to destination and then move rest of disks (all except of biggest one) from
     * tmp to destination.
     *
     * @param n
     * @return
     */
    private List<String> hanoiTowerRecursive(int n) {
        List<String> steps = new LinkedList<>();
        helper(steps, n, 0, 1, 2);
        return steps;
    }

    private void helper(List<String> steps, int disks, int source, int tmp, int dest) {
        if (disks >= 1) {
            helper(steps, disks - 1, source, dest, tmp);
            String step = "move " + disks + "  " + source +" -> " + dest;
            steps.add(step);
            helper(steps, disks - 1, tmp, source, dest);
        }
    }

    private List<String> hanoiTowersOOP(int n) {
        HanoiTower[] towers = new HanoiTower[3];
        IntStream.range(0, 3).forEach(i->towers[i] = new HanoiTower(i));
        for(int i = n; i >= 1; i--)
            towers[0].add(i);

        List<String> steps = new ArrayList<>();
        towers[0].moveDisks(n, towers[1], towers[2], steps);
        return steps;
    }

    class HanoiTower {
        Stack<Integer> disks;
        int idx;

        HanoiTower(int i) {
            idx = i;
            disks = new Stack<>();
        }

        void add(int disk) {
            if (!disks.isEmpty() && disk > disks.peek()) {
                throw new RuntimeException("Disk must be smaller then the current top");
            }
            disks.push(disk);
        }

        void moveTopToOther(HanoiTower t, List<String> steps) {
            if (!this.disks.isEmpty()) {
                int disk = this.disks.pop();
                t.add(disk);
                String step = "move " + disk + "  " + this.idx +" -> " + t.idx;
                steps.add(step);
            }
        }

        void moveDisks(int disks, HanoiTower buffer, HanoiTower dest, List<String> steps) {
            if (disks > 0) {
                moveDisks(disks - 1, dest, buffer, steps);
                moveTopToOther(dest, steps);
                buffer.moveDisks(disks - 1, this, dest, steps);
            }
        }

    }

    /**
     * Given a real number n, find the square root of n. For example, given n = 9, return 3.
     * @param x
     * @return
     */
    public int mySqrt(int x) {
        long l = 0;
        long r = 1 + (x/2);
        while (l <= r ) {
            long m = l + (r - l) / 2;
            long ans = m * m;
            if (x == ans)
                return (int)m;
            else if (ans > x) {
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return (int)r;
    }

    /**
     * This problem was asked by Amazon.
     *
     * Given a node in a binary tree, return the next bigger element, also known as the inorder successor.
     *
     * For example, the inorder successor of 22 is 30.
     *
     *    10
     *   /  \
     *  5    30
     *      /  \
     *    22    35
     * You can assume each node has a parent pointer.
     * @param node
     * @return
     */
    public TreeNode inorderSuccessorBST(TreeNode node) {
        //idea is to find proper node right away.
        //- if node has right child - take this right and then find leftmost its child
        //- else result is one of node previous nodes. This prev node must be left child of some parent.
        //thus we take node and it's parent and check if the node is left child of that parent
        TreeNode res;
        if (node.right != null) {
            node = node.right;
            while (node.left != null) {
                node = node.left;
            }
            res = node;
        } else {
            TreeNode p = node.parent;
            if (p != null && node != p.left) {
                node = p;
                p = node.parent;
            }
            res = p;
        }
        return res;
    }

    public static void main(String[] args) {
        SolutionDailyCodingNovember2018 obj = new SolutionDailyCodingNovember2018();

        System.out.println("---- check if singly linked list is a palindrome ----");
        ListNode head = ListUtils.getSinglyLinkedList(new int[] {1, 2, 5, 2, 1});
        System.out.println(obj.isPalindrome(head));

        head = ListUtils.getSinglyLinkedList(new int[] {1});
        System.out.println(obj.isPalindrome(head));

        head = ListUtils.getSinglyLinkedList(new int[] {1, 2});
        System.out.println(obj.isPalindrome(head));

        head = ListUtils.getSinglyLinkedList(new int[] {1, 2, 2, 1});
        System.out.println(obj.isPalindrome(head));

        head = ListUtils.getSinglyLinkedList(new int[] {1, 2, 4, 1});
        System.out.println(obj.isPalindrome(head));

        head = ListUtils.getSinglyLinkedList(new int[] {1, 2, 1});
        System.out.println(obj.isPalindrome(head));

        head = ListUtils.getSinglyLinkedList(new int[] {1, 3, 3, 1});
        System.out.println(obj.isPalindrome(head));

        head = ListUtils.getSinglyLinkedList(new int[] {2, 3, 4, 1});
        System.out.println(obj.isPalindrome(head));

        System.out.println("---- check if possible to reach last element from 0 in array of hops -----");

        System.out.println(obj.hopToLast(new int[] {2, 0, 1, 0})); //true
        System.out.println(obj.hopToLast(new int[] {1, 1, 0, 1})); //false
        System.out.println(obj.hopToLast(new int[] {3, 3, 0, 2, 2})); //true
        System.out.println(obj.hopToLast(new int[] {1, 1, 0, 5})); //false

        System.out.println("---- print binary tree in level order ---");
        TreeNode root = new TreeNode(1,
                new TreeNode(2),
                new TreeNode(3,
                        new TreeNode(4), new TreeNode(5)));
        List<Integer> levelOrder = obj.binaryTreeLevelOrder(root);
        levelOrder.forEach(i-> System.out.print(i + " "));
        System.out.println();

        System.out.println("---- check if string a can be a shifted to get string b ----");
        System.out.println(obj.shiftPossible("abcde", "cdeab"));
        System.out.println(obj.shiftPossible("abc", "acb"));

        System.out.println("--- swap odd and even bits of number (8bit int)----");
        System.out.println(Integer.toBinaryString(
                obj.swapBits(Integer.parseInt("10101010", 2))));//01010101
        System.out.println(Integer.toBinaryString(
                obj.swapBits(Integer.parseInt("11100010", 2))));//11010001

        System.out.println("--- return all paths of binary tree ----");
        root = new TreeNode(1,
                new TreeNode(2),
                new TreeNode(3,
                        new TreeNode(4), new TreeNode(5)));
        List<List<Integer>> res = obj.getBTPaths(root);
        for (List<Integer> onePath : res) {
            onePath.forEach(i-> System.out.print(i +" "));
            System.out.print("\n");
        }

        root = new TreeNode(1,
                new TreeNode(2,
                        new TreeNode(4), null),
                new TreeNode(3,
                        new TreeNode(5,
                                new TreeNode(7), new TreeNode(8)),
                        new TreeNode(6)));
        System.out.println("Tree #2");
        res = obj.getBTPaths(root);
        for (List<Integer> onePath : res) {
            onePath.forEach(i-> System.out.print(i +" "));
            System.out.print("\n");
        }

        System.out.println("---- find all anagrams of substring ----");
        List<Integer> anaIndexes = obj.findAnagrams("cbaebabacd", "abc");
        System.out.println(StringUtils.listToString(anaIndexes));
        //anaIndexes = obj.findAnagrams("abacbabc", "abc");
        //System.out.println(StringUtils.listToString(anaIndexes));

        anaIndexes = obj.findAnagrams("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        System.out.println(StringUtils.listToString(anaIndexes));

        System.out.println("--- reverse string of several words ----");
        System.out.println(obj.reverse("hello cruel world!"));
        System.out.println(obj.reverse("oh solle, oh solle mio"));

        System.out.println("--- lowest common ancestor for two binary tree nodes ----");
        /*
         *               1
         *              /  \
         *            2     3
         *           / \   /
         *          4   5  6
         *         /
         *        7
         */
        TreeNode one = new TreeNode(1);
        TreeNode two = new TreeNode(2);
        two.parent = one;
        one.left = two;
        TreeNode three = new TreeNode(3);
        three.parent = one;
        one.right = three;

        TreeNode four = new TreeNode(4);
        two.left = four;
        four.parent = two;
        TreeNode five = new TreeNode(5);
        two.right = five;
        five.parent = two;
        TreeNode six = new TreeNode(6);
        three.left = six;
        six.parent = three;
        TreeNode seven = new TreeNode(7);
        seven.parent = four;
        four.left = seven;
        TreeNode eight = new TreeNode(8);

        TreeNode lca = obj.lowestCommonAncestor(seven, five);
        System.out.println(lca == null ? "no LCA found" : lca.val);//2

        lca = obj.lowestCommonAncestor(seven, six);
        System.out.println(lca == null ? "no LCA found" : lca.val);//1

        lca = obj.lowestCommonAncestor(seven, four);
        System.out.println(lca == null ? "no LCA found" : lca.val);//4

        lca = obj.lowestCommonAncestor(seven, eight);
        System.out.println(lca == null ? "no LCA found" : lca.val);//n/a

        System.out.println("---- reverse string with set of delimiters ------");
        System.out.println(obj.reverse("hello/world:here", "/:"));
        System.out.println(obj.reverse("hello/world:here/", "/:"));
        System.out.println(obj.reverse("hello//world:here", "/:"));
        System.out.println(obj.reverse("my/random:set:of/words+and+delimeters", "/:+"));

        System.out.println("--- check if tree is a subtree of another tree ---");
        System.out.println(obj.checkSubTree(one, two));//true
        System.out.println(obj.checkSubTree(one, eight));//false
        System.out.println(obj.checkSubTree(two, three));//false

        System.out.println("--- level min sum in binary tree ----");
        TreeNode root3 = new TreeNode(40,
                new TreeNode(5,
                        new TreeNode(2,null,null),
                        new TreeNode(1,
                                new TreeNode(10,null,null),
                                new TreeNode(13,null,null))),
                new TreeNode(12,
                        new TreeNode(1,null,null),
                        new TreeNode(4,
                                new TreeNode(32),null)));

        System.out.println(obj.levelMinSum(root3));

        TreeNode root4 = new TreeNode(40,
                new TreeNode(5,
                        new TreeNode(2,null,null),
                        new TreeNode(1,
                                new TreeNode(10,null,null),
                                new TreeNode(13,null,null))),
                new TreeNode(-12,
                        new TreeNode(1,null,null),
                        new TreeNode(4,
                                new TreeNode(32),null)));

        System.out.println(obj.levelMinSum(root4));

        System.out.println("--- return sorted array of squares of elements ---");
        int[] sortedSq;
        sortedSq = obj.sortedArrayOfSquares(new int[] {-9, -2, 0, 2, 3, 5});
        Arrays.stream(sortedSq).forEach(i->System.out.print(i + " "));
        System.out.print("\n");

        sortedSq = obj.sortedArrayOfSquares(new int[] {-9, -5, -2});
        Arrays.stream(sortedSq).forEach(i->System.out.print(i + " "));
        System.out.print("\n");

        sortedSq = obj.sortedArrayOfSquares(new int[] {0, 4, 6, 11});
        Arrays.stream(sortedSq).forEach(i->System.out.print(i + " "));
        System.out.print("\n");

        sortedSq = obj.sortedArrayOfSquares(new int[] {-4, 1, 3, 5, 11});
        Arrays.stream(sortedSq).forEach(i->System.out.print(i + " "));
        System.out.print("\n");

        System.out.println("---- find set that covers all numbers from array of sets ----");
        List<int[]> sets = new ArrayList<>();
        sets.add(new int[] {10, 20});
        sets.add(new int[] {1, 6});
        sets.add(new int[] {3, 8});
        sets.add(new int[] {7, 12});
        List<Integer> intersectionSet = obj.intersectionSet(sets);
        intersectionSet.forEach(i-> System.out.print(i+ " "));
        System.out.print("\n");

        sets = new ArrayList<>();
        sets.add(new int[] {10, 20});
        sets.add(new int[] {1, 6});
        sets.add(new int[] {3, 8});
        sets.add(new int[] {7, 12});
        sets.add(new int[] {15, 18});
        sets.add(new int[] {19, 22});
        intersectionSet = obj.intersectionSet(sets);
        intersectionSet.forEach(i-> System.out.print(i+ " "));
        System.out.print("\n");

        sets = new ArrayList<>();
        sets.add(new int[] {3, 10});
        sets.add(new int[] {4, 8});
        sets.add(new int[] {9, 12});
        intersectionSet = obj.intersectionSet(sets);
        intersectionSet.forEach(i-> System.out.print(i+ " "));

        System.out.println("--- check if string is a K palindrome (be a pali by removing k chars) ---");
        System.out.println(obj.checkKPalindrome("abc", 1));//false
        System.out.println(obj.checkKPalindrome("abc", 2));//true
        System.out.println(obj.checkKPalindrome("abbea", 1));//true
        System.out.println(obj.checkKPalindrome("waterrfetawx", 2));//true
        System.out.println(obj.checkKPalindrome("random string", 3));//false

        System.out.println("--- path with max coins to collect in grid of coins (right/down) ---");

        System.out.println(obj.maxCoinsCollect(new int[][] {
                {1, 3, 5, 0},
                {5, 4, 3, 2},
                {3, 0, 1, 4}
        }));

        System.out.println(obj.maxCoinsCollect(new int[][] {
                {0, 3, 1, 1},
                {2, 0, 0, 4},
                {1, 5, 3, 1}
        }));

        System.out.println("--- number of times to flip n fair coins ---");
        System.out.println(obj.flipCoins(100));//7
        System.out.println(obj.flipCoins(200));//8
        System.out.println(obj.flipCoins(4));//2

        System.out.println("--- rotate arrays of ints on k positions ---");
        int[] rotateArray = new int[]{1, 2, 3, 4, 5, 6, 7};
        obj.rotateArray(rotateArray, 3);
        Arrays.stream(rotateArray).forEach(i->System.out.print(i + " "));
        System.out.println();

        System.out.println("--- add numbers as lists ---");
        ListNode l1 = new ListNode(2, new ListNode(4, new ListNode(8)));
        ListNode l2 = new ListNode(4, new ListNode(7, new ListNode(3, new ListNode(2))));
        ListNode addRes = obj.addNumbers(l1, l2);
        System.out.println("result of addition : " + linked_list.StringUtils.singlyListNodeToString(addRes));

        System.out.println("--- towers of hanoi -----");
        List<String> steps;
        System.out.println("2 disks");
        steps = obj.hanoiTowers(2);
        steps.forEach(System.out::println);

        System.out.println("3 disks");
        steps = obj.hanoiTowers(3);
        steps.forEach(System.out::println);

        System.out.println("4 disks");
        steps = obj.hanoiTowers(4);
        steps.forEach(System.out::println);

        System.out.println("--- inorder successor in BST ----");
        /*
         *               20
         *              /  \
         *           12     30
         *           / \   /
         *          8  16 25
         *         / \
         *        7  10
         */
        one = new TreeNode(20);
        two = new TreeNode(12);
        two.parent = one;
        one.left = two;
        three = new TreeNode(30);
        three.parent = one;
        one.right = three;

        four = new TreeNode(8);
        two.left = four;
        four.parent = two;
        five = new TreeNode(16);
        two.right = five;
        five.parent = two;
        six = new TreeNode(25);
        three.left = six;
        six.parent = three;
        seven = new TreeNode(7);
        seven.parent = four;
        four.left = seven;
        eight = new TreeNode(10);
        four.right = eight;
        eight.parent = four;

        TreeNode inorderNext = null;
        inorderNext = obj.inorderSuccessorBST(two);
        System.out.println(inorderNext);//16

        inorderNext = obj.inorderSuccessorBST(one);
        System.out.println(inorderNext);//25

        inorderNext = obj.inorderSuccessorBST(seven);
        System.out.println(inorderNext);//8

        inorderNext = obj.inorderSuccessorBST(six);
        System.out.println(inorderNext);//30

        inorderNext = obj.inorderSuccessorBST(eight);
        System.out.println(inorderNext);//12
    }

}
