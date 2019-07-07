package random_problems;

/**
 * 1109. Corporate Flight Bookings
 *
 * There are n flights, and they are labeled from 1 to n.
 *
 * We have a list of flight bookings.  The i-th booking bookings[i] = [i, j, k] means that we booked k seats from
 * flights labeled i to j inclusive.
 *
 * Return an array answer of length n, representing the number of seats booked on each flight in order of their label.
 *
 *
 *
 * Example 1:
 *
 * Input: bookings = [[1,2,10],[2,3,20],[2,5,25]], n = 5
 * Output: [10,55,45,25,25]
 *
 *
 * Constraints:
 *
 * 1 <= bookings.length <= 20000
 * 1 <= bookings[i][0] <= bookings[i][1] <= n <= 20000
 * 1 <= bookings[i][2] <= 10000
 */
public class CorporateFlightBookings {

    /**
     *
     * @param bookings
     * @param n
     * @return
     */
    public int[] corpFlightBookings(int[][] bookings, int n) {

        int[] res = new int[n];

        //for each booking tuple
        for (int[] b : bookings) {
            //for each from b[0] to b[1] add b[2] tickets
            for (int i = b[0]; i <= b[1]; i++) {
                res[i - 1] += b[2];
            }
        }
        return res;
    }
}
