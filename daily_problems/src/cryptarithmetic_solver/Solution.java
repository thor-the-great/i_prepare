package cryptarithmetic_solver;

import java.util.HashMap;
import java.util.Map;

public class Solution {

    Map<Character, Integer> res;
    boolean[] numbers;

    Map<Character, Integer> solvePuzzle(String[] expr) {
        res = new HashMap<>();
        numbers = new boolean[10];
        //IntStream.range(0, 10).forEach(numbers::add);
        if (helper(expr, new int[] {0,0,0}, 0))
            return res;
        else
            return null;
    }

    boolean helper(String[] expr, int[] pointers, int carry) {
        char ch1 = expr[0].charAt(pointers[0]);
        char ch2 = expr[1].charAt(pointers[1]);
        char chS = expr[2].charAt(pointers[2]);

        int n1 = res.getOrDefault(ch1, -1);
        int n2 = res.getOrDefault(ch2, -1);
        int sum = res.getOrDefault(chS, -1);

        boolean newN1 = false;
        boolean newN2 = false;
        boolean newSum = false;

        if (n1 == -1) {
            int tmp = getNextNum();
            if (tmp == -1)
                return false;
            else {
                n1 = tmp;
                newN1 = true;
            }
        }

        if (n2 == -1) {
            int tmp = getNextNum();
            if (tmp == -1)
                return false;
            else {
                n2 = tmp;
                newN2 = true;
            }
        }

        if (sum != -1) {
            if ((n1 + n2 + carry) % 10 != sum) {
                if (newN1)
                    returnNum(n1);

                if (newN2)
                    returnNum(n2);

                return false;
            }
            else {
                if (newN1)
                    res.put(ch1, n1);
                if (newN2)
                    res.put(ch2, n2);
                if (newSum)
                    res.put(chS, sum);
                carry = (n1 + n2 + carry) / 10;
            }
        } else {
            int tmpSum = (n1 + n2 + carry) % 10;
            if (!numbers[tmpSum]) {
                numbers[tmpSum] = true;
                res.put(chS, tmpSum);
                carry = (n1 + n2 + carry) / 10;
                newSum = true;
            }
            else {
                //backtrack
                if (newN1)
                    returnNum(n1);

                if (newN2)
                    returnNum(n2);
                return false;
            }
        }

        pointers[0]++;
        pointers[1]++;
        pointers[2]++;

        if (helper(expr, pointers, carry)) {
            return true;
        } else {
            //backtrack
            if (newN1) {
                returnNum(n1);
                res.remove(ch1);
            }
            if (newN2) {
                returnNum(n2);
                res.remove(ch2);
            }
            if (newSum) {
                returnNum(sum);
                res.remove(chS);
            }
            pointers[0]--;
            pointers[1]--;
            pointers[2]--;
            return false;
        }
    }

    int getNextNum() {
        for (int i = 0; i < 10; i++) {
            if (!numbers[i]) {
                numbers[i] = true;
                return i;
            }
        }

        return -1;
    }

    void returnNum(int i) {
        numbers[i] = false;
    }

    public static void main(String[] args) {
        Solution obj = new Solution();
        Map<Character, Integer> res = obj.solvePuzzle(new String []{
            "SEND", "MORE", "MONEY"
        });
        System.out.println(res);
    }
}
