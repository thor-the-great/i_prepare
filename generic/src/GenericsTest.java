public class GenericsTest <T> {

    void doSomething(T p1) {
        if (p1.getClass().getName().equals(Long.class.getName())) {
            System.out.println("case 1");
        }
        else {
            System.out.println("case 2");
        }
    }

    public static void main(String[] args) {
        GenericsTest<Long> instance1 = new GenericsTest<>();
        instance1.doSomething(Long.valueOf(100));

        GenericsTest<String> instance2 = new GenericsTest<>();
        instance2.doSomething("kitty");
    }
}
