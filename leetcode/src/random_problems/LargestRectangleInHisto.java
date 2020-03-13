package random_problems;

import java.util.Stack;

public class LargestRectangleInHisto {

    public int largestRectangleArea(int[] heights) {
        return largestRectangleAreaStack(heights);
        //return largestRectangleAreaDivConq(heights);
    }

    /**
     *
     * @param arr
     * @return
     */
    public int largestRectangleAreaTwoStacks(int[] arr) {
        int N = arr.length;
        if (N == 0)
            return 0;

        int max = 0, tmpIdx = 0;
        //one stack holds heights and second one - positions.
        //each stack represents the beginning of the rectangle (the left most). As we go we check
        //next height, if it's larger than the previous (peek()) - it's possible that this one and
        //previous one will combine (in terms of positions). In it's smaller - we can't keep
        //adding positions, need to go back and flush all smaller rectangles
        Stack<Integer> vals = new Stack(), pos = new Stack();

        for (int i = 0; i < N; i++) {
            int h = arr[i];
            if (vals.isEmpty() || vals.peek() < h) {
                vals.push(h);
                pos.push(i);
            } else if (vals.peek() > h) {
                while (!vals.isEmpty() && vals.peek() > h) {
                    tmpIdx = pos.pop();
                    max = Math.max(max, vals.pop() * (i - tmpIdx));
                }
                vals.push(h);
                pos.push(tmpIdx);
            }
        }

        while (!vals.isEmpty()) {
            tmpIdx = pos.pop();
            max = Math.max(max, vals.pop() * (N - tmpIdx));
        }

        return max;
    }

    int largestRectangleAreaDivConq(int[] heights) {
        int N = heights.length;
        if (N == 0)
            return 0;
        return helper(heights, 0, N - 1);
    }

    int helper(int[] h, int start, int end) {
        if (start > end)
            return 0;
        int min = start;
        for (int i = start + 1; i <= end; i++) {
            if (h[i] < h[min])
                min = i;
        }
        int res = Math.max(h[min] * (end - start + 1),
                Math.max(helper(h, start, min - 1), helper(h, min + 1, end)));
        return res;
    }

    int largestRectangleAreaStack(int[] heights) {
        int N = heights.length;
        if (N == 0)
            return 0;
        int res = 0;
        Stack<Integer> s = new Stack();
        s.push(-1);
        for (int i = 0; i < N; i++) {
            while (s.peek() != -1 && heights[i] <= heights[s.peek()]) {
                int next = s.pop();
                res = Math.max(res, heights[next] * (i - s.peek() - 1));
            }
            s.push(i);
        }
        while (s.peek() != -1) {
            int p = s.pop();
            res = Math.max(res, heights[p] * (N - s.peek() - 1));
        }
        return res;
    }

    public static void main(String[] args) {
        LargestRectangleInHisto obj = new LargestRectangleInHisto();
        System.out.println(obj.largestRectangleArea(new int[] {2,1,5,6,2,3}));
    }
}
