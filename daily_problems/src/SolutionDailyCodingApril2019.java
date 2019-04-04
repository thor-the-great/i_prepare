import diff_problems.TreeNode;
import linked_list.ListNode;
import linked_list.StringUtils;
import path.google.TrappedRainWater;
import sun.reflect.generics.tree.Tree;
import trees.TreeUtils;
import util.NaryTreeNode;
import utils.ArrayUtil;

import java.util.*;

public class SolutionDailyCodingApril2019 {

    /**
     * This problem was asked by Fitbit.
     *
     * Given a linked list, rearrange the node values such that they appear in alternating low -> high -> low ->
     * high ... form. For example, given 1 -> 2 -> 3 -> 4 -> 5, you should return 1 -> 3 -> 2 -> 5 -> 4.
     * @param node
     * @return
     */
    public ListNode alternatingOrder(ListNode node) {
        /**
         * Idea - do the sorting of the list, use merge sort. Then split list in the middle and merge to halves
         * using elements from half in turns
         */
        ListNode sortedList = sort(node);
        ListNode midOfSorted = getMiddle(sortedList);
        ListNode midNextOfSorted = midOfSorted.next;
        midOfSorted.next = null;
        ListNode head = sortedList;
        while (sortedList != null) {
            ListNode n = sortedList.next;
            if (midNextOfSorted != null) {
                sortedList.next = midNextOfSorted;
                midNextOfSorted = midNextOfSorted.next;
                sortedList.next.next = n;
            }
            sortedList = n;
        }

        return head;
    }

    ListNode sort(ListNode node) {
        if (node == null || node.next == null)
            return node;

        ListNode midNode = getMiddle(node);
        ListNode midNextNode = midNode.next;
        midNode.next = null;

        ListNode leftPart = sort(node);
        ListNode rightPart = sort(midNextNode);

        ListNode sortedList = sortedMerge(leftPart, rightPart);

        return sortedList;
    }

    ListNode sortedMerge(ListNode r, ListNode l) {
        if (r == null)
            return l;
        if (l == null)
            return r;

        ListNode result;

        if (l.val < r.val) {
            result = l;
            result.next = sortedMerge(l.next, r);
        } else {
            result = r;
            result.next = sortedMerge(l, r.next);
        }

        return result;
    }

    ListNode getMiddle(ListNode n) {
        if ( n == null)
            return n;

        ListNode slow = n;
        ListNode fast = n.next;

        while (fast != null) {
            fast = fast.next;
            if (fast != null) {
                fast = fast.next;
                slow = slow.next;
            }
        }

        return slow;
    }

    /**
     * This problem was asked by WhatsApp.
     *
     * Given an array of integers out of order, determine the bounds of the smallest window that must be sorted in
     * order for the entire array to be sorted. For example, given [3, 7, 5, 6, 9], you should return (1, 3).
     * @param arr
     * @return
     */
    int[] shortestSubarrayToKeepSorted(int[] arr) {
        int[] res = new int[] {-1, -1};

        int N = arr.length;
        int l = 0;
        //finding first non-sorted element
        while (l < N - 1) {
            if (arr[l] > arr[l + 1])
                break;
            l++;
        }

        //it can be sorted already
        if (l == N - 1)
            return res;

        int r = N - 1;
        //searching for right one - first unsorted starting from the end
        while (r > 0) {
            if (arr[r] < arr[r - 1])
                break;
            r--;
        }
        //finding min and max in the left-right fragment
        int max = arr[l], min = arr[l];
        for (int i = l + 1; i <=r; i++ ) {
            if (arr[i] > max)
                max = arr[i];

            if (arr[i] < min)
                min = arr[i];
        }
        //checking if there are elements to the left of the left
        for (int i = 0; i < l; i++ ) {
            if (arr[i] > min) {
                l = i;
                break;
            }
        }
        //checking if there are elements to the right of the right
        for (int i = N - 1; i >= r; i--) {
            if (arr[i] < max) {
                r = i;
                break;
            }
        }
        res[0] = l;
        res[1] = r;

        return res;
    }

    /**
     * This problem was asked by Morgan Stanley.
     *
     * In Ancient Greece, it was common to write text with the first line going left to right, the second line going
     * right to left, and continuing to go back and forth. This style was called "boustrophedon".
     *
     * Given a binary tree, write an algorithm to print the nodes in boustrophedon order.
     *
     * For example, given the following tree:
     *
     *        1
     *     /     \
     *   2         3
     *  / \       / \
     * 4   5     6   7
     * You should return [1, 3, 2, 4, 5, 6, 7].
     *
     * @param root
     * @return
     */
    List<Integer> boustrophedonTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();

        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        boolean direct = true;

        LinkedList<Integer> level = new LinkedList<>();
        while (!q.isEmpty()) {
            int levelNodeSize = q.size();
            for (int i = 0; i < levelNodeSize; i++)
            {
                TreeNode n = q.poll();
                if (direct)
                    level.addLast(n.val);
                else
                    level.addFirst(n.val);

                if (n.left != null) q.add(n.left);
                if (n.right != null) q.add(n.right);
            }
            res.addAll(level);
            level.clear();
            direct = !direct;
        }

        return res;
    }


    public static void main(String[] args) {
        SolutionDailyCodingApril2019 obj = new SolutionDailyCodingApril2019();

        System.out.println("---- smallest int that is not a sum of subset, array is sorted ----");
        ListNode node = new ListNode(4, new ListNode(7, new ListNode(2, new ListNode(6, new ListNode(9)))));
        System.out.println(StringUtils.singlyListNodeToString(node));

        System.out.println(StringUtils.singlyListNodeToString(obj.alternatingOrder(node)));

        node = new ListNode(12, new ListNode(5, new ListNode(2, new ListNode(7))));
        System.out.println(StringUtils.singlyListNodeToString(node));

        System.out.println(StringUtils.singlyListNodeToString(obj.alternatingOrder(node)));

        node = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        System.out.println(StringUtils.singlyListNodeToString(node));

        System.out.println(StringUtils.singlyListNodeToString(obj.alternatingOrder(node)));

        System.out.println("--- find the shortest sub-array that will make array sorted ---");
        int[] subArrSorted;
        subArrSorted = obj.shortestSubarrayToKeepSorted(new int[] {2, 8, 7, 3, 5, 10, 9, 12});
        System.out.println(Arrays.toString(subArrSorted));

        System.out.println(Arrays.toString(obj.shortestSubarrayToKeepSorted(new int[] {3, 7, 5, 6, 9})));

        System.out.println(Arrays.toString(obj.shortestSubarrayToKeepSorted(new int[] {
                10, 12, 20, 30, 25, 40, 32, 31, 35, 50, 60
        })));

        System.out.println(Arrays.toString(obj.shortestSubarrayToKeepSorted(new int[] {
                0, 1, 15, 25, 6, 7, 30, 40, 50
        })));

        System.out.println("--- algorithm to traverse the nodes in boustrophedon order ---");
        List<Integer> zigZagTraversal;
        TreeNode root1 = new TreeNode(1,
                new TreeNode(2,
                        new TreeNode(4),
                        new TreeNode(5)),
                new TreeNode(3,
                    new TreeNode(6),
                    new TreeNode(7)));
        zigZagTraversal = obj.boustrophedonTraversal(root1);

        zigZagTraversal.forEach(i-> System.out.print(i + ", "));
        System.out.print("\n");

        root1 = new TreeNode(1,
                new TreeNode(2,
                        new TreeNode(4,
                                new TreeNode(7),
                                new TreeNode(8)),
                        null),
                new TreeNode(3,
                        new TreeNode(5,
                                new TreeNode(9),
                                null),
                        new TreeNode(6)));
        zigZagTraversal = obj.boustrophedonTraversal(root1);

        zigZagTraversal.forEach(i-> System.out.print(i + ", "));
        System.out.print("\n");
    }
}
