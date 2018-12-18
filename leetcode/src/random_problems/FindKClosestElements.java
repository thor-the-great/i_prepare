package random_problems;

import java.util.*;

/**
 * 658. Find K Closest Elements
 * Medium
 * Given a sorted array, two integers k and x, find the k closest elements to x in the array. The result should also
 * be sorted in ascending order. If there is a tie, the smaller elements are always preferred.
 *
 * Example 1:
 * Input: [1,2,3,4,5], k=4, x=3
 * Output: [1,2,3,4]
 * Example 2:
 * Input: [1,2,3,4,5], k=4, x=-1
 * Output: [1,2,3,4]
 * Note:
 * The value k is positive and will always be smaller than the length of the sorted array.
 * Length of the given array is positive and will not exceed 104
 * Absolute value of elements in the array and x will not exceed 104
 * UPDATE (2017/9/19):
 * The arr parameter had been changed to an array of integers (instead of a list of integers). Please reload the code
 * definition to get the latest changes.
 */
public class FindKClosestElements {

    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        //return findClosestElementsPQ(arr, k, x);
        //return findClosestElementsBinarySearchTwoPointers(arr, k, x);
        return findClosestElementsBinarySearch(arr, k, x);
    }

    /**
     * Idea similar to previous one, but instead of searching for the closest element and then expand let's search for
     * the whole interval at once. By comparing first and last elements in the interval we can tell if the interval
     * is good or not.
     * @param arr
     * @param k
     * @param x
     * @return
     */
    public List<Integer> findClosestElementsBinarySearch(int[] arr, int k, int x) {
        int l = 0;
        int r = arr.length - k;

        while(l < r) {
            int m = l + (r - l) / 2;
            if (x - arr[m] > arr[m + k] - x) {
                l = m + 1;
            } else
                r = m;
        }
        List<Integer> res = new ArrayList<>();
        for (int i = l; i < l + k; i++)
            res.add(arr[i]);
        return res;
    }

    /**
     * Idea - find the x element (or closest to x from the array) using binary search. Standard Java BS implementation
     * gives you insertion point!
     * Then having x position start expanding using two pointers to left and right comparing distance to x. Take side
     * that has min distance and move only that pointer. Do it until result list will be full.
     * Catch 1: - because we know that elements are increasing to the right and decreasing to the left we can remove
     * abs().
     * Catch 2: - we use deque to store result, addFirst for left elements and addLast for right elements
     * @param arr
     * @param k
     * @param x
     * @return
     */
    public List<Integer> findClosestElementsBinarySearchTwoPointers(int[] arr, int k, int x) {
        int xIdx = Arrays.binarySearch(arr, x);
        if (xIdx < 0)
            xIdx = - xIdx - 1;
        LinkedList<Integer> res = new LinkedList<>();
        int r = xIdx;
        int l = xIdx - 1;
        int dR = Integer.MAX_VALUE;
        int dL = Integer.MAX_VALUE;
        while (res.size() < k) {
            if (r < arr.length)
                dR = arr[r] - x;
            if (l >= 0)
                dL = x - arr[l];

            if (dL <= dR) {
                res.addFirst(arr[l]);
                l--;
                dL = Integer.MAX_VALUE;
            }
            else {
                res.addLast(arr[r]);
                r++;
                dR = Integer.MAX_VALUE;
            }
        }
        return res;
    }

    public List<Integer> findClosestElementsPQ(int[] arr, int k, int x) {
        Comparator<Integer> comp = (Integer i1, Integer i2)->{
            int d1 = Math.abs(i1 - x);
            int d2 = Math.abs(i2 - x);
            if (d1 != d2)
                return Integer.compare(d2, d1);
            return Integer.compare(i2, i1);
        };

        PriorityQueue<Integer> pq = new PriorityQueue(comp);
        for (int i =0; i < k; i++) {
            pq.add(arr[i]);
        }
        for (int i =k; i < arr.length; i++) {
            pq.add(arr[i]);
            pq.poll();
        }
        List<Integer> res = new ArrayList(pq);
        Collections.sort(res);
        return res;
    }

    public static void main(String[] args) {
        FindKClosestElements obj = new FindKClosestElements();
        int[] arr;
        int x;
        int k;
        List<Integer> closest;

        arr = new int[] {1,2,3,4,5};
        k = 4;
        x =3;
        closest = obj.findClosestElements(arr, k, x);
        closest.forEach(i->System.out.print(i+" ")); //1,2,3,4
    }
}
