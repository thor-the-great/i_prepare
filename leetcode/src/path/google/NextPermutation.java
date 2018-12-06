package path.google;

import java.util.Arrays;

public class NextPermutation {
    /**
     * Idea is following - we can build next biggest permutation as follows:
     * - find first element where a[i] < a[i + 1], save the i
     * - start from the end (N - 1) go forward till 0 and find first element that is lower than a[i]. Save it as j
     * - swap i and j elements
     * - reverse part of the array from i + 1 to the end (N - 1)
     * @param nums
     */
    public void nextPermutation(int[] nums) {
        int N = nums.length;
        if (N <= 1)
            return;
        int r = N - 2;
        while (r >= 0 && nums[r] >= nums[r + 1]) {
            r--;
        }
        //this is the case when array is orders desc, so we just need to return it in asc -> reverse all array
        if (r < 0) {
            reverse(nums, 0, N - 1);
            return;
        }
        int j = N - 1;
        while (j >= r && nums[j] <= nums[r]) {
            j--;
        }
        swap(r, j, nums);
        reverse(nums, r + 1, N - 1);
    }

    void swap(int i, int j, int[] arr) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    void reverse(int[] arr, int l, int r) {
        while (l < r) {
            swap(l, r, arr);
            l++;
            r--;
        }
    }

    public static void main(String[] args) {
        NextPermutation obj = new NextPermutation();
        int[] arr;
        arr = new int[] {1, 2, 3};
        obj.nextPermutation(arr);
        Arrays.stream(arr).forEach(i->System.out.print(i + " ")); // 1, 3, 2
    }
}
