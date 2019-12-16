package path.amazon;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SingleNum {

    public int singleNumber(int[] nums) {
        //count times of every possible bit using &
        int bits = 32;
        int num = 0;
        for (int b = 0; b < bits; b++) {
            int sum = 0;
            int check = 1<<b;
            for (int i = 0; i < nums.length; i++) {
                if ((nums[i] & check) == 0 )
                    sum++;
            }
            if (sum % 2 == 0) {
                num = num + check;
            }
        }
        return num;
    }

    public int singleNumberXOR(int[] nums) {
        //do XOR for every bit in every num. Because every bit count will be %2 == 0 it should work
        int num = 0;
        for (int n : nums) {
            num = num ^ n;
        }
        return num;
    }

    public int singleNumberXORIt2(int[] nums) {
        if (nums.length == 0)
            return -1;

        int mask = 0;
        for (int num : nums) {
            mask^=num;
        }
        return mask;
    }
}

class SolutionTest {
    SingleNum obj = new SingleNum();

    @Test
    void findElementTest1() {
        int[] testArr = new int[] {3, 4, 2, 5, 3, 5, 2};
        assertEquals(obj.singleNumberXORIt2(testArr), 4);
    }

    @Test
    void findElementTest2() {
        int[] testArr = new int[] {3};
        assertEquals(obj.singleNumberXORIt2(testArr), 3);
    }

    @Test
    void findElementTest3() {
        Random r = new Random();
        int n = r.nextInt(1_000_000);
        int[] testArr = new int[2*n + 1];
        for (int i = 0; i < 2*n; i+=2) {
            testArr[i] = i;
            testArr[i + 1] = i;
        }
        testArr[2*n] = 2*n;
        assertEquals(obj.singleNumberXORIt2(testArr), 2*n);
    }
}