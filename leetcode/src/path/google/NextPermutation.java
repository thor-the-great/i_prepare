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
        if (N < 2)
            return;

        //first dec
        int dec = N - 2;
        while (dec >= 0 && nums[dec] >= nums[dec + 1])
            --dec;

        if (dec < 0) {
            reverse(nums, 0, N - 1);
            return;
        }

        //finding just greater
        int grt = N - 1;
        while(grt >= 0 && nums[grt] <= nums[dec])
            --grt;

        //swap
        int t = nums[grt];
        nums[grt] = nums[dec];
        nums[dec] = t;

        //revert the tail
        reverse(nums, dec + 1, N - 1);
    }

    void reverse(int[] nums, int l, int r) {
        while(l < r) {
            int t = nums[l];
            nums[l] = nums[r];
            nums[r] = t;
            ++l;--r;
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
