package dp;

/**
 * 1105. Filling Bookcase Shelves
 * Medium
 *
 * We have a sequence of books: the i-th book has thickness books[i][0] and height books[i][1].
 *
 * We want to place these books in order onto bookcase shelves that have total width shelf_width.
 *
 * We choose some of the books to place on this shelf (such that the sum of their thickness is <= shelf_width), then
 * build another level of shelf of the bookcase so that the total height of the bookcase has increased by the maximum
 * height of the books we just put down.  We repeat this process until there are no more books to place.
 *
 * Note again that at each step of the above process, the order of the books we place is the same order as the given
 * sequence of books.  For example, if we have an ordered list of 5 books, we might place the first and second book
 * onto the first shelf, the third book on the second shelf, and the fourth and fifth book on the last shelf.
 *
 * Return the minimum possible height that the total bookshelf can be after placing shelves in this manner.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: books = [[1,1],[2,3],[2,3],[1,1],[1,1],[1,1],[1,2]], shelf_width = 4
 * Output: 6
 * Explanation:
 * The sum of the heights of the 3 shelves are 1 + 3 + 2 = 6.
 * Notice that book number 2 does not have to be on the first shelf.
 *
 *
 * Constraints:
 *
 * 1 <= books.length <= 1000
 * 1 <= books[i][0] <= shelf_width <= 1000
 * 1 <= books[i][1] <= 1000
 */
public class FillingBookcaseShelves {

    /**
     * Idea - use dp. For every book in the set we'll have the dp[i] that will have optimal solution on height.
     * For every book either we put in on the next shelf - h = dp[i - 1] + books[i][1], or we can put in on the
     * previous shelf, but only if it fits. So count until the width is >= 0 and keep the height as a max height
     *
     * @param books
     * @param shelf_width
     * @return
     */
    public int minHeightShelves(int[][] books, int shelf_width) {
        int N = books.length;
        int[] dp = new int[N + 1];
        dp[0] = 0;
        for (int i = 1; i <= N; i++) {
            int j = i - 1;
            int w = shelf_width;
            int h = 0;
            dp[i] = dp[j] + books[i - 1][1];
            while(j >= 0 && w - books[j][0] >= 0) {
                w = w - books[j][0];
                h = Math.max(books[j][1], h);
                dp[i] = Math.min(dp[i], dp[j] + h);
                j--;
            }
        }
        return dp[N];
    }
}
