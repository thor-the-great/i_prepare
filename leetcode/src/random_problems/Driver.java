package random_problems;

import linked_list.ListNode;
import linked_list.StringUtils;

import java.util.Arrays;

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
    }
}
