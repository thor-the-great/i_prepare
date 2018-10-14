package cracking.moderate;

public class GetMax {

    int getMax(int a, int b) {
        int sdif = sign(a -b);
        int sa = sign(a);
        int sb = sign(b);

        int s_ab_dif = sa^sb;
        int k = s_ab_dif*sa + invert(s_ab_dif)*sdif;

        return a*k + b*invert(k);
    }

    int invert(int s) {
        return 1 - s;
    }

    int sign(int n) {
        return 1 + (n>>31);
    }

    public static void main(String[] args) {
        GetMax obj = new GetMax();

        System.out.println(obj.getMax(5, 7));
        System.out.println(obj.getMax(10, 2));
        System.out.println(obj.getMax(1, 8));
        System.out.println(obj.getMax(20, 19));
        System.out.println(obj.getMax(Integer.MAX_VALUE - 2, -15));
    }
}
