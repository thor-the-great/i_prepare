package random_problems;

import java.util.ArrayList;
import java.util.List;

/**
 * 1276. Number of Burgers with No Waste of Ingredients
 * Medium
 *
 * Given two integers tomatoSlices and cheeseSlices. The ingredients of different burgers are as follows:
 *
 * Jumbo Burger: 4 tomato slices and 1 cheese slice.
 * Small Burger: 2 Tomato slices and 1 cheese slice.
 * Return [total_jumbo, total_small] so that the number of remaining tomatoSlices equal to 0 and the number of
 * remaining cheeseSlices equal to 0. If it is not possible to make the remaining tomatoSlices and cheeseSlices equal
 * to 0 return [].
 *
 *
 *
 * Example 1:
 *
 * Input: tomatoSlices = 16, cheeseSlices = 7
 * Output: [1,6]
 * Explantion: To make one jumbo burger and 6 small burgers we need 4*1 + 2*6 = 16 tomato and 1 + 6 = 7 cheese. There
 * will be no remaining ingredients.
 * Example 2:
 *
 * Input: tomatoSlices = 17, cheeseSlices = 4
 * Output: []
 * Explantion: There will be no way to use all ingredients to make small and jumbo burgers.
 * Example 3:
 *
 * Input: tomatoSlices = 4, cheeseSlices = 17
 * Output: []
 * Explantion: Making 1 jumbo burger there will be 16 cheese remaining and making 2 small burgers there will be 15
 * cheese remaining.
 * Example 4:
 *
 * Input: tomatoSlices = 0, cheeseSlices = 0
 * Output: [0,0]
 * Example 5:
 *
 * Input: tomatoSlices = 2, cheeseSlices = 1
 * Output: [0,1]
 *
 *
 * Constraints:
 *
 * 0 <= tomatoSlices <= 10^7
 * 0 <= cheeseSlices <= 10^7
 */
public class NumberOfBurgersWithNoWasteOfIngredients {

    /**
     * It's possible to write two equations from the problem statement. If there will be non-negative int solution for
     * it then return this solution, otherwise return empty.
     *
     * x + y = a
     * 2x + 4y = b
     *
     * for each small (x) and jumbo (y) we need one cheese, so sum of both will be total slices of cheese. Similarly
     * for all smalls we need 2x tomatoes and for all jumbos 4y tomatoes - the sum will be total tomatoes. Solution
     * for x and y will be:
     *
     * x = 2a - (b/2)
     * y = (b - 2a)/2
     *
     * plug in a and b and check solutions.
     *
     * O(1) both time and space - formula and few variables for state.
     *
     * I've done some micro optimization to the code so it looks more compact.
     * @param tomatoSlices
     * @param cheeseSlices
     * @return
     */
    public List<Integer> numOfBurgers(int tomatoSlices, int cheeseSlices) {
        List<Integer> res = new ArrayList();

        int smallsPart = tomatoSlices - 2*cheeseSlices;
        int jumboPart = 4*cheeseSlices - tomatoSlices;

        if (smallsPart < 0 || smallsPart % 2 == 1
                || jumboPart < 0 || jumboPart % 2 == 1) {
            return res;
        }

        res.add(smallsPart/ 2);
        res.add(jumboPart/ 2);
        return res;
    }
}
