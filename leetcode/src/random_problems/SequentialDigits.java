package random_problems;

import java.util.ArrayList;
import java.util.List;

/**
 * 1291. Sequential Digits
 * Medium
 *
 * An integer has sequential digits if and only if each digit in the number is one more than the previous digit.
 *
 * Return a sorted list of all the integers in the range [low, high] inclusive that have sequential digits.
 *
 *
 *
 * Example 1:
 *
 * Input: low = 100, high = 300
 * Output: [123,234]
 * Example 2:
 *
 * Input: low = 1000, high = 13000
 * Output: [1234,2345,3456,4567,5678,6789,12345]
 *
 *
 * Constraints:
 *
 * 10 <= low <= high <= 10^9
 */
public class SequentialDigits {

    public List<Integer> sequentialDigits(int low, int high) {
        //handle first number
        int firstDigit = low;
        int numOfDigits = 1;
        while (firstDigit > 9) {
            firstDigit/=10;
            ++numOfDigits;
        }
        //in case the first number has more digits than in low
        if (firstDigit + numOfDigits - 1 > 9) {
            firstDigit = 1;
            ++numOfDigits;
        }
        int first = firstDigit;
        int d = firstDigit;

        for (int i = 1; i < numOfDigits; i++) {
            ++d;
            first = (first * 10) + d;
        }
        //in case low > first we need to generate next number as first
        if (low > first)
            first = getNext(first);

        List<Integer> res = new ArrayList();

        while (first <= high) {
            res.add(first);
            first = getNext(first);
        }
        return res;
    }

    int getNext(int num) {
        int digits = 0;
        int i = num;
        while (i > 0) {
            i/=10;
            ++digits;
        }
        int last = num % 10;

        int first = 1;
        int finalDigit = 0;
        if (last < 9) {
            first = last + 2 - digits;
            finalDigit = 1;
        }
        int res = first;
        while (digits > finalDigit) {
            ++first;
            res = res * 10 + first;
            --digits;
        }
        return res;
    }
}
