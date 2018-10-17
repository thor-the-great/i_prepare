package cracking.hard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class FindMissingBinary {

    //idea is - it must be a balance of 0s and 1s, ideally always 1 <= 0. In our case it's broken, so depends if n is odd
    //or even it must be: even - 1s == 0s, odd - Os = 1s + 1. When removed =
    //
    int findMissing(List<BitInteger> nums) {
        return findMissing(nums,  BitInteger.INTEGER_SIZE - 1);
    }

    int findMissing(List<BitInteger> nums, int col) {
        if (col < 0)
            return 0;

        ArrayList<BitInteger> zeroes = new ArrayList(nums.size() / 2);
        ArrayList<BitInteger> ones = new ArrayList(nums.size() / 2);
        for (BitInteger nextNum : nums) {
            int nextBit = nextNum.fetch(col);
            if (nextBit == 1)
                ones.add(nextNum);
            else
                zeroes.add(nextNum);
        }

        if (zeroes.size() <= ones.size()) {
            int missing = findMissing(zeroes, col - 1);
            return (missing << 1) | 0;
        }
        else {
            int missing = findMissing(ones, col - 1);
            return (missing << 1) | 1;
        }
    }

    public static void main(String[] args) {
        int numOfElements = 10000;
        BitInteger.INTEGER_SIZE = Integer.toBinaryString(numOfElements).length();
        List<BitInteger> nums = new ArrayList<>();
        int rand = new Random().nextInt(numOfElements);
        for (int i = 0; i < numOfElements; i++) {
            if (i != rand) {
                BitInteger bInt = new BitInteger(i);
                nums.add(bInt);
            }
        }
        Collections.shuffle(nums);
        FindMissingBinary obj = new FindMissingBinary();
        int foundMissing = obj.findMissing(nums);
        System.out.println("Found missing - " + foundMissing);
        System.out.println("Element actually removed - " + rand);
    }
}

class BitInteger {
    public static int INTEGER_SIZE;
    private boolean[] bits;
    public BitInteger() {
        bits = new boolean[INTEGER_SIZE];
    }
    /* Creates a number equal to given value. Takes time proportional
     * to INTEGER_SIZE. */
    public BitInteger(int value){
        bits = new boolean[INTEGER_SIZE];
        for (int j = 0; j < INTEGER_SIZE; j++){
            if (((value >> j) & 1) == 1) bits[INTEGER_SIZE - 1 - j] = true;
            else bits[INTEGER_SIZE - 1 - j] = false;
        }
    }

    /** Returns k-th most-significant bit. */
    public int fetch(int k){
        if (bits[k]) return 1;
        else return 0;
    }

    /** Sets k-th most-significant bit. */
    public void set(int k, int bitValue){
        if (bitValue == 0 ) bits[k] = false;
        else bits[k] = true;
    }

    /** Sets k-th most-significant bit. */
    public void set(int k, char bitValue){
        if (bitValue == '0' ) bits[k] = false;
        else bits[k] = true;
    }

    /** Sets k-th most-significant bit. */
    public void set(int k, boolean bitValue){
        bits[k] = bitValue;
    }

    public void swapValues(BitInteger number) {
        for (int i = 0; i < INTEGER_SIZE; i++) {
            int temp = number.fetch(i);
            number.set(i, this.fetch(i));
            this.set(i, temp);
        }
    }

    public int toInt() {
        int number = 0;
        for (int j = INTEGER_SIZE - 1; j >= 0; j--){
            number = number | fetch(j);
            if (j > 0) {
                number = number << 1;
            }
        }
        return number;
    }

    @Override
    public String toString() {
        return "" + toInt();
    }
}
