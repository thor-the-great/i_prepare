
public class Thingy {

    Thingy() {

    }

    public String doThing() {
        long shift = 32;
        long one = 1;
        //long res = ;
        System.out.println(Long.toBinaryString(1l << 32));

        int a = 5;
        System.out.println(1 + (a >> 31));
        a = 45;
        System.out.println(1 + (a >> 31));
        a = Integer.MAX_VALUE;
        System.out.println(1 + (a >> 31));
        a = 18;
        System.out.println(1 + (a >> 31));
        a = -25;
        System.out.println(1 + (a >> 31));

        return "result";
    }

    public static void main(String[] args) {
        Thingy obj = new Thingy();
        System.out.println(obj.doThing());
    }
}
