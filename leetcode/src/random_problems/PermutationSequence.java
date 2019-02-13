package random_problems;

import java.util.ArrayList;
import java.util.List;

/**
 * 60. Permutation Sequence
Medium

The set [1,2,3,...,n] contains a total of n! unique permutations.

By listing and labeling all of the permutations in order, we get the following sequence for n = 3:

"123"
"132"
"213"
"231"
"312"
"321"
Given n and k, return the kth permutation sequence.

Note:

Given n will be between 1 and 9 inclusive.
Given k will be between 1 and n! inclusive.
Example 1:

Input: n = 3, k = 3
Output: "213"
Example 2:

Input: n = 4, k = 9
Output: "2314"
 */
public class PermutationSequence {
    
    /**
     *Idea: Calculate exact permutation based on factorial number. For each
      number i-th the index of the proper number from the sequence will be
      idx = k /(i - 1)!, then we add nums[idx] to result and remove it from
      possible numbers, and decrement k = k - idx * (i- 1)!  
     */    
    public String getPermutation(int n, int k) {

        List<Integer> nums = new ArrayList();

        int[] factorials = new int[n + 1];
        factorials[0] = 1;
        for (int i = 1; i < n + 1; i++) {
            factorials[i] = i * factorials[i - 1];
        }

        //store number that we'll use in factorial search
        for(int i = 1; i <= n; i++) {
            nums.add(i);
        }

        k--;

        char[] sol = new char[n];
        int j = 0;
        int fac;
        for (int i = 1; i <= n; i++) {
            fac = factorials[n - i];
            int idx = k / fac;
            sol[j++] = (char)('0' + nums.get(idx));
            nums.remove(idx);
            k -= idx * fac;
        }

        return new String(sol);
    }
}
