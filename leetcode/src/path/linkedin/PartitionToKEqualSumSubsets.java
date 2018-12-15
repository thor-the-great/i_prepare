package path.linkedin;

import java.util.Arrays;

public class PartitionToKEqualSumSubsets {

    int s;
    int[] nums;
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int N = nums.length;
        int sum = 0;
        for (int num : nums)
            sum += num;
        if (sum % k != 0)
            return false;
        int s = sum / k;

        Arrays.sort(nums);
        if (nums[N - 1] > s) return false;
        int lastIdx = N - 1;
        while (lastIdx >= 0 && nums[lastIdx] == s) {
            lastIdx--;
            k--;
        }
        this.s = s;
        this.nums = nums;
        return helper(new int[k], lastIdx);
    }

    boolean helper(int[] sums, int lastIdx) {
        if (lastIdx < 0)
            return true;
        int val = nums[lastIdx];
        lastIdx--;
        int numOfSums = sums.length;
        for (int i = 0; i < numOfSums; i++) {
            if (sums[i] <= s - val) {
                sums[i] += val;
                if (helper(sums, lastIdx))
                    return true;
                sums[i] -= val;
            }
            if (sums[i] == 0) break;
        }
        return false;
    }

    public static void main(String[] args) {
        PartitionToKEqualSumSubsets obj = new PartitionToKEqualSumSubsets();
        int[] arr;
        int k;
        arr = new int[]{4, 3, 2, 3, 5, 2, 1};
        k = 4;
        System.out.println(obj.canPartitionKSubsets(arr, k));
    }
}
