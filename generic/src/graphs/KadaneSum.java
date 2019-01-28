package graphs;

public class KadaneSum {

    int getMaxSum(int[] arr) {
        /*int[] dp = new int[arr.length + 1];
        for (int i = 0; i < arr.length; i++) {
            dp[i + 1] = arr[i] + Math.max(0, dp[i]);
        }
        return dp[arr.length];*/
        int curr = Integer.MIN_VALUE, max = Integer.MIN_VALUE;
        for (int num : arr) {
            curr = num + Math.max(0, curr);
            max = Math.max(curr, max);
        }
        return max;
    }

    public static void main(String[] args) {
        KadaneSum obj = new KadaneSum();
        System.out.println(obj.getMaxSum(new int[] {5, -3, 5}));

        System.out.println(obj.getMaxSum(new int[] {5, -3, 2, -1, 5}));

        System.out.println(obj.getMaxSum(new int[] {5, -3, 5, -6, 6}));

        System.out.println(obj.getMaxSum(new int[] {-6}));
    }
}
