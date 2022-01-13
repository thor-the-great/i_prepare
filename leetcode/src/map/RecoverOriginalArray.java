package map;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 2122. Recover the Original Array
Hard

Alice had a 0-indexed array arr consisting of n positive integers. She chose an arbitrary positive integer k and created two new 0-indexed integer arrays lower and higher in the following manner:

lower[i] = arr[i] - k, for every index i where 0 <= i < n
higher[i] = arr[i] + k, for every index i where 0 <= i < n
Unfortunately, Alice lost all three arrays. However, she remembers the integers that were present in the arrays lower and higher, but not the array each integer belonged to. Help Alice and recover the original array.

Given an array nums consisting of 2n integers, where exactly n of the integers were present in lower and the remaining in higher, return the original array arr. In case the answer is not unique, return any valid array.

Note: The test cases are generated such that there exists at least one valid array arr.

 

Example 1:

Input: nums = [2,10,6,4,8,12]
Output: [3,7,11]
Explanation:
If arr = [3,7,11] and k = 1, we get lower = [2,6,10] and higher = [4,8,12].
Combining lower and higher gives us [2,6,10,4,8,12], which is a permutation of nums.
Another valid possibility is that arr = [5,7,9] and k = 3. In that case, lower = [2,4,6] and higher = [8,10,12]. 
Example 2:

Input: nums = [1,1,3,3]
Output: [2,2]
Explanation:
If arr = [2,2] and k = 1, we get lower = [1,1] and higher = [3,3].
Combining lower and higher gives us [1,1,3,3], which is equal to nums.
Note that arr cannot be [1,3] because in that case, the only possible way to obtain [1,1,3,3] is with k = 0.
This is invalid since k must be positive.
Example 3:

Input: nums = [5,435]
Output: [220]
Explanation:
The only possible combination is arr = [220] and k = 215. Using them, we get lower = [5] and higher = [435].
 

Constraints:

2 * n == nums.length
1 <= n <= 1000
1 <= nums[i] <= 109
The test cases are generated such that there exists at least one valid array arr.

https://leetcode.com/problems/recover-the-original-array/
 */
public class RecoverOriginalArray {
    
    /**
     * Idea:
     * - count all elements in original array
     * - sort original array. Starting from the min any possible k will be one from the list (arr[1] - arr[0]/2, ... arr[n] - arr[0]/2)
     * - start to check from smallest in array in greedy manner, if both from pair arr[i] arr[i] + 2*k are in array. for that utilize map, remove
     * the elements once the check is done
     * - do above for each potential k, return first that passes for all elements from array
     */
    public int[] recoverArray(int[] nums) {
        Arrays.sort(nums);
        int N = nums.length;

        Map<Integer, Integer> mapCount = new HashMap();
        for (int n : nums) {
            if (mapCount.containsKey(n)) {
                mapCount.put(n, mapCount.get(n) + 1);
            } else {
                mapCount.put(n, 1);
            }
        }
        
        //checking each k for possible match
        for (int i = 1; i < N; i++) {
            int dif = nums[i] - nums[0];
            if ( dif == 0 || dif % 2 == 1) {
                continue;
            }
            int k = dif / 2;
            Map<Integer, Integer> map = new HashMap(mapCount);
            //iterating over array
            int[] res = new int[N/2];
            int idx = 0;
            for (int p = 0; p < N; p++) {
                if (map.containsKey(nums[p])) {
                    int higherNum = nums[p] + (2*k);
                    if (map.containsKey(higherNum)) {
                        if (map.get(nums[p]) > 1) {
                            map.put(nums[p], map.get(nums[p]) - 1);
                        } else {
                            map.remove(nums[p]);
                        }
                        if (map.get(higherNum) > 1) {
                            map.put(higherNum, map.get(higherNum) - 1);
                        } else {
                            map.remove(higherNum);
                        }
                        
                        res[idx++] = nums[p] + k;
                    } else {
                        break;
                    }
                }
            }
            if (idx == N/2) {
                return res;
            }
        }
        
        return new int[N/2];
    }
}
