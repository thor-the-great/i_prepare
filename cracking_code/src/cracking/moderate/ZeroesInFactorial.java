package cracking.moderate;

public class ZeroesInFactorial {
    int trailingZeroes(int n) {
        int zeroes = 0;
        for (int i = 1; i <= n; i++) {
            int num = i;
            while (num >= 5) {
                if (num % 5 == 0) {
                    zeroes++;
                    num = num/5;
                }
                else
                    break;
            }
        }
        return zeroes;
    }

    public static void main(String[] args) {
        ZeroesInFactorial obj = new ZeroesInFactorial();
        System.out.println(obj.trailingZeroes(15));
    }
}
