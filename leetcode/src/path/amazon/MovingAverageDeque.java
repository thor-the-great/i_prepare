package path.amazon;

import java.util.Deque;
import java.util.LinkedList;

public class MovingAverageDeque {

    Deque<Integer> q;
    int size;
    int sum;

    public MovingAverageDeque(int size) {
        this.size = size;
        q = new LinkedList();
        sum = 0;
    }

    public double next(int val) {
        if (q.size() == size) {
            sum -= q.pollFirst();
        }
        q.addLast(val);
        sum += val;

        return (double) sum / q.size();
    }

    public static void main(String[] args) {
        MovingAverageDeque obj = new MovingAverageDeque(3);
        System.out.println(obj.next(1));
        System.out.println(obj.next(10));
        System.out.println(obj.next(3));
        System.out.println(obj.next(5));
    }
}
