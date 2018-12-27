import java.util.Random;

public class FibonacciConcurrent {

    Memoizer memoizer = new Memoizer((int arg, int prev1, int prev2)->{

        String s = "ddfgdfgdfgdfgh";
        int iter = 1_000_000 + new Random().nextInt(300);
        for (int j =0; j < iter; j++) {
            int k = j*j;
            for (int l = 0; l < s.length();l++) {
                String b = s.substring(0, l + 1);
            }
        }
        return prev1 + prev2;
    });

    int getNumberConcurrent(int i) {
        try {
            return memoizer.compute(i);
        } catch (InterruptedException e) {
            return -1;
        }
    }

    int getNumberStraight(int i) {
        int[] dp = new int[i + 1];
        dp[1] = 1;
        dp[2] = 1;
        for (int kk = 3; kk <= i; kk++) {
            String s = "ddfgdfgdfgdfgh";
            int iter = 1_000_000 + new Random().nextInt(300);
            for (int j =0; j < iter; j++) {
                int k = j*j;
                for (int l = 0; l < s.length();l++) {
                    String b = s.substring(0, l + 1);
                }
            }
            dp[kk] = dp[kk - 1] + dp[kk - 2];
        }
        return dp[i];
    }

    public static void main(String[] args) {
        FibonacciConcurrent obj = new FibonacciConcurrent();

        int num = 200;

        long start = System.currentTimeMillis();
        int concNum = obj.getNumberConcurrent(num);
        long end = System.currentTimeMillis() - start;
        System.out.println("Concurrent " + concNum + " time " + end);

        start = System.currentTimeMillis();
        concNum = obj.getNumberStraight(num);
        end = System.currentTimeMillis() - start;
        System.out.println("Normal " + concNum + " time " + end);
    }
}
