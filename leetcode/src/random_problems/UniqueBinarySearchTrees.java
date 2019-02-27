package random_problems;

/**
 * 96. Unique Binary Search Trees
 * Medium
 *
 * Given n, how many structurally unique BST's (binary search trees) that store values 1 ... n?
 *
 * Example:
 *
 * Input: 3
 * Output: 5
 * Explanation:
 * Given n = 3, there are a total of 5 unique BST's:
 *
 *    1         3     3      2      1
 *     \       /     /      / \      \
 *      3     2     1      1   3      2
 *     /     /       \                 \
 *    2     1         2                 3
 *
 */
public class UniqueBinarySearchTrees {

    /**
     * Idea: based on Catalan number :
     *
     * c(n + 1) = c(n)*2*(2*i + 1)/(i + 2)
     *
     * @param n
     * @return
     */
    public int numTrees(int n) {
        long res = 1;

        for (int i = 0; i < n; i++) {
            res = res*2*(2*i + 1)/(i + 2);
        }
        return (int)res;
    }
}
