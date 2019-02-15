import cracking.trees_graphs.DiGraph;
import diff_problems.TreeNode;
import trees.TreeUtils;
import utils.StringUtils;

import java.util.*;
import java.util.stream.IntStream;

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
        System.out.println("For " + maxNum + " the number " + maxes[0] + " has max sequence of " + maxes[1] + ", elapsed time " + elapsed);
    }
}
