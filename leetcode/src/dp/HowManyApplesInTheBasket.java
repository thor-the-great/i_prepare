package dp;

import java.util.Arrays;

public class HowManyApplesInTheBasket {

    public int maxNumberOfApples(int[] arr) {
        Arrays.sort(arr);
        int res = 0, cap = 0;
        int i = 0;

        while (i < arr.length && cap + arr[i] <= 5000) {
            cap += arr[i++];
            res++;
        }

        return res;
    }

    public static void main(String[] args) {
        HowManyApplesInTheBasket obj = new HowManyApplesInTheBasket();
        System.out.println(obj.maxNumberOfApples(new int[] {100,200,150,1000} ));
    }
}
