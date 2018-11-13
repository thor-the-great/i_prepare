package path.amazon;

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
}
