package random_problems;

public class LargestSumOfAverages {

    public double largestSumOfAverages(int[] A, int K) {
        //return largestSumOfAveragesDP(A, K);
        return largestSumOfAveragesRecursive(A, K);
    }

    public double largestSumOfAveragesDP(int[] A, int K) {
        int N = A.length;
        double[] P = new double[N + 1];
        for (int i = 0; i < N; i ++) {
            P[i + 1] = P[i] + A[i];
        }

        double[] dp = new double[N];
        for (int i = 0; i < N; i++) {
            dp[i] = (P[N] - P[i]) / (N - i);
        }

        for (int k = 0; k < K - 1; k++)
            for (int i = 0; i < N; i++)
                for (int j = i + 1; j < N; j++)
                    dp[i] = Math.max(dp[i], (P[j] - P[i])/(j - i) + dp[j]);
        return dp[0];
    }

    public double largestSumOfAveragesRecursive(int[] A, int K) {
        int[] sum = new int[A.length];
        for (int i = 0;i < A.length; i++) sum[i] = A[i] + (i > 0 ? sum[i-1] : 0);
        return helper2(A, K, sum, 0);
    }

    public double helper2(int[] A, int k, int[] sum, int s) {
        int len = A.length;
        if (k == 1)
            return ((double)(sum[len-1] - sum[s] + A[s]) / (len-s));
        double num = 0;
        for (int i = s; i + k <= len ; i++) {
            double avg = ((double) (sum[i] - sum[s] + A[s]) / (i - s + 1));
            num = Math.max(num,
                     avg + helper2(A, k-1, sum, i+1));
        }
        return num;
    }

    public static void main(String[] args) {
        LargestSumOfAverages obj = new LargestSumOfAverages();
        System.out.println(obj.largestSumOfAverages(new int[]{9,1,2,3,9}, 3));
    }
}
