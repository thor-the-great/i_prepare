package bit_array;

/**
 * This problem was asked by Amazon.
 *
 * Implement a bit array.
 *
 * A bit array is a space efficient array that holds a value of 1 or 0 at each index.
 *
 * init(size): initialize the array with size
 * set(i, val): updates index at i with val where val is either 1 or 0.
 * get(i): gets the value at index i.
 *
 */
public class BitArray {
    /**
     * Idea is to use individual bits in array of longs. Each element can utilize 64 bits = 64 elements of bit array
     * Correct element found by index/64, internal index of bit - by index%64.
     */
    long[] values;
    int MAX_BITS = 64;

    //initialize the array with size
    void init(int size) {
        int numOfElements = size / MAX_BITS;
        if (size % MAX_BITS > 0)
            numOfElements++;

        values = new long[numOfElements];
    }

    //updates index at i with val where val is either 1 or 0.
    void set(int i, int val) {
        int numPosition = i / MAX_BITS;
        int intPosition = i % MAX_BITS;

        if (val == 1)
            values[numPosition] |= (val << intPosition);
        else
            values[numPosition] &= ~(val << intPosition);
    }

    //gets the value at index i
    int get(int i) {
        int numPosition = i / MAX_BITS;
        int intPosition = i % MAX_BITS;
        long val = values[numPosition] & (1 << intPosition);
        return (int) (val >> intPosition);
    }

    public static void main(String[] args) {
        BitArray bitArray = new BitArray();
        bitArray.init(130);
        bitArray.set(20, 1);
        bitArray.set(10, 1);
        bitArray.set(36, 1);
        bitArray.set(70, 1);
        bitArray.set(120, 1);
        System.out.println(bitArray.get(15));//0
        System.out.println(bitArray.get(20));//1
        System.out.println(bitArray.get(25));//0
        System.out.println(bitArray.get(70));//1
        System.out.println(bitArray.get(72));//0
        bitArray.set(70, 0);
        System.out.println(bitArray.get(70));//0
        bitArray.set(70, 1);
        bitArray.set(70, 1);
        System.out.println(bitArray.get(70));//1
        System.out.println(bitArray.get(64));//0
        bitArray.set(64, 1);
        System.out.println(bitArray.get(64));//1
    }
}