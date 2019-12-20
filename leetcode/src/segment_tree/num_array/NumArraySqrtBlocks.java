package segment_tree.num_array;

/**
 * 307. Range Sum Query - Mutable
 * Medium
 *
 * Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.
 *
 * The update(i, val) function modifies nums by updating the element at index i to val.
 *
 * Example:
 *
 * Given nums = [1, 3, 5]
 *
 * sumRange(0, 2) -> 9
 * update(1, 2)
 * sumRange(0, 2) -> 8
 * Note:
 *
 * The array is only modifiable by the update function.
 * You may assume the number of calls to update and sumRange function is distributed evenly.
 */
public class NumArraySqrtBlocks {
    int[] arr;
    int[] aux;
    int len;

    /**
     * Create blocks of SQRT length, on updates update block sum, on range sum query get sum of first and last blocks
     * partially and then all blocks in between as blocks
     * @param nums
     */
    public NumArraySqrtBlocks(int[] nums) {
        arr = nums;
        len = (int) Math.ceil(nums.length / (Math.sqrt(nums.length)));
        aux = new int[len];
        for (int i = 0; i < nums.length; i++) {
            aux[i/len] += nums[i];
        }
    }

    public void update(int i, int val) {
        aux[i/len] -= arr[i];
        aux[i/len] += val;
        arr[i] = val;
    }

    public int sumRange(int i, int j) {
        int res = 0;
        int l = i / len, r = j / len;

        if (l == r) {
            for (int x = i; x <= j; x++)
                res+=arr[x];
            return res;
        }

        for (int x = i; x <= (l+1)*len - 1; x++)
            res += arr[x];

        for (int x = l + 1; x <= r - 1; x++)
            res += aux[x];

        for (int x = r*len; x <= j; x++)
            res += arr[x];

        return res;
    }
}

