package random_problems;

/**
  153. Find Minimum in Rotated Sorted Array
Medium

Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

(i.e.,  [0,1,2,4,5,6,7] might become  [4,5,6,7,0,1,2]).

Find the minimum element.

You may assume no duplicate exists in the array.

Example 1:

Input: [3,4,5,1,2] 
Output: 1
Example 2:

Input: [4,5,6,7,0,1,2]
Output: 0
**/
public class FindMinimumRotatedSortedArray {
    
    /**
    * Idea: to the binary search, the lement that we looking for must be less than 
    * both it's neighbours
    */ 
    public int findMin(int[] nums) {
			int N = nums.length;
        if (N == 1)
            return nums[0];
        if (N == 2)
            return Math.max(nums[0], nums[1]);
        //in this case array is still sorted
        if (nums[0] < nums[N-1])
            return nums[0];

        int l = 0, r = N - 1;
        while (l <= r) {
            int m = l + (r - l)/2;
            if (nums[m] < nums[m -1])
							return nums[m];
            if (nums[m] > nums[m + 1])
							return nums[m + 1];

            if (nums[m] > nums[0])
							l = m + 1;
            else
							r = m - 1;
        }
        return -1;
    }

    public static void main(String[] args) {
    	FindMinimumRotatedSortedArray obj = new FindMinimumRotatedSortedArray();
       	int[] arr;
        arr = new int[] {4, 5, 6, 7, 0, 1, 2};
        System.out.println(obj.findMin(arr));

        arr = new int[] {5, 6, 8, 10, 1, 2, 3, 4};
        System.out.println(obj.findMin(arr));

    }
}
