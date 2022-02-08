package arrays;

/**
 * 849. Maximize Distance to Closest Person
Medium

You are given an array representing a row of seats where seats[i] = 1 represents a person sitting in the ith seat, and seats[i] = 0 represents that the ith seat is empty (0-indexed).

There is at least one empty seat, and at least one person sitting.

Alex wants to sit in the seat such that the distance between him and the closest person to him is maximized. 

Return that maximum distance to the closest person.

 

Example 1:

Input: seats = [1,0,0,0,1,0,1]
Output: 2
Explanation: 
If Alex sits in the second open seat (i.e. seats[2]), then the closest person has distance 2.
If Alex sits in any other open seat, the closest person has distance 1.
Thus, the maximum distance to the closest person is 2.

Example 2:

Input: seats = [1,0,0,0]
Output: 3
Explanation: 
If Alex sits in the last seat (i.e. seats[3]), the closest person is 3 seats away.
This is the maximum distance possible, so the answer is 3.

Example 3:

Input: seats = [0,1]
Output: 1

 

Constraints:

    2 <= seats.length <= 2 * 104
    seats[i] is 0 or 1.
    At least one seat is empty.
    At least one seat is occupied.

https://leetcode.com/problems/maximize-distance-to-closest-person/
 */
public class MaximizeDistanceClosestPerson {
    
    /**
     * Find the left and start points first, distance for those are the index itself as Alex can sit
     * at the very first and very last seat.
     * Then we do 2 pointers for each two adj seats and find the distance as (seat_idx - pre_seat_idx)/2. Compare with rolling max  
     */
    public int maxDistToClosest(int[] seats) {
        int prev = -1, res = 0;
        for (int i = 0; i < seats.length; i++) {
            if (seats[i] == 1) {
                if (prev == -1) {
                    res = i;
                } else {
                    res = Math.max(res, (i - prev) / 2);
                }
                prev = i;
            }
        }
        //checking rightmost case
        return Math.max(res, seats.length - 1 - prev);
    }
}
