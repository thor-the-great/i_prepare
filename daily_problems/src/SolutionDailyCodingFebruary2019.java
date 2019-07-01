import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import graphs.DiGraph;
import trees.TreeNode;
import graphs.GraphUtils;

public class SolutionDailyCodingFebruary2019 {

    /**
     * This problem was asked by Google.
     *
     * You are given an array of arrays of integers, where each array corresponds to a row in a triangle of numbers.
     * For example, [[1], [2, 3], [1, 5, 1]] represents the triangle:
     *
     *   1
     *  2 3
     * 1 5 1
     *
     * We define a path in the triangle to start at the top and go down one row at a time to an adjacent value,
     * eventually ending with an entry on the bottom row. For example, 1 -> 3 -> 5. The weight of the path is the
     * sum of the entries.
     *
     * Write a program that returns the weight of the maximum weight path.
     *
     * @param triangle
     * @return
     */
    public int maxPathInTriangle(List<List<Integer>> triangle) {
        int N = triangle.size();
        int[] dp = new int[N];

        List<Integer> bottom = triangle.get(N - 1);
        for (int i =0; i < N; i++) {
            dp[i] = bottom.get(i);
        }

        for (int l = N - 2; l >= 0; l--) {
            List<Integer> layer = triangle.get(l);
            for (int i =0; i < layer.size(); i++) {
                dp[i] = Math.max(dp[i], dp[i+ 1]) + layer.get(i);
            }
        }
        return dp[0];
    }

    /**
     * This problem was asked by Palantir.
     *
     * Write a program that checks whether an integer is a palindrome. For example, 121 is a palindrome, as well as 888.
     * 678 is not a palindrome. Do not convert the integer into a string.
     */
    boolean isPalindrome(int num) {
        if (num < 0) return false;
        int reverted = 0;
        int t = num;
        while (t > 0) {
            reverted = reverted * 10 + t % 10;
            t /= 10;
        }
        return num == reverted;
    }
    
    /**
     * This problem was asked by YouTube.
     *
     * Write a program that computes the length of the longest common subsequence of
     * three given strings. For example, given "epidemiologist", "refrigeration", and
     * "supercalifragilisticexpialodocious", it should return 5, since the longest
     * common subsequence is "eieio".
     *
     */
    public int longestCommonSubsequenceOfTree(String[] strings) {
        int N0 = strings[0].length();
        int N1 = strings[1].length();
        int N2 = strings[2].length();

        int[][][] dp = new int[N0 + 1][N1 + 1][N2 + 1];
        for (int i0 = 1; i0 <= N0; i0++) {
            for (int i1 = 1; i1 <= N1; i1++) {
                for (int i2 = 1; i2 <= N2; i2++) {
                    if (strings[0].charAt(i0 - 1) == strings[1].charAt(i1 - 1)
                            && strings[1].charAt(i1 - 1) == strings[2].charAt(i2 - 1)) {
                        dp[i0][i1][i2] = dp[i0 - 1][i1 - 1][i2 - 1] + 1;
                    } else {
                        dp[i0][i1][i2] = Math.max(dp[i0 - 1][i1][i2],
                                Math.max(dp[i0][i1 - 1][i2], dp[i0][i1][i2 - 1]));
                    }
                }
            }
        }
        return dp[N0][N1][N2];
    }

    private int lcs(String s1, String s2) {
        int N = s1.length();
        int M = s2.length();
        int[][] dp = new int[N + 1][M + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[N][M]; 
    }
    
    /**
     * This problem was asked by Dropbox.

    Spreadsheets often use this alphabetical encoding for its columns: 
    "A", "B", "C", ..., "AA", "AB", ..., "ZZ", "AAA", "AAB", ....

    Given a column number, return its alphabetical column id. For example, 
    given 1, return "A". Given 27, return "AA".
     */
    public String getAlphaNumberById(int num) {
        char[] chars = new char[26];
        for (int i = 0; i < 26; i++) {
            chars[i] = (char) ('A' + i);
        }

        StringBuilder sb = new StringBuilder();
        while (num > 0) {
            int idx = num % 26;
            sb.append(chars[idx - 1]);
            num /= 26;
        } 
        return sb.toString();
    }

    /**
     * This problem was asked by Apple.
     *
     * A Collatz sequence in mathematics can be defined as follows. Starting with any positive integer:
     *
     * if n is even, the next number in the sequence is n / 2
     * if n is odd, the next number in the sequence is 3n + 1
     * It is conjectured that every such sequence eventually reaches the number 1. Test this conjecture.
     *
     * Bonus: What input n <= 1000000 gives the longest sequence?
     * @param n
     * @return
     */
    public int checkCollatz(long n) {
        Set<Long> s = new HashSet<>();
        int c = 0;
        while (!s.contains(n) && n != 1) {
            if (n % 2 == 0)
                n /=2;
            else
                n = 3*n + 1;
            c++;
        }

        return n == 1 ? c : -1;
    }

    public long[] longestCollatz(long n) {
        Map<Long, Long> m = new HashMap<>();
        long maxNum = -1;
        long maxSeq = -1;
        for (long i = 1; i <= n; i++) {
            long num = i;
            Set<Long> s = new HashSet<>();
            long c = 0;
            while (!s.contains(n) && num != 1) {
                if (m.containsKey(num)) {
                    c += m.get(num);
                    num = 1;
                    break;
                }
                if (num % 2 == 0)
                    num /=2;
                else
                    num = 3*num + 1;
                c++;
            }
            if (num == 1) {
                m.put(i, c);
                if (c > maxSeq) {
                    maxSeq = c;
                    maxNum = i;
                }
            }
        }
        return new long[] {maxNum, maxSeq};
    }

    /**
     * This problem was asked by Microsoft.
     *
     * Given a string and a pattern, find the starting indices of all occurrences of the pattern in the string.
     * For example, given the string "abracadabra" and the pattern "abr", you should return [0, 7].
     *
     * @param s
     * @param p
     * @return
     */
    int[] findPatternIndexes(String s, String p) {
        List<Integer> res = new ArrayList();

        int[] kmpTable = generateKMPTable(p);

        int idx = 0;
        boolean match;
        while (idx <= s.length() - p.length()) {
            match = true;
            for (int j = 0; j < kmpTable.length; j++) {
                if (p.charAt(j) != s.charAt(j + idx)) {
                    match = false;
                    idx += (j == 0) ? 1 : (j - kmpTable[j - 1]);
                    break;
                }
            }
            if (match) {
                res.add(idx);
                idx += p.length();
            }
        }

        int[] resArray = new int[res.size()];
        int i = 0;
        for(int n : res) {
            resArray[i++] = n;
        }
        return resArray;
    }

    private int[] generateKMPTable(String p) {
        int[] kmpTable = new int[p.length()];
        int prev;
        for (int i = 1; i < p.length(); i++) {
            prev = kmpTable[i - 1];
            while(prev > 0 && p.charAt(prev) != p.charAt(i)) {
                prev = kmpTable[prev - 1];
            }

            if (p.charAt(prev) == p.charAt(i)) {
                kmpTable[i] = prev + 1;
            }
        }
        return kmpTable;
    }

    /**
     * This problem was asked by Stripe.
     *
     * Given an integer n, return the length of the longest consecutive run of 1s in its binary representation.
     *
     * For example, given 156, you should return 3.
     *
     * @return
     */
    public int longestConsequtiveSequenceOfOnesBitwise(int num) {
        int res = 0;

        while (num > 0) {
            num &= (num << 1);
            res++;
        }

        return res;
    }
	
	int dist = Integer.MAX_VALUE;

	public int horizontalDistanceOfBinaryTree(TreeNode root, int v) {
        dist = Integer.MAX_VALUE;
		helper(root, v, 0);
		return dist;
	}

	private void helper(TreeNode n, int v, int d) {
		if (n == null || dist != Integer.MAX_VALUE)
			return;

		if (n.val == v)
			dist = d;

		helper(n.left, v, d - 1);
		helper(n.right, v, d + 1);
	}

    /**
     * This problem was asked by Yelp.
     *
     * The horizontal distance of a binary tree node describes how far left or right the node will be when the tree is
     * printed out.
     *
     * More rigorously, we can define it as follows:
     *
     * The horizontal distance of the root is 0.
     * The horizontal distance of a left child is hd(parent) - 1.
     * The horizontal distance of a right child is hd(parent) + 1.
     * For example, for the following tree, hd(1) = -2, and hd(6) = 0.
     *
     *              5
     *           /     \
     *         3         7
     *       /  \      /   \
     *     1     4    6     9
     *    /                /
     *   0                8
     * The bottom view of a tree, then, consists of the lowest node at each horizontal distance. If there are two
     * nodes at the same depth and horizontal distance, either is acceptable.
     *
     * For this tree, for example, the bottom view could be [0, 1, 3, 6, 8, 9].
     *
     * Given the root to a binary tree, return its bottom view.
     *
     * @param root
     * @return
     */
	public List<Integer> bottomViewOfBinaryTree(TreeNode root) {
        TreeMap<Integer, Integer> m = new TreeMap<>();
        bottomViewHelper(m, root, 0);
        List<Integer> res = new ArrayList<>();
        for (int d : m.keySet()) {
            res.add(m.get(d));
        }
        return res;
    }

    void bottomViewHelper(TreeMap<Integer, Integer> m, TreeNode n, int d) {
	    if (n != null)
	        m.put(d, n.val);
	    else
	        return;

        bottomViewHelper(m, n.left, d - 1);
        bottomViewHelper(m, n.right, d + 1);
    }

    /**
     * This problem was asked by Oracle.
     *
     * We say a number is sparse if there are no adjacent ones in its binary representation. For example, 21 (10101)
     * is sparse, but 22 (10110) is not. For a given input N, find the smallest sparse number greater than or equal
     * to N.
     *
     * Do this in faster than O(N log N) time.
     *
     * @param num
     * @return
     */
    public int nextSmallestSparseNum(int num) {
        /**
         * 1) Find binary of the given number and store it in a
         *    boolean array.
         * 2) Initialize last_finalized bit position as 0.
         * 3) Start traversing the binary from least significant bit.
         *     a) If we get two adjacent 1's such that next (or third)
         *        bit is not 1, then
         *           (i)  Make all bits after this 1 to last finalized
         *                bit (including last finalized) as 0.
         *           (ii) Update last finalized bit as next bit.
         */
        int[] bits = new int[32];
        int sizeOfBin = -1;
        while (num != 0) {
            bits[++sizeOfBin] = (num & 1);
            num>>=1;
        }
        sizeOfBin++;
        int lastFinal = 0;
        for (int i = 1; i < sizeOfBin; i++) {
            if (bits[i] == 1 && bits[i - 1] == 1 && bits[i + 1] != 1) {
                bits[i + 1] = 1;

                for (int j = i; j >= lastFinal; j--)
                    bits[j] = 0;

                lastFinal = i + 1;
            }
        }

        int res = 0;
        for (int i =0; i <= sizeOfBin; i++) {
            res += bits[i] *(1<<i);
        }
        return res;
    }

    /**
     * This problem was asked by Yahoo.
     *
     * Write an algorithm that computes the reversal of a directed graph. For example, if a graph consists of
     * A -> B -> C, it should become A <- B <- C.
     *
     * @param g
     * @return
     */
    public DiGraph reverseDiGraph(DiGraph g) {
        if (g == null)
            return null;

        DiGraph revG = new DiGraph(g.getV());

        for (int v = 0; v < g.getV(); v++) {
            List<DiGraph.Edge> adjEdges = g.adjEdges(v);
            if (adjEdges != null && !adjEdges.isEmpty()) {
                for(DiGraph.Edge edge : adjEdges) {
                    int u = edge.to;
                    revG.addEdge(u, v);
                }
            }
        }

        return revG;
    }

    public int getSevenish(int n) {
        if (n < 1)
            throw new RuntimeException("Invalid argument");

        List<Integer> list = new ArrayList();

        int power = 0;
        while (list.size() < n) {
            power =  power == 0 ? 1 : power*7;
            List<Integer> tmp = new ArrayList();
            tmp.add(power);
            for (int t : list) {
                if (list.size() + tmp.size() == n)
                    return tmp.get(tmp.size() - 1);
                tmp.add(t + power);
            }
            list.addAll(tmp);
        }

        return list.get(list.size() - 1);
    }

    public static void main(String[] args) {
        SolutionDailyCodingFebruary2019 obj = new SolutionDailyCodingFebruary2019();

        System.out.println("---- count smaller numbers of self ----");
        List<List<Integer>> triangle = new ArrayList<>();
        triangle.add(Arrays.asList(new Integer[]{2}));
        triangle.add(Arrays.asList(new Integer[]{3, 4}));
        triangle.add(Arrays.asList(new Integer[]{6, 5, 7}));
        triangle.add(Arrays.asList(new Integer[]{4, 1, 8, 3}));
        System.out.println("Max path in triangle: " + obj.maxPathInTriangle(triangle));

        System.out.println("--- is number a palindrome ---");
        System.out.println(obj.isPalindrome(-5));//false
        System.out.println(obj.isPalindrome(10));//false
        System.out.println(obj.isPalindrome(3443));//true
        System.out.println(obj.isPalindrome(12523));//false
        System.out.println(obj.isPalindrome(12721));//true

        System.out.println("--- longest common subsequence ---");
        System.out.println(obj.lcs("german", "thegerms"));
        System.out.println(obj.lcs("knife", "laptop"));
        System.out.println(obj.lcs("epidemiologist", "refrigeration"));

        System.out.println("--- longest common subsequence of 3 strings ---");
        System.out.println(obj.longestCommonSubsequenceOfTree(
                new String[] {"epidemiologist", "refrigeration", "supercalifragilisticexpialodocious"}));

        System.out.println(obj.longestCommonSubsequenceOfTree(
                new String[] {"laptop", "linux", "display"}));

        System.out.println("--- get char code by column number ---");
        int num;
        num = 1;
        System.out.println(obj.getAlphaNumberById(num));
        num = 27;
        System.out.println(obj.getAlphaNumberById(num));
        num = 1235;
        System.out.println(obj.getAlphaNumberById(num));

        System.out.println("--- check Collatz sequence ---");
        System.out.println(obj.checkCollatz(3));
        System.out.println(obj.checkCollatz(4));
        System.out.println(obj.checkCollatz(9));
        System.out.println(obj.checkCollatz(27));
        //System.out.println(obj.checkCollatz(837799));

        long maxNum = 1000000;
        long start = System.currentTimeMillis();
        long[] maxes = obj.longestCollatz(maxNum);
        long elapsed =  System.currentTimeMillis() - start;
        System.out.println("For " + maxNum + " the number " + maxes[0] + " has max sequence of " + maxes[1]
                + ", elapsed time " + elapsed);

        System.out.println("---  find the starting indices of all occurrences of the pattern in the string ---");
        int[] substringIndexes;
        substringIndexes = obj.findPatternIndexes("abracadabra", "abr");
        Arrays.stream(substringIndexes).forEach(idx->System.out.print(idx +" ")); System.out.print("\n");

        substringIndexes = obj.findPatternIndexes("laptop", "key");
        Arrays.stream(substringIndexes).forEach(idx->System.out.print(idx +" ")); System.out.print("\n");

        substringIndexes = obj.findPatternIndexes("hotdog is not for dog", "dog");
        Arrays.stream(substringIndexes).forEach(idx->System.out.print(idx +" ")); System.out.print("\n");


        System.out.println("--- longest consecutive run of 1s in its binary representation ---");
        System.out.println(obj.longestConsequtiveSequenceOfOnesBitwise(156)); //3
        System.out.println(obj.longestConsequtiveSequenceOfOnesBitwise(222)); //4

		System.out.println("--- horizontal distance for node  ---");
        TreeNode binaryTree1 = new TreeNode(5,
                new TreeNode(3,
                        new TreeNode(1,
                                new TreeNode(0), null),
                        new TreeNode(4)),
                new TreeNode(7,
                        new TreeNode(6),
                        new TreeNode(9,
                                new TreeNode(8), null))
        );
        System.out.println(obj.horizontalDistanceOfBinaryTree(binaryTree1, 7));//1
        System.out.println(obj.horizontalDistanceOfBinaryTree(binaryTree1, 1));//-2
        System.out.println(obj.horizontalDistanceOfBinaryTree(binaryTree1, 6));//0

        System.out.println("--- bottom view of a tree ---");
        System.out.println(obj.bottomViewOfBinaryTree(binaryTree1));

        System.out.println("--- find the smallest sparse number greater than or equal to N ---");
        int sparce;
        num = 6;
        sparce = obj.nextSmallestSparseNum(num);
        System.out.println("for number " + Integer.toBinaryString(num) +" next sparse is " + sparce + " ("
                + Integer.toBinaryString(sparce) +")");

        num = 4;
        sparce = obj.nextSmallestSparseNum(num);
        System.out.println("for number " + Integer.toBinaryString(num) +" next sparse is " + sparce + " ("
                + Integer.toBinaryString(sparce) +")");

        num = 38;
        sparce = obj.nextSmallestSparseNum(num);
        System.out.println("for number " + Integer.toBinaryString(num) +" next sparse is " + sparce + " ("
                + Integer.toBinaryString(sparce) +")");

        num = 44;
        sparce = obj.nextSmallestSparseNum(num);
        System.out.println("for number " + Integer.toBinaryString(num) +" next sparse is " + sparce + " ("
                + Integer.toBinaryString(sparce) +")");

        System.out.println("--- get N-th sevenish number ---");
        System.out.println(obj.getSevenish(1));
        System.out.println(obj.getSevenish(2));
        System.out.println(obj.getSevenish(3));
        System.out.println(obj.getSevenish(4));
        System.out.println(obj.getSevenish(5));
        System.out.println(obj.getSevenish(10));

        System.out.println("--- reverse directed graph ---");
        DiGraph g1 = GraphUtils.getDiGraphWeighted1();
        System.out.println(g1);
        DiGraph g1Rev = obj.reverseDiGraph(g1);
        System.out.println("Reversed graph ");
        System.out.println(g1Rev);
    }
}
