package cracking.recursion_dp;

import java.util.Arrays;

public class TripleStairStep {

    int getSteps(int stairs) {
        //return getStepsDP(stairs);
        int[] dp = new int[stairs + 1];
        Arrays.fill(dp, -1);
        return getStepsRecusrsive(stairs, dp);
    }

    int getStepsRecusrsive(int stairs, int[] dp) {
        if (stairs < 0)
            return 0;
        else if (stairs == 0)
            return 1;
        else if (dp[stairs] > -1)
            return dp[stairs];
        else {
            dp[stairs] = getStepsRecusrsive(stairs - 1, dp) + getStepsRecusrsive(stairs - 2, dp) + getStepsRecusrsive(stairs - 3, dp);
            return dp[stairs];
        }
    }

    private int getStepsDP(int stairs) {
        int[] dp = new int[stairs + 1];
        dp[0] = 1;
        if (stairs >= 1)
            dp[1] = 1;
        if (stairs >= 2)
            dp[2] = 2;

        for (int i = 3; i <= stairs; i++) {
            dp[i] = dp[i -1] + dp[i - 2] + dp[i -3];
        }
        return dp[stairs];
    }

    public static void main(String[] args) {
        TripleStairStep obj = new TripleStairStep();
        System.out.println(obj.getSteps(3));
        System.out.println(obj.getSteps(4));
        System.out.println(obj.getSteps(5));
        System.out.println(obj.getSteps(10));
    }
}
