package path.google;

public class JumpGame {

    public boolean canJump_Greedy_Optimal(int[] nums) {
        int N = nums.length;
        int longest = 0, curr = 0;
        for (int i =0; i < N; i++) {
            longest = Math.max(nums[i] + i, longest);
            if (i == curr) {
                curr = longest;
            }
        }
        return curr >= N -1;
    }

    public boolean canJumpDP_Optimal(int[] nums) {
        int N = nums.length;
        int pos = N - 1;
        for (int idx = N - 1; idx >= 0; idx--) {
            if (idx + nums[idx] >= pos)
                pos = idx;
        }
        return pos == 0;
    }

    public boolean canJumpDP_UD_ONSquare(int[] nums) {
        int N = nums.length;
        boolean[] jumps = new boolean[N];
        jumps[0] = true;

        for (int idx = 0; idx < N; idx++) {
            if (!jumps[idx]) continue;
            int nextJump = nums[idx];
            if (nextJump == 0)
                continue;
            int limit = Math.min(idx + nextJump, N - 1);
            for (int j = idx + 1; j <= limit; j++) {
                jumps[j] = true;
            }
        }
        return jumps[nums.length - 1];
    }

    public boolean canJumpDP_BU_ONSquare(int[] nums) {
        int N = nums.length;
        boolean[] jumps = new boolean[N];
        jumps[N - 1] = true;

        for (int idx = N - 2; idx >= 0; idx--) {
            int limit = Math.min(idx + nums[idx], N - 1);
            for (int j = idx + 1; j <= limit; j++) {
                if (jumps[j]) {
                    jumps[idx] = true;
                    break;
                }
            }
        }
        return jumps[0];
    }
}
