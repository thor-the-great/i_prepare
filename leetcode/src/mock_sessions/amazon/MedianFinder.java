package mock_sessions.amazon;

import java.util.PriorityQueue;

/**
 * Idea is to keep two heaps divided in the middle of array (corner cases applied) - one is min other is max
 * Keep balancing those heaps so they are different in size at no more than 1
 * Median will be average between top in min and top in max, or min or max depending on which heap is greater
 *
 */
public class MedianFinder {
    /** initialize your data structure here. */
    PriorityQueue<Integer> minQ = new PriorityQueue();
    PriorityQueue<Integer> maxQ = new PriorityQueue<>((x, y) -> y - x);

    public MedianFinder() {
        minQ.clear();
        maxQ.clear();
    }

    public void addNum(int num) {
        if (minQ.size() == 0 && maxQ.size() == 0){
            maxQ.add(num);
            return;
        }

        int max = maxQ.peek();
        if (num > max)
            minQ.add(num);
        else
            maxQ.add(num);

        //now rebalance queues as per size
        if (minQ.size() == maxQ.size()) return;

        if (maxQ.size() > minQ.size())
            minQ.add(maxQ.poll());
        else
            maxQ.add(minQ.poll());
    }

    public double findMedian() {
        if (maxQ.size() == minQ.size())
            return (float) (maxQ.peek() + minQ.peek()) / 2;
        if (maxQ.size() > minQ.size())
            return maxQ.peek();
        else
            return minQ.peek();
    }
}
