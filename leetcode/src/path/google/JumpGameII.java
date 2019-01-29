package path.google;

public class JumpGameII {

    public int jump(int[] nums) {
        int N = nums.length;
        int count = 0;
        int longest = 0, curr = 0;

        for (int i = 0; i < N - 1; i++) {
            longest = Math.max(longest, i + nums[i]);
            if ( i == curr) {
                count++;
                curr = longest;
            }
        }

        return count;
    }
}
