package problems;

public class Thingy {

    Thingy() {

    }

    public String doThing() {
        return "result";
    }

    public static void main(String[] args) {
        Thingy obj = new Thingy();
        System.out.println(obj.doThing());
    }
}
