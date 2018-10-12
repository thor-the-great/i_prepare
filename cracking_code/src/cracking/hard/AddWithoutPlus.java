package cracking.hard;

public class AddWithoutPlus {

    int addNums(int a, int b) {
        //idea - XOR (^) does the addition but without carrying to th higher bits
        //we can do carry with AND (&) and then shift it. But we may end up doing it more that once
        //so need to run a loop
        while(b != 0) {
            int s = a ^ b;
            b = (a & b) << 1;
            a = s;
        }
        return a;
    }

    public static void main(String[] args) {
        AddWithoutPlus obj = new AddWithoutPlus();
        System.out.println(obj.addNums(6, 7));
        System.out.println(obj.addNums(10, 15));
    }
}
