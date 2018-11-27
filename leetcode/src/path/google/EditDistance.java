package path.google;

public class EditDistance {

    public int minDistance(String word1, String word2) {
        int N1 = word1.length();
        int N2 = word2.length();

        int[][] dp = new int[N1 + 1][N2 + 1];
        for (int i = 0; i < N1 + 1; i++) dp[i][0] = i;
        for (int i = 0; i < N2 + 1; i++) dp[0][i] = i;

        for (int i = 1; i <= N1; i++) {
            for (int j = 1; j <= N2; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 1 + Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1]));
                }
            }
        }
        return dp[N1][N2];
    }

    public static void main(String[] args) {
        EditDistance obj = new EditDistance();
        System.out.println(obj.minDistance("b", ""));
    }
}
