package arrays.two_pointers_tech;

import java.util.Arrays;

public class Solution {

    /**
     * https://leetcode.com/explore/learn/card/array-and-string/205/array-two-pointer-technique/1183/
     */
    public String reverseString(String s) {
        char[] strChars = s.toCharArray();
        int l = 0, r = strChars.length - 1;
        while(l < r) {
            char tmp = strChars[l];
            strChars[l] = strChars[r];
            strChars[r] = tmp;
            l++;
            r--;
        }
        String result = new String(strChars);
        return result;
    }

    /**
     * https://leetcode.com/explore/learn/card/array-and-string/205/array-two-pointer-technique/1154/
     * @param nums
     * @return
     */
    public int arrayPairSum(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length/2;
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            int startIndex = (i - 1) * 2;
            int nextMin = Math.min(nums[startIndex], nums[startIndex+1]);
            sum += nextMin;
        }
        return sum;
    }

    /**
     * https://leetcode.com/explore/learn/card/array-and-string/205/array-two-pointer-technique/1153/
     * @param numbers
     * @param target
     * @return
     */
    public int[] twoSum(int[] numbers, int target) {
        int l = 0, r = numbers.length - 1;
        int[] result = new int[] {0, 0};
        while(l < r) {
            int sum = numbers[l] + numbers[r];
            if (sum == target) {
                result = new int[] {l  +1, r + 1};
                break;
            } else if (sum > target) {
                r--;
            } else
                l++;
        }
        return result;
    }

    public static void main(String[] args) {
        Solution obj = new Solution();
        System.out.println(obj.twoSum(new int[] {2,7,11,15}, 9));
    }
}
