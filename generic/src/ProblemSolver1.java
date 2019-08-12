import java.util.Random;

public class ProblemSolver1 {

    String doSomething(int[] arr) {
        String res = "";

        for (int i = 0; i < arr.length; i++) {
            res = res + arr[i] + ",";
        }

        return res;
    }

    int[] doSomethingElse(String s, int j, boolean b, boolean b2) {
        int i = 0;
        int[] res = new int[s.length()];
        for (char ch : s.toCharArray()) {
            res[i++] = ch;
        }
        return res;
    }


    public static void main(String[] args) {
        ProblemSolver1 myIdea = new ProblemSolver1();

        String res = myIdea.doSomething(new int[] {3, 6, 2, 7});
        System.out.println(res);

        int[] myArray = new int[3];
        myArray[0] = 45;
        myArray[1] = 20;
        myArray[2] = 32;

        System.out.println(myIdea.doSomething(myArray));

        int[] anotherMyArray = new Random().ints(0, 10).limit(20).toArray();
        System.out.println(myIdea.doSomething(anotherMyArray));
    }
}
