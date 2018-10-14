package cracking.moderate;

public class ContiguosSequence {

    int contSequence(int[] arr) {
        int l = 0;
        int max = 0, run_max = 0;
        while(l < arr.length) {
            run_max += arr[l];
            if (max < run_max) {
                max = run_max;
            } else if (run_max < 0) {
                run_max = 0;
            }
            l++;
        }
        return max;
    }

    public static void main(String[] args) {
        ContiguosSequence obj = new ContiguosSequence();
        int[] arr = null;
        arr = new int[] {2, -8, 3, -2, 4, -10};
        System.out.println(obj.contSequence(arr));

        arr = new int[] {-5, 6, -2, -3, 5, -7, 8, 2, -3, -2, 3};
        System.out.println(obj.contSequence(arr));

        arr = new int[] {10, -3, 5, -2, -2, 1, 9};
        System.out.println(obj.contSequence(arr));

        arr = new int[] {-2, -3, -4};
        System.out.println(obj.contSequence(arr));
    }
}
