/**
 * Copyright (c) 2019 Ariba, Inc. All rights reserved. Patents pending.
 */
package binary_search;

/**
 * 81. Search in Rotated Sorted Array II
 * Medium
 *
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 *
 * (i.e., [0,0,1,2,2,5,6] might become [2,5,6,0,0,1,2]).
 *
 * You are given a target value to search. If found in the array return true, otherwise return
 * false.
 *
 * Example 1:
 *
 * Input: nums = [2,5,6,0,0,1,2], target = 0
 * Output: true
 * Example 2:
 *
 * Input: nums = [2,5,6,0,0,1,2], target = 3
 * Output: false
 * Follow up:
 *
 * This is a follow up problem to Search in Rotated Sorted Array, where nums may contain duplicates.
 * Would this affect the run-time complexity? How and why?
 */
public class SearchInRotatedSortedArray2 {

    public boolean search(int[] nums, int target) {
        int l = 0, r = nums.length - 1;

        while (l <= r) {
            int m = l + (r - l)/2;
            if (nums[m] == target)
                return true;

            //right side
            if (nums[r] > nums[m] || nums[m] < nums[l]) {
                if (target > nums[m] && target <= nums[r])
                    l = m + 1;
                else
                    r = m - 1;
            }
            //left side
            else if (nums[r] < nums[m] || nums[m] > nums[l]) {
                if (target < nums[m] && target >= nums[l])
                    r = m - 1;
                else
                    l = m + 1;
            }
            //both element are equal, just short the search area
            else --r;
        }

        return false;
    }
}
