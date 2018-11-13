package path.amazon;

import java.util.Deque;
import java.util.LinkedList;

public class MaxSlidingWindow {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (k == 0) return new int[0];
        int[] res = new int[nums.length - k + 1];
        if (nums.length == 0) return res;
        Deque<Integer> dq = new LinkedList();
        //fill the dq with initial content of the window
        int i = 0;
        for (;i < k; i++) {
            while (!dq.isEmpty() && nums[i] > nums[dq.peekLast()]) {
                dq.pollLast();
            }
            dq.addLast(i);
        }
        res[0] = nums[dq.peekFirst()];

        for (; i < nums.length; i++) {
            //evict invalid (smaller that windows border) indexes
            while(!dq.isEmpty() && dq.peekFirst() <= (i - k))
                dq.pollFirst();
            //then remove elements smaller than current - they can't be max
            while (!dq.isEmpty() && nums[i] > nums[dq.peekLast()]) {
                dq.pollLast();
            }
            dq.addLast(i);
            res[i - k + 1] = nums[dq.peekFirst()];
        }
        return res;
    }
}
