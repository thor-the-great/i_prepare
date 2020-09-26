package arrays;

/**
 * 495. Teemo Attacking Medium
 * 
 * In LOL world, there is a hero called Teemo and his attacking can make his
 * enemy Ashe be in poisoned condition. Now, given the Teemo's attacking
 * ascending time series towards Ashe and the poisoning time duration per
 * Teemo's attacking, you need to output the total time that Ashe is in poisoned
 * condition.
 * 
 * You may assume that Teemo attacks at the very beginning of a specific time
 * point, and makes Ashe be in poisoned condition immediately.
 * 
 * Example 1:
 * 
 * Input: [1,4], 2 Output: 4 Explanation: At time point 1, Teemo starts
 * attacking Ashe and makes Ashe be poisoned immediately. This poisoned status
 * will last 2 seconds until the end of time point 2. And at time point 4, Teemo
 * attacks Ashe again, and causes Ashe to be in poisoned status for another 2
 * seconds. So you finally need to output 4.
 * 
 * 
 * Example 2:
 * 
 * Input: [1,2], 2 Output: 3 Explanation: At time point 1, Teemo starts
 * attacking Ashe and makes Ashe be poisoned. This poisoned status will last 2
 * seconds until the end of time point 2. However, at the beginning of time
 * point 2, Teemo attacks Ashe again who is already in poisoned status. Since
 * the poisoned status won't add up together, though the second poisoning attack
 * will still work at time point 2, it will stop at the end of time point 3. So
 * you finally need to output 3.
 * 
 * 
 * Note:
 * 
 * You may assume the length of given time series array won't exceed 10000. You
 * may assume the numbers in the Teemo's attacking time series and his poisoning
 * time duration per attacking are non-negative integers, which won't exceed
 * 10,000,000.
 * 
 * https://leetcode.com/problems/teemo-attacking/
 */
public class TeemoAtacking {
    
    /**
     * Key point for this problem is how to accumulate poisoned time correctly.
     * 
     * Initially I was thinking of adding time at the end of event, when I meet the
     * point of next attack. However I think this makes code more complex. After
     * trying few interation I reimplemented solution and start count poisoned time
     * at the time of attack.
     * 
     * There are two cases possible:
     * 
     * time when effect of previous attack ends is earlier than a time of next
     * attack. E.g. previous attack started at 2 and duration is 3 so it lasts till
     * 5, then next attack time is 7. time when effect of previous attack ends is
     * later than the time of next attack. E.g. previous attack started at 2 and
     * duration is 3 so it lasts till 5, then next attack time is 4. In case 1
     * effect of a previous attack is already counted in full on previous steps.
     * Thus we start from scratch, add whole duration to the result and set end
     * point as attack_point + duration.
     * 
     * In case 2 end point must be updated. Trick is for duration, we already count
     * the full duration amount in case 1. So what we need to count here is just the
     * difference between end with a previously recorded attack and end point with
     * the updated time. This will be difference between : max of (endPoint,
     * attackPoint + duration) - min of (endPoint, attackPoint + duration) For
     * example:
     * 
     * [1, 3] 3
     * 
     * at the step 1 we recorded start = 1, end = 1 + 3 = 4, res = 3 at the step 2
     * we'll add max(4, 6) - min (4,6) = 4 - 6 = 2, res = 3 + 2 = 5
     * 
     * O(n) time - one scan of array O(1) space - few variables to save state
     * 
     * @param timeSeries
     * @param duration
     * @return
     */
    public int findPoisonedDuration(int[] timeSeries, int duration) {
        int res = 0;
        int startPoint = 0, endPoint = 0;
        
        for (int attackPoint : timeSeries) {
            if (endPoint <= attackPoint) {
                startPoint = attackPoint;
                endPoint = attackPoint + duration;
                res+=duration;
            } else {
                int newEndPoint = Math.max(endPoint, attackPoint + duration);
                res+=newEndPoint - Math.min(endPoint, attackPoint + duration);
                endPoint = newEndPoint;
            }
        }
        return res;
    }
}
