/**
 * Copyright (c) 2019 Ariba, Inc. All rights reserved. Patents pending.
 */
package segment_tree.array_tree;

import java.util.Arrays;

/**
 * 307. Range Sum Query - Mutable
 * Medium
 *
 * Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j),
 * inclusive.
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
public class NumArray {
    int[] tree;
    int N;

    /**
     * This one is a real segment tree built like a binary tree. Use array to keep the tree,
     * start from summing every two nodes to the next level node
     * @param nums
     */
    public NumArray(int[] nums) {
        N = nums.length;
        tree = new int[ 2*N ];
        buildTree(nums);
    }

    public void update(int pos, int val) {
        pos += N;
        tree[pos] = val;
        while (pos > 0) {
            int left = pos, right = pos;
            if (pos % 2 == 0)
                right = pos + 1;
            else
                left = pos - 1;

            tree[pos / 2] = tree[left] + tree[right];
            pos /= 2;
        }
    }

    private void buildTree(int[] nums) {
        for (int i = N, j = 0; i < 2*N; i++, j++) {
            tree[i] = nums[j];
        }
        for (int i = N - 1; i > 0; --i) {
            tree[i] = tree[2 * i] + tree[2 * i + 1];
        }
    }

    public int sumRange(int l, int r) {
        l += N; r += N;
        int sum = 0;

        while (l <= r) {
            if ((l % 2) == 1) {
                sum += tree[l];
                ++l;
            }
            if ((r % 2) == 0) {
                sum += tree[r];
                --r;
            }
            l/=2;
            r/=2;
        }
        return sum;
    }

    public static void main(String[] args) {
        int[] array = new int[] {
            2, 4, 5, 7, 8, 9
        };
        NumArray obj = new NumArray(array);
        System.out.println(Arrays.toString(obj.tree));
        System.out.println(obj.sumRange(2, 4));

        obj.update(1, 14);
        System.out.println(Arrays.toString(obj.tree));

        obj.update(5, 7);
        System.out.println(Arrays.toString(obj.tree));
    }
}
