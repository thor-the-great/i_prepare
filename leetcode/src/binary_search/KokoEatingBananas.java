package binary_search;

/**
 * 875. Koko Eating Bananas
Medium

Share
Koko loves to eat bananas. There are n piles of bananas, the ith pile has piles[i] bananas. The guards have gone and will come back in h hours.

Koko can decide her bananas-per-hour eating speed of k. Each hour, she chooses some pile of bananas and eats k bananas from that pile. If the pile has less than k bananas, she eats all of them instead and will not eat any more bananas during this hour.

Koko likes to eat slowly but still wants to finish eating all the bananas before the guards return.

Return the minimum integer k such that she can eat all the bananas within h hours.

 

Example 1:

Input: piles = [3,6,7,11], h = 8
Output: 4
Example 2:

Input: piles = [30,11,23,4,20], h = 5
Output: 30
Example 3:

Input: piles = [30,11,23,4,20], h = 6
Output: 23
 

Constraints:

1 <= piles.length <= 104
piles.length <= h <= 109
1 <= piles[i] <= 109

https://leetcode.com/problems/koko-eating-bananas/
 */
public class KokoEatingBananas {
    
    /**
     * Checking each k from 1 to max in piles. To speed up the process do it in binary search, trying each potential k and moving left and right edges accordingly
     */
    public int minEatingSpeed(int[] piles, int h) {
        int r = 0;
        for (int p : piles) {
            r = Math.max(r, p);
        }
        int l = 1;
        
        while (l < r) {
            int k = l + (r - l)/2;
            if (findHours(piles, k) <= h) {
                r = k;
            } else {
                l = k + 1;
            }
        }
        return l;
    } 
    
    int findHours(int[] piles, int k) {
        int h = 0;
        for (int i = 0; i < piles.length; i++) {
            h += (piles[i]/k) + (piles[i] % k > 0 ? 1 : 0);
        }
        return h;
    }
}
