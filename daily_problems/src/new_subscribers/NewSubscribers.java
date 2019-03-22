package new_subscribers;

/**
 * This problem was asked by Twitter.
 *
 * You are given an array of length 24, where each element represents the number of new subscribers during the
 * corresponding hour. Implement a data structure that efficiently supports the following:
 *
 * update(hour: int, value: int): Increment the element at index hour by value.
 * query(start: int, end: int): Retrieve the number of subscribers that have signed up between start and end (inclusive).
 * You can assume that all values get cleared at the end of the day, and that you will not be asked for start and end
 * values that wrap around midnight.
 */
public class NewSubscribers {

    int[][] prefixSum;

    NewSubscribers(int[] subs) {
        prefixSum = new int[subs.length][2];
        for (int i = 0; i < subs.length; i++) {
            if ( i > 0) {
                prefixSum[i][0] = subs[i - 1] + prefixSum[i - 1][0];
            }
            prefixSum[i][1] = subs[i] + (i == 0 ? 0 : prefixSum[i - 1][1]);
        }
    }

    /**
     * update(hour: int, value: int): Increment the element at index hour by value.
     * @param hour
     * @param value
     */
    public void update(int hour, int value) {
        for (int i = hour; i < prefixSum.length; i++) {
            if (i + 1 < prefixSum.length)
                prefixSum[i + 1][0]+= value;

            prefixSum[i][1]+=value;
        }
    }

    /**
     * Retrieve the number of subscribers that have signed up between start and end (inclusive)
     * @param start
     * @param end
     * @return
     */
    public int query(int start, int end) {
        return prefixSum[end][1] - prefixSum[start][0];
    }

    public static void main(String[] args) {
        int[] subs = new int[24];
        subs[3] = 4;
        subs[5] = 2;
        subs[20] = 3;
        NewSubscribers obj = new NewSubscribers(subs);
        assert(obj.query(3, 3) == 4);
        assert(obj.query(2, 6) == 6);

        obj.update(3, 1);
        obj.update(4, 2);

        assert(obj.query(2, 6) == 9);
        assert(obj.query(0, 23) == 12);

        System.out.println("All tests passed");
    }
}
