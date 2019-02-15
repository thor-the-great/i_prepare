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
     *This problem was asked by YouTube.

Write a program that computes the length of the longest common subsequence of 
three given strings. For example, given "epidemiologist", "refrigeration", and 
"supercalifragilisticexpialodocious", it should return 5, since the longest
common subsequence is "eieio".
     */
    public int longestCommonSubsequenceOfTree(String[] strings) {
        return 0;
    }

    private int lcs(String s1, String s2) {
        int N = s1.length();
        int M = s2.length();
        int[][] dp = new int[N + 1][M + 1];
        for (int i = 1; i < N; i++) {
            for (int j = 1; j < M; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[N][M]; 
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
        System.out.pritnln(obj.lcs("knife", "laptop"));
    }
}
