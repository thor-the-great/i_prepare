package queue;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 1942. The Number of the Smallest Unoccupied Chair
Medium

234

15

Add to List

Share
There is a party where n friends numbered from 0 to n - 1 are attending. There is an infinite number of chairs in this party that are numbered from 0 to infinity. When a friend arrives at the party, they sit on the unoccupied chair with the smallest number.

For example, if chairs 0, 1, and 5 are occupied when a friend comes, they will sit on chair number 2.
When a friend leaves the party, their chair becomes unoccupied at the moment they leave. If another friend arrives at that same moment, they can sit in that chair.

You are given a 0-indexed 2D integer array times where times[i] = [arrivali, leavingi], indicating the arrival and leaving times of the ith friend respectively, and an integer targetFriend. All arrival times are distinct.

Return the chair number that the friend numbered targetFriend will sit on.

 

Example 1:

Input: times = [[1,4],[2,3],[4,6]], targetFriend = 1
Output: 1
Explanation: 
- Friend 0 arrives at time 1 and sits on chair 0.
- Friend 1 arrives at time 2 and sits on chair 1.
- Friend 1 leaves at time 3 and chair 1 becomes empty.
- Friend 0 leaves at time 4 and chair 0 becomes empty.
- Friend 2 arrives at time 4 and sits on chair 0.
Since friend 1 sat on chair 1, we return 1.
Example 2:

Input: times = [[3,10],[1,5],[2,6]], targetFriend = 0
Output: 2
Explanation: 
- Friend 1 arrives at time 1 and sits on chair 0.
- Friend 2 arrives at time 2 and sits on chair 1.
- Friend 0 arrives at time 3 and sits on chair 2.
- Friend 1 leaves at time 5 and chair 0 becomes empty.
- Friend 2 leaves at time 6 and chair 1 becomes empty.
- Friend 0 leaves at time 10 and chair 2 becomes empty.
Since friend 0 sat on chair 2, we return 2.
 

Constraints:

n == times.length
2 <= n <= 104
times[i].length == 2
1 <= arrivali < leavingi <= 105
0 <= targetFriend <= n - 1
Each arrivali time is distinct.

https://leetcode.com/problems/the-number-of-the-smallest-unoccupied-chair/
 */
public class NumberOfSmallestUnoccupiedChair {

    /**
     * Idea - 2 min heaps
     * - store arrival time of the target friend
     * - sort array by time of arrival
     * - create min heap 1 of integers from 0 to N - 1, these are chairs from where we'll pick next avaliable one which must be the smallest number
     * - create min heap 2 of arrays where each array is - time of leaving for friend + his chair number
     * - start iterating from 0 to N - 1, on each step:
     *    - check friends that left - get times[i][0] time of arrival of next friend and check everythgin that smaller from minheap 2, return each
     *      chair back to avaliable 
     *    - check if current time is for that target friend, break if it is
     *    - for this next friend pick the next avaliable chair from minheap1 and put it to minheap2
     * @param times
     * @param targetFriend
     * @return
     */
    public int smallestChair(int[][] times, int targetFriend) {
        int targetTime = times[targetFriend][0];
        Arrays.sort(times, (a1, a2) -> a1[0] - a2[0]);
        //fill the min heap of chairs
        PriorityQueue<Integer> avChairs = new PriorityQueue<>();
        for (int i = 0; i < times.length; i++) {
            avChairs.add(i);
        }
        //this is heap of [leaving time, chair taken]
        PriorityQueue<int[]> leavingTimesChairs = new PriorityQueue<>((a1, a2) -> a1[0] - a2[0]);
        
        for (int i = 0; i < times.length; i++) {
            //check who from friend left sinse last iteration, for each who left put their chairs back to avChairs
            while(!leavingTimesChairs.isEmpty() && leavingTimesChairs.peek()[0] <= times[i][0]) {
                int chair = leavingTimesChairs.poll()[1];
                avChairs.add(chair);
            }
            
            if (times[i][0] == targetTime) {
                break;
            }
            //add leaving time and taken chair to the heap1
            int[] curFriend = new int[] {times[i][1], avChairs.poll()};
            leavingTimesChairs.add(curFriend);
        }
        
        return avChairs.peek();
    }
}
