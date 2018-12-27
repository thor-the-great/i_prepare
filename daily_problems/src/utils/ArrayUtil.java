package utils;

import java.util.Random;
import java.util.stream.IntStream;

public class ArrayUtil {
    static Random r = new Random();

    public static int[] getRandomIntArray(int size, int maxElement) {
        int[] arr = new int[size];
        IntStream.range(0, size).forEach(i->arr[i] = r.nextInt(maxElement  + 1));
        return arr;
    }
}
