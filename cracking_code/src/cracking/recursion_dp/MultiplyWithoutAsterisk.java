package cracking.recursion_dp;

import java.util.Scanner;

/**
 * Multiply two ints without usage of "*". Addition, subtraction and bit shift is ok
 */
public class MultiplyWithoutAsterisk {

    int multiplyInts(int a, int b) {
        int smaller = a < b ? a : b;
        int big = a >= b ? a : b;
        return multiHelper(big, smaller);
    }

    int multiHelper(int big, int small) {
        if (small == 0)
            return 0;
        if (small == 1)
            return big;

        int smallHalf = small >> 1;
        int multiOfHalves = multiHelper(big, smallHalf);
        if (smallHalf + smallHalf == small)
            return multiOfHalves + multiOfHalves;
        else
            return multiOfHalves + multiOfHalves + big;
    }

    public static void main(String[] args) {
        MultiplyWithoutAsterisk obj = new MultiplyWithoutAsterisk();
        Scanner in = new Scanner(System.in);
        System.out.println("Enter 2 number to multiply : ");
        int num1 = in.nextInt();
        int num2 = in.nextInt();
        System.out.println("Product is : " + obj.multiplyInts(num1, num2));
    }
}
