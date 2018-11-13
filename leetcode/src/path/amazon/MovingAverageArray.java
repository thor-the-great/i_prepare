package path.amazon;

public class MovingAverageArray {
    /**
     * Initialize your data structure here.
     */
    int[] q;
    long sum;
    int p;
    int n;

    public MovingAverageArray(int size) {
        q = new int[size];
        sum = 0;
        p = 0;
    }

    public double next(int val) {
        if (n < q.length) {
            n++;
        } else {
            sum -= q[p];
        }
        q[p] = val;
        sum += val;
        p = (p + 1) % q.length;

        return (double) sum / n;
    }
}
