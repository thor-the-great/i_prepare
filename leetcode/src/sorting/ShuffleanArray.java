package sorting;

import java.util.Arrays;
import java.util.Random;

/**
 * 
 * 384. Shuffle an Array
Medium

Given an integer array nums, design an algorithm to randomly shuffle the array. All permutations of the array should be equally 
likely as a result of the shuffling.

Implement the Solution class:

Solution(int[] nums) Initializes the object with the integer array nums.
int[] reset() Resets the array to its original configuration and returns it.
int[] shuffle() Returns a random shuffling of the array.
 

Example 1:

Input
["Solution", "shuffle", "reset", "shuffle"]
[[[1, 2, 3]], [], [], []]
Output
[null, [3, 1, 2], [1, 2, 3], [1, 3, 2]]

Explanation
Solution solution = new Solution([1, 2, 3]);
solution.shuffle();    // Shuffle the array [1,2,3] and return its result.
                       // Any permutation of [1,2,3] must be equally likely to be returned.
                       // Example: return [3, 1, 2]
solution.reset();      // Resets the array back to its original configuration [1,2,3]. Return [1, 2, 3]
solution.shuffle();    // Returns the random shuffling of array [1,2,3]. Example: return [1, 3, 2]

 

Constraints:

1 <= nums.length <= 200
-106 <= nums[i] <= 106
All the elements of nums are unique.
At most 5 * 104 calls in total will be made to reset and shuffle.

 * https://leetcode.com/problems/shuffle-an-array/
 */
public class ShuffleanArray {
    int[] arr;
    Random rand = new Random();

    /**
     * Idea - use the random sorting, Knuth randomization shuffling:
     * 
     * - iterate over array with i
     * - generate randome from 0 to i + 1
     * - exchange i with rand elements
     * 
     * O(n) time
     * 
     * Another option is to assgin random real numbers to indexes and sort based on those indexes
     * @param nums
     */
    public ShuffleanArray(int[] nums) {
        arr = nums;
    }
    
    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        return arr;
    }
    
    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
        int[] res = Arrays.copyOf(arr, arr.length);
        for (int i = 0; i < res.length; i++) {
            int randIdx = rand.nextInt(i + 1);
            exch(res, i, randIdx);
        }
        return res;
    }
    
    void exch(int[] array, int i, int j) {
        int t = array[i];
        array[i] = array[j];
        array[j] = t;
    }
}
