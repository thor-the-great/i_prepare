package random_problems;

import linked_list.ListNode;
import linked_list.StringUtils;

import java.util.*;

public class Driver {

    /**
     * 704. Binary Search
     *
     * Given a sorted (in ascending order) integer array nums of n elements and a target value, write a function to
     * search target in nums. If target exists, then return its index, otherwise return -1.
     *
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        while(l <= r) {
            int m = l + (r - l)/2;
            if (target == nums[m])
                return m;
            if (nums[m] > target)
                r = m - 1;
            else
                l = m + 1;
        }
        return -1;
    }

    public void deleteNode(ListNode node) {
        ListNode prev = null;
        while (node.next != null) {
            ListNode nextNode = node.next;
            node.val = node.next.val;
            prev = node;
            node = node.next;
        }
        prev.next = null;
    }

    public int triangleNumber(int[] nums) {
        Arrays.sort(nums);
        int c = 0;
        int n = nums.length;
        for (int i =0; i < n - 2; i++) {
            /*for (int j = i + 1; j < n - 1; j++) {
                int s = nums[i] + nums[j];
                int k = j + 1;
                while (k < n && s > nums[k]) {
                    k++;
                    c++;
                }
            }*/
            for (int j = i + 2; j < n; j++) {
                for (int k = j - 1; k >= i + 1; k--) {
                    int dif = nums[j] - nums[k];
                    if (dif < nums[i]) c++;
                    else break;
                }
            }
        }
        return c;
    }

    /**
     * return 3-rd unique max number from array, in case there are < 3 numbers return max one
     *
     * @param nums
     * @return
     */
    public int thirdMax(int[] nums) {
        long max1 = Long.MIN_VALUE, max2 = Long.MIN_VALUE, max3 = Long.MIN_VALUE;
        int count = 0;
        for (int num : nums) {
            if (num == max1 || num == max2 || num == max3)
                continue;
            count++;
            if (num > max1) {
                max3 = max2;
                max2 = max1;
                max1 = num;
            } else if (num > max2) {
                max3 = max2;
                max2 = num;
            } else if (num > max3) {
                max3 = num;
            }
        }

        if (count >= 3)
            return (int) max3;
        else
            return (int) max1;
    }

    public String[] findRestaurant(String[] list1, String[] list2) {
        Map<String, Integer> m = new HashMap();
        List<String> res = new LinkedList();
        for (int i = 0; i < list1.length; i++) {
            m.put(list1[i], i);
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < list2.length; i++) {
            String s = list2[i];
            int ind = m.getOrDefault(s, -1);
            if (ind != -1) {
                int sum = i + ind;
                if (sum < min) {
                    min = sum;
                    res.clear();
                    res.add(s);
                } else if (sum == min) {
                    res.add(s);
                }
            }
        }
        return res.toArray(new String[res.size()]);
    }

    /**
     * You are standing at position 0 on an infinite number line. There is a goal at position target.
     *
     * On each move, you can either go left or right. During the n-th move (starting from 1), you take n steps.
     *
     * Return the minimum number of steps required to reach the destination.
     *
     * Example 1:
     * Input: target = 3
     * Output: 2
     * Explanation:
     * On the first move we step from 0 to 1.
     * On the second step we step from 1 to 3.
     * Example 2:
     * Input: target = 2
     * Output: 3
     * Explanation:
     * On the first move we step from 0 to 1.
     * On the second move we step  from 1 to -1.
     * On the third move we step from -1 to 2.
     * Note:
     * target will be a non-zero integer in the range [-10^9, 10^9].
     *
     * @param target
     * @return
     */
    public int reachNumber(int target) {
        target = Math.abs(target);
        int i = 0;
        while (target > 0) {
            target = target - (++i);
        }
        if (target % 2 == 0)
            return i;
        else
            return i + 1 + i%2;
    }

    public double largestSumOfAverages(int[] A, int K) {
        int N = A.length;
        double[] P = new double[N + 1];
        for (int i = 0; i < N; i++) {
            P[i + 1] = P[i] + A[i];
        }

        double[] dp = new double[N];
        for (int i = 0; i < N; i++) {
            dp[i] = (P[N] - P[i]) / (N - i);
        }

        for (int k = 0; k < K - 1; k++)
            for (int i = 0; i < N; i++)
                for (int j = i + 1; j < N; j++)
                    dp[i] = Math.max(dp[i], (P[j] - P[i])/(j - i) + dp[j]);
        return dp[0];
    }

    public int arrayNesting(int[] nums) {
        int l = 1;
        int c = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != -1) {
                if (nums[i] == i) {
                    nums[i] = -1;
                    continue;
                }
                c = 0;
                int j = i;
                while (nums[j] != -1) {
                    c++;
                    int tmp = nums[j];
                    nums[j] = -1;
                    j = tmp;
                }
                if (l < c) l = c;
            }
        }
        return l;
    }

    public static void main(String[] args) {
        Driver obj = new Driver();
        System.out.println("---- find numbers in sorted array -----");
        System.out.println(obj.search(new int[] {-1,0,3,5,9,12}, 9));//4
        System.out.println(obj.search(new int[] {-1,0,3,5,9,12}, 2));//-1
        System.out.println(obj.search(new int[] {5}, 5));//0

        System.out.println("---- delete node from the list -----");
        ListNode one = new ListNode(1);
        ListNode two = new ListNode(2);
        ListNode tree = new ListNode(3);
        ListNode four= new ListNode(4);
        one.next = two;
        two.next = tree;
        tree.next = four;
        System.out.println("List before deletion : " + StringUtils.singlyListNodeToString(one));
        obj.deleteNode(two);
        System.out.println("List after deletion : " + StringUtils.singlyListNodeToString(one));

        System.out.println(obj.triangleNumber(new int[] {1,2,3,4,5,6}));

        System.out.println(obj.largestSumOfAverages(new int[] {9,1,2,3,9}, 3));
    }
}
