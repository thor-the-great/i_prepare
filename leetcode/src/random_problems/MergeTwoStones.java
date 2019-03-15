package random_problems;

public class MergeTwoStones {

    public int minCost(int[] stones) {
        int N = stones.length;
        if (N <= 1)
            return -1;

        int[][] dp = new int[N + 1][N + 1];
        int[] pref = new int[N + 1];
        for (int i = 1; i <= N; i++)
            pref[i] = pref[i - 1] + stones[i - 1];

        for (int l = 2; l <= N; l++) {
            for (int i = 1; i <= N - l + 1; i++) {
                int j = i + l - 1;
                dp[i][j] = Integer.MAX_VALUE;
                for (int mid = i; mid < j; mid++) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][mid] + dp[mid + 1][j]);
                }
                dp[i][j] += pref[j] - pref[i - 1];
            }
        }

        return dp[1][N];
    }

    public static void main(String[] args) {
        MergeTwoStones obj = new MergeTwoStones();
        System.out.println(obj.minCost(new int[] {3, 8, 2}));
        System.out.println(obj.minCost(new int[] {3, 5, 2, 7}));
        System.out.println(obj.minCost(new int[] {6, 4, 4, 6}));
    }
}
