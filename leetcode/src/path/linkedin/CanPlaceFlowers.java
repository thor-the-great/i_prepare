package path.linkedin;

/**
 * 605. Can Place Flowers
 * Easy
 *
 * Suppose you have a long flowerbed in which some of the plots are planted and some are not. However, flowers cannot
 * be planted in adjacent plots - they would compete for water and both would die.
 *
 * Given a flowerbed (represented as an array containing 0 and 1, where 0 means empty and 1 means not empty), and a
 * number n, return if n new flowers can be planted in it without violating the no-adjacent-flowers rule.
 *
 * Example 1:
 * Input: flowerbed = [1,0,0,0,1], n = 1
 * Output: True
 * Example 2:
 * Input: flowerbed = [1,0,0,0,1], n = 2
 * Output: False
 * Note:
 * The input array won't violate no-adjacent-flowers rule.
 * The input array size is in the range of [1, 20000].
 * n is a non-negative integer which won't exceed the input array size.
 */
public class CanPlaceFlowers {
    /**
     * Idea - simply test for every empty space and in that space - check number of empty cells. The number of flowers
     * we can plant is (num of cells - 1) / 2. Catch is - need to treat first and last cells as empty - there is no
     * next or previous cell
     * @param flowerbed
     * @param n
     * @return
     */
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        if (n == 0)
            return true;
        int N = flowerbed.length;
        for (int i = 0; i < N; i++) {
            if (flowerbed[i] == 0) {
                if ((i - 1 < 0 || flowerbed[i - 1] == 0)
                        && (i + 1 == N || flowerbed[i + 1] == 0)) {
                    --n;
                    flowerbed[i] = 1;
                }
            }
            if ( n == 0)
                return true;
        }
        return n == 0;
    }


    public boolean canPlaceFlowersOld(int[] flowerbed, int n) {
        int count = 0;
        int l = 0, r;
        int N = flowerbed.length;
        while (l < N) {
            //if we found empty cell - start contiguous sequence
            if (flowerbed[l] == 0) {
                r = l;
                //loop until we find the end of sequence
                while ((r + 1) < N && flowerbed[r + 1] == 0) {
                    r++;
                }
                //add one more to the end or to the start in case of edge cell
                if (l == 0)
                    l--;
                if (r == N - 1)
                    r++;
                //this is how many flowers we can put
                int numOfFlowers = (r - l) / 2;
                if (numOfFlowers > 0) {
                    count += numOfFlowers;
                    //early exit
                    if (count >= n)
                        return true;
                }
                l = r + 1;
            }
            else
                l++;
        }
        return count >= n;
    }
}
