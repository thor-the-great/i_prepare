package cracking.moderate;

public class SwapNumbersNoExtraVar {

    int[] swapVars(int a, int b) {
        b = b - a;
        a = a + b;
        b = a - b;
        return new int[] {a, b};
    }

    public static void main(String[] args) {
        SwapNumbersNoExtraVar obj = new SwapNumbersNoExtraVar();
        int a = 5;
        int b = 16;
        System.out.println("a = " + a +", b = " + b);
        int[] res = obj.swapVars(a, b);
        System.out.println("a = " + res[0] +", b = " + res[1]);
    }
}
