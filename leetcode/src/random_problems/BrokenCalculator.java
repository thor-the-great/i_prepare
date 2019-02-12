package random_problems;

public class BrokenCalculator {

    public int brokenCalc(int X, int Y) {
        int res = 0;

        if (X == Y)
            return 0;

        while (Y > X) {
            res++;
            if (Y % 2 == 0) {
                Y = Y / 2;
            } else {
                Y++;
            }
        }

        return res + (X - Y);
    }

    public static void main(String[] args) {
        BrokenCalculator obj = new BrokenCalculator();
        System.out.println(obj.brokenCalc(5, 8));
        System.out.println(obj.brokenCalc(1, 1000000000));
    }
}
