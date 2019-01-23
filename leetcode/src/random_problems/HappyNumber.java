package random_problems;

import java.util.HashSet;
import java.util.Set;

public class HappyNumber {

    public boolean isHappy(int n) {
        Set<Integer> set = new HashSet();
        while(!set.contains(n)) {
            set.add(n);
            int d = n;
            int sum = 0;
            while (d > 0) {
                int d1 = d % 10;
                sum += d1*d1;
                d /= 10;
            }
            n = sum;
            if (n == 1)
                return true;
        }
        return false;
    }

    public static void main(String[] args) {
        HappyNumber obj = new HappyNumber();
        System.out.println(obj.isHappy(8));
        System.out.println(obj.isHappy(7));
        System.out.println(obj.isHappy(19));

    }
}
