package path.google;

/**
 * 55. Jump Game
 * Medium
 *
 * Given an array of non-negative integers, you are initially positioned at the first index of the array.
 *
 * Each element in the array represents your maximum jump length at that position.
 *
 * Determine if you are able to reach the last index.
 *
 * Example 1:
 *
 * Input: [2,3,1,1,4]
 * Output: true
 * Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
 * Example 2:
 *
 * Input: [3,2,1,0,4]
 * Output: false
 * Explanation: You will always arrive at index 3 no matter what. Its maximum
 *              jump length is 0, which makes it impossible to reach the last index.
 */
public class JumpGame {

    /**
     * Keep pos as maximum position we can reach so far. We don't care about every cell that we can reach, just the
     * fact that we can go up to i means we can do i, i-1, i-2 etc.
     * For every position that we can reach keep the max next position we can reach from there. Doing so means we
     * keep the max position, and increment it for every next index. We do this until we can't reach position from
     * any previous one (fail case) of unless we reach the end of array (true case)
     * @param nums
     * @return
     */
    public boolean canJump_Greedy_Optimal(int[] nums) {
        if (nums.length == 1)
            return true;

        int N = nums.length, longest = 0;
        for (int i = 0; i < N; i++) {
            if (longest < i)
                return false;
            longest = Math.max(longest, i + nums[i]);
        }
        return longest >= N - 1;
    }

    public boolean canJumpGreedyOptimal2(int[] nums) {
        int pos = 0;

        for (int i = 0; i < nums.length && pos < nums.length; i++) {
            if (pos < i)
                return false;
            pos = Math.max(pos, i + nums[i]);
        }

        return true;
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
