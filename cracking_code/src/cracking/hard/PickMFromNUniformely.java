package cracking.hard;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class PickMFromNUniformely {

    int[] pickM(int[] nums, int m) {
        //similar to card shuffling. First copy first m elements from original array
        //then on each next element generate random number between 0 and i and if it's
        //within m - replace with element with i-th lement from original array
        int[] newNums = Arrays.copyOfRange(nums, 0, m);
        Random rand = new Random();
        for (int i = m; i < nums.length; i++) {
            int randNum = rand.nextInt(i);
            if (randNum < m)
                newNums[randNum] = nums[i];
        }
        return newNums;
    }

    public static void main(String[] args) {
        PickMFromNUniformely obj = new PickMFromNUniformely();
        int n = 100;
        int[] nums = IntStream.range(0, n).toArray();
        int[] picked = obj.pickM(nums, 25);
        for (int el : picked) {
            System.out.print(el + ", ");
        }
    }
}
