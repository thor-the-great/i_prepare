import diff_problems.TreeNode;
import linked_list.ListNode;
import util.Point;
import utils.StringUtils;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.IntStream;

public class SolutionDailyCodingJanuary2019 {

    /**
     * This problem was asked by Google.
     *
     * Given an array of integers, return a new array where each element in the new array is the number of smaller
     * elements to the right of that element in the original input array.
     *
     * For example, given the array [3, 4, 9, 6, 1], return [1, 1, 2, 1, 0], since:
     *
     * There is 1 smaller element to the right of 3
     * There is 1 smaller element to the right of 4
     * There are 2 smaller elements to the right of 9
     * There is 1 smaller element to the right of 6
     * There are no smaller elements to the right of 1
     * @param nums
     * @return
     */
    public List<Integer> countSmaller(int[] nums) {
        //idea - we use merge sort algo, those left elements that will have smaller elements from right - +1 for every
        //such element. Rest is optimization. O(nlogn).
        int n = nums.length;
        int[][] arr = new int[n][2];
        int[][] aux = new int[n][2];
        for (int i = 0; i < n; i++) {
            arr[i] = new int[] {nums[i], i};
        }
        int[] count = new int[n];
        sort(arr, count, 0, n - 1, aux);
        List<Integer> list = new ArrayList();
        for (int i = 0; i < n; i++){
            list.add(count[i]);
        }
        return list;
    }

    private void sort(int[][] arr, int[] count, int lo, int hi, int[][] aux) {
        if (lo >= hi) return;
        int mid = lo + (hi - lo) / 2;
        sort(arr, count, lo, mid, aux);
        sort(arr, count, mid + 1, hi, aux);
        for (int i = lo; i <= hi; i++) {
            aux[i] = arr[i];
        }
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i == mid + 1) { arr[k] = aux[j++]; }
            else if (j == hi + 1 || aux[i][0] <= aux[j][0]) {
                count[aux[i][1]] += j - (mid + 1);
                arr[k] = aux[i++];
            } else {
                arr[k] = aux[j++];
            }
        }
    }

    public static void main(String[] args) {
        SolutionDailyCodingJanuary2019 obj = new SolutionDailyCodingJanuary2019();

        System.out.println("---- count smaller numbers of self ----");
        int[] arr;
        List<Integer> smallers;
        arr = new int[] {5,2,6,1};
        smallers = obj.countSmaller(arr);
        System.out.print(StringUtils.listToString(smallers));//[2, 1, 1, 0]
    }
}
