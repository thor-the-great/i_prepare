package dp;

public class NthTribonacciNumber {
    /**
     * same dp approach as for fibonachi - keep 3 previous numbers, get each next as sum of 3 previous then
     * adjust each of previous ones.
     * 
     * O(n) time, O(1) space
     */
    public int tribonacci(int n) {
        if (n == 0) {
             return 0;
        }
        if (n > 0 && n <= 2) {
            return 1;
        }
        int prev = 1, prevPrev = 1, prevPrevPrev = 0, cur = 0;
        for (int i = 3; i <= n; i++) {
            cur = prev + prevPrev + prevPrevPrev;
            prevPrevPrev = prevPrev;
            prevPrev = prev;
            prev = cur; //cur
        }
        return cur;
    }
}
