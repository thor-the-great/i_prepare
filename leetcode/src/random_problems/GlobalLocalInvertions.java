package random_problems;

public class GlobalLocalInvertions {

    public boolean isIdealPermutation(int[] A) {
        int N = A.length;
        int min = N;
        for (int i = N - 1; i >= 2; i--) {
            min = Math.min(min, A[i]);
            if (A[i - 2] > min)
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        GlobalLocalInvertions obj = new GlobalLocalInvertions();
        System.out.println(obj.isIdealPermutation(new int[]{2, 0 , 1}));
    }
}
