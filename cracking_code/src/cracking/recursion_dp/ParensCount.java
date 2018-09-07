package cracking.recursion_dp;

import java.util.ArrayList;
import java.util.List;

/**
 * Compute all possible valid pairs of N parens
 */
public class ParensCount {

    List<String> getParens(int n) {
        //create pool of available chars of every parens - 2*n parens
        //iterate over this array trying different permutations
        char[] pool = new char[2*n];
        List<String> res = new ArrayList<>();
        addParensHelper(res, pool, n, n, 0);
        return res;
    }

    void addParensHelper(List<String> list, char[] pool, int leftCount, int rightCount, int index) {
        //pool has possible parent set, left and right counts are counting how many parens of every type we used
        //index indicates current position in the pool array
        if (leftCount > rightCount || leftCount < 0 || rightCount < 0)
            return;
        if (leftCount == rightCount && leftCount == 0) {
            list.add(new String(pool));
        } else {
            pool[index] = '(';
            addParensHelper(list, pool, leftCount - 1, rightCount, index + 1);
            pool[index] = ')';
            addParensHelper(list, pool, leftCount, rightCount - 1, index + 1);
        }
    }

    public static void main(String[] args) {
        ParensCount obj = new ParensCount();
        List<String> parens = obj.getParens(2);
        for (String parenString : parens) {
            System.out.println(parenString);
        }
    }
}
