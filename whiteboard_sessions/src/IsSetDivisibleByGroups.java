import java.util.HashMap;
import java.util.Map;


public class InSetDivisibleBuGroups {

    boolean isDividePossible(int[] carts) {
        Map<Integer, Integer> counts = new HashMap();
        for (int el : carts) {
            if (counts.containsKey(el))
                counts.put(el, counts.get(el) + 1);
            else
                counts.put(el, 1);
        }
        Integer[] countsArray = counts.values().toArray(new Integer[0]);
        int gcd = countsArray[0];
        for (int i = 1; i < countsArray.length; i++) {
            gcd = findGCD(gcd, countsArray[i]);
        }
        return gcd > 1;
    }

    int findGCD(int a, int b) {
        if (b == 0)
            return a;
        return findGCD(b, a % b);
    }

    public static void main(String args[]) {
        InSetDivisibleBuGroups obj = new InSetDivisibleBuGroups();
        System.out.println(obj.isDividePossible(new int[]{1, 1, 2, 1, 2, 2}));
        System.out.println(obj.isDividePossible(new int[]{1, 1, 2, 1, 2}));
        System.out.println(obj.isDividePossible(new int[]{1, 5, 2, 1, 2}));
        System.out.println(obj.isDividePossible(new int[]{1, 1, 2, 2, 1, 1, 4, 4, 4, 4}));
    }
}
