package path.google;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class NextGreaterElement {

    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] res = new int[nums1.length];
        int N = nums2.length;
        Map<Integer, Integer> indexes = new HashMap();
        for (int i = 0; i <N; i++) {
            indexes.put(nums2[i], i);
        }

        for (int i = 0; i < nums1.length; i++) {
            int idx = indexes.get(nums1[i]);
            int j = idx + 1;
            while(j < N) {
                if (nums2[j] > nums1[i]) {
                    res[i] = nums2[j];
                    break;
                }
                j++;
            }
            if (j >= N)
                res[i] = -1;
        }
        return res;
    }

    /**
     * Keep pushing to the stack is elements are decreasing. Once greater element found - start popping from stack and
     * save greater for the elements
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] nextGreaterElementStackMap(int[] nums1, int[] nums2) {
        int[] res = new int[nums1.length];
        int N = nums2.length;
        if (N == 0)
            return res;
        //build map and stack
        Map<Integer, Integer> m = new HashMap();
        Stack<Integer> s = new Stack();
        s.push(nums2[0]);
        for (int i = 1; i < N; i++) {
            if (nums2[i] < s.peek())
                s.push(nums2[i]);
            else {
                while(!s.isEmpty() && nums2[i] > s.peek())
                    m.put(s.pop(), nums2[i]);
                s.push(nums2[i]);
            }
        }
        //now in stack are only elements that don't have greaters, so fill it with -1
        while(!s.isEmpty()) {
            m.put(s.pop(), -1);
        }
        //form the result array
        for (int i = 0; i < nums1.length; i++)
            res[i] = m.get(nums1[i]);
        return res;
    }

    public static void main(String[] args) {
        NextGreaterElement obj = new NextGreaterElement();
        obj.nextGreaterElement(new int[]{4,1,2}, new int[] {1,3,4,2});
    }
}
