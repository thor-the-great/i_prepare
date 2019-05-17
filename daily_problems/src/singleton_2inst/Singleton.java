package singleton_2inst;

import java.util.stream.IntStream;

public class Singleton {
    private static Singleton instanceOne;
    private static Singleton instanceTwo;
    private static boolean instanceFlagOne = false;
    String name;

    private Singleton(String name) {
        this.name = name;
    }

    public static Singleton getInstance() {
        instanceFlagOne = !instanceFlagOne;
        if (instanceFlagOne) {
            if (instanceOne == null)
                instanceOne = new Singleton("one");
            return instanceOne;
        }
        else {
            if (instanceTwo == null)
                instanceTwo = new Singleton("two");
            return instanceTwo;
        }
    }

    public static void main(String[] args) {
        IntStream.range(0, 20).forEach(i->{
            System.out.print("it # " + i);
            Singleton s = Singleton.getInstance();
            System.out.print( " " + s.name +"\n");
        });
    }
}
