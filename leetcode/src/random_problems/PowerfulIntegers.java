package random_problems;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 970. Powerful Integers
 * Easy
 *
 * Given two non-negative integers x and y, an integer is powerful if it is equal to x^i + y^j for some integers
 * i >= 0 and j >= 0.
 *
 * Return a list of all powerful integers that have value less than or equal to bound.
 *
 * You may return the answer in any order.  In your answer, each value should occur at most once.
 *
 *
 *
 * Example 1:
 *
 * Input: x = 2, y = 3, bound = 10
 * Output: [2,3,4,5,7,9,10]
 * Explanation:
 * 2 = 2^0 + 3^0
 * 3 = 2^1 + 3^0
 * 4 = 2^0 + 3^1
 * 5 = 2^1 + 3^1
 * 7 = 2^2 + 3^1
 * 9 = 2^3 + 3^0
 * 10 = 2^0 + 3^2
 * Example 2:
 *
 * Input: x = 3, y = 5, bound = 15
 * Output: [2,4,6,8,10,14]
 */
public class PowerfulIntegers {

    public static void main(String[] args) {
        PowerfulIntegers obj = new PowerfulIntegers();
        List<Integer> res = obj.powerfulIntegers(2, 3, 10);
        res.forEach(i -> System.out.print(i + " "));
        System.out.print("\n");
        res = obj.powerfulIntegers(1, 1, 2);
        res.forEach(i -> System.out.print(i + " "));
    }

    public List<Integer> powerfulIntegers(int x, int y, int bound) {
        Set<Integer> res = new HashSet();
        int xx = 1;
        while (bound - xx > 0) {
            int p1 = bound - xx;
            int yy = 1;
            while (p1 - yy >= 0) {
                res.add(xx + yy);
                if (y == 1)
                    break;
                yy = yy * y;
            }
            if (x == 1)
                break;
            xx = xx * x;
        }
        List<Integer> ans = new ArrayList(res);
        return ans;
    }
}
