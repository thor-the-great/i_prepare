package arrays;

/**
 * 915. Partition Array into Disjoint Intervals
Medium

Given an array nums, partition it into two (contiguous) subarrays left and right so that:

Every element in left is less than or equal to every element in right.
left and right are non-empty.
left has the smallest possible size.
Return the length of left after such a partitioning.  It is guaranteed that such a partitioning exists.

 

Example 1:

Input: nums = [5,0,3,8,6]
Output: 3
Explanation: left = [5,0,3], right = [8,6]
Example 2:

Input: nums = [1,1,1,0,6,12]
Output: 4
Explanation: left = [1,1,1,0], right = [6,12]
 

Note:

2 <= nums.length <= 30000
0 <= nums[i] <= 106
It is guaranteed there is at least one way to partition nums as described.

https://leetcode.com/problems/partition-array-into-disjoint-intervals/
 */
public class PartitionArrayIntoDisjointIntervals {

    /**
     * Idea to split the array we need to make sure that max of left part is less or equal to 
     * min in right part.
     * We can do rolling max as we scan, but to min we have to know one for each position.
     * For that we can use array and scan the array from the end to 0 to find one.
     * Then do left to right scan, at the point where max left is less or eq to min right we stop,
     * as adding this element will break the balance
     * 
     * O(n) time
     * O(n) space
     * 
     * @param nums
     * @return
     */
    public int partitionDisjoint(int[] nums) {
        int N = nums.length;
        int maxLeft = nums[0]; 
        int[] minRight = new int[N];
        minRight[N - 1] = nums[N - 1];
        for (int i = N - 2; i > 0; i--) {
            minRight[i] = Math.min(minRight[i + 1], nums[i]);
        }
        for (int i = 1; i < N - 1; i++) {
            if (maxLeft <= minRight[i])
                return i;
            maxLeft = Math.max(nums[i], maxLeft);
        }
        return N - 1;
    }
}
