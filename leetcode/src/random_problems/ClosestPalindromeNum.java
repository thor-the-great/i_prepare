package random_problems;

import java.util.Arrays;

/**
 * 564. Find the Closest Palindrome
 * Hard
 *
 * Given an integer n, find the closest integer (not including itself), which is a palindrome.
 *
 * The 'closest' is defined as absolute difference minimized between two integers.
 *
 * Example 1:
 * Input: "123"
 * Output: "121"
 * Note:
 * The input n is a positive integer represented by string, whose length will not exceed 18.
 * If there is a tie, return the smaller one as answer.
 */
public class ClosestPalindromeNum {

    /**
     * We first need to find the higher palindrome and lower palidrome respectively. and return the one who has the
     * least different with the input number.
     *
     * For the higher palindrome, the low MOD is number + 1 while for the lower palindrome, the high MOD is
     * number - 1.
     *
     * One global solution to find a palindrome is to copy first half part of the array to the last half part, we
     * regards this as standard palindrome.
     * We need to detect this standard palindrome belongs to higher one or the lower one. And other solutions will be
     * based on this standard one.
     *
     * For the higher palindrome, if the standard one belongs to higher, we just simply return it. Or we need to change it.
     * For example, String n is 1343, and the standard palindrome is 1331. to get the higher one from the standard
     * palidrome, we start from the
     * first 3, which is (n.length - 1) / 2. Add the number by 1, (---> 1431)if the added result is not higher than 9,
     * the changing process is finished,
     * otherwise, continuously changing the number of previous index by i. After the changing process, we re-palidrome
     * the string. (1431 -- > 1441)
     *
     * For the lower palindrome, similar idea.But we need to notice that when we decrease a number, and if the first
     * character of the string is '0',
     * we need to resize the array of n.length - 1, and fill in with '9'. (for example, n is '1000', the standard
     * palidrome is '1001'(higher one) ,the lower one '0000'-->'999'.)
     * @param n
     * @return
     */
    public String nearestPalindromic(String n) {
       long num = Long.parseLong(n);
       long greater = getGreater( num  + 1);

       long lower = getLower(num - 1);

       return Math.abs(greater - num) < Math.abs(lower - num) ? String.valueOf(greater) : String.valueOf(lower);
    }

    long getGreater(long num) {
        String str = String.valueOf(num);
        int N = str.length();
        char[] limit = str.toCharArray();
        char[] target = Arrays.copyOf(limit, N);
        //create standard pali
        for (int i = 0; i < N / 2; i++) {
            target[N - i - 1] = target[i];
        }

        for (int d = 0; d < N; d++) {
            if (limit[d] == target[d]) continue;
            if (limit[d] < target[d])
                return Long.parseLong(String.valueOf(target));
            for (int j = (N - 1) /2; j >=0; j--) {
                if (++target[j] <= '9') break;
                target[j] = '0';
            }
            //palindroming it again
            for (int i = 0; i < N / 2; i++) {
                target[N - i - 1] = target[i];
            }
            return Long.parseLong(String.valueOf(target));
        }
        return Long.parseLong(String.valueOf(target));
    }

    long getLower(long num) {
        String str = String.valueOf(num);
        int N = str.length();
        char[] limit = str.toCharArray();
        char[] target = Arrays.copyOf(limit, N);
        //create standard pali
        for (int i = 0; i < N / 2; i++) {
            target[N - i - 1] = target[i];
        }

        for (int d = 0; d < N; d++) {
            if (limit[d] == target[d]) continue;
            if (limit[d] > target[d])
                return Long.parseLong(String.valueOf(target));
            for (int j = (N - 1) /2; j >=0; j--) {
                if (--target[j] >= '0') break;
                target[j] = '9';
            }
            if (target[0] == '0') {
                long res = 0;
                for (int i = 1; i <= N - 1; i++) {
                    res = res * 10 + 9;
                }
                return res;
            }
            //palindroming it again
            for (int i = 0; i < N / 2; i++) {
                target[N - i - 1] = target[i];
            }
            return Long.parseLong(String.valueOf(target));
        }
        return Long.parseLong(String.valueOf(target));
    }

    public static void main(String[] args) {
        ClosestPalindromeNum obj = new ClosestPalindromeNum();
        System.out.println(obj.nearestPalindromic("11911"));
    }
}
