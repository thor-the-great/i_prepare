package cracking.moderate;

public class MathOperations {

    public int sub(int a, int b) {
        return a + negate(b);
    }

    public int multi(int a, int b) {
        int res = 0;
        if ( a > b)
            return multi(b, a);
        for (int i = 0; i < abs(a); i++) {
            res += b;
        }
        if (a < 0)
            res = negate(res);
        return res;
    }

    public int div(int a, int b) {
        if (b == 0)
            throw new RuntimeException("Division by zero");
        int res = 0;
        int runProduct = 0;
        int absa = abs(a);
        int absb = abs(b);
        while(runProduct < absa) {
            runProduct += absb;
            res++;
        }

        if ((a > 0 && b > 0) || (a < 0 && b < 0))
            return res;
        else
            return negate(res);
    }

    private int negate(int a) {
        int signOne = a > 0 ? -1 : 1;
        int copy = a;
        int res = 0;
        while (copy != 0) {
            res += signOne;
            copy += signOne;
        }
        return res;
    }

    private int abs(int a) {
        if (a > 0)
            return a;
        else
            return negate(a);
    }

    public static void main(String[] args) {
        MathOperations obj = new MathOperations();
        System.out.println(obj.sub(45, 30) + " expecting 15");
        System.out.println(obj.multi(5, 4) + " expecting 20");
        System.out.println(obj.div(45, 9) + " expecting 5");

    }
}
