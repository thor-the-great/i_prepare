package arrays;

import java.util.ArrayList;
import java.util.List;

/**
 * 969. Pancake Sorting
Medium

Given an array of integers A, We need to sort the array performing a series of pancake flips.

In one pancake flip we do the following steps:

Choose an integer k where 0 <= k < A.length.
Reverse the sub-array A[0...k].
For example, if A = [3,2,1,4] and we performed a pancake flip choosing k = 2, we reverse the sub-array [3,2,1], so A = [1,2,3,4] after the pancake flip at k = 2.

Return an array of the k-values of the pancake flips that should be performed in order to sort A. Any valid answer that sorts the array within 10 * A.length flips will be judged as correct.

 

Example 1:

Input: A = [3,2,4,1]
Output: [4,2,4,3]
Explanation: 
We perform 4 pancake flips, with k values 4, 2, 4, and 3.
Starting state: A = [3, 2, 4, 1]
After 1st flip (k = 4): A = [1, 4, 2, 3]
After 2nd flip (k = 2): A = [4, 1, 2, 3]
After 3rd flip (k = 4): A = [3, 2, 1, 4]
After 4th flip (k = 3): A = [1, 2, 3, 4], which is sorted.
Notice that we return an array of the chosen k values of the pancake flips.
Example 2:

Input: A = [1,2,3]
Output: []
Explanation: The input is already sorted, so there is no need to flip anything.
Note that other answers, such as [3, 3], would also be accepted.
 

Constraints:

1 <= A.length <= 100
1 <= A[i] <= A.length
All integers in A are unique (i.e. A is a permutation of the integers from 1 to A.length).

https://leetcode.com/problems/pancake-sorting/
 */
public class PancakeSorting {

    /**
     * I've found that it's easier to think about this problem from the task of sorting and in desc order. Because all numbers are in sequence we know that the next number is current + 1, so if it's a different number - we have to find the right one -> do the flip.
Because of the requirements we can do only flips for the whole 0..k sub-array, so in order to put the number in it's correct place we need 2 flips - one to move desired element to index 0, and second to put it to the correct final index. These two flips will be for different k.

Example:
[3,2,4,{1}] - going from last index, last element must be 4, but it's not - need to move it there
a) - [3,2,{4},1] - find the position of 4
b) - [4,2,3,1] - flip 1
c) - [1,3,2,4] - flip 2

[1,3,{2},4] - next element is 3, but found 2
a) - [1,{3},2,4] - find the position of 3
b) - [3,1,2,4] - flip 1
c) - [2,1,3,4] - flip 2

[2,{1},3,4] - next element is 2, but found 1
a) - [{2},1,3,4] - find the position of 2
b) - [2,1,3,4] - flip 1 (no changes are done)
c) - [1,2,3,4] - flip 2

We actually do need to simulate flips because on next step we need to know location of elements (

O(n^2) time - for every element we can scan the whole array
O(1) space - no extra space needed except for few state variables
     * @param A
     * @return
     */
    public List<Integer> pancakeSort(int[] A) {
        List<Integer> res = new ArrayList();
        int N = A.length, p = N - 1;
        
        while (p > 0) {
            if (A[p] != p + 1) {    
                //find the element
                int pos = p;
                while (A[pos] != p + 1) {
                    --pos;
                }
                res.add(pos + 1);
                doFlip(A, pos);
                res.add(p + 1);
                doFlip(A, p);
            }
            --p;
        }
        
        return res;
    }
    
    void doFlip(int[] arr, int idx) {
        int l = 0, r = idx;
        while (l < r) {
            int t = arr[l];
            arr[l] = arr[r];
            arr[r] = t;
            ++l; --r;
        }
    }
}