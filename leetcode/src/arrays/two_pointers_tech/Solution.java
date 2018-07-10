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

    /**
     * https://leetcode.com/explore/learn/card/array-and-string/205/array-two-pointer-technique/1151/
     * @param nums
     * @param val
     * @return
     */
    public int removeElement(int[] nums, int val) {
        int cnt = 0;
        int i = 0;
        while (i < nums.length - cnt) {
            if (nums[i] == val) {
                int f = i + 1;
                while ( f < (nums.length - cnt)) {
                    nums[f - 1] = nums[f];
                    f++;
                }
                cnt++;
            } else {
                i++;
            }
        }
        return nums.length - cnt;
    }

    /**
     * https://leetcode.com/explore/learn/card/array-and-string/205/array-two-pointer-technique/1301/
     * @param nums
     * @return
     */
    public int findMaxConsecutiveOnes(int[] nums) {
        int count = 0;
        int f, s = 0;
        while (s < nums.length) {
            if (nums[s] == 1) {
                int c = 1;
                f = s + 1;
                while (f < nums.length) {
                    if (nums[f] == 1) {
                        c++;
                        f++;
                    } else
                        break;
                }
                if (count < c)
                    count = c;
                s = f++;
            }
            else
                s++;
        }
        return count;
    }


    public static void main(String[] args) {
        Solution obj = new Solution();
        //System.out.println(obj.twoSum(new int[] {2,7,11,15}, 9));

        //System.out.println(obj.removeElement(new int[] {3, 2, 2, 3}, 3));

        //System.out.println(obj.removeElement(new int[]{0,1,2,2,3,0,4,2},2));

        System.out.println(obj.findMaxConsecutiveOnes(new int[]{1,0,1,1,0,1}));
    }
}
